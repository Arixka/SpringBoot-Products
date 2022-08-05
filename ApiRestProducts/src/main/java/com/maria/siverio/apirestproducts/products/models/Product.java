package com.maria.siverio.apirestproducts.products.models;

import com.maria.siverio.apirestproducts.products.enums.Status;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "item_code"))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @Column(name = "id_product")
    private Long idProduct;


    @Column(name = "item_code", nullable = false)
    private String itemCode;
    @Column(nullable = false)
    private String description;
    private Double price;

    //enum
    @Column(name = "status", nullable = false, length = 8)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreatedDate
    private LocalDateTime createdAt; //TODO localDateTIME y quitamos el timestamp cambialo

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "suppliers_products",
            joinColumns = @JoinColumn(name = "id_product", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_supplier", nullable = false)
    )
    private List<Supplier> suppliers;

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prices_reductions_products",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_price_reduction")}
    )
    private List<PriceReduction> pricesReductions;

    public void addSupplier(PriceReduction priceReduction) {
        pricesReductions.add(priceReduction);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_creator", referencedColumnName = "id_user")
    private User creatorUser;

}
