package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import com.maria.siverio.apirestproducts.pricereductions.mapper.PriceReductionMapper;
import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.mappers.ProductMapper;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import com.maria.siverio.apirestproducts.suppliers.dtos.SupplierDto;
import com.maria.siverio.apirestproducts.suppliers.mapper.SupplierMapper;
import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import com.maria.siverio.apirestproducts.suppliers.repositories.SupplierRepository;
import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductService implements IProductService {
    // TODO añadir excepciones y validaciones

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PriceReductionMapper priceMapper;
    @Autowired
    private SupplierMapper supplierMapper;

    public void ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll() {

        List<Product> products = null;
        try {
            products = productRepository.findAll();
            return productMapper.getListDtos(products);
        } catch (Exception e) {
            log.error("Error when searching for products " + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ProductDto> findProductsByStatus(Status status) {
        List<Product> products = null;
        try {
            products = productRepository.findProductsByStatus(status);
            return productMapper.getListDtos(products);
        } catch (Exception e) {
            log.error("Error when searching for products by Status " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        product.setStatus(Status.ACTIVE); //default active
        User creator = null;
        try {
            creator = userRepository.findUsersByUsername(product.getCreatorUser().getUsername());
            product.setCreatedAt(LocalDateTime.now());
            product.setCreatorUser(creator);
            return productMapper.entityToDTO(productRepository.save(product));
        } catch (Exception e) {
            log.error("Error when creating a products " + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductDto editProduct(ProductDto productDto) {
        Product productExists = null;
        try {
            productExists = productRepository.getProductByItemCode(productDto.getItemCode());

            if (productExists.getStatus() == Status.ACTIVE) {
                productExists.setDescription(productDto.getDescription());
                productExists.setPrice(productDto.getPrice());

                Set<SupplierDto> supplierList = productDto.getSuppliers();

                //usar filter con callback
                for (SupplierDto supplierDto : supplierList) {
                    Supplier supplier = supplierRepository.getSupplierByName(supplierDto.getName());
                    if (!productExists.getSuppliers().contains(supplier)) {
                        //TODO aqui mal
                        productExists.addSupplier(supplierMapper.dtoToEntity(supplierDto));
                    }
                }

                for (PriceReductionDto priceReductionDto : productDto.getPricesReductions()) {
                    productExists.addSupplier(priceMapper.dtoToEntity(priceReductionDto));
                }
            }
            Product editProduct = productRepository.save(productExists);
            return productMapper.entityToDTO(editProduct);

        } catch (Exception e) {
            log.error("Error when editing a products " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto desactiveProduct(String itemCode, UserDto user, String description) {
        //TODO desactivar, añadir campo descripcion y user
        return null;
    }


}
