package com.djad.mestestdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoopHttpHelper implements HttpHelper {

    private static final Logger logger = LoggerFactory.getLogger(NoopHttpHelper.class);

    @Override
    public void doPost(String urlString, String message) {
        logger.debug("POST to " + urlString + " with " + message);
    }

    @Override
    public void doDelete(String urlString) {
        logger.debug("DELETE " + urlString);
    }

    @Override
    public String doGet(String urlString) {
        logger.debug("GET " + urlString);
        return null;
    }

    public String toString() {
        return "NoopHttpHelper";
    }
}
