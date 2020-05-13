package com.djad.mes.service;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.domain.product.Product;
import com.djad.mes.repository.ProductRepository;
import com.djad.mes.repository.ProductionRunRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductionRunServiceTests {

    @Mock
    ProductionRunRepository productionRunRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductionRunService service;

    @Test
    public void testGetProductionRun() {
        service.getProductionRun("A");
        verify(productionRunRepository).findByName("A");
    }

    @Test
    public void testCreateProductionRun() {
        when(productRepository.findByName(anyString())).thenReturn(
                Optional.of(new Product("NAME", "DESC")));
        service.createProductionRun("NAME", "DESC");
        verify(productionRunRepository).save(any());
    }

    @Test(expected=MesApplicationRuntimeException.class)
    public void testCreateProductionRunWithInvalidProduct() {
        when(productRepository.findByName(anyString())).thenReturn(Optional.empty());
        service.createProductionRun("NAME", "DESC");
    }
}
