package org.dinesh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Table;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "cart_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_details_id")
    private long cartDetailsId  ;
    @Column(unique = true)
    private long userId;

    @OneToMany(mappedBy = "cartDetails",fetch = FetchType.EAGER) //  mapped by should specify the object name you mention the other class not this entity name
    private List<CartItems> cartItems ;

    @Column(columnDefinition = "timestamp")
    private Timestamp createdTime ;
    @Column(columnDefinition = "timestamp")
    private Timestamp updateUser ;
}

