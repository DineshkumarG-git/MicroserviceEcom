package org.dinesh.dto;
import  java.util.*;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private Long id;

    private String skuCode;
    private String productName;
    private BigDecimal price;
    private String brand;
    private String Category;
    private String description;
    private String specification;


    private List<CartItemsDto> cartItems ;

    private int discount;
}
