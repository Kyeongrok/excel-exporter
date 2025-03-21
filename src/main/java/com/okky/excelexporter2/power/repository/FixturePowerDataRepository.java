package com.okky.excelexporter2.power.repository;

import com.okky.excelexporter2.power.domain.PowerDataDto;

import java.util.List;

public class FixturePowerDataRepository implements PowerDataRepository{
    @Override
    public List<PowerDataDto> findAll() {
        return List.of();
    }

    @Override
    public List<PowerDataDto> findById(long id) {
        return List.of();
    }
}
