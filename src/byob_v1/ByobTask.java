package byob_v1;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ByobTask represents a single task that the executor service has to execute.
 * It implements the Runnable interface, and each class instance re-schedule 
 * its own execution according to the information stored in the URLDetails 
 * contact. 
 * @author Cappello, Nazio
 */
public class ByobTask implements Runnable {
    
    /**Project's scheduler*/
    final static ScheduledExecutorService ses = ByobSingleton.getInstance().ses;
    
    /**Details of the connection to perform*/
    URLDetails contact;
    
    /**Random number generator*/
    private static final Random random = new Random();
    
    /**
    * Constructor.
    * @param contact details of the contact to perform.
    */
    ByobTask(URLDetails contact){
        this.contact = contact;
    }
    
    /**
     * Override of the function Runnable.run
     */
    @Override
    public void run() {
        
        if(contact.sleepMode()) {
            /**Sleep mode: try again in 30/45 minutes */
            int minTimeRestInterval = 30; //Minutes
            int maxTimeRestInterval = 45; //Minutes
            long randomInterval = minTimeRestInterval + 
                    random.nextInt(maxTimeRestInterval - minTimeRestInterval + 1);
            String msg = "Check sleep condition for " + contact.getURL() + 
                    " in " + randomInterval + " minutes";
            System.out.println(msg);
            /**Write to log file*/
            ByobSingleton.getInstance().myLogger.fine(msg);
            /**Re-schedule this task in 30/45 minutes*/
            synchronized(ses){
                ses.schedule(this, randomInterval, TimeUnit.MINUTES);
            }
        } else {        
            /**Synchronized function*/
            if (contact.decreaseContactNum() < 0) 
                return; 
            /**If contactsNum (number of contact yet to perform) is > 0, 
               then re-schedule this task*/
            else if(contact.getContactsNum() > 0){
                double randomInterval = contact.getMinWaitTime() + 
                         (contact.getMaxWaitTime() - contact.getMinWaitTime()) * random.nextDouble();
                synchronized(ses){
                    ses.schedule(this, (long)randomInterval, TimeUnit.MILLISECONDS);
                }
            }
            /**Write to log file*/
            ByobSingleton.getInstance().myLogger.fine(contact.toString());
            System.out.println("Contacting " + contact.getURL() + " : " + contact.getContactsNum() + " more times");
            /**Perform the connection (http GET)*/
            int code = ByobComm.httpGet(contact.getURL(), contact.getUserAgent(), 
                       URLDetails.proxyIp, URLDetails.proxyPort, contact.waitForResponse);
            
            /**Log the Server response if needed*/
            if(contact.waitForResponse){
                String res = contact.getURL() + " returned: " + code;
                System.out.println(res);
                ByobSingleton.getInstance().myLogger.fine(res);
            }
        }
    }  
}
