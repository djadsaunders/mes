package com.djad.mes.repository;

import com.djad.mes.domain.resource.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    Optional<Resource> findByTag(String tag);
    void deleteByTag(String tag);
}