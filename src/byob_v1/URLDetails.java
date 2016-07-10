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
    
    String URL;
    int waitTime;  // DEPRECATED
    int minWaitTime;
    int maxWaitTime;
    long contactsNum;
    int sleepMode;
    String userAgent;
    
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
    URLDetails(String URL, int waitTime, long contactsNum, int sleepMode, String userAgent) {
        
        this.URL = URL;
        this.waitTime = waitTime;
        this.contactsNum = contactsNum;
        this.sleepMode = sleepMode;
        this.userAgent = userAgent;
    }  
}
