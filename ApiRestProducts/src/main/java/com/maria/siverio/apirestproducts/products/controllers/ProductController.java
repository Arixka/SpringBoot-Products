package com.maria.siverio.apirestproducts.products.controllers;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductRequestDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductResponseDto;
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

    @Autowired
    private ProductService service;

    @GetMapping("/all/{status}")
    private ResponseEntity<List<ProductResponseDto>> getAllProductsByStatus(@PathVariable StatusEnum status) {
        List<ProductResponseDto> productsResponse = service.findProductsByStatus(status);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productsResponse);
    }

    @PostMapping("/")
    private ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createProductResponse = service.createProduct(productRequestDto);

        if (createProductResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductResponse);
    }

    @PutMapping("/")
    private ResponseEntity<ProductResponseDto> editProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto editProduct = service.editProduct(productRequestDto);
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
    private ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productResponse = service.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponse);
    }


}
