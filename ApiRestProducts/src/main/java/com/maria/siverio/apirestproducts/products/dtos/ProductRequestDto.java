package com.maria.siverio.apirestproducts.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotNull(message = "ItemCode may not be null")
    private String itemCode;
    @NotNull(message = "Description may not be null")
    private String description;

    private Double price;
    private String creatorUser;
    private String reasonDeactivation;
    private String supplierName;
    private String supplierCountry;
    private Double priceReduction;
    private String reductionStartDate;
    private String reductionEndDate;
}
