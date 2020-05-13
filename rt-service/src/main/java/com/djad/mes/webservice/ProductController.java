package com.djad.mes.webservice;

import com.djad.mes.domain.product.Product;
import com.djad.mes.dto.ProductDTO;
import com.djad.mes.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/product")
@Api(value="product",  tags=("product"))
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value="Get all products", notes="Get all products", nickname="getProducts")
    public List<Product> getProducts() {
        logger.debug("Product web service: getProducts");
        return service.getProducts();
    }

    @GetMapping("/{name}")
    @ApiOperation(value="Get a product", notes="Get a single product", nickname="getProduct")
    public Optional<Product> getProduct(@PathVariable String name) {
        logger.debug("Product web service: getProduct");
        return service.getProduct(name);
    }

    @PostMapping
    @ApiOperation(value="Add a product", notes="Add a product", nickname="createProduct")
    public Product createProduct(@RequestBody ProductDTO dto) {
        logger.debug("Product web service: createProduct");
        return service.createProduct(dto.getName(), dto.getDescription());
    }
}
