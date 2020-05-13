package com.djad.mes.dto;

public class ProductionStateDTO {
    private int state;

    public ProductionStateDTO() {
    }

    public ProductionStateDTO(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
