package byob_v1;

import java.io.IOException;
import java.util.ArrayList;


/**
 *  Class contains the main method who invokes the GUI and invokes parsing method
 *  to extract the user configuration parameters.
 *  @author Cappello, Nazio
 */
public class Byob_v1 {

    public static void main(String[] args) {

        GUI frame = new GUI();
        frame.setVisible(true);

//        try{
//            ByobComm.asyncHttpGet("www.google.com", "", "", 0);
//        } catch(Exception e){
//            
//        }
//        String file = "proconf.txt";//"geppconf2.txt";
//        Parser parser = new Parser(file);
//        try {
//            ArrayList <URLDetails> taskList = parser.readConfigurationFile();
//            Tools.schedule(taskList);
//        } catch (IOException ex) {
//            Tools.BYOB_WRAPPER.myLogger.severe("Parser I/O exception");
//        }
//        while(true);
        
//        System.out.printf(Tools.getBrowsers());

        
//        Tools.getBrowsers();
        
//        while(byobWrapper.ses.isTerminated()){
//            // sleep and write to C&C (?)
//        }
        
    }
    
}
