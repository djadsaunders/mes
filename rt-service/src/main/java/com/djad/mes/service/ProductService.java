package com.djad.mes.service;

import com.djad.mes.domain.product.Product;
import com.djad.mes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Product> getProduct(String name) {
        return productRepository.findByName(name);
    }

    @Transactional
    public List<Product> getProducts() {
        return (List<Product>)productRepository.findAll();
    }

    @Transactional
    public Product createProduct(String name, String description) {
        Product product = new Product(name, description);
        return productRepository.save(product);
    }
}
