package com.djad.mes.service;

import com.djad.mes.repository.CrewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CrewServiceTests {

    @Mock
    CrewRepository crewRepository;

    @InjectMocks
    CrewService crewService;

    @Test
    public void testGetCrew() {
        crewService.getCrew("CREW");
        verify(crewRepository).findByName("CREW");
    }

    @Test
    public void testGetCrews() {
        crewService.getCrews();
        verify(crewRepository).findAll();
    }

    @Test
    public void testCreateCrew() {
        crewService.createCrew("CREW");
        verify(crewRepository).save(any());
    }
}
