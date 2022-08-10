package com.maria.siverio.apirestproducts.products.controllers;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.StatusEnum;
import com.maria.siverio.apirestproducts.products.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = {"http://127.0.0.1:5173", "http://localhost:5173"})
@RestController
@RequestMapping("/api/product")
public class ProductController {

    /**
     * Get all product by status
     * Create item -> Active as default, mandatory ItemCode and Descrition, and current data
     * Edit Item ->
     * Desactive Item -> change status to Discontinued, I need column register user and reason =_=
     */
    @Autowired
    private ProductService service;

    @GetMapping("/all/{status}")
    private ResponseEntity<List<ProductDto>> getAllProductsByStatus(@PathVariable StatusEnum status) {
        List<ProductDto> products = service.findProductsByStatus(status);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
    }

    @PostMapping("/")
    private ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto createProduct = service.createProduct(productDto);

        if (createProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    }

    @PutMapping("/")
    private ResponseEntity<ProductDto> editProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto editProduct = service.editProduct(productDto);
        if (editProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(editProduct);
    }

    @PutMapping("/desactive/{itemcode}")
    private ResponseEntity<ProductDto> desactiveProduct(@PathVariable String itemcode, @RequestBody ProductDto productDto) {
        ProductDto disabletProduct = service.desactiveProduct(itemcode, productDto.getReasonDeactivation());
        if (disabletProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(disabletProduct);
    }


    /**
     * Endpoints para probar la api
     */
    @GetMapping("/all")
    private ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = service.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
    }


}
