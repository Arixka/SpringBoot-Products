package com.maria.siverio.apirestproducts.products.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotEmpty(message = "ItemCode may not be null")
    private String itemCode;
    @NotEmpty(message = "Description may not be null")
    private String description;
//    private Double price;
//    private LocalDate createtAt;


}
