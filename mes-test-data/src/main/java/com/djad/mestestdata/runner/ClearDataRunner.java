package com.djad.mestestdata.runner;

import com.djad.mestestdata.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearDataRunner extends AbstractDataRunner {
    private Logger logger = LoggerFactory.getLogger(ClearDataRunner.class);

    public ClearDataRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public String getName() {
        return "Clear Data";
    }

    @Override
    public void run() {
        logger.debug("Clear all data");
        httpHelper.doDelete("/admin/data");
    }
}
