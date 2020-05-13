package com.djad.mes.service;

import com.djad.mes.repository.ShiftRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShiftServiceTests {

    @Mock
    ShiftRepository shiftRepository;

    @InjectMocks
    ShiftService service;

    @Test
    public void testGetShift() {
        service.getShift("AM");
        verify(shiftRepository).findByName("AM");
    }

    @Test
    public void testGetShifts() {
        service.getShifts();
        verify(shiftRepository).findAll();
    }

    @Test
    public void testCreateShift() {
        service.createShift("AM");
        verify(shiftRepository).save(any());
    }
}
