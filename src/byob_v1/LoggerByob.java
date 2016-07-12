/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Alessio
 */
public class LoggerByob {
    String toLog="";  // DEPRECATED
    GregorianCalendar clock;  // DEPRECATED
    
    public LoggerByob() {}
    public static final Logger myLogger = Logger.getLogger("BYOB");
 
    private static LoggerByob instance = null;
    
    public static LoggerByob getInstance() throws IOException {
      if(instance == null) {
        prepareLogger();
        instance = new LoggerByob ();
      }
      return instance;
   }
  
    private static void prepareLogger() throws IOException {
        FileHandler myFileHandler = new FileHandler("_log.txt");
        myFileHandler.setFormatter(new SimpleFormatter());
        myLogger.addHandler(myFileHandler);
        myLogger.setUseParentHandlers(false);
        myLogger.setLevel(Level.FINEST);
} 
//     public void writeLog(String input){
//        clock = new GregorianCalendar();
//        toLog = "["+clock+"] " + input;
//        String path = "Ser/log.txt";
//        FileWriter fw;
//        try {
//            File file = new File(path);
//            if(!file.exists())  
//                fw = new FileWriter(file,true);
//            else      
//            {
//                file.createNewFile();
//                fw = new FileWriter(file);
//            }
//            fw.write(toLog+"\r\n");
//            fw.flush();
//            fw.close();
//        } catch(IOException e) {
//            e.printStackTrace();
//            }
//    }
}
