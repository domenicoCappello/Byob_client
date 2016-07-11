/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author diomenik
 */
public class ByobTask implements Runnable {

    ScheduledExecutorService ses;
    int nContacts;
    String name;
    
    ByobTask(ScheduledExecutorService ses, int n, String name){
        this.ses = ses;
        this.nContacts = n;
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println("Running " + name + " : " + nContacts + " more times");
        nContacts = nContacts - 1;
        
        if(nContacts > 0)
            ses.schedule(this, (long)1*1000, TimeUnit.MILLISECONDS);

    }
    
}
