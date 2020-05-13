package com.djad.mes.dto;

public class ProductionRunDTO {
    private String runName;

    public ProductionRunDTO() {
    }

    public ProductionRunDTO(String runName) {
        this.runName = runName;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }
}
