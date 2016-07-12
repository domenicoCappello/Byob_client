/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diomenik
 */
public class Byob_v1 {

    /**
     * @param args the command line arguments
     */
    
    final static String FILE_CONF_PATH = "";
   
    public static void main(String[] args) throws IOException {

//        Parser parser = new Parser(FILE_CONF_PATH);
//        try {
//            ArrayList <URLDetails> task = parser.readConfigurationFile();
//        } catch (IOException ex) {
//            Logger.getLogger(Byob_v1.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        // Create threadpool and start threads
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(new ByobTask(ses, 3, "A"), 500, TimeUnit.MILLISECONDS);
        ses.schedule(new ByobTask(ses, 3, "B"), 1000, TimeUnit.MILLISECONDS);
        LoggerByob loggerWrapper = LoggerByob.getInstance();
        loggerWrapper.myLogger.severe( "Your severe message" );
        while(ses.isTerminated()){
            // sleep and write to C&C (?)
        }
        
    }
    
}
