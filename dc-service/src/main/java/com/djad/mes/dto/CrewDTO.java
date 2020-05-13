package com.djad.mes.dto;

public class CrewDTO {
    private String crewName;

    public CrewDTO() {
    }

    public CrewDTO(String crewName) {
        this.crewName = crewName;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }
}
