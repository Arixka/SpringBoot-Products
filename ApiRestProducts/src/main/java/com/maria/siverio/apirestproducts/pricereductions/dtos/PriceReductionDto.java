package com.maria.siverio.apirestproducts.pricereductions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceReductionDto {

    private Double reducedPrice;
    private String startDate;
    private String endDate;
}
