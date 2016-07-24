/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diomenik
 */
public class Tools {

    public static String getOs(){
        return System.getProperty("os.name");
    }
    
    public static String getBrowsers(){
        
        String browsers = "";
        String tmp;
        if(getOs().toLowerCase().contains("linux")){
            tmp = linuxTermOut("google-chrome --version");
            System.out.println(tmp);
            tmp = linuxTermOut("firefox --version");
            System.out.println(tmp);
            tmp = "Opera ".concat(linuxTermOut("opera --version"));
            System.out.println(tmp);
            tmp = linuxTermOut("chromium-browser --version");
            System.out.println(tmp);
            //Scrivi tutto su browsers
        }
        else if(getOs().toLowerCase().contains("windows")){
            
        }
        else if(getOs().toLowerCase().contains("osx")){ //???
            
        }
        else
            browsers = "Unrecognized OS";
        return browsers;
    }
    
    private static String linuxTermOut(String cmd){
        String[] args = new String[] {"/bin/bash", "-c", cmd};
            String out = "";
            try {
                Process proc = new ProcessBuilder(args).start();
                BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                out = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
            return out;
    }
    
    /**
     * The function runs a command on the host's command line and captures
     * the result fromthe console.
     * @param command   command to be run
     * @return output from the command line
     */
    public static String runCmd(String command) {
        String cmdOutput = "";
        String _string = null;

        try {
            Process _process = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(_process.getInputStream()));
            while((_string = stdInput.readLine()) != null) {
                cmdOutput += _string+"\n";
            }
        }

        catch(IOException e) {
            e.printStackTrace();
        }
        
        return cmdOutput;
        }
    }
