package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import org.junit.After;

public abstract class AbstractRunnerTests {
    @After
    public void tearDown() {
        DataRegister.getInstance().register.clear();
    }
}
