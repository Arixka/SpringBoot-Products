package com.maria.siverio.apirestproducts.pricereductions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceReductionDto {

    private Double reducedPrice;
    private Date startDate;
    private Date endDate;
}
