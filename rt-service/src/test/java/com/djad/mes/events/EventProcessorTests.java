package com.djad.mes.events;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.service.AdminService;
import com.djad.mes.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventProcessorTests {

    @Mock
    private ResourceService resourceService;

    @Mock
    private AdminService adminService;

    @Mock
    private EventConsumerDelegate eventConsumer;

    @InjectMocks
    private EventProcessor eventProcessor;

    @Test(expected = MesApplicationRuntimeException.class)
    public void testConsumeSingleEventWithNullValue() {
        eventProcessor.consumeSingleEvent("key", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConsumeSingleEventWithInvalidType() {
        eventProcessor.consumeSingleEvent("key", "only_one_part");
    }

    @Test
    public void testMakeAvailable() {
        eventProcessor.consumeSingleEvent("key", "AVAILABILITY,1");
        verify(resourceService).makeAvailable("key");
    }

    @Test
    public void testMakeUnavailable() {
        eventProcessor.consumeSingleEvent("key", "AVAILABILITY,0");
        verify(resourceService).makeUnavailable("key");
    }

    @Test
    public void testChangeCrew() {
        eventProcessor.consumeSingleEvent("key", "CREW,value");
        verify(resourceService).changeCrew("key", "value");
    }

    @Test
    public void testChangeShift() {
        eventProcessor.consumeSingleEvent("key", "SHIFT,value");
        verify(resourceService).changeShift("key", "value");
    }

    @Test
    public void testChangeRun() {
        eventProcessor.consumeSingleEvent("key", "PRODUCTION_RUN,value");
        verify(resourceService).changeProductionRun("key", "value");
    }

    @Test
    public void testStop() {
        eventProcessor.consumeSingleEvent("key", "PRODUCTION_STATE,0");
        verify(resourceService).stop("key");
    }

    @Test
    public void testSetRunningSlow() {
        eventProcessor.consumeSingleEvent("key", "PRODUCTION_STATE,1");
        verify(resourceService).setRunningSlow("key");
    }

    @Test
    public void testSetRunningFullSpeed() {
        eventProcessor.consumeSingleEvent("key", "PRODUCTION_STATE,2");
        verify(resourceService).setRunningFullSpeed("key");
    }

    @Test
    public void testCreateResource() {
        eventProcessor.consumeSingleEvent("key", "CREATE_RESOURCE,tag,name");
        verify(resourceService).createResource("key", "name");
    }
}
