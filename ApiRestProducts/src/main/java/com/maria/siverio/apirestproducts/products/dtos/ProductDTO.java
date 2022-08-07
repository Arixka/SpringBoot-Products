package com.maria.siverio.apirestproducts.products.dtos;

import com.maria.siverio.apirestproducts.products.enums.Status;
import com.maria.siverio.apirestproducts.products.models.PriceReduction;
import com.maria.siverio.apirestproducts.products.models.Supplier;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotEmpty(message = "ItemCode may not be null")
    private String itemCode;
    @NotEmpty(message = "Description may not be null")
    private String description;

    private Double price;
    private String createdAt;
    private UserDto creatorUser;
    private Status status;
    private Set<Supplier> suppliers = new HashSet();
    private Set<PriceReduction> pricesReductions = new HashSet();


}
