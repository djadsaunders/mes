package com.djad.mes.repository;

import com.djad.mes.domain.product.ProductionRun;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionRunRepository extends CrudRepository<ProductionRun, Long> {
    Optional<ProductionRun> findByName(String name);
}
