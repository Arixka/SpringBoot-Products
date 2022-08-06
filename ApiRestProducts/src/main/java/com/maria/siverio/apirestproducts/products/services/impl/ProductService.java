package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.mappers.ProductMapper;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public void ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product entity : products) {
            productDtos.add(productMapper.entityToDTO(entity));
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> findProductsByStatus(Status status) {
        return null;
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.entityToDTO(productRepository.findById(id).orElse(null));

    }

    @Override
    public void saveProduct(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(ProductDto productDto, Long id) {
        Product productExists = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Product not found"));
//pasar los datos del dto a un pjo y setear el productexist
        productRepository.save(productExists);
    }

}
