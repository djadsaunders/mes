package com.djad.mes.webservice;

import com.djad.mes.service.DCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin")
@Api(value="admin",  tags=("admin"))
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private DCService dcService;

    @Autowired
    public AdminController(DCService DCService) {
        this.dcService = DCService;
    }

    @DeleteMapping("/data")
    @ApiOperation(value="Reset all data", notes="Deletes all data in the database", nickname="resetData")
    public void resetData() {
        logger.debug("Admin web service: resetData");
        dcService.resetData();
    }
}
