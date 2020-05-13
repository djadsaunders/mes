package com.djad.mestestdata.runner;

import com.djad.mestestdata.*;

public class MasterdataRunner extends AbstractDataRunner {

    public MasterdataRunner(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public String getName() {
        return "Master Data";
    }

    private DataRegister dataRegister = DataRegister.getInstance();

    @Override
    public void run() {
        this.createResource("PACK1", "Packing Line 1");
        this.createResource("PACK2", "Packing Line 2");
        this.createResource("PACK3", "Packing Line 3");
        this.createCrew("Gerry Rafferty");
        this.createCrew("Jeff Lynne");
        this.createCrew("Brian May");
        this.createProduct("PEA250","Peanuts Salted 250g Bag");
        this.createProduct("CAS150", "Cashews 150g Bag");
        this.createProduct("BOMTB", "Bombay Mix 500g Tub");
        this.createShift("AM");
        this.createShift("PM");
        this.createShift("NIGHTS");
        this.createProductionRun("PEA250","R-001");
        this.createProductionRun("PEA250","R-002");
        this.createProductionRun("PEA250","R-003");
        this.createProductionRun("CAS150","R-101");
        this.createProductionRun("CAS150","R-102");
        this.createProductionRun("CAS150","R-103");
        this.createProductionRun("BOMTB","R-201");
        this.createProductionRun("BOMTB","R-202");
        this.createProductionRun("BOMTB","R-203");
    }

    private final void createResource(String tag, String name) {
        httpHelper.doPost("/resource", RestMessages.getMessage("CREATE_RESOURCE", tag, name));
        dataRegister.addItems(new DataItem("RESOURCE", tag + "," + name));
    }

    private final void createCrew(String name) {
        httpHelper.doPost("/crew", RestMessages.getMessage("CREATE_CREW", name));
        dataRegister.addItems(new DataItem("CREW", name));
    }

    private final void createShift(String name) {
        httpHelper.doPost("/shift", RestMessages.getMessage("CREATE_SHIFT", name));
        dataRegister.addItems(new DataItem("SHIFT", name));
    }

    private final void createProduct(String name, String description) {
        httpHelper.doPost("/product", RestMessages.getMessage("CREATE_PRODUCT", name, description));
        dataRegister.addItems(new DataItem("PRODUCT", name));
    }

    private final void createProductionRun(String productName, String runName) {
        httpHelper.doPost("/productionRun", RestMessages.getMessage("CREATE_PRODUCTION_RUN", productName, runName));
        dataRegister.addItems(new DataItem("PRODUCTION_RUN", runName));
    }

}
