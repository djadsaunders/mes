package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductionStateRunnerTests extends AbstractRunnerTests {

    @Test
    public void testRun() {
        HttpHelper httpHelper = mock(HttpHelper.class);
        ProductionStateRunner runner = new ProductionStateRunner(httpHelper);

        DataRegister register = DataRegister.getInstance();
        register.addItems("RESOURCE", "A,a","B,b","C,c");

        runner.run();
        verify(httpHelper).doPost(eq("/resource/A/state"), anyString());
        verify(httpHelper).doPost(eq("/resource/B/state"), anyString());
        verify(httpHelper).doPost(eq("/resource/C/state"), anyString());
    }
}
