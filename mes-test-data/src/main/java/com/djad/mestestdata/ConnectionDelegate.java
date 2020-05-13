package com.djad.mestestdata;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class ConnectionDelegate {
    private HttpURLConnection conn;

    public ConnectionDelegate(HttpURLConnection conn) throws ProtocolException {
        this.conn = conn;
        this.conn.setRequestMethod("POST");
        this.conn.setDoOutput(true);
        this.conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
    }

    public DataOutputStream getOutputStream() throws IOException {
        return new DataOutputStream(conn.getOutputStream());
    }

    public int getResponseCode() throws IOException {
        return conn.getResponseCode();
    }

    public void disconnect() {
        conn.disconnect();
    }
}
