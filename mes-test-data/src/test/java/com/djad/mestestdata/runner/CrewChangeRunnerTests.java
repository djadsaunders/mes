package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CrewChangeRunnerTests extends AbstractRunnerTests {

    @After
    public void tearDown() {
        DataRegister.getInstance().register.clear();
    }

    @Test
    public void testRun() {
        HttpHelper httpHelper = mock(HttpHelper.class);
        CrewChangeRunner runner = new CrewChangeRunner(httpHelper);

        DataRegister register = DataRegister.getInstance();
        register.addItems("RESOURCE", "A,a","B,b","C,c");

        runner.run();
        verify(httpHelper).doPost(eq("/resource/A/crew"), anyString());
        verifyNoMoreInteractions(httpHelper);
    }
}
