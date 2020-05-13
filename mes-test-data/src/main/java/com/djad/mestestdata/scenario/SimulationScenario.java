package com.djad.mestestdata.scenario;

import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.runner.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djad.mestestdata.runner.RunSpecification.ExecutionStyle.PARALLEL;
import static com.djad.mestestdata.runner.RunSpecification.ExecutionStyle.SERIAL;

public class SimulationScenario extends AbstractScenario {

    private static final Logger logger = LoggerFactory.getLogger(SimulationScenario.class);

    public SimulationScenario(HttpHelper httpHelper) {
        super(httpHelper);
    }

    @Override
    public void runScenario() {

        logger.debug("Running SimulationScenario with " + this.httpHelper);

        this.addRunner(new ClearDataRunner(httpHelper), new RunSpecification(SERIAL));
        this.addRunner(new MasterdataRunner(httpHelper), new RunSpecification(SERIAL));
        this.addRunner(new InitSimRunner(httpHelper), new RunSpecification(SERIAL));
        this.addRunner(new CountersRunner(httpHelper), new RunSpecification(PARALLEL, 60));
        this.addRunner(new ProductionStateRunner(httpHelper), new RunSpecification(PARALLEL, 30));

        this.executeRunners();
    }
}
