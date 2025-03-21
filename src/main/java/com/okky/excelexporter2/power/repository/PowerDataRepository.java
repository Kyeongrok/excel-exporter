package com.okky.excelexporter2.power.repository;

import com.okky.excelexporter2.power.domain.PowerDataDto;

import java.util.List;

public interface PowerDataRepository {
    List<PowerDataDto> findAll();
    List<PowerDataDto> findById(long id);
}
