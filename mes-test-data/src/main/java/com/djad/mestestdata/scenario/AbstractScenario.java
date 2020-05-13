package com.djad.mestestdata.scenario;

import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.runner.DataRunner;
import com.djad.mestestdata.runner.RunSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractScenario implements Scenario {
    private Logger logger = LoggerFactory.getLogger(AbstractScenario.class);

    private Map<DataRunner, RunSpecification> runners = new LinkedHashMap<>();

    protected AbstractScenario(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    protected HttpHelper httpHelper;

    protected void addRunner(DataRunner dataRunner, RunSpecification runSpecification) {
        runners.put(dataRunner, runSpecification);
    }

    protected void executeRunners() {

        for (DataRunner runner : runners.keySet()) {
            RunSpecification runSpecification = runners.get(runner);
            switch (runSpecification.getExecutionStyle()) {
                case SERIAL: // run now
                    logger.debug("Running '" + runner.getName() + "' now");
                    runner.run();
                    break;
                case PARALLEL: // run concurrently just once or repeating
                    if (runSpecification.getRunEverySeconds() > 0) {
                        logger.debug("Running '" + runner.getName() + "' in a thread, repeating every " +
                                runSpecification.getRunEverySeconds() + " seconds");
                        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                        executorService.scheduleAtFixedRate(runner, 0,
                                runSpecification.getRunEverySeconds(), TimeUnit.SECONDS);
                    }
                    else {
                        logger.debug("Running '" + runner.getName() + "' in a thread");
                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.execute(runner);
                    }
                    break;
            }
        }
    }
}
