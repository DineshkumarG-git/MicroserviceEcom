package org.dinesh.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponseDto {
    private String skuCode;
    private String productName;
    private BigDecimal price;
    private String brand;
    private String Category;
    private String description;
    private String specification;

    private int discount;
}
