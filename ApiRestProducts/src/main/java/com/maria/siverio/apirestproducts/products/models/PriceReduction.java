package com.maria.siverio.apirestproducts.products.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices_reductions")
public class PriceReduction {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "price_reduction_id_seq")
    @SequenceGenerator(name = "price_reduction_id_seq", sequenceName = "price_reduction_id_seq", allocationSize = 1)
    @Column(name = "id_price_reduction")
    private Long idPriceReduction;

    private Double reducedPrice;
    private Date startDate;
    private Date endDate;
}
