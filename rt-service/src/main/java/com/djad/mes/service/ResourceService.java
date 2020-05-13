package com.djad.mes.service;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.domain.crew.Crew;
import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.domain.resource.Resource;
import com.djad.mes.domain.resource.ResourceFactory;
import com.djad.mes.domain.shift.Shift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.djad.mes.repository.*;

import java.util.*;

@Service
@Transactional
public class ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    ResourceRepository resourceRepository;
    ShiftRepository shiftRepository;
    CrewRepository crewRepository;
    ProductionRunRepository productionRunRepository;
    ResourceFactory resourceFactory;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, ResourceFactory resourceFactory,
                           ShiftRepository shiftRepository, ProductionRunRepository productionRunRepository,
                           CrewRepository crewRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceFactory = resourceFactory;
        this.shiftRepository = shiftRepository;
        this.crewRepository = crewRepository;
        this.productionRunRepository = productionRunRepository;
    }

    public Resource setRunningFullSpeed(String tag) {
        logger.debug("Resource Service: set running full speed");

        Resource resource = this.getResourceByTag(tag);
        resource.setRunningFullSpeed();

        return resourceRepository.save(resource);
    }

    public Resource setRunningSlow(String tag) {
        logger.debug("Resource Service: set running slow");

        Resource resource = this.getResourceByTag(tag);
        resource.setRunningSlow();

        return resourceRepository.save(resource);
    }

    public Resource stop(String tag) {
        logger.debug("Resource Service: stop");

        Resource resource = this.getResourceByTag(tag);
        resource.stop();

        return resourceRepository.save(resource);
    }

    public Resource makeAvailable(String tag) {
        logger.debug("Resource Service: make available");

        Resource resource = this.getResourceByTag(tag);
        resource.makeAvailable();

        return resourceRepository.save(resource);
    }

    public Resource makeUnavailable(String tag) {
        logger.debug("Resource Service: make unavailable");

        Resource resource = this.getResourceByTag(tag);
        resource.makeUnavailable();

        return resourceRepository.save(resource);
    }

    public Resource changeShift(String tag, String value) {
        logger.debug("Resource Service: change shift");

        Resource resource = this.getResourceByTag(tag);
        Shift shift = this.getShiftByName(value);

        resource.changeShift(shift);

        return resourceRepository.save(resource);
    }

    public Resource changeCrew(String tag, String value) {
        logger.debug("Resource Service: change crew");

        Resource resource = this.getResourceByTag(tag);
        Crew crew = this.getCrewByName(value);

        resource.changeCrew(crew);

        return resourceRepository.save(resource);
    }

    public Resource changeProductionRun(String tag, String value) {
        logger.debug("Resource Service: change run");

        Resource resource = this.getResourceByTag(tag);
        ProductionRun run = this.getProductionRunByName(value);

        resource.changeProductionRun(run);

        return resourceRepository.save(resource);
    }

    public void logInCount(String tag, double value) {
        logger.debug("Resource Service: log in count");

        Resource resource = this.getResourceByTag(tag);
        resource.logInCount(value);

        resourceRepository.save(resource);
    }

    public void logOutCount(String tag, double value) {
        logger.debug("Resource Service: log out count");

        Resource resource = this.getResourceByTag(tag);
        resource.logOutCount(value);

        resourceRepository.save(resource);
    }

    public List<Resource> getAllResources() {
        return (List<Resource>)resourceRepository.findAll();
    }

    public Resource getResourceByTag(String tag) {
        return resourceRepository.findByTag(tag).orElseThrow(() ->
                new MesApplicationRuntimeException("Cannot find resource with tag: " + tag));
    }

    public Shift getShiftByName(String name) {
        return shiftRepository.findByName(name).orElseThrow(() ->
                new MesApplicationRuntimeException("Cannot find shift with name: " + name));
    }

    public Crew getCrewByName(String name) {
        return crewRepository.findByName(name).orElseThrow(() ->
                new MesApplicationRuntimeException("Cannot find crew with name: " + name));
    }

    public ProductionRun getProductionRunByName(String name) {
        return productionRunRepository.findByName(name).orElseThrow(() ->
                new MesApplicationRuntimeException("Cannot find run with name: " + name));
    }

    public Resource createResource(String tag, String name) {
        resourceRepository.findByTag(tag).ifPresent(resource -> {
            throw new MesApplicationRuntimeException("Cannot create resource, a resource with tag " +
                    resource.getTag() + " already exists");
        });
        Resource resource = resourceFactory.createResource(tag, name);
        resourceRepository.save(resource);
        return resource;
    }
}
