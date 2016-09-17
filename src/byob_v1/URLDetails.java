package byob_v1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *  The class contains parameters that each bot uses during the contact.
 *  @author Cappello, Nazio
 */
public class URLDetails {
    
    // Unique identifier of the bot
    String idBot;             
    
    // Whether or not wait for server response
    public Boolean waitForResponse = false;
    
    // URL - TO DO è un array
    private String URL;
    
    // Minimum time of contact
    private int minWaitTime;
    
    // Max time of contact
    private int maxWaitTime;
    
    // Number of contacts to the URL
    private long contactsNum;
    
    // Sleep Condition xy
    // x = O/E/'' : Odd/Even day of the week 
    // y = A/P/'' : AM/PM hour of the day
    private String sleepMode;   
    
    // User-Agent string
    private String userAgent;
    
    // Proxy Hostname 
    public static String proxyIp = "";
    
    // Proxy Port
    public static int proxyPort = 0;
    
    // Max fields number
    public static final int NUM_FIELDS = 6;
    
    /**
    * Empty Constructor.
    */
    URLDetails() {}
    
    /**
     * Constructor.
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
    
    /**
     * Method sets proxy.
     * @param ip    Proxy's IP address
     * @param port  Proxy's port
     */
    public static void setProxy(String ip, int port){
        proxyIp = ip;
        proxyPort = port;
        ByobSingleton.getInstance().myLogger.fine("Setting up proxy: " + proxyIp + 
                            " : " + proxyPort);
    }
    
    /**
    *   Function returns a string conversion.
    *   @return string with host's details.
    */
    @Override
    public String toString(){
        String ret = URL + ", contactsNum: " + contactsNum + ", sleepMode: " + 
                sleepMode + ", userAgent: " + userAgent; 
        return ret;
    }
    
    /**
     *  Function returns if a sleep mode is set.
     *  @return True if set, false otherwise
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
    
    /**
     *  Function returns if a sleep mode is set for days with an odd number.
     *  @return True if set, false otherwise
     */
    private boolean sleepOddDays(){
        return sleepMode.toLowerCase().contains("o");
    }
    
    /**
     *  Function returns if a sleep mode is set for days with an even number.
     *  @return True if set, false otherwise
     */
    private boolean sleepEvenDays(){
        return sleepMode.toLowerCase().contains("e");
    }
    
    /**
     *  Function returns if a sleep mode is set in the first half of the day.
     *  @return True if set, false otherwise
     */
    private boolean sleepAMHours(){
        return sleepMode.toLowerCase().contains("a");
    }
    
    /**
     *  Function returns if a sleep mode is set in the second half of the day.
     *  @return True if set, false otherwise
     */
    private boolean sleepPMHours(){
        return sleepMode.toLowerCase().contains("p");
    }
    
    /**
     *  Method sets URL value.
     *  @param URL String of the URL
    */
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    /**
    *   Method sets minimum time value of the slots.
    *  @param minWaitTime value of the left interval endpoint
    */
    public void setMinWaitTime(int minWaitTime) {
        this.minWaitTime = minWaitTime;
    }
    
    /**
    *   Method set maximum time value of the slots.
    *   @param maxWaitTime value of the right interval endpoint
    */
    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    
    /**
    *   Method sets the number of the contacts.
    *  @param contactsNum number of the contacts
    */
    public void setContactsNum(long contactsNum) {
        this.contactsNum = contactsNum;
    }

    /**
    *   Method sets the sleep mode.
    *   @param sleepMode
    */
    public void setSleepMode(String sleepMode) {
        this.sleepMode = sleepMode;
    }
    
    /**
    *  Method to set the user-agent.
    *  @param userAgent String of the custom user-agent
    */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
    *   Function returns the minimum waiting time of the slot.
    *   @return  minimum waiting time
    */
    public int getMinWaitTime() {
        return minWaitTime;
    }
    
    /**
    *   Function returns the maximum waiting time of the slot.
    *   @return  maximum waiting time.
    */
    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    /**
    *   Function returns the URL's number of contact.
    *   @return  minimum waiting time.
    */
    public long getContactsNum() {
        return contactsNum;
    }
    
    /**
    *   Function returns bot's sleep condition.
    *   @return sleep condition
    */
    public String getSleepMode() {
        return sleepMode;
    }

    /**
     *  Function returns the user-agent.
     *  @return user-agent     
     */
    public String getUserAgent() {
        return userAgent;
    }
    
    /**
     * Method decreases URL's number of contact.
     * @return contactsNum
     */
    public synchronized long decreaseContactNum(){
        contactsNum = contactsNum - 1;
        return contactsNum;
    }
    
    /**
     *  Function returns the url to contact.
     *  @return URL
     */
    public String getURL(){
        return URL;
    }
}
