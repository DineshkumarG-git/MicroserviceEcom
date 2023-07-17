package org.dinesh.dto;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsDto {

    private long cartDetailsId  ;

    private long userId;



    private List<CartItemsDto> cartItems ;


    private Timestamp createdTime ;

    private Timestamp updateUser ;
}
