package com.maria.siverio.apirestproducts.products.controller;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.services.impl.ProductService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/all") //min 15:16
    public ResponseEntity<?> getAllProducts(){
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
