package byob_v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class manages every aspect of the configuration file like its creation and reading,
 * its splitting and its parameter conversions.
 * @author Cappello, Nazio
 */
public class Parser {
    
    // Name of the file
    static String FILE_NAME;
    
    // Encoding of the file
    static Charset ENCODING;
    
    // Tags for the parameters' log file
    static String[] tags = {
        "url ",
        "minT",
        "manT",
        "numC",
        "sleC",
        "usAg"
    };
    
    /**
     * Constructor.
    */
   public Parser(){
     FILE_NAME = "conf.txt";
     ENCODING = StandardCharsets.UTF_8;
    }
  
    /**
     * Constructor.
     * @param fileName full name of an existing, readable text file.
     */
    public Parser(String fileName){
      FILE_NAME = fileName;
      ENCODING = StandardCharsets.UTF_8;
    }
  
    /**
    * Function returns an list of array with hosts' informations.
    * @exception IOException in case of reading's problem.
    * @return hosts' informations.
    */
    public ArrayList<URLDetails> readConfigurationFile() throws IOException{
        return readConfigurationFile(FILE_NAME);
    }
  
    /**
    * Function returns configuration values of a file.
    * @param fileName full name of an existing, readable .txt file.
    * @return Variables' list of values
    * @throws java.io.IOException
    * @throws java.io.FileNotFoundException
    */
    public ArrayList<URLDetails> readConfigurationFile(String fileName) throws IOException, FileNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<URLDetails> configuration = new ArrayList<>();
        String url;
        Boolean first = true;
        String delim = ";";
        while ((url = br.readLine()) != null) {

            //Search at the beginning of the configuration file for proxy setup
            if (first){
                first = false;
                if (url.charAt(0) == '$'){
                    String[] proxyDet = splitString(url.substring(1), ":");
                    URLDetails.setProxy(proxyDet[0], Integer.parseInt(proxyDet[1]));
                    System.out.println(proxyDet[0] + ":" + proxyDet[1]);
                    continue;
                }
            }

            //Check URL identification char
            if(!url.contains("*")){
                System.err.println("Error in configuration file, aborting");
                System.exit(-1);
            }

            // Build the contact string ("URL;minT;maxT;numC;sleepC;userAgent;")
            String contact = url.substring(1);
            String line;
            for(int i = 0; i < URLDetails.NUM_FIELDS - 1; i++){
                if ((line = br.readLine()) != null)
                   contact = contact + delim + line;
            }

            //Build detail string array
            String[] detail = splitString(contact, delim);

            // Build URLDetails obj and add to configuration arrayList
            URLDetails det = convertParam(detail);
            if(det == null){
                String logError = "Parser error: " + url + " can't be processed";
                ByobSingleton.getInstance().myLogger.severe(logError);
            }
            else
                configuration.add(det);
        }
        
        br.close();    
        return configuration;
    }
  
    /**
     * Function runs a command on the host's command line and captures
     * the result from the console.
     * @param fileToWrite
     * @param params
     * @param columns
     * @throws java.io.IOException
     */
    public static void writeConfigurationFile(File fileToWrite, String[] params, int columns) throws IOException {
        if (!fileToWrite.exists())
	    fileToWrite.createNewFile();
        try (FileWriter fw = new FileWriter(fileToWrite.getAbsolutePath()); 
                BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                bw.write(param);
                if (i < params.length - 1)
                    bw.newLine();
            }      
        }
     }
    
    /**
     * Method writes the configuration parameters inside the log file.
     * @param proxy Proxy IP & port
     * @param params Configuration parameters    
     */
    public static void writeParamsFile(String proxy, String[] params) {    
        String delimiter = "----------------------------------";
        StringBuilder sb = new StringBuilder();
        if(!proxy.isEmpty()) 
            sb.append("\nProxy: ").append(proxy.replace("$", ""));
        for (int i = 0; i < params.length-1; i++) {
            if(i%tags.length==0) {
                sb.append(delimiter).append("\n");
                params[i] = params[i].replace("*", "");
            }
                
            sb.append(tags[i%tags.length]).append(": ").append(params[i]).append("\n");     
        }
        ByobSingleton.myLogger.info(sb.toString());     
     }
    
    /**
     * Function returns number of lines of a file.
     * @param fileName full name of an existing, readable text file.
     * @return  Number of Lines
     * @throws IOException
     */
    @SuppressWarnings("empty-statement")
    public static int countLines(String fileName) throws IOException{
        try (LineNumberReader reader = new LineNumberReader(new FileReader(fileName))) {
            while ((reader.readLine()) != null);
            return reader.getLineNumber();
        } catch (Exception ex) {
            return -1;
        }
    }
    
    /**
     *  Function returns an array of a splitted String on a delimiter.
     *  @param toBeParsed string who needs parsing 
     *  @param delimiter string to split
     *  @return Array of splitted Strings
     */
    public static String[] splitString(String toBeParsed, String delimiter){
        delimiter = "["+delimiter+"]";
        String[] tokens = toBeParsed.split(delimiter, -1);
        return tokens;
    }
    
    /**
    *   Function returns an URLDetails object with the configuration parameters.
    *   @param params Array of parameters
    *   @return Host's details.
    */
    private static URLDetails convertParam(String[] params) {
        URLDetails detail = null;
        if(params.length == URLDetails.NUM_FIELDS) {
            String _URL = params[0];
            int _minTime = Integer.parseInt(params[1]);
            int _maxTime = Integer.parseInt(params[2]);
            long _contactsNum = Long.parseLong(params[3]);
            String _sleepMode = params[4];
            String _userAgent = params[5]; 
            detail = new URLDetails(_URL, _minTime, _maxTime, _contactsNum, _sleepMode, _userAgent);
        }
        return detail;
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
     *  @param number  String of the number 
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
}
