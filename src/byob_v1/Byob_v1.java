package byob_v1;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *  Class contains the main method that writes OS and browsers' information file
 *  and invokes the GUI.
 *  @author Cappello, Nazio
 */
public class Byob_v1 {

    public static void main(String[] args) {
        /**Start the GUI*/
        GUI frame = new GUI();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setTitle("BYOB v_1");
        frame.setLocation(x, y);
        frame.setVisible(true);
        /**Gather system informations and write them on sys_info.txt*/
        Tools.writeInfoFile("sys_info.txt");
//        String file = "geppconf2.txt";
//        Parser parser = new Parser(file);
//        try {
//            ArrayList <URLDetails> taskList = parser.readConfigurationFile();
//            Tools.schedule(taskList);
//        } catch (IOException ex) {
//            Tools.BYOB_WRAPPER.myLogger.severe("Parser I/O exception");
//        }
//        while(true);   
    }
    
}
