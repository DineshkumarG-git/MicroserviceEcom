package org.dinesh.model;


import jakarta.persistence.*;
import lombok.*;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Entity(name = "userDetails")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String email;
    private String userName;
    private String phoneNo;
    private String passWord;
    private String state;
    @Column(columnDefinition = "timestamp")
    private Timestamp createdTime;

    @Column(columnDefinition = "timestamp")
    private Timestamp updatedTime;
    private  String country ;
    private String street ;
    private String address;
}