package com.djad.mestestdata.scenario;

import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.runner.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djad.mestestdata.runner.RunSpecification.ExecutionStyle.SERIAL;

public class MasterDataScenario extends AbstractScenario {

    private static final Logger logger = LoggerFactory.getLogger(MasterDataScenario.class);

    public MasterDataScenario(HttpHelper httpHelper) {
        super(httpHelper);
    }

    @Override
    public void runScenario() {

        logger.debug("Running MasterDataScenario with " + this.httpHelper);

        this.addRunner(new ClearDataRunner(httpHelper), new RunSpecification(SERIAL));
        this.addRunner(new MasterdataRunner(httpHelper), new RunSpecification(SERIAL));

        this.executeRunners();
    }
}
