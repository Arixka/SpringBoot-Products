package com.maria.siverio.apirestproducts.products.controllers;

import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public void cualquiercosa() {
        //aqui llamamos al servicio, en la capa de servicio se hacen las transformaciones
        List<Product> products = repository.findProductsByStatus(Status.ACTIVE);
        if (products.isEmpty()){
            System.out.printf("no hay nadie ");
        }
        //TODO
    }

}
