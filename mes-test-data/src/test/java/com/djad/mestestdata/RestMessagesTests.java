package com.djad.mestestdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RestMessagesTests {
    @Test
    public void testGetMessage() {
        String message = RestMessages.getMessage("CREATE_RESOURCE");
        assertEquals(message, "{\"tag\":\"?\",\"name\":\"?\"}");
    }

    @Test
    public void testGetMessageWithParameters() {
        String message = RestMessages.getMessage("CREATE_RESOURCE", "one", "two");
        assertEquals(message, "{\"tag\":\"one\",\"name\":\"two\"}");
    }

    @Test(expected=RuntimeException.class)
    public void testGetMessageInvalidKey() {
        RestMessages.getMessage("MADE_UP_KEY");
    }

    @Test(expected=RuntimeException.class)
    public void testGetMessageWrongNumberOfParameters() {
        RestMessages.getMessage("CREATE_RESOURCE", "only-one");
    }
}
