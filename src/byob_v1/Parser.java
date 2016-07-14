package byob_v1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author Alessio
 */
public class Parser {
    
  static String FILE_NAME;
  static Charset ENCODING;
  
   /**
   Constructor.
  */
  public Parser(){
    FILE_NAME = "C:\\input.txt";
    ENCODING = StandardCharsets.UTF_8;
  }
  
  /**
   Constructor.
   @param fileName full name of an existing, readable textfile.
  */
  public Parser(String fileName){
    FILE_NAME = fileName;
    ENCODING = StandardCharsets.UTF_8;
  }
  
  public ArrayList<URLDetails> readConfigurationFile() throws IOException{
      
      return readConfigurationFile(FILE_NAME);
  }
  
  /**
   Function returns configuration values of a file.
   @param fileName full name of an existing, readable .txt file.
     * @return Variables' list of values
     * @throws java.io.IOException
  */
  public ArrayList<URLDetails> readConfigurationFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    ArrayList<URLDetails> configuration = new ArrayList<>();
    for(int i=0; i < countLines(fileName); i++)
    {   
        String line = br.readLine();
        if (line != null)
        {
            String[] detail = splitString(line, ";");
            if (detail.length >= 5) 
                configuration.add(convertParam(detail));
        }
    }
    br.close();    
    return configuration;
  }
  
    /**
     * Function returns number of lines of a file.
     * @param fileName full name of an existing, readable .txt file.
     * @return  Number of Lines
     * @throws IOException
     */
    public static int countLines(String fileName) throws IOException{
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(fileName));
            while ((reader.readLine()) != null);
            return reader.getLineNumber();
        } catch (Exception ex) {
            return -1;
        } finally { 
            if(reader != null) 
                reader.close();
        }
    }
    
    /**
     * Function returns an array of a String splitted on a deliiter.
     * @param toBeParsed string who needs parsing
     * @param delimeter symbol for splitting @param toBeParsed 
     * @return  Array of splitted Strings
     */
    public static String[] splitString(String toBeParsed, String delimiter){
        delimiter = "["+delimiter+"]";
        String[] tokens = toBeParsed.split(delimiter);
        return tokens;
    }
    
    /**
    Function returns an URLDetails object with the configuration parameters.
    @param params Array of parameters
     * @return URLDetails object
    */
    private static URLDetails convertParam(String[] params) {
        URLDetails detail = null;
        if(params.length >= 5) {
            String _URL = params[0];
            int _minTime = Integer.parseInt(params[1]);
            int _maxTime = Integer.parseInt(params[2]);
            long _contactsNum = Long.parseLong(params[3]);
            int _sleepMode = Integer.parseInt(params[4]);
            String _userAgent = params[5];
               
            detail = new URLDetails(_URL, _minTime, _maxTime, _contactsNum, _sleepMode, _userAgent);
            System.out.println(detail.getURL());
        }
        return detail;
    }
    
}
