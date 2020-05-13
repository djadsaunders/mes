package com.djad.mesquery.service;

import com.djad.mesquery.dto.ProductionRunDTO;
import com.djad.mesquery.dto.ResourceDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.djad.mesquery.repository.*;

import java.util.List;

@Service
public class QueryService {

	QueryRepository queryRepository = null;

	@Autowired
	public QueryService(QueryRepository queryRepository) {
		this.queryRepository = queryRepository;
	}

	public ResourceDTO getSingleResource(String tag) {
		return queryRepository.getSingleResource(tag);
	}

	public List<ResourceDTO> getAllResources() {
		return queryRepository.getAllResources();
	}

	public ProductionRunDTO getProductionRunInfo(String tag) {
		// TODO: what if there is no current run on the resource
		return queryRepository.getProductionRun(tag);
	}
}