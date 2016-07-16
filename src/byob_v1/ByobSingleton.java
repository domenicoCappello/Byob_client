package byob_v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * TODO
 * @author Cappello, Nazio
 */
public class ByobSingleton {
    
    private static ByobSingleton instance = null;
    public static final Logger myLogger = Logger.getLogger("BYOB");
    public static final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
 
    /**
    * Costructor.
    */
    public ByobSingleton() {}
        
    /**
    * Fuction that gets the instance of the singleton.
    * @return instance Singleton's obecjt instance
    */
    public static ByobSingleton getInstance() {
      if(instance == null) {
        prepareLogger();
        instance = new ByobSingleton ();
      }
      return instance;
    }
    
    /**
    * Method that creates and initializes the Log file.
    * @exception IOException
    * @exception SecurityException
    */
    private static void prepareLogger() {
        try {
            FileHandler myFileHandler = new FileHandler("_log.txt");
            myFileHandler.setFormatter(new SimpleFormatter());
            myLogger.addHandler(myFileHandler);
            myLogger.setUseParentHandlers(false);
            myLogger.setLevel(Level.FINEST);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(ByobSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
