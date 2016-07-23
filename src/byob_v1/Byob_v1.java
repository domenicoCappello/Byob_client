/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author diomenik
 */
public class Byob_v1 {

    /**
     * @param args the command line arguments
     */
    
    final static String FILE_CONF_PATH = "conf.txt";
    final static ByobSingleton byobWrapper = ByobSingleton.getInstance();
   
    public static void main(String[] args) throws IOException {
        GUI frame = new GUI();
        frame.setVisible(true); //or whatever the method is in jframe.class
        Parser parser = new Parser(FILE_CONF_PATH);
        try {
            ArrayList <URLDetails> taskList = parser.readConfigurationFile();
            schedule(taskList);
        } catch (IOException ex) {
            byobWrapper.myLogger.severe("Parser I/O exception");
        }

//        Tools.getBrowsers();
        
//        while(byobWrapper.ses.isTerminated()){
//            // sleep and write to C&C (?)
//        }
        
    }
    
    public static void schedule(ArrayList <URLDetails> task){
        for(int i = 0; i < task.size(); i++){
            byobWrapper.ses.schedule(new ByobTask(task.get(i)), 0, TimeUnit.MILLISECONDS);
        }
    }
    
}
