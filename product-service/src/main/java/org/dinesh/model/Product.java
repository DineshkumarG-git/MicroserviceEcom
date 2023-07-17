package org.dinesh.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,columnDefinition = "text")
    private String skudCode;
    private String productName;
    private BigDecimal price;
    private String brand;
    private String Category;
    private String description;
    private String specification;

    private int discount;
}
