package com.okky.excelexporter2.factory;

import com.okky.excelexporter2.monitoring.repository.FixtureMonitoringDataRepository;
import com.okky.excelexporter2.monitoring.service.MonitoringDataService;
import com.okky.excelexporter2.power.repository.FixturePowerDataRepository;
import com.okky.excelexporter2.power.service.PowerDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
    @Bean
    public MonitoringDataService fixtureMonitoringDataService() {
        FixtureMonitoringDataRepository repository = new FixtureMonitoringDataRepository();
        return new MonitoringDataService(repository);
    }

    @Bean
    public PowerDataService fixturePowerDataService() {
        FixturePowerDataRepository repository = new FixturePowerDataRepository();
        return new PowerDataService(repository);
    }
}