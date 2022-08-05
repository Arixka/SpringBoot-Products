package com.maria.siverio.apirestproducts.products.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String itemCode;
    private String description;
//    private Double price;
//    private LocalDate createtAt;


}
