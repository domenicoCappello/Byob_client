package byob_v1;


/**
 *  Class contains the main method who invokes the GUI and invokes parsing method
 *  to extract the user configuration parameters.
 *  @author Cappello, Nazio
 */
public class Byob_v1 {

    public static void main(){
        GUI frame = new GUI();
        frame.setVisible(true);

//        String file = "guiconf.txt";
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
