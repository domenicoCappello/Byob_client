package byob_v1;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author Cappello, Nazio
 */
public class ByobTask implements Runnable {

    ScheduledExecutorService ses = ByobSingleton.getInstance().ses;
    URLDetails contact;
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
        
        if(contact.sleepMode()){
            int minTimeRestInterval = 30; //Minutes
            int maxTimeRestInterval = 45; //Minutes
            long randomInterval = minTimeRestInterval + 
                    random.nextInt(maxTimeRestInterval - minTimeRestInterval + 1);
            System.out.println("Contacting " + contact.getURL() + " in " 
                    + randomInterval + " minutes");
            ByobSingleton.getInstance().myLogger.severe("Contacting "  
                   + contact.getURL() + " in " + randomInterval + " minutes");
            
            ses.schedule(this, randomInterval, TimeUnit.MINUTES);
        } else {        
            ByobSingleton.getInstance().myLogger.severe(contact.toString());
            System.out.println("Contacting " + contact.getURL() + " : " + contact.getContactsNum() + " more times");
            int code = ByobComm.httpGet(contact.getURL(), contact.getUserAgent(), URLDetails.proxyIp, URLDetails.proxyPort);
            contact.decreaseContactNum(); 
            System.out.println(contact.getURL() + " returned: " + code);

            if(contact.getContactsNum() > 0){
                double randomInterval = contact.getMinWaitTime() + 
                         (contact.getMaxWaitTime() - contact.getMinWaitTime()) * random.nextDouble();
                ses.schedule(this, (long)(randomInterval*1000), TimeUnit.MILLISECONDS);
            }
        }
    }  
}
