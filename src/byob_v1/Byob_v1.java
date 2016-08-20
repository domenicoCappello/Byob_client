/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;


/**
 *
 * @author diomenik
 */
public class Byob_v1 {

    /**
     * @param args the command line arguments
     */
       
    public static void main(String[] args){
//        GUI frame = new GUI();
//        frame.setVisible(true); //or whatever the method is in jframe.class

        String file = "guiconf.txt";
        ByobComm.waitForResponse = true;    //Wait for response to the http request
        Parser parser = new Parser(file);
        try {
            ArrayList <URLDetails> taskList = parser.readConfigurationFile();
            Tools.schedule(taskList);
        } catch (IOException ex) {
            Tools.BYOB_WRAPPER.myLogger.severe("Parser I/O exception");
        }
        while(true);
        
//        System.out.printf(Tools.getBrowsers());

        
//        Tools.getBrowsers();
        
//        while(byobWrapper.ses.isTerminated()){
//            // sleep and write to C&C (?)
//        }
        
    }
    
}
