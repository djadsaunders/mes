package com.djad.mes.webservice;

import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.dto.ProductionRunDTO;
import com.djad.mes.service.ProductionRunService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/productionRun")
@Api(value="productionRun",  tags=("productionRun"))
public class ProductionRunController {

    private static final Logger logger = LoggerFactory.getLogger(ProductionRunController.class);

    private ProductionRunService service;

    @Autowired
    public ProductionRunController(ProductionRunService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    @ApiOperation(value="Get a productionRun", notes="Get a single productionRun", nickname="getProductionRun")
    public Optional<ProductionRun> getProductionRun(@PathVariable String name) {
        logger.debug("ProductionRun web service: getProductionRun");
        return service.getProductionRun(name);
    }

    @PostMapping
    @ApiOperation(value="Add a productionRun", notes="Add a productionRun", nickname="createProductionRun")
    public ProductionRun createProductionRun(@RequestBody ProductionRunDTO dto) {
        logger.debug("ProductionRun web service: createProductionRun");
        return service.createProductionRun(dto.getRunName(), dto.getProductName());
    }
}
