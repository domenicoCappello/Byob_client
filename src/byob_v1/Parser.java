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
    
    static String FILE_NAME;
    static Charset ENCODING;
  
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
    *   Function returns boolean connected to validation of an IP address.
    *   @param ipAddress String of the IP address
    *   @return True if correct, false otherwise
    */
    public boolean validateIPAddress( String ipAddress ) { 
        String[] tokens = ipAddress.split("\\."); 
        if (tokens.length != 4) 
             return false; 
        for (String str : tokens) { 
            int i = Integer.parseInt(str); 
            if ((i < 0) || (i > 255)) 
                return false;
        }
            return true; 
     }
}
