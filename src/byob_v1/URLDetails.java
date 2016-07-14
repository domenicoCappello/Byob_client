/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byob_v1;

/**
 *
 * @author diomenik
 */
public class URLDetails {
    
    private String URL;
    private int minWaitTime;
    private int maxWaitTime;
    private long contactsNum;
    private int sleepMode;
    private String userAgent;
    
    /**
    Constructor.
    */
    URLDetails() {}
    
    /**
    Constructor.
    * @param URL URL to contact
    * @param waitTime time slot for waiting   
    * @param contactsNum number of times contact has to be done   
    * @param sleepMode sleep time
    * @param userAgent new userAgent to use
    */
    URLDetails(String URL, int minTime, int maxTime, long contactsNum, int sleepMode, String userAgent) {
        
        this.URL = URL;
        this.minWaitTime = minTime;
        this.maxWaitTime = maxTime;
        this.contactsNum = contactsNum;
        this.sleepMode = sleepMode;
        this.userAgent = userAgent;
    }  
    
    @Override
    public String toString(){
        String ret = URL + ", contactsNum: " + contactsNum + ", sleepMode: " + 
                sleepMode + ", userAgent: " + userAgent; 
        return ret;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setMinWaitTime(int minWaitTime) {
        this.minWaitTime = minWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void setContactsNum(long contactsNum) {
        this.contactsNum = contactsNum;
    }

    public void setSleepMode(int sleepMode) {
        this.sleepMode = sleepMode;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getMinWaitTime() {
        return minWaitTime;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public long getContactsNum() {
        return contactsNum;
    }

    public int getSleepMode() {
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
