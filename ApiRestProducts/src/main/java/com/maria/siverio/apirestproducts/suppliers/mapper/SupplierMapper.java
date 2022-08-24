package com.maria.siverio.apirestproducts.suppliers.mapper;

import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import com.maria.siverio.apirestproducts.suppliers.dtos.SupplierDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier dtoToEntity(SupplierDto supplierDto);
    SupplierDto entitiToDto(Supplier supplier);
    List<Supplier> getListEntities(List<SupplierDto> supplierDtoList);
    List<SupplierDto> getListDtos(List<Supplier> supplierList);
}
