package com.maria.siverio.apirestproducts.products.services.impl;

import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import com.maria.siverio.apirestproducts.pricereductions.mapper.PriceReductionMapper;
import com.maria.siverio.apirestproducts.pricereductions.models.PriceReduction;
import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductRequestDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductResponseDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductService implements IProductService {

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
    public List<ProductResponseDto> findAll() {

        List<Product> products;
        try {
            products = productRepository.findAll();
            return productResponseDtos(products);

        } catch (Exception e) {
            log.error("Error when searching for products " + e);
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<ProductResponseDto> findProductsByStatus(StatusEnum status) {

        try {
            List<Product> products = productRepository.findProductsByStatus(status);
            return productResponseDtos(products);

        } catch (Exception e) {
            log.error("Error when searching for products by Status " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = requestDtoToEntity(productRequestDto);

        product.setStatus(StatusEnum.ACTIVE);
        User creator;
        try {
            if (product.getCreatorUser() != null) {

                creator = userRepository.findUsersByUsername(product.getCreatorUser().getUsername());
                product.setCreatorUser(creator);

            }
            product.setCreatedAt(LocalDateTime.now());

            return entityToResponseDto(product);

        } catch (Exception e) {
            log.error("Error when creating a products " + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductResponseDto editProduct(ProductRequestDto productRequestDto) {

        Product productExists;
        try {
            productExists = productRepository.getProductByItemCode(productRequestDto.getItemCode());
//TODO POR AQUI
            if (productExists.getStatus() == StatusEnum.ACTIVE) {
                productExists.setDescription(productRequestDto.getDescription());
                productExists.setPrice(productRequestDto.getPrice());

//                Set<SupplierDto> supplierList = new HashSet<>();
                Supplier supplier = new Supplier();
                supplier.setName(productRequestDto.getSupplierName());
                supplier.setCountry(productRequestDto.getSupplierCountry());

                Supplier supplierExists = supplierRepository.getSupplierByName(supplier.getName());
                if (!productExists.getSuppliers().contains(supplierExists)) {
                    //TODO REVISAR
                    productExists.addSupplier(supplier);
                }
                PriceReduction priceReduction = new PriceReduction();
                priceReduction.setReducedPrice(productRequestDto.getPrice());
                priceReduction.setStartDate(stringToLocalDate(productRequestDto.getReductionStartDate()));
                priceReduction.setEndDate((stringToLocalDate(productRequestDto.getReductionEndDate())));

                //TODO REVISAR Comprobar si existe?
                    productExists.addReduction(priceReduction);

            }
            //Tenemos que guardar una entidad y devolver un ProductResponseDto
            Product editProduct = productRepository.save(productExists);
            return entityToResponseDto(editProduct);

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

    protected ProductResponseDto entityToResponseDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDto productResponse = new ProductResponseDto();
        productResponse.setItemCode(product.getItemCode());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStatus(product.getStatus().name());//TODO revisar
        productResponse.setCreatorUser(product.getCreatorUser().getUsername());
        Set<PriceReduction> priceReductionList = product.getPricesReductions();
        if (priceReductionList != null) {
            for (PriceReduction priceReduction : priceReductionList) {
                productResponse.addReduction(priceMapper.entityToDto(priceReduction));
            }
        }
        Set<Supplier> suppliersList = product.getSuppliers();
        if (suppliersList != null) {
            for (Supplier supplier : suppliersList) {
                productResponse.addSupplier(supplierMapper.entitiToDto(supplier));
            }
        }
        return productResponse;
    }

    protected List<ProductResponseDto> productResponseDtos(List<Product> products) {
        List<ProductResponseDto> productsResponseDtos = new ArrayList<>();
        for (Product product : products) {
            productsResponseDtos.add(entityToResponseDto(product));
        }
        return productsResponseDtos;
    }

    protected Product requestDtoToEntity(ProductRequestDto productRequestDto) {
        if (productRequestDto == null) {
            return null;
        }
        Product product = new Product();

        product.setItemCode(productRequestDto.getItemCode());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        User userCreator = new User();
        userCreator.setUsername(productRequestDto.getCreatorUser());
        product.setCreatorUser(userCreator);

        PriceReduction reduction = new PriceReduction();
        reduction.setReducedPrice(productRequestDto.getPrice());
        reduction.setStartDate(stringToLocalDate(productRequestDto.getReductionStartDate()));
        reduction.setEndDate(stringToLocalDate(productRequestDto.getReductionEndDate()));
        product.addReduction(reduction);

        Supplier supplier = new Supplier();
        supplier.setName(productRequestDto.getSupplierName());
        supplier.setCountry(productRequestDto.getSupplierCountry());
        product.addSupplier(supplier);

        return product;
    }

    protected LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

}
