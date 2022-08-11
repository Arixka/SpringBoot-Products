package com.maria.siverio.apirestproducts.products.dtos;

import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import com.maria.siverio.apirestproducts.pricereductions.models.PriceReduction;
import com.maria.siverio.apirestproducts.suppliers.dtos.SupplierDto;
import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {


    private String itemCode;
    private String description;
    private Double price;
    private String creatorUser;
    private Set<SupplierDto> suppliers = new HashSet();
    public void addSupplier(SupplierDto supplier) {
        suppliers.add(supplier);
    }
    private Set<PriceReductionDto> pricesReductions = new HashSet();
    public void addReduction(PriceReductionDto priceReduction) {
        pricesReductions.add(priceReduction);
    }
}
