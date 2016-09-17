package byob_v1;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TO DO
 * @author Cappello, Nazio
 */
public class ByobTask implements Runnable {
    
    // Scheduler Executor Service
    final ScheduledExecutorService ses = ByobSingleton.getInstance().ses;
    
    // Contact parameters
    URLDetails contact;
    
    // Random number generator
    private static final Random random = new Random();
    
    /**
    * Constructor.
    * @param contact details of the hosts to contact.
    */
    ByobTask(URLDetails contact){
        this.contact = contact;
    }
    
    @Override
    public void run() {
        
        if(contact.sleepMode()) {
            int minTimeRestInterval = 30; //Minutes
            int maxTimeRestInterval = 45; //Minutes
            long randomInterval = minTimeRestInterval + 
                    random.nextInt(maxTimeRestInterval - minTimeRestInterval + 1);
            String msg = "Check sleep condition for " + contact.getURL() + 
                    " in " + randomInterval + " minutes";
            System.out.println(msg);
            ByobSingleton.getInstance().myLogger.fine(msg);
            synchronized(ses){
                ses.schedule(this, randomInterval, TimeUnit.MINUTES);
            }
        } else {        
            /**Synchronized*/
            if (contact.decreaseContactNum() < 0) 
                return; 
            else if(contact.getContactsNum() > 0){
                double randomInterval = contact.getMinWaitTime() + 
                         (contact.getMaxWaitTime() - contact.getMinWaitTime()) * random.nextDouble();
                synchronized(ses){
                    ses.schedule(this, (long)randomInterval, TimeUnit.MILLISECONDS);
                }
            }
            
            ByobSingleton.getInstance().myLogger.fine(contact.toString());
            System.out.println("Contacting " + contact.getURL() + " : " + contact.getContactsNum() + " more times");

            int code = ByobComm.httpGet(contact.getURL(), contact.getUserAgent(), 
                       URLDetails.proxyIp, URLDetails.proxyPort, contact.waitForResponse);
            
            
            if(contact.waitForResponse){
                String res = contact.getURL() + " returned: " + code;
                System.out.println(res);
                ByobSingleton.getInstance().myLogger.fine(res);
            }
        }
    }  
}
