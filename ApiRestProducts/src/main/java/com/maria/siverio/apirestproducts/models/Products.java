package com.maria.siverio.apirestproducts.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "item_code"))
public class Products {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "item_code")
    private String itemCode;
    private String description;
    private Double price;
    private String state;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JoinTable(
            name = "suppliers_products",
            joinColumns = @JoinColumn(name = "id_product", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_supplier", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Supplier> suppliers;

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }


    @ManyToMany
    @JoinTable(
            name = "prices_reductions_products",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_price_reduction")}
    )
    private List<PriceReduction>  pricesReductions;

    public void addSupplier(PriceReduction priceReduction) {
        pricesReductions.add(priceReduction);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_creator", referencedColumnName = "id_user")
    private User creatorUser;

}
