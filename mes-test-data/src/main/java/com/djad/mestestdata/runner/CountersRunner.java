package com.djad.mestestdata.runner;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.MESUtil;
import com.djad.mestestdata.RestMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountersRunner extends AbstractDataRunner {

    private Logger logger = LoggerFactory.getLogger(CountersRunner.class);

    private final int LOWER_BOUND = 75, UPPER_BOUND = 150;

    public CountersRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public String getName() {
        return "Counters";
    }

    @Override
    public void run() {
        DataRegister dataRegister = DataRegister.getInstance();

        logger.debug("Logging counts");

        dataRegister.getDataStream().
            filter(item -> item.getType().equals("RESOURCE")).
            forEach(item -> {
                String tag = item.getValue().split(",")[0];

                String urlStringForInCount = "/resource/" + tag + "/inCount";
                String urlStringForOutCount = "/resource/" + tag + "/outCount";

                // Generate random in count between upper and lower bounds
                int in = MESUtil.generateRandomInt(LOWER_BOUND, UPPER_BOUND);

                // Out count is in count reduced by up to 10%
                int out = Math.round(in * (Float.valueOf(MESUtil.generateRandomInt(90, 100)) / 100));

                httpHelper.doPost(urlStringForInCount, RestMessages.getMessage("IN_COUNT", String.valueOf(in)));
                httpHelper.doPost(urlStringForOutCount, RestMessages.getMessage("OUT_COUNT", String.valueOf(out)));
            });
    }
}
