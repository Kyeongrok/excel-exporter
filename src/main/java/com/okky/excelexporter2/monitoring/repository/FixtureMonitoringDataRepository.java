package com.okky.excelexporter2.monitoring.repository;

import com.okky.excelexporter2.monitoring.domain.MonitoringDataDto;

import java.util.List;

public class FixtureMonitoringDataRepository implements MonitoringDataRepository {
    @Override
    public List<MonitoringDataDto> findAll() {
        return List.of();
    }

    @Override
    public List<MonitoringDataDto> findById(long id) {
        return List.of();
    }
}
