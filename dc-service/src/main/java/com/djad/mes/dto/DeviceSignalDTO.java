package com.djad.mes.dto;

import java.util.Date;

public class DeviceSignalDTO {
    private String deviceId;
    private String sensorId;
    private Date signalTime;
    private String value;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Date getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(Date signalTime) {
        this.signalTime = signalTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}