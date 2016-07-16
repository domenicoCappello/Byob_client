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
 * TODO
 * @author Cappello - Nazio
 */
public class ByobComm {
    
    /**
    * Costructor.
    */
    ByobComm(){}
    
    /**
    * TODO
    * @param url string with the URL of the site to contact.
    * @exception MalformedURLException
    * @exception IOException
    * @return
    */
    static int httpGet(String url, String proxyIp, int proxyPort) {
        
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
//            System.out.println(connection.getResponseMessage());
            int ret = connection.getResponseCode();
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
}
