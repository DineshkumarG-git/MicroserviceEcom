package org.dinesh.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {

    private long userId;
    private  String street ;
    private String country ;
    private String address;
    private String  state;
    private BigDecimal amount ;
    private String status;
    private  String paymentMethod ;
    private Timestamp payementTime ;
}
