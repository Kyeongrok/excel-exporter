package com.okky.excelexporter2.monitoring.repository;

import com.okky.excelexporter2.monitoring.domain.MonitoringDataDto;

import java.util.List;

public interface MonitoringDataRepository {

    List<MonitoringDataDto> findAll();
    List<MonitoringDataDto> findById(long id);
}
