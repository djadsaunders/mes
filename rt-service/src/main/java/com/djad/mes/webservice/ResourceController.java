package com.djad.mes.webservice;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.domain.resource.Resource;
import com.djad.mes.dto.AvailabilityDTO;
import com.djad.mes.dto.CrewDTO;
import com.djad.mes.dto.ProductionCountDTO;
import com.djad.mes.dto.ProductionRunDTO;
import com.djad.mes.dto.ProductionStateDTO;
import com.djad.mes.dto.ResourceDTO;
import com.djad.mes.dto.ShiftDTO;
import com.djad.mes.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/resource")
@Api(value="resource",  tags=("resource"))
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    private ResourceService service;

    @Autowired
    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value="Get all resources", notes="Get all resources", nickname="getResources")
    public List<Resource> getResources() {
        logger.debug("Resource web service: getResources");
        return service.getAllResources();
    }

    @GetMapping("/{tag}")
    @ApiOperation(value="Get a resource", notes="Get a single resource", nickname="getResource")
    public Resource getResource(@PathVariable String tag) {
        logger.debug("Resource web service: getResource");
        return service.getResourceByTag(tag);
    }

    @PostMapping
    @ApiOperation(value="Add a resource", notes="Add a resource", nickname="createResource")
    public Resource createResource(@RequestBody ResourceDTO dto) {
        logger.debug("Crew web service: createCrew");
        return service.createResource(dto.getTag(), dto.getName());
    }

    @PostMapping("/{tag}/availability")
    @ApiOperation(value="Change resource availability", notes="Change resource availability", nickname="changeAvailability")
    public void changeAvailability(@RequestBody AvailabilityDTO availabilityDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeAvailability");

        if (tag == null) {
            throw new MesApplicationRuntimeException("Change availability: 'tag' is a required parameter");
        }

        switch (availabilityDTO.getAvailability()) {
            case 1:
                service.makeAvailable(tag);
                break;
            case 0:
                service.makeUnavailable(tag);
                break;
            default:
                throw new MesApplicationRuntimeException("Change availability: invalid availability value passed");
        }
    }

    @PostMapping("/{tag}/state")
    @ApiOperation(value="Change resource state", notes="Change resource state", nickname="changeState")
    public void changeState(@RequestBody ProductionStateDTO productionStateDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeState");

        if (tag == null) {
            throw new MesApplicationRuntimeException("Change state: 'tag' is a required parameter");
        }

        switch (productionStateDTO.getState()) {
            case 2:
                service.setRunningFullSpeed(tag);
                break;
            case 1:
                service.setRunningSlow(tag);
                break;
            case 0:
                service.setRunningSlow(tag);
                break;
            default:
                throw new MesApplicationRuntimeException("Change state: invalid state value passed");
        }
    }

    @PostMapping("/{tag}/shift")
    @ApiOperation(value="Change shift", notes="Change shift", nickname="changeShift")
    public void changeShift(@RequestBody ShiftDTO shiftDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeShift");
        service.changeShift(tag, shiftDTO.getName());
    }

    @PostMapping("/{tag}/crew")
    @ApiOperation(value="Change crew", notes="Change crew", nickname="changeCrew")
    public void changeCrew(@RequestBody CrewDTO crewDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeCrew");
        service.changeCrew(tag, crewDTO.getName());
    }

    @PostMapping("/{tag}/run")
    @ApiOperation(value="Change run", notes="Change run", nickname="changeRun")
    public void changeRun(@RequestBody ProductionRunDTO productionRunDTO, @PathVariable String tag) {
        logger.debug("Resource web service: changeRun");
        service.changeProductionRun(tag, productionRunDTO.getRunName());
    }

    @PostMapping("/{tag}/inCount")
    @ApiOperation(value="Log in count", notes="Log in count", nickname="logInCount")
    public void logInCount(@RequestBody ProductionCountDTO productionCountDTO, @PathVariable String tag) {
        logger.debug("Resource web service: logInCount");
        service.logInCount(tag, productionCountDTO.getValue());
    }

    @PostMapping("/{tag}/outCount")
    @ApiOperation(value="Log out count", notes="Log out count", nickname="logInCount")
    public void logOutCount(@RequestBody ProductionCountDTO productionCountDTO, @PathVariable String tag) {
        logger.debug("Resource web service: logOutCount");
        service.logOutCount(tag, productionCountDTO.getValue());
    }
}
