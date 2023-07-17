package org.dinesh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;


@Entity(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class OrderItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  orderItemsId ;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id" ,referencedColumnName = "order_id")
    private OrderDetails orderDetails ;

    private String skuCode;
    private String productName;
    private BigDecimal price;
    private String brand;
    private String Category;
    private String description;
    private String specification;
    private int discount ;
    private int Quantity ;
}
