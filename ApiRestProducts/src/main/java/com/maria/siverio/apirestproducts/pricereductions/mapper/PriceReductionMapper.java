package com.maria.siverio.apirestproducts.pricereductions.mapper;

import com.maria.siverio.apirestproducts.pricereductions.models.PriceReduction;
import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceReductionMapper {

    @Mapping(target="startDate", source="priceReductionDto.startDate",
            dateFormat="dd-MM-yyyy")
    @Mapping(target="endDate", source="priceReductionDto.endDate",
            dateFormat="dd-MM-yyyy")
    PriceReduction dtoToEntity(PriceReductionDto priceReductionDto);

    @Mapping(target="startDate", source="priceReduction.startDate",
            dateFormat="dd-MM-yyyy")
    @Mapping(target="endDate", source="priceReduction.endDate",
            dateFormat="dd-MM-yyyy")
    PriceReductionDto entityToDto(PriceReduction priceReduction);
    List<PriceReduction> getListEntities(List<PriceReductionDto> priceReductionDtoList);
    List<PriceReductionDto> getListDtos(List<PriceReduction> priceReductionList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PriceReductionDto priceReductionDto, @MappingTarget PriceReduction priceReduction);
}
