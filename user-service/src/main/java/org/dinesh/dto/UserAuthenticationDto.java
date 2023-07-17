package org.dinesh.dto;

import lombok.*;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDto {
    private String emailId;
    private String passWord;
}
