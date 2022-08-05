package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void findAll() {
        List<Product> products = productRepository.findAll();
        //Transformar con mapStruct o modelmapper

    }

    @Override
    public void createProduct() {

    }
}
