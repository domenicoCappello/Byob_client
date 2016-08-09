/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *  TODO
 *  @author Cappello, Nazio
 */
public class URLDetails {
    
    String idBot;
    private String URL;
    private int minWaitTime;
    private int maxWaitTime;
    private long contactsNum;
    private String sleepMode;   // xy, x = O/E/'' : Odd/Even day of the week, y = A/P/'' : AM/PM hour of the day
    private String userAgent;
    
    public static String proxyIp = "";
    public static int proxyPort = 0;
    
    /**
    Constructor.
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
    
    
    public void setProxy(String ip, int port){
        proxyIp = ip;
        proxyPort = port;
    }
    
    @Override
    /**
    *   Function to string conversion.
    *   @return string with host's details.
    */
    public String toString(){
        String ret = URL + ", contactsNum: " + contactsNum + ", sleepMode: " + 
                sleepMode + ", userAgent: " + userAgent; 
        return ret;
    }
    
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
