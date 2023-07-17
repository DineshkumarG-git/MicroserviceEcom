package org.dinesh.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Entity(name = "payment_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId ;
    private BigDecimal amount ;
    private String status;
    private  String paymentMethod ;
    private Timestamp payementTime ;
    private Timestamp modifiedTime ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id", referencedColumnName = "order_id")
    private  OrderDetails orderDetails ;
}
