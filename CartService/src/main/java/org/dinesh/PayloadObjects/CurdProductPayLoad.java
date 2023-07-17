package org.dinesh.PayloadObjects;


import lombok.*;
import org.dinesh.enumdefinition.Operation;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CurdProductPayLoad {

    private long userId ;

    private String productSkuCode;

    private Operation operation ;

    private int quantiy ;

}
