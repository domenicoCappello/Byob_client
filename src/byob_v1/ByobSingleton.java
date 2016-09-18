package byob_v1;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *  Singleton class 
 *  It manages every aspect of the Log functionality and of the scheduling.
 *  @author Cappello, Nazio
 */
public class ByobSingleton {
    
    // Singleton instance
    private static ByobSingleton instance = null;
    
    // Logger class
    public static final Logger myLogger = Logger.getLogger("BYOB");
    
    // Scheduler Executor Service
    public static final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1000);
 
    /**
    * Constructor.
    */
    private ByobSingleton() {}
        
    /**
    * Function gets the instance of the singleton.
    * @return Singleton's object instance
    */
    public static ByobSingleton getInstance() {
      if(instance == null) {
        prepareLogger();
        instance = new ByobSingleton ();
      }
      return instance;
    }
    
    /**
    * Method creates and initializes the Log file.
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
