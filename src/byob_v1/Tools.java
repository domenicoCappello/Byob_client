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
 *  @author Cappello - Nazio
 */
public class Tools { 
    
    // Scheduler's instance
    final static ByobSingleton BYOB_WRAPPER = ByobSingleton.getInstance();
    
    /**
     *  Method schedules the contact's parameters. 
     *  @param task List of the contacts to do
     */
    public static void schedule(ArrayList <URLDetails> task) {
        for(int i = 0; i < task.size(); i++) {
            ByobSingleton.ses.schedule(new ByobTask(task.get(i)), 0, TimeUnit.MILLISECONDS);
        }
    }
        
    /**
     *  Functions returns the correctness of IPv4 address. 
     *  @param ip   String of the IP address
     *  @return     True if correct, false otherwise
     */
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
    
    /**
     *  Functions returns the correctness of a port number. 
     *  @param port  String of the port 
     *  @return     True if correct, false otherwise
     */
    public static Boolean checkPort(String port){
        if(!checkNumber(port))
            return false;
        return (Integer.parseInt(port) >= 1025 && Integer.parseInt(port) <= 65535);
    }
    
    /**
     *  Functions returns the correctness of a number. 
     *  @param port  String of the number 
     *  @return     True if correct, false otherwise
     */
    public static Boolean checkNumber(String number){
        char[] charArray = number.toCharArray();
        if(number.equals(""))
            return false;
        for(char c: charArray)
            if(!Character.isDigit(c))
                return false;
        return true;
    }
    
    /**
     *  Functions returns the correctness of a number interval. 
     *  @param min  String of the left endpoint
     *  @param max  String of the right endpoint
     *  @return     True if correct, false otherwise
     */
    public static Boolean checkInterval(String min, String max){
        if(!checkNumber(min) || !checkNumber(max))
            return false;
        return Integer.parseInt(min) <= Integer.parseInt(max);
    }
    
    /**
     *  Function creates a List of warning message to show to the user in case 
     *  of input errors.
     *  @param params   User's Input parameters
     *  @return     Warning messages list
     */
    public static List<String> warningMessage(String[] params){
        List<String> warning = new ArrayList<>(); 
        warning.add("Fix the following parameters:\n");
//        if(!ByobComm.URLResponse(params[0])) // 
//            warning.add("- Tried to contact URL but there is no response;\n");
//        if(!checkNumber(params[1]))
        if(params[2].equals("-"))
            warning.add("- Contact time value not valid;\n");
//            else
//                warning.add("- Minimum contact time value not valid;\n");
        if(!checkNumber(params[2]))
            if(!params[2].equals("-") || !params[2].equals("-"))
                warning.add("- Maximum contact time value not valid;\n");
        if(!checkNumber(params[3]))
            warning.add("- Number of contacts not valid;\n");
        if(!params[6].equals("") && !checkIPv4String(params[6]))
            warning.add("- Proxy IP not valid;\n");
        if(!params[7].equals("") && !checkPort(params[7]))
            warning.add("- Proxy port not valid;\n");
        if(warning.size() == 1)
            return null;
        else {
            warning.add("do you want to use default settings where data is missing?");
            return warning;
        }
    }
    
    /**
     *  Function gets machine's operating system.
     *  @return Operating system
     */
    public static String getOs(){
        return System.getProperty("os.name");
    }
    
    /**
     *  Function returns system's architecture.
     *  @return true if it is x64, false otherwise
     */
    public static boolean isSystem64Bit(){
        return System.getProperty("os.arch").contains("64");
    }
    
    /**
     *  Function gets machine's installed browsers.
     *  (Supported browsers: Chrome, Firefox, Opera, Chromium, Internet Explorer)
     *  @return String of the installed browsers
     */
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
        else if(getOs().toLowerCase().contains("windows")) {
            
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
    
    /**
     *  Function creates a linux bash and returns its output.
     *  @param cmd  Command to launch
     *  @return     Command's result
     */
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
     *  Function runs a Windows command on the host's command line and captures
     *  the result from the console.
     *  @param command   command to be run
     *  @return output from the command line
     */
    public static String runCmd(String command) {
        String cmdOutput = "";
        String _string = null;

        try {
            Process _process = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(_process.getInputStream()));
            while((_string = stdInput.readLine()) != null)
                cmdOutput += _string+"\n";
        }

        catch(IOException e) {
        }
        
        return cmdOutput;
    }
    
    /**
     *  Function returns a unique bot id using a hash of the host hardware
     *  information and other ones. 
     *  @return Unique ID of the bot
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
     * Function returns the MD5 Checksum of a string.
     * @param hw String to compute the checksum of
     * @return Checksum of the string
     */
    @SuppressWarnings("null")
    public static String hashFunction(String hw) {
        
        byte[] bytesMsg = null;
        byte[] bytesDigest;
        MessageDigest md = null;
        String hashText;
        
        try {
            bytesMsg = hw.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }

        bytesDigest = md.digest(bytesMsg);
        hashText = (new BigInteger(1, bytesDigest)).toString(16);
        while(hashText.length() < 32) {
            hashText = "0"+hashText;
        }
        
        return hashText;
    }
}