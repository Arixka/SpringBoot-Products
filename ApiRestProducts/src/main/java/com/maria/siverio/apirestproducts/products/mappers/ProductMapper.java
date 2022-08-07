package com.maria.siverio.apirestproducts.products.mappers;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.models.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target="createdAt", source="productDto.createdAt",
            dateFormat="dd-MM-yyyy HH:mm:ss")
    Product dtoToEntity(ProductDto productDto);

    @Mapping(target="createdAt", source = "product.createdAt",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    ProductDto entityToDTO(Product product);

    List<ProductDto> getListDtos(List<Product> productList);
    List<Product> getListEntities(List<ProductDto> productDtoList); //TODO la usamos?

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductDto productDto, @MappingTarget Product product);
}
