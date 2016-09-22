package byob_v1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

/**
 * This class is responsible for anything concerning the http GET method and its response.
 * @author Cappello, Nazio
 */
public class ByobComm {
    
    /**
    * Costructor.
    */
    ByobComm(){}
    
    /**
     * httpGet performs a http connection to the url and eventually waits 
     * for the server response
     * @param url String of the URL to contact
     * @param waitForResponse Whether or not wait for the server response
     * @return Response code from the URL (-1 if it is Malformed, -2 for Input Error)
     */
    static int httpGet(String url, Boolean waitForResponse){
        
        return httpGet(url, "", "", -1, waitForResponse);
    }
    
    /**
     * httpGet performs a http connection (using the user agent userAgent) 
     * to the url and eventually waits for the server response
     * @param url
     * @param userAgent
     * @param waitForResponse
     * @return 
     */
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
    * @return Response code from the URL (-1 if it is Malformed, -2 for I/O Exc)
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
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", charset);
            
            if(!userAgent.isEmpty())
                connection.setRequestProperty("User-Agent", userAgent);
            else
                connection.setRequestProperty("User-Agent", "");
            connection.connect();
            int ret = waitForResponse ? connection.getResponseCode() : 0;
            connection.disconnect();
            return ret;
            
        } catch (MalformedURLException ex) {
            ByobSingleton.getInstance().myLogger.severe("MalformedURLException");
            return -1;
            
        } catch (IOException ex) {
            ByobSingleton.getInstance().myLogger.severe("IOException: check URL");
            return -2; 
        }   
    }
}
