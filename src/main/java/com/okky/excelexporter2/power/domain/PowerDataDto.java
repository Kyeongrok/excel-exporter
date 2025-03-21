package com.okky.excelexporter2.power.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PowerDataDto {
    private float voltageL1L2;
    private float voltageL2L3;
    private float voltageL3L1;
}
