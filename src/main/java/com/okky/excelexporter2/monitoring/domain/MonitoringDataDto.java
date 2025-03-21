package com.okky.excelexporter2.monitoring.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MonitoringDataDto {
    private String idMachine;
    private String machineName;
    private String equipmentName;
    private double energyMonitored;
    private float voltageRS;
    private float voltageST;
    private float voltageTR;
    private int qty;
    private int spm;
    private boolean isOn;      // onoff_monitored
    private LocalDateTime monitoredAt;  // monitored_at_datetime
    private String factoryName;
    private float powerFactor;     // 역률
    private float reactivePower;   // 무효전력
    private int errorChannel;
}
