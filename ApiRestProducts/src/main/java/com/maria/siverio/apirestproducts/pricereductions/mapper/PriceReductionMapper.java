package com.maria.siverio.apirestproducts.pricereductions.mapper;

import com.maria.siverio.apirestproducts.pricereductions.PriceReduction;
import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceReductionMapper {

    PriceReduction dtoToEntity(PriceReductionDto priceReductionDto);
    PriceReductionDto entityToDto(PriceReduction priceReduction);
    List<PriceReduction> getListEntities(List<PriceReductionDto> priceReductionDtoList);
    List<PriceReductionDto> getListDtos(List<PriceReduction> priceReductionList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PriceReductionDto priceReductionDto, @MappingTarget PriceReduction priceReduction);
}
