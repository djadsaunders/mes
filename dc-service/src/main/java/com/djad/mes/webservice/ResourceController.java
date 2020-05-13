package com.djad.mes.webservice;

import com.djad.mes.MesApplicationRuntimeException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.djad.mes.service.DCService;
import com.djad.mes.dto.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/resource")
@Api(value="resource",  tags=("resource"))
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    DCService dcService;

    @Autowired
    public ResourceController(DCService DCService) {
        this.dcService = DCService;
    }

    @PostMapping("/{tag}/state")
    @ApiOperation(value="Change resource production state", notes="Change resource production state", nickname="changeProductionState")
    public void changeProductionState(@RequestBody ProductionStateDTO productionStateDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeProductionState");

        if (tag == null || tag.length() == 0) {
            throw new MesApplicationRuntimeException("Change production state: 'tag' is a required parameter");
        }

        if (productionStateDTO.getState() == 0) {
            dcService.stop(tag);
        }
        else if (productionStateDTO.getState() == 1) {
            dcService.setRunningSlow(tag);
        }
        else {
            dcService.setRunningFullSpeed(tag);
        }
    }

    @PostMapping("/{tag}/availability")
    @ApiOperation(value="Change resource availability", notes="Change resource availability", nickname="changeAvailability")
    public void changeAvailability(@RequestBody AvailabilityDTO availabilityDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeAvailability");

        if (tag == null) {
            throw new MesApplicationRuntimeException("Change availability: 'tag' is a required parameter");
        }

        if (availabilityDTO.getAvailability() == 1) {
            dcService.makeAvailable(tag);
        }
        else {
            dcService.makeUnavailable(tag);
        }
    }

    @PostMapping("/{tag}/shift")
    @ApiOperation(value="Change shift", notes="Change shift", nickname="changeShift")
    public void changeShift(@RequestBody ShiftDTO shiftDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeShift");
        dcService.changeShift(tag, shiftDTO.getShiftName());
    }

    @PostMapping("/{tag}/crew")
    @ApiOperation(value="Change crew", notes="Change crew", nickname="changeCrew")
    public void changeCrew(@RequestBody CrewDTO crewDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeCrew");
        dcService.changeCrew(tag, crewDTO.getCrewName());
    }

    @PostMapping("/{tag}/run")
    @ApiOperation(value="Change run", notes="Change run", nickname="changeRun")
    public void changeRun(@RequestBody ProductionRunDTO productionRunDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeRun");
        dcService.changeRun(tag, productionRunDTO.getRunName());
    }

    @PostMapping("/{tag}/inCount")
    @ApiOperation(value="Log in count", notes="Log in count", nickname="logInCount")
    public void logInCount(@RequestBody ProductionCountDTO productionCountDTO, @PathVariable String tag) {
        logger.debug("Resource web service: logInCount");
        dcService.logInCount(tag, productionCountDTO.getValue());
    }

    @PostMapping("/{tag}/outCount")
    @ApiOperation(value="Log out count", notes="Log out count", nickname="logInCount")
    public void logOutCount(@RequestBody ProductionCountDTO productionCountDTO, @PathVariable String tag) {
        logger.debug("Resource web service: logOutCount");
        dcService.logOutCount(tag, productionCountDTO.getValue());
    }

    @PostMapping
    @ApiOperation(value="Create resource", notes="Create a new resource", nickname="createResource")
    public void createResource(@RequestBody ResourceDTO resourceDTO) {
        logger.debug("Resource web service: createResource");
        dcService.createResource(resourceDTO.getTag(), resourceDTO.getName());
    }

    @DeleteMapping("/{tag}")
    @ApiOperation(value="Remove resource", notes="Removes a resource", nickname="remoeResource")
    public void removeResource(@PathVariable String tag) {
        logger.debug("Resource web service: removeResource");
        dcService.removeResource(tag);
    }
}
