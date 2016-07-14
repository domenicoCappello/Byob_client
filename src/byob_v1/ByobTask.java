/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 *
 * @author diomenik
 */
public class ByobTask implements Runnable {

    ScheduledExecutorService ses = ByobSingleton.getInstance().ses;
    URLDetails contact;
    private static final Random random = new Random();
    
    ByobTask(URLDetails contact){
        this.contact = contact;
    }
    
    @Override
    public void run() {
        ByobSingleton.getInstance().myLogger.severe(contact.toString());
        System.out.println("Contacting " + contact.getURL() + " : " + contact.getContactsNum() + " more times");
        int code = ByobComm.httpGet(contact.getURL());
        contact.decreaseContactNum(); 
        System.out.println(contact.getURL() + " returned: " + code);
        
        if(contact.getContactsNum() > 0){
            double randomInterval = contact.getMinWaitTime() + 
                     (contact.getMaxWaitTime() - contact.getMinWaitTime()) * random.nextDouble();
            ses.schedule(this, (long)(randomInterval*1000), TimeUnit.MILLISECONDS);
        }

    }
    
}
