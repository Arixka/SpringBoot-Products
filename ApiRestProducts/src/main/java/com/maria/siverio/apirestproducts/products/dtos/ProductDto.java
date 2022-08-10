package com.maria.siverio.apirestproducts.products.dtos;

import com.maria.siverio.apirestproducts.pricereductions.dtos.PriceReductionDto;
import com.maria.siverio.apirestproducts.products.enums.StatusEnum;
import com.maria.siverio.apirestproducts.suppliers.dtos.SupplierDto;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull(message = "ItemCode may not be null")
    private String itemCode;
    @NotNull(message = "Description may not be null")
    private String description;

    private Double price;
    private String createdAt;
    private UserDto creatorUser;
    private StatusEnum status;
    private String reasonDeactivation;
    private Set<SupplierDto> suppliers = new HashSet(); //dto
    private Set<PriceReductionDto> pricesReductions = new HashSet();


}
