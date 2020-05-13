package com.djad.mes.service;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.domain.crew.Crew;
import com.djad.mes.domain.product.Product;
import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.domain.resource.Resource;
import com.djad.mes.domain.resource.ResourceFactory;
import com.djad.mes.domain.shift.Shift;
import com.djad.mes.repository.CrewRepository;
import com.djad.mes.repository.ProductionRunRepository;
import com.djad.mes.repository.ResourceRepository;
import com.djad.mes.repository.ShiftRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceTests {

    private final String DEFAULT_LINE_TAG = "LINEA";

    @Mock
    ResourceRepository resourceRepository;

    @Mock
    ShiftRepository shiftRepository;

    @Mock
    CrewRepository crewRepository;

    @Mock
    ProductionRunRepository productionRunRepository;

    @Mock
    ResourceFactory resourceFactory;

    @InjectMocks
    ResourceService resourceService;

    @Test
    public void testSetRunningFullSpeed() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.stop();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.setRunningFullSpeed(DEFAULT_LINE_TAG);

        assertTrue(resource.isRunningFullSpeed());
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testRunningFullSpeedWhenNoChange() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.setRunningFullSpeed();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.setRunningFullSpeed(DEFAULT_LINE_TAG);

        verify(resourceRepository).save(resource);
    }

    @Test
    public void testSetRunningSlow() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.stop();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.setRunningSlow(DEFAULT_LINE_TAG);

        assertTrue(resource.isRunningSlow());
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testStop() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.setRunningFullSpeed();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.stop(DEFAULT_LINE_TAG);

        assertTrue(resource.isStopped());
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testMakeAvailable() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.makeUnavailable();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.makeAvailable(DEFAULT_LINE_TAG);

        assertTrue(resource.isAvailable());
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testMakeUnavailable() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        resource.makeAvailable();
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));

        resourceService.makeUnavailable(DEFAULT_LINE_TAG);

        assertFalse(resource.isAvailable());
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testChangeShift() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        Shift shift = new Shift("AM");

        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        when(shiftRepository.findByName(anyString())).thenReturn(Optional.of(shift));

        resourceService.changeShift(DEFAULT_LINE_TAG, "AM");

        verify(resourceRepository).save(resource);

        assertEquals(resource.getCurrentShift().get().getName(), "AM");
    }

    @Test
    public void testChangeCrew() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        Crew crew = new Crew("A");

        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        when(crewRepository.findByName(anyString())).thenReturn(Optional.of(crew));

        resourceService.changeCrew(DEFAULT_LINE_TAG, "A");

        verify(resourceRepository).save(resource);

        assertEquals(resource.getCurrentCrew().get().getName(), "A");
    }

    @Test
    public void testChangeProductionRun() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        ProductionRun run = new ProductionRun(new Product("A","A"),"A");

        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        when(productionRunRepository.findByName(anyString())).thenReturn(Optional.of(run));

        resourceService.changeProductionRun(DEFAULT_LINE_TAG, "A");

        verify(resourceRepository).save(resource);

        assertEquals(resource.getCurrentProductionRun().get().getName(), "A");
    }

    @Test
    public void testGetAllResources() {
        resourceService.getAllResources();
        verify(resourceRepository).findAll();
    }

    @Test
    public void testGetResourceByTag() {
        String tag = DEFAULT_LINE_TAG;
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        resourceService.getResourceByTag(tag);
        verify(resourceRepository).findByTag(tag);
    }

    @Test(expected=MesApplicationRuntimeException.class)
    public void testGetResourceByTagWhenResourceNotFound() {
        String tag = DEFAULT_LINE_TAG;
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.empty());
        resourceService.getResourceByTag(tag);
        verify(resourceRepository).findByTag(tag);
    }

    @Test
    public void testCreateResource() {
        String tag = "TAG";
        String name = "NAME";

        Resource resource = resourceService.createResource(tag, name);

        verify(resourceFactory).createResource(tag, name);
        verify(resourceRepository).save(resource);
    }

    @Test(expected=MesApplicationRuntimeException.class)
    public void testCreateResourceWhenResourceAlreadyExists() {
        when(resourceRepository.findByTag(any())).thenReturn(Optional.of(new Resource()));
        resourceService.createResource("", "");
    }

    @Test
    public void testLogInCount() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        resourceService.logInCount(DEFAULT_LINE_TAG, 10.0);
        verify(resourceRepository).save(resource);
    }

    @Test
    public void testLogOutCount() {
        Resource resource = new Resource(DEFAULT_LINE_TAG);
        when(resourceRepository.findByTag(DEFAULT_LINE_TAG)).thenReturn(Optional.of(resource));
        resourceService.logOutCount(DEFAULT_LINE_TAG, 10.0);
        verify(resourceRepository).save(resource);
    }
}
