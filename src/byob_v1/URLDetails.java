package byob_v1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *  The class contains the parameters that each bot uses during the contact.
 *  @author Cappello, Nazio
 */
public class URLDetails {
    
    // Unique identifier of the bot
    String idBot;                       // Perche' identificatore bot e' in URLDetails?
    // URL - TO DO è un array
    private String URL;
    // Minimum time of contact
    private int minWaitTime;
    // Max time of contact
    private int maxWaitTime;
    // Number of contacts to the URL
    private long contactsNum;
    // Sleep Condition
    private String sleepMode;   // xy, x = O/E/'' : Odd/Even day of the week, y = A/P/'' : AM/PM hour of the day
    // User-Agent string
    private String userAgent;
    // Proxy Hostname 
    public static String proxyIp = "";
    // Proxy Port
    public static int proxyPort = 0;
    
    public static final int NUM_FIELDS = 6;
    
    /**
    * Constructor.
    */
    URLDetails() {}
    
    /**
    Constructor.
    * @param URL URL to contact
    * @param minTime minimum waiting time of the slot
    * @param maxTime maximum waiting time of the slot 
    * @param contactsNum number of times contact has to be done   
    * @param sleepMode sleep time
    * @param userAgent new userAgent to use
    */
    URLDetails(String URL, int minTime, int maxTime, long contactsNum, String sleepMode, String userAgent) {
        
        this.URL = URL;
        this.minWaitTime = minTime;
        this.maxWaitTime = maxTime;
        this.contactsNum = contactsNum;
        this.sleepMode = sleepMode;
        this.userAgent = userAgent;
    }  
    
    
    public static void setProxy(String ip, int port){
        proxyIp = ip;
        proxyPort = port;
        ByobSingleton.getInstance().myLogger.severe("Setting up proxy: " + proxyIp + 
                            " : " + proxyPort);
    }
    
    @Override
    /**
    *   The function returns a string conversion.
    *   @return string with host's details.
    */
    public String toString(){
        String ret = URL + ", contactsNum: " + contactsNum + ", sleepMode: " + 
                sleepMode + ", userAgent: " + userAgent; 
        return ret;
    }
    
    /**
     * 
     * @return 
     */
    public boolean sleepMode(){
        LocalDate date  = LocalDate.now();
        DayOfWeek dow   = date.getDayOfWeek();
        boolean odd     = (dow.getValue() % 2 == 1);
        Calendar cal    = Calendar.getInstance();
        boolean am      = (cal.get(Calendar.AM_PM) == Calendar.AM);
                
        return (odd  && sleepOddDays() )||
               (!odd && sleepEvenDays())||
               (am   && sleepAMHours() )||
               (!am  && sleepPMHours() );
    }
    
    private boolean sleepOddDays(){
        return sleepMode.toLowerCase().contains("o");
    }
    
    private boolean sleepEvenDays(){
        return sleepMode.toLowerCase().contains("e");
    }
    
    private boolean sleepAMHours(){
        return sleepMode.toLowerCase().contains("a");
    }
    
    private boolean sleepPMHours(){
        return sleepMode.toLowerCase().contains("p");
    }
    
    /**
    *   Method to set URL value.
    */
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    /**
    *   Method to set minimum time value of the slots.
    */
    public void setMinWaitTime(int minWaitTime) {
        this.minWaitTime = minWaitTime;
    }
    
    /**
    *   Method to set maximum time value of the slots.
    */
    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    
    /**
    *   Method to set the number of the contacts.
    */public void setContactsNum(long contactsNum) {
        this.contactsNum = contactsNum;
    }

    /**
    *   Method to set the sleep mode.
    */
    public void setSleepMode(String sleepMode) {
        this.sleepMode = sleepMode;
    }
    
    /**
    *   Method to set the user-agent.
    */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
    * Function returns the minimum waiting time of the slot.
    * @return  minimum waiting time.
    */
    public int getMinWaitTime() {
        return minWaitTime;
    }
    
    /**
    * Function returns the maximum waiting time of the slot.
    * @return  minimum waiting time.
    */
    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public long getContactsNum() {
        return contactsNum;
    }

    public String getSleepMode() {
        return sleepMode;
    }

    public String getUserAgent() {
        return userAgent;
    }
    
    public void decreaseContactNum(){
        contactsNum = contactsNum - 1;
    }
    
    public String getURL(){
        return URL;
    }
}
