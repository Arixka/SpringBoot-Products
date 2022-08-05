package com.maria.siverio.apirestproducts.products.services;


import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.models.Product;

import java.util.List;

public interface IProductService {

     List<ProductDto> findAll();
     List<ProductDto> findProductsByStatus(Status status);
     ProductDto getProductById(Long id);
     void saveProduct(ProductDto productDto);
     void deleteProduct(Long id);

     void updateProduct(ProductDto productDto, Long id);



}
