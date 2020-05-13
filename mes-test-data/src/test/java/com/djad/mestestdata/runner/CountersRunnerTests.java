package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountersRunnerTests extends AbstractRunnerTests {

    @Test
    public void testRun() {
        HttpHelper httpHelper = mock(HttpHelper.class);
        CountersRunner runner = new CountersRunner(httpHelper);

        DataRegister register = DataRegister.getInstance();
        register.addItems("RESOURCE", "A,a","B,b","C,c");

        runner.run();
        verify(httpHelper).doPost(eq("/resource/A/inCount"), anyString());
        verify(httpHelper).doPost(eq("/resource/A/outCount"), anyString());
        verify(httpHelper).doPost(eq("/resource/B/inCount"), anyString());
        verify(httpHelper).doPost(eq("/resource/B/outCount"), anyString());
        verify(httpHelper).doPost(eq("/resource/C/inCount"), anyString());
        verify(httpHelper).doPost(eq("/resource/C/outCount"), anyString());
    }
}
