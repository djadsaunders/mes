package com.djad.mes.webservice;

import com.djad.mes.domain.shift.Shift;
import com.djad.mes.dto.ShiftDTO;
import com.djad.mes.service.ShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/shift")
@Api(value="shift",  tags=("shift"))
public class ShiftController {

    private static final Logger logger = LoggerFactory.getLogger(ShiftController.class);

    private ShiftService service;

    @Autowired
    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value="Get all shifts", notes="Get all shifts", nickname="getShifts")
    public List<Shift> getShifts() {
        logger.debug("Shift web service: getShifts");
        return service.getShifts();
    }

    @GetMapping("/{name}")
    @ApiOperation(value="Get a shift", notes="Get a single shift", nickname="getShift")
    public Optional<Shift> getShift(@PathVariable String name) {
        logger.debug("Shift web service: getShift");
        return service.getShift(name);
    }

    @PostMapping
    @ApiOperation(value="Add a shift", notes="Add a shift", nickname="createShift")
    public Shift createShift(@RequestBody ShiftDTO dto) {
        logger.debug("Shift web service: createShift");
        return service.createShift(dto.getName());
    }
}
