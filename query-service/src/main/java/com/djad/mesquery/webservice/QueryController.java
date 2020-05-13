package com.djad.mesquery.webservice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.djad.mesquery.dto.*;
import com.djad.mesquery.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/query")
@Api(value="query",  tags=("query"))
public class QueryController {

	QueryService queryService = null;

	@Autowired
	public QueryController(QueryService queryService) {
		this.queryService = queryService;
	}

	@GetMapping("/singleResource")
	@ApiOperation(value="Get single resource", notes="Returns information about a resource", nickname="getSingleResource")
	public ResourceDTO getSingleResource(@RequestParam String tag) {
		return queryService.getSingleResource(tag);
	}

	@GetMapping("/allResources")
	@ApiOperation(value="Get all resources", notes="Returns information about all resources", nickname="getAllResources")
	public List<ResourceDTO> getSingleResource() {
		return queryService.getAllResources();
	}

	@GetMapping("/productionRun")
	@ApiOperation(value="Get production run info", notes="Returns information for the current run in a resource", nickname="getProductionRunInfo")
	public ProductionRunDTO getProductionRunInfo(@RequestParam String tag) {
		return queryService.getProductionRunInfo(tag);
	}
}