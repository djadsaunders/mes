package com.djad.mes.webservice;

import com.djad.mes.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/admin")
@Api(value="admin",  tags=("admin"))
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @DeleteMapping(value="/data")
    @ApiOperation(value="Delete all data", notes="Delete transactional data", nickname="deleteTransactionalData")
    public void data() {
        logger.debug("Admin web service: deleteTransactionalData");
        service.deleteTransactionalData();
    }
}
