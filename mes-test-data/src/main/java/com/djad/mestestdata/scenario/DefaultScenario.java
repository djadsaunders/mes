package com.djad.mestestdata.scenario;

import com.djad.mestestdata.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultScenario extends AbstractScenario {

    private static final Logger logger = LoggerFactory.getLogger(DefaultScenario.class);

    public DefaultScenario(HttpHelper httpHelper) {
        super(httpHelper);
    }

    @Override
    public void runScenario() {
        logger.debug("Running DefaultScenario with " + this.httpHelper);
    }
}
