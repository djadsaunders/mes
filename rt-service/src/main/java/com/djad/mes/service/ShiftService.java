package com.djad.mes.service;

import com.djad.mes.domain.shift.Shift;
import com.djad.mes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Transactional
    public Shift createShift(String name) {
        Shift shift = new Shift(name);
        return shiftRepository.save(shift);
    }

    @Transactional
    public Optional<Shift> getShift(String name) {
        return shiftRepository.findByName(name);
    }

    @Transactional
    public List<Shift> getShifts() {
        return (List<Shift>)shiftRepository.findAll();
    }
}
