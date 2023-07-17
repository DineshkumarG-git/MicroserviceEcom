package org.dinesh.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String phoneNo;
    private String passWord;
    private String state;

    private  String country ;
    private String street ;
    private String address;
    private String userName;
}
