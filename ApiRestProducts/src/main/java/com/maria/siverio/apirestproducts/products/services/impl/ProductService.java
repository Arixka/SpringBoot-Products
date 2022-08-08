package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.mappers.ProductMapper;
import com.maria.siverio.apirestproducts.products.models.PriceReduction;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.models.Supplier;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import com.maria.siverio.apirestproducts.users.User;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ProductService implements IProductService {
    // TODO añadir excepciones y validaciones

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductMapper mapper;

    public void ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.mapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return mapper.getListDtos(products);
    }

    @Override
    public List<ProductDto> findProductsByStatus(Status status) {
        List<Product> products = productRepository.findProductsByStatus(status);
        return mapper.getListDtos(products);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return mapper.entityToDTO(product);

    }

    @Override
    public ProductDto getProductByItemCode(String itemCode) {
        Product product = productRepository.getProductByItemCode(itemCode);
        return mapper.entityToDTO(product);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = mapper.dtoToEntity(productDto);
        product.setStatus(Status.ACTIVE); //default active
        //buscar al creador por nombre y setear el id al creador
        User creator = userRepository.findUsersByUsername(product.getCreatorUser().getUsername());
        product.setCreatorUser(creator);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void editProduct(String itemCode, ProductDto productDto) {
        Product productExists = productRepository.getProductByItemCode(itemCode);
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
        productRepository.save(productExists);
    }

    @Override
    public void desactiveProduct(String itemCode, UserDto user, String description) {
        //TODO desactivar, añadir campo descripcion y user
    }


}
