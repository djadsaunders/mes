package com.djad.mes.dto;

public class ShiftDTO {
    private String shiftName;

    public ShiftDTO() {
    }

    public ShiftDTO(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }
}
