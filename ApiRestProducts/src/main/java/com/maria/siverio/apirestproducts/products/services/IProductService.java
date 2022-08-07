package com.maria.siverio.apirestproducts.products.services;


import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;

import java.util.List;

public interface IProductService {

     List<ProductDto> findAll();
     List<ProductDto> findProductsByStatus(Status status);
     ProductDto getProductById(Long id);
     ProductDto getProductByItemCode(String itemCode);
     void createProduct(ProductDto productDto);
     void deleteProduct(Long id);
     void editProduct(String itemCode, ProductDto productDto);

     void desactiveProduct(String itemCode, UserDto user, String description);


}
