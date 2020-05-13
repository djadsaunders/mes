package com.djad.mes.repository;

import com.djad.mes.domain.shift.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    Optional<Shift> findByName(String name);
}
