package com.djad.mes.webservice;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.dto.*;
import com.djad.mes.service.DCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResourceControllerTests {

    @InjectMocks
    ResourceController resourceController;

    @Mock
    DCService dcService;

    @Test(expected= MesApplicationRuntimeException.class)
    public void testChangeProductionStateWithNullTag() {
        resourceController.changeProductionState(new ProductionStateDTO(), null);
    }

    @Test(expected= MesApplicationRuntimeException.class)
    public void testChangeAvailabilityWithNullTag() {
        resourceController.changeAvailability(new AvailabilityDTO(), null);
    }

    @Test
    public void testChangeProductionState() {
        ProductionStateDTO dto = new ProductionStateDTO();
        String tag = "LINEA";

        dto.setState(0);
        resourceController.changeProductionState(dto, tag);
        verify(dcService).stop(tag);

        dto.setState(1);
        resourceController.changeProductionState(dto, tag);
        verify(dcService).setRunningSlow(tag);

        dto.setState(2);
        resourceController.changeProductionState(dto, tag);
        verify(dcService).setRunningFullSpeed(tag);
    }

    @Test
    public void testChangeAvailability() {
        AvailabilityDTO dto = new AvailabilityDTO();
        String tag = "LINEA";

        dto.setAvailability(0);
        resourceController.changeAvailability(dto, tag);
        verify(dcService).makeUnavailable(tag);

        dto.setAvailability(1);
        resourceController.changeAvailability(dto, tag);
        verify(dcService).makeAvailable(tag);
    }

    @Test
    public void testChangeShift() {
        ShiftDTO dto = new ShiftDTO();
        String tag = "LINEA";
        String name = "AM";
        dto.setShiftName(name);

        resourceController.changeShift(dto, tag);
        verify(dcService).changeShift(tag, name);
    }

    @Test
    public void testChangeCrew() {
        CrewDTO dto = new CrewDTO();
        String tag = "LINEA";
        String name = "BLUE";
        dto.setCrewName(name);

        resourceController.changeCrew(dto, tag);
        verify(dcService).changeCrew(tag, name);
    }

    @Test
    public void testChangeRun() {
        ProductionRunDTO dto = new ProductionRunDTO();
        String tag = "LINEA";
        String name = "001";
        dto.setRunName(name);

        resourceController.changeRun(dto, tag);
        verify(dcService).changeRun(tag, name);
    }

    @Test
    public void testLogInCount() {
        ProductionCountDTO dto = new ProductionCountDTO();
        double value = 100;
        String tag = "LINEA";
        dto.setValue(value);

        resourceController.logInCount(dto, tag);
        verify(dcService).logInCount(tag, value);
    }

    @Test
    public void testLogOutCount() {
        ProductionCountDTO dto = new ProductionCountDTO();
        double value = 100;
        String tag = "LINEA";
        dto.setValue(value);

        resourceController.logOutCount(dto, tag);
        verify(dcService).logOutCount(tag, value);
    }

    @Test
    public void testCreateResource() {
        ResourceDTO dto = new ResourceDTO();
        String tag = "LINEA";
        dto.setTag("LINEA");
        dto.setName("Line A");

        resourceController.createResource(dto);
        verify(dcService).createResource(tag, "Line A");
    }

    @Test
    public void testRemoveResource() {
        resourceController.removeResource("LINEA");
        verify(dcService).removeResource("LINEA");
    }
}
