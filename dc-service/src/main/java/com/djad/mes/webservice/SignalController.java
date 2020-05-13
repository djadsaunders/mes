package com.djad.mes.webservice;

import com.djad.mes.MesApplicationRuntimeException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.djad.mes.service.SignalService;
import com.djad.mes.dto.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/signal")
@Api(value="signal",  tags=("signal"))
public class SignalController {

    private static final Logger logger = LoggerFactory.getLogger(SignalController.class);

    SignalService signalService;

    @Autowired
    public SignalController(SignalService signalService) {
        this.signalService = signalService;
    }

    @PostMapping("/{deviceId}/{sensorId}/{value}")
    @ApiOperation(value="Process a signal sent to a sensor", notes="Process a signal sent to a sensor", nickname="receiveSignal")
    public void receiveSignal(@PathVariable String deviceId, @PathVariable String sensorId, @PathVariable String value) {
        logger.debug("Signal web service: receiveSignal");

        signalService.processSignal(deviceId, sensorId, value);
    }
}
