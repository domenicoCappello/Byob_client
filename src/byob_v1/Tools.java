/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diomenik
 */
public class Tools { 
    
    final static ByobSingleton BYOB_WRAPPER = ByobSingleton.getInstance();
    
        public static void schedule(ArrayList <URLDetails> task){
        for(int i = 0; i < task.size(); i++){
            BYOB_WRAPPER.ses.schedule(new ByobTask(task.get(i)), 0, TimeUnit.MILLISECONDS);
        }
    }
        
    public static Boolean checkIPv4String(String ip){
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4){
            return false;
        }
        for (String tok : tokens){
            try{
                int i = Integer.parseInt(tok);
                if (i < 0 || i > 255)
                    return false;
                
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }
    
    public static Boolean checkPort(String port){
        if(!checkNumber(port))
            return false;
        return (Integer.parseInt(port) >= 1025 && Integer.parseInt(port) <= 65535);
    }
    
    public static Boolean checkNumber(String number){
        char[] charArray = number.toCharArray();
        if(number.equals(""))
            return false;
        for(char c: charArray)
            if(!Character.isDigit(c))
                return false;
        return true;
    }
    
    public static Boolean checkInterval(String min, String max){
        if(!checkNumber(min) || !checkNumber(max))
            return false;
        return Integer.parseInt(min) <= Integer.parseInt(max);
    }
    
    public List<String> warningMessage(String[] params){
        List<String> warning = new ArrayList<>(); 
        warning.add("Fix the following parameters:\n");
        if(false) // 
            warning.add("- URL not valid;\n");
        if(!checkNumber(params[1]))
            if(params[2].equals("-"))
                warning.add("- Contact time value not valid;\n");
            else
                warning.add("- Minimum contact time value not valid;\n");
        if(!checkNumber(params[2]))
            if(!params[2].equals("-") || !params[2].equals("-"))
                warning.add("- Maximum contact time value not valid;\n");
        if(!checkNumber(params[3]))
            warning.add("- Number of contacts not valid;\n");
        if(!checkIPv4String(params[6]))
            warning.add("- Proxy IP not valid;\n");
        if(!checkPort(params[7]))
            warning.add("- Proxy port not valid;\n");
        
        return (warning.size() == 1 ? 
                null : 
                warning);
    }
    
    public static String getOs(){
        return System.getProperty("os.name");
    }
    
    public static boolean isSystem64Bit(){
        return System.getProperty("os.arch").contains("64");
    }
    
    public static String getBrowsers(){
        
        String browsers = "";
        if(getOs().toLowerCase().contains("linux")){
            String tmp;
            tmp = linuxTermOut("google-chrome --version");
            browsers = browsers + tmp + "\n";
            tmp = linuxTermOut("firefox --version");
            browsers = browsers + tmp + "\n";
            tmp = "Opera ".concat(linuxTermOut("opera --version"));
            browsers = browsers + tmp + "\n";
            tmp = linuxTermOut("chromium-browser --version");
            browsers = browsers + tmp + "\n";
            //Scrivi tutto su browsers
        }
        else if(getOs().toLowerCase().contains("windows")){
            // IE
            String path = "SOFTWARE\\Microsoft\\Internet Explorer";
            String vField = getOs().toLowerCase().equals("windows 8")? "svcVersion" : "Version";
            String version = Advapi32Util.registryGetStringValue(   
                WinReg.HKEY_LOCAL_MACHINE, path, vField);
            browsers = browsers + "Internet Explorer " + version + "\n";
            
            //Google Chrome
            String wowNode = isSystem64Bit() ? "Wow6432Node\\" : "";
            try{
                path = "SOFTWARE\\" + wowNode + "Google\\Update\\Clients";
                String key[] = Advapi32Util.registryGetKeys(
                    WinReg.HKEY_LOCAL_MACHINE, path);
                for (String key1 : key) {
                    
                    try {
                        String name = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, path + "\\" + key1, "name");
                        if(name.toLowerCase().equals("google chrome")){
                            version = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, path + "\\" + key1, "pv");
                            browsers = browsers + "Google Chrome " + version + "\n";
                        }
                    }catch (Exception e){}
                }
            } catch(Exception e){}
            
            // Mozilla Firefox
            try{
                version = Advapi32Util.registryGetStringValue( 
                    WinReg.HKEY_LOCAL_MACHINE, "SOFTWARE\\" + wowNode + "Mozilla\\Mozilla Firefox", "CurrentVersion");
                browsers = browsers + "Mozilla Firefox " + version + "\n";

            } catch(Exception e){}
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
     * the result from the console.
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
        }
        
        return cmdOutput;
        }
    
    /**
     * Th function returns a unique bot id using a hash of the host hardware
     * information. 
     * @return unique ID of the bot
     */
    public String idGeneration() {
        String hardware;
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
     * @param hw the string you want to compute the checksum of
     * @returnthe checksum of the string
     */
    public static String hashFunction(String hw) {
        
        byte[] bytesMsg = null;
        byte[] bytesDigest;
        MessageDigest md = null;
        String hashText;
        
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