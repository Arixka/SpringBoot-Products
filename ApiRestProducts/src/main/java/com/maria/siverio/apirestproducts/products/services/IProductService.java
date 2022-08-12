package com.maria.siverio.apirestproducts.products.services;


import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductRequestDto;
import com.maria.siverio.apirestproducts.products.dtos.ProductResponseDto;
import com.maria.siverio.apirestproducts.products.enums.StatusEnum;

import java.util.List;

public interface IProductService {

     List<ProductResponseDto> findAll();
     List<ProductResponseDto> findProductsByStatus(StatusEnum status);
     ProductResponseDto createProduct(ProductRequestDto productRequestDto);
     ProductResponseDto editProduct(ProductRequestDto productRequestDto);
     ProductResponseDto desactiveProduct(String itemCode, String reason);


}
