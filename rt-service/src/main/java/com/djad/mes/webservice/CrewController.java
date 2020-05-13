package com.djad.mes.webservice;

import com.djad.mes.domain.crew.Crew;
import com.djad.mes.dto.CrewDTO;
import com.djad.mes.service.CrewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/crew")
@Api(value="crew",  tags=("crew"))
public class CrewController {

    private static final Logger logger = LoggerFactory.getLogger(CrewController.class);

    private CrewService service;

    @Autowired
    public CrewController(CrewService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value="Get all crews", notes="Get all crews", nickname="getCrews")
    public List<Crew> getCrews() {
        logger.debug("Crew web service: getCrews");
        return service.getCrews();
    }

    @GetMapping("/{name}")
    @ApiOperation(value="Get a crew", notes="Get a single crew", nickname="getCrew")
    public Optional<Crew> getCrew(@PathVariable String name) {
        logger.debug("Crew web service: getCrew");
        return service.getCrew(name);
    }

    @PostMapping
    @ApiOperation(value="Add a crew", notes="Add a crew", nickname="createCrew")
    public Crew createCrew(@RequestBody CrewDTO dto) {
        logger.debug("Crew web service: createCrew");
        return service.createCrew(dto.getName());
    }
}
