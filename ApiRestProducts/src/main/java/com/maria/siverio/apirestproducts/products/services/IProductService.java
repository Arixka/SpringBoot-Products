package com.maria.siverio.apirestproducts.products.services;


import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;

import java.util.List;

public interface IProductService {

     List<ProductDto> findAll();
     List<ProductDto> findProductsByStatus(Status status);
     ProductDto createProduct(ProductDto productDto);
     ProductDto editProduct(ProductDto productDto);
     ProductDto desactiveProduct(String itemCode, UserDto user, String description);


}
