package com.djad.mestestdata;

public class HttpHelperFactory {

    public static HttpHelper createHttpHelper(String helperName, String urlPrefix) {
        HttpHelper httpHelper;

        switch (helperName.toUpperCase()) {
            case "DEFAULT":
                httpHelper = new DefaultHttpHelper(urlPrefix);
                break;
            case "NOOP":
                httpHelper = new NoopHttpHelper();
                break;
            default:
                throw new RuntimeException("HttpHelper " + helperName + " is not a valid http helper");
        }

        return httpHelper;
    }
}
