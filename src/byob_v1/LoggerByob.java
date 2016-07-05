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

/**
 *
 * @author Alessio
 */
public class LoggerByob {
    String toLog="";
    GregorianCalendar clock;
    
     public LoggerByob() {}
     
     public void writeLog(String input){
        clock = new GregorianCalendar();
        toLog = "["+clock+"] " + input;
        String path = "Ser/log.txt";
        FileWriter fw;
        try {
            File file = new File(path);
            if(!file.exists())  
                fw = new FileWriter(file,true);
            else      
            {
                file.createNewFile();
                fw = new FileWriter(file);
            }
            fw.write(toLog+"\r\n");
            fw.flush();
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
            }
    }
}
