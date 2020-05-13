package com.djad.mestestdata;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDelegate {
    private URL url;

    public URLDelegate(String urlString) {
        try {
            this.url = new URL(urlString);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDelegate openConnection() throws IOException {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        return new ConnectionDelegate(conn);
    }
}
