package org.dinesh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsDto {

    private long id ;


    private int quantiy ;



    private  ProductDto product;






    private CartDetailsDto cartDetails ;


    private BigDecimal price ;
}
