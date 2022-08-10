package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import com.maria.siverio.apirestproducts.pricereductions.mapper.PriceReductionMapper;
import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.StatusEnum;
import com.maria.siverio.apirestproducts.products.mappers.ProductMapper;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.repositories.ProductRepository;
import com.maria.siverio.apirestproducts.products.services.IProductService;
import com.maria.siverio.apirestproducts.suppliers.dtos.SupplierDto;
import com.maria.siverio.apirestproducts.suppliers.mapper.SupplierMapper;
import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import com.maria.siverio.apirestproducts.suppliers.repositories.SupplierRepository;
import com.maria.siverio.apirestproducts.users.models.User;
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
    public List<ProductDto> findProductsByStatus(StatusEnum status) {
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
        product.setStatus(StatusEnum.ACTIVE);
        User creator = null;
        try {
            if(product.getCreatorUser()!=null){
            creator = userRepository.findUsersByUsername(product.getCreatorUser().getUsername());
                product.setCreatorUser(creator);

            }
            product.setCreatedAt(LocalDateTime.now());

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

            if (productExists.getStatus() == StatusEnum.ACTIVE) {
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
                    productExists.addReduction(priceMapper.dtoToEntity(priceReductionDto));
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
    public ProductDto desactiveProduct(String itemCode, String reason) {
        try {
            Product productExists = productRepository.getProductByItemCode(itemCode);
            productExists.setStatus(StatusEnum.DISCONTINUED);
            productExists.setReasonDeactivation(reason.trim());
            return productMapper.entityToDTO(productRepository.save(productExists));
        } catch (Exception e) {
            log.error("Error when disable a product " + e);
            throw new RuntimeException(e);
        }
    }


}
