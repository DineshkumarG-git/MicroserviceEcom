package org.dinesh.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.math.BigDecimal;

@Entity(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;


    private int quantiy ;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_sku_code", referencedColumnName = "sku_code")
    private  Product product;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_details_id",referencedColumnName = "cart_details_id")
    private CartDetails cartDetails ;


    private BigDecimal price ;
}
