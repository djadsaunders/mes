package com.djad.mestestdata.runner;

import com.djad.mestestdata.DefaultHttpHelper;
import com.djad.mestestdata.URLDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClearDataRunnerTests extends AbstractRunnerTests {

    URLDelegate mockUrl = mock(URLDelegate.class);
    DefaultHttpHelper mockHttpHelper = mock(DefaultHttpHelper.class);

    ClearDataRunner runner = new ClearDataRunner(mockHttpHelper);

    @Test
    public void testRun() {
        runner.run();
        verify(mockHttpHelper).doDelete(anyString());
    }
}
