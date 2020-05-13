package com.djad.mestestdata.runner;

import com.djad.mestestdata.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrewChangeRunner extends AbstractDataRunner {

    private Logger logger = LoggerFactory.getLogger(CrewChangeRunner.class);

    public CrewChangeRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public String getName() {
        return "Crew Change";
    }

    final String[] crewNames = {"Brian May", "Jeff Lynne", "Gerry Rafferty"};

    @Override
    public void run() {
        DataRegister dataRegister = DataRegister.getInstance();

        logger.debug("Logging crew changes");

        // Get first resource from register
        DataItem firstItem = dataRegister.getDataStream().
            filter(item -> item.getType().equals("RESOURCE")).
                findFirst().get();
        String urlString = "/resource/" + firstItem.getValue().split(",")[0] + "/crew";

        String crewName = crewNames[MESUtil.generateRandomInt(0,crewNames.length - 1)];

        httpHelper.doPost(urlString, RestMessages.getMessage("CREW", crewName));
    }
}
