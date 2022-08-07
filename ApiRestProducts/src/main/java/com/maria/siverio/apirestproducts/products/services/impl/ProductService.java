package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.mappers.ProductMapper;
import com.maria.siverio.apirestproducts.products.models.PriceReduction;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.models.Supplier;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements IProductService {
    // TODO añadir excepciones y validaciones
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;

    public void ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.repository = productRepository;
        this.mapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = repository.findAll();
        return mapper.getListDtos(products);
    }

    @Override
    public List<ProductDto> findProductsByStatus(Status status) {
        List<Product> products = repository.findProductsByStatus(status);
        return mapper.getListDtos(products);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        return mapper.entityToDTO(product);

    }

    @Override
    public ProductDto getProductByItemCode(String itemCode) {
        Product product = repository.getProductByItemCode(itemCode);
        return mapper.entityToDTO(product);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = mapper.dtoToEntity(productDto);
        repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editProduct(String itemCode, ProductDto productDto) {
        Product productExists = repository.getProductByItemCode(itemCode);
        if (productExists == null) {
            new NoSuchElementException("No product with this ItemCode");
        }
        productExists.setDescription(productDto.getDescription());
        productExists.setPrice(productDto.getPrice());
        productExists.setStatus(productDto.getStatus());
        for (Supplier supplier : productDto.getSuppliers()) {
            productExists.addSupplier(supplier);
        }
        for (PriceReduction priceReduction : productDto.getPricesReductions()) {
            productExists.addSupplier(priceReduction);
        }
        repository.save(productExists);
    }

    @Override
    public void desactiveProduct(String itemCode, UserDto user, String description) {
        //TODO desactivar, añadir campo descripcion y user
    }


}
