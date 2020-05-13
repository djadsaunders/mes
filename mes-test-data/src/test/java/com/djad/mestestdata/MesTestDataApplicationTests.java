package com.djad.mestestdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MesTestDataApplicationTests {

    @Test
    public void testInit() {
        new MesTestDataApplication(
                "com.djad.mestestdata.scenario.DefaultScenario",
                "default","URL_PREFIX");
    }

    @Test(expected = RuntimeException.class)
    public void testInitWithInvalidScenarioClass() {
        new MesTestDataApplication("any","default", "URL_PREFIX");
    }
}
