package com.maria.siverio.apirestproducts.products.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    /**
     * not empty no deja que sea nulo, y tambien controla que no este vacio, la anotacion notNull permite que
     * el campo este vacio
     */

    @NotEmpty(message = "ItemCode may not be null")
    private String itemCode;
    @NotEmpty(message = "Description may not be null")
    private String description;
//    private Double price;
//    private LocalDate createtAt;

}
