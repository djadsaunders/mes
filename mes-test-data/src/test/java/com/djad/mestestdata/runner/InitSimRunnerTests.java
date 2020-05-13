package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InitSimRunnerTests extends AbstractRunnerTests {

    @Test
    public void testRun() {
        HttpHelper httpHelper = mock(HttpHelper.class);
        InitSimRunner runner = new InitSimRunner(httpHelper);
        DataRegister register = DataRegister.getInstance();

        register.addItems("RESOURCE", "A,a","B,b");
        register.addItems("CREW", "A,a","B,b");
        register.addItems("SHIFT", "A,a","B,b");
        register.addItems("PRODUCTION_RUN", "A,a","B,b");

        runner.run();

        verify(httpHelper).
                doPost("/resource/A/availability", "{\"availability\":1}");
        verify(httpHelper).
                doPost("/resource/B/availability", "{\"availability\":1}");
        verify(httpHelper).
                doPost(eq("/resource/A/shift"), anyString());
        verify(httpHelper).
                doPost(eq("/resource/B/shift"), anyString());
        verify(httpHelper).
                doPost(eq("/resource/A/crew"), anyString());
        verify(httpHelper).
                doPost(eq("/resource/B/crew"), anyString());
        verify(httpHelper).
                doPost(eq("/resource/A/run"), anyString());
        verify(httpHelper).
                doPost(eq("/resource/B/run"), anyString());
    }
}
