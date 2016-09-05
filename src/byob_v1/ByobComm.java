package byob_v1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class is responsible for anything concerning the GET method of HTTP and its response.
 * @author Cappello, Nazio
 */
public class ByobComm {
    
    /**
    * Costructor.
    */
    ByobComm(){}
    
    static int httpGet(String url, Boolean waitForResponse){
        
        return httpGet(url, "", "", -1, waitForResponse);
    }
    
    static int httpGet(String url, String userAgent, Boolean waitForResponse){
        
        return httpGet(url, userAgent, "", -1, waitForResponse);
    }
    
    /**
    * Function returns the response code of the HTTP's GET method.
    * @param url String of site's URL to contact.
    * @param userAgent String with the URL of the site to contact.
    * @param proxyIp Proxy's IP address.
    * @param proxyPort Proxy's port number.
    * @param waitForResponse Choice to wait for a response from url or not.
    * @exception MalformedURLException
    * @exception IOException
    * @return Response code from the URL (-1 if it is Malformed, -2 for Input Error)
    */
    static int httpGet(String url, String userAgent, String proxyIp, int proxyPort, Boolean waitForResponse) {  
        String charset = "UTF-8"; 
        HttpURLConnection connection;
        try {
            if(proxyPort > 0){
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
                connection = (HttpURLConnection) new URL(url).openConnection(proxy);
            } else {
                connection = (HttpURLConnection) new URL(url).openConnection();
            }
            connection.setRequestProperty("Accept-Charset", charset);
            
            if(!userAgent.isEmpty())
                connection.setRequestProperty("User-Agent", userAgent);
            else
                connection.setRequestProperty("User-Agent", "");
            int ret = waitForResponse ? connection.getResponseCode() : 0;
            connection.disconnect();
            return ret;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ByobComm.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
            
        } catch (IOException ex) {
            Logger.getLogger(ByobComm.class.getName()).log(Level.SEVERE, null, ex);
            return -2; 
        }   
    }
    
    /**
    * Function returns true if there's a response code from the contacted URL.
    * @param url String of site's URL to contact.
    * @exception MalformedURLException
    * @exception IOException
    * @return Boolean if the URL answers or not.
    */
    static boolean URLResponse(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url.replace("*", "")).openConnection();
            int response = connection.getResponseCode();
            connection.disconnect();
            return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ByobComm.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ByobComm.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
