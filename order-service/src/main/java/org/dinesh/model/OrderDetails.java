package org.dinesh.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId ;

    private long userId ;
    private Timestamp orderTime ;
    private Timestamp delieveredTime ;

    private Boolean isCancelled ;
    private Boolean isDelivered ;
    private  String street ;
    private String country ;
    private String address;
    private String  state;
    private Timestamp updatedTime ;

    @OneToOne(mappedBy = "orderDetails",fetch = FetchType.EAGER)
    private PaymentDetails paymentDetails ;

    @OneToMany(mappedBy = "orderDetails",fetch = FetchType.EAGER)
    private List<OrderItems> orderItems ;
}
