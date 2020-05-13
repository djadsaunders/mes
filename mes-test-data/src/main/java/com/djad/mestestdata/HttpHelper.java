package com.djad.mestestdata;

public interface HttpHelper {
    String CONTENT_TYPE = "application/json; charset=utf-8";

    void doPost(String urlString, String message);

    void doDelete(String urlString);

    String doGet(String urlString);
}
