package byob_v1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
  
  /**
   Function returns configuration values of a file.
   @param fileName full name of an existing, readable .txt file.
     * @return Variables' list of values
     * @throws java.io.IOException
  */
  public List<String> readTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    return Files.readAllLines(path, ENCODING);
  }
    
}
