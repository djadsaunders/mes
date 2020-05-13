package com.djad.mestestdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DefaultHttpHelper implements HttpHelper {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHttpHelper.class);

    private final String URL_PREFIX;

    public DefaultHttpHelper(String urlPrefix) {
        URL_PREFIX = urlPrefix;
    }

    @Override
    public void doPost(String uri, String message) {
        HttpURLConnection conn = null;
        DataOutputStream os = null;

        String urlString = URL_PREFIX + uri;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", CONTENT_TYPE);
            os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(message);
            os.flush();
            logger.debug("POST to " + urlString + " returned " + conn.getResponseCode());
        }
        catch (Exception e) {
            logger.error("POST to " + urlString + " failed: " + e.getMessage());
        }
        finally {
            try {
                if (os != null) os.close();
                if (conn != null) conn.disconnect();
            }
            catch (Exception e) {
            }
        }
    }

    @Override
    public void doDelete(String urlString) {
        HttpURLConnection conn = null;

        try {
            URL url = new URL(URL_PREFIX + urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.connect();
            logger.debug("DELETE " + urlString + " returned " + conn.getResponseCode());
        }
        catch (Exception e) {
            logger.error("DELETE " + urlString + " failed: " + e.getMessage());
        }
        finally {
            try {
                if (conn != null) conn.disconnect();
            }
            catch (Exception e) {
            }
        }
    }

    @Override
    public String doGet(String urlString) {
        HttpURLConnection conn = null;
        BufferedReader br = null;
        StringBuilder contents = new StringBuilder();

        try {
            URL url = new URL(URL_PREFIX + urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", CONTENT_TYPE);
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = br.readLine();
            while (line != null) {
                contents.append(line);
                line = br.readLine();
            }

            logger.debug("GET " + urlString + " returned " + conn.getResponseCode());
        }
        catch (Exception e) {
            logger.error("GET " + urlString + " failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (br != null) br.close();
                if (conn != null) conn.disconnect();
            }
            catch (Exception e) {
            }
        }

        return contents.toString();
    }

    public String toString() {
        return "DefaultHttpHelper";
    }
}
