package com.djad.mes.repository;

import com.djad.mes.domain.crew.Crew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrewRepository extends CrudRepository<Crew, Long> {
    Optional<Crew> findByName(String name);
}
