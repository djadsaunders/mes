package com.djad.mestestdata.runner;

import com.djad.mestestdata.DefaultHttpHelper;
import com.djad.mestestdata.URLDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MasterdataRunnerTests extends AbstractRunnerTests {

    URLDelegate mockUrl = mock(URLDelegate.class);
    DefaultHttpHelper mockHttpHelper = mock(DefaultHttpHelper.class);

    MasterdataRunner runner = new MasterdataRunner(mockHttpHelper);

    @Test
    public void testRun() throws IOException {
        runner.run();
        verify(mockHttpHelper,times(21)).doPost(anyString(), anyString());
    }
}
