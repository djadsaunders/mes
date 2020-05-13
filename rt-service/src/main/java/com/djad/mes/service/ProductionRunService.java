package com.djad.mes.service;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.domain.product.Product;
import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.repository.ProductRepository;
import com.djad.mes.repository.ProductionRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductionRunService {

    ProductionRunRepository productionRunRepository;
    ProductRepository productRepository;

    @Autowired
    public ProductionRunService(ProductionRunRepository productionRunRepository, ProductRepository productRepository) {
        this.productionRunRepository = productionRunRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<ProductionRun> getProductionRun(String name) {
        return productionRunRepository.findByName(name);
    }

    @Transactional
    public ProductionRun createProductionRun(String runName, String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        if (!product.isPresent()) throw new MesApplicationRuntimeException("Cannot find product " + productName);
        ProductionRun productionRun = new ProductionRun(product.get(), runName);
        return productionRunRepository.save(productionRun);
    }
}
