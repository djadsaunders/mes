package com.djad.mestestdata.runner;

import java.util.List;

import com.djad.mestestdata.DataRegister;
import com.djad.mestestdata.HttpHelper;
import com.djad.mestestdata.MESUtil;
import com.djad.mestestdata.RestMessages;

public class InitSimRunner extends AbstractDataRunner {

    public InitSimRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public String getName() {
        return "Init Sim";
    }

    private final String URL_PREFIX = "/resource/";

    private DataRegister dataRegister = DataRegister.getInstance();

    @Override
    public void run() {
        List<String> crews = dataRegister.getAllValuesByType("CREW");
        List<String> shifts = dataRegister.getAllValuesByType("SHIFT");
        List<String> runs = dataRegister.getAllValuesByType("PRODUCTION_RUN");

        dataRegister.getDataStream().
                filter(item -> item.getType().equals("RESOURCE")).
                forEach(item -> {
                    String tag = item.getValue().split(",")[0];

                    // Availability
                    httpHelper.doPost(URL_PREFIX + tag + "/availability",
                            RestMessages.getMessage("AVAILABLE"));
                    // Shift
                    httpHelper.doPost(URL_PREFIX + tag + "/shift",
                            RestMessages.getMessage("SHIFT",
                                shifts.get(MESUtil.generateRandomInt(0, shifts.size() - 1))));
                    // Crew
                    httpHelper.doPost(URL_PREFIX + tag + "/crew",
                            RestMessages.getMessage("CREW",
                                crews.get(MESUtil.generateRandomInt(0, crews.size() - 1))));
                    // Run
                    httpHelper.doPost(URL_PREFIX + tag + "/run",
                            RestMessages.getMessage("PRODUCTION_RUN",
                                runs.get(MESUtil.generateRandomInt(0, runs.size() - 1))));
                });
    }
}
