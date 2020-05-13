package com.djad.mes.service;

import com.djad.mes.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService service;

    @Test
    public void testGetProduct() {
        service.getProduct("A");
        verify(productRepository).findByName("A");
    }

    @Test
    public void testGetProducts() {
        service.getProducts();
        verify(productRepository).findAll();
    }

    @Test
    public void testCreateProduct() {
        service.createProduct("NAME", "DESC");
        verify(productRepository).save(any());
    }
}
