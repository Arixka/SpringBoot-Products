package com.maria.siverio.apirestproducts.products.mappers;

import com.maria.siverio.apirestproducts.products.dtos.ProductDto;
import com.maria.siverio.apirestproducts.products.models.Product;
import com.maria.siverio.apirestproducts.products.services.impl.ProductService;
import org.mapstruct.*;

import java.text.ParseException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {



    @Mapping(target="createdAt", source="productDto.createdAt",
            dateFormat="dd-MM-yyyy HH:mm:ss")
    Product dtoToEntity(ProductDto productDto);//TODO error al parsear cualquier fecha

    @Mapping(target="createdAt", source = "product.createdAt",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    ProductDto entityToDTO(Product product);
    //decirle a mapstruct q viene un string

    List<ProductDto> getListDtos(List<Product> productList);
    List<Product> getListEntities(List<ProductDto> productDtoList); //TODO la usamos?

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductDto productDto, @MappingTarget Product product) throws ParseException;
}
