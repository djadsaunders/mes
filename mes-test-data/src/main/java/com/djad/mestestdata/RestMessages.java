package com.djad.mestestdata;

import java.util.HashMap;
import java.util.Map;

public class RestMessages {

    private static Map<String, String> restMessages = new HashMap<>();

    static {
        restMessages.put("CREATE_RESOURCE", "{\"tag\":\"?\",\"name\":\"?\"}");
        restMessages.put("AVAILABLE", "{\"availability\":1}");
        restMessages.put("UNAVAILABLE", "{\"availability\":0}");
        restMessages.put("IN_COUNT", "{\"value\":?}");
        restMessages.put("OUT_COUNT", "{\"value\":?}");
        restMessages.put("PRODUCTION_STATE_RUNNING", "{\"state\":2}");
        restMessages.put("PRODUCTION_STATE_SLOW", "{\"state\":1}");
        restMessages.put("PRODUCTION_STATE_STOPPED", "{\"state\":0}");
        restMessages.put("SHIFT", "{\"name\":\"?\"}");
        restMessages.put("CREW", "{\"name\":\"?\"}");
        restMessages.put("PRODUCTION_RUN", "{\"runName\":\"?\"}");
        restMessages.put("CREATE_CREW", "{\"name\":\"?\"}");
        restMessages.put("CREATE_SHIFT", "{\"name\":\"?\"}");
        restMessages.put("CREATE_PRODUCT", "{\"name\":\"?\",\"description\":\"?\"}");
        restMessages.put("CREATE_PRODUCTION_RUN", "{\"productName\":\"?\",\"runName\":\"?\"}");
    }

    public static String getMessage(String name) {
        String formattedMessage = restMessages.get(name);
        if (formattedMessage == null) throw new RuntimeException("Cannot get message for key: " + name);
        return formattedMessage;
    }

    public static String getMessage(String name, String... parameters) {
        String message = RestMessages.getMessage(name);

        if (parameters.length != message.split("\\?").length - 1)
            throw new RuntimeException("Number of parameter binding points does not match number of passed in parameters");

        for (String parameter : parameters) {
            message = message.replaceFirst("\\?", parameter);
        }

        return message;
    }
}
