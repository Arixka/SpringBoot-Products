package com.maria.siverio.apirestproducts.products.controllers;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.services.impl.ProductService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    /**
     * Get all product by status
     * Create item -> Active as default, mandatory ItemCode and Descrition, and current data
     * Edit Item
     * Desactive Item -> change status to Discontinued, I need column register user and reason =_=
     */
    @Autowired
    private ProductService service;

    @GetMapping("/all")
    private ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = service.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
    }

    @GetMapping("/all/{status}")
    private ResponseEntity<List<ProductDto>> getAllProductsByStatus(@PathVariable Status status){
        List<ProductDto> products = service.findProductsByStatus(status);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
    }

    @PostMapping("/")
    private ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto){
        service.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }

    @GetMapping("/{itemCode}")
    private ResponseEntity<?> getProductByItemCode(@PathVariable String itemCode){
        ProductDto product = service.getProductByItemCode(itemCode);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{itemCode}")
    private ResponseEntity<?> editProduct(@PathVariable String itemCode, @RequestBody ProductDto productDto){
        return null;
    }



}
