package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.MESUtil;
import com.djad.mestestdata.RestMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductionStateRunner extends AbstractDataRunner {

    private Logger logger = LoggerFactory.getLogger(ProductionStateRunner.class);

    public ProductionStateRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public String getName() {
        return "State Change";
    }

    private final int[] states = {0,1,2};

    @Override
    public void run() {
        DataRegister dataRegister = DataRegister.getInstance();

        logger.debug("Logging state changes");

        dataRegister.getDataStream().
                filter(item -> item.getType().equals("RESOURCE")).
                forEach(item -> {
                    String tag = item.getValue().split(",")[0];

                    String urlString = "/resource/" + tag + "/state";
                    int nextState = states[MESUtil.generateRandomInt(0, 2)];
                    if (nextState == 0) {
                        httpHelper.doPost(urlString, RestMessages.getMessage("PRODUCTION_STATE_STOPPED"));
                    } else if (nextState == 1) {
                        httpHelper.doPost(urlString, RestMessages.getMessage("PRODUCTION_STATE_SLOW"));
                    } else {
                        httpHelper.doPost(urlString, RestMessages.getMessage("PRODUCTION_STATE_RUNNING"));
                    }
                });
    }
}
