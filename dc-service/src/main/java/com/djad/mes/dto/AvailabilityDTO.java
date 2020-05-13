package com.djad.mes.dto;

public class AvailabilityDTO {
    private int availability;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(int availability) {
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
