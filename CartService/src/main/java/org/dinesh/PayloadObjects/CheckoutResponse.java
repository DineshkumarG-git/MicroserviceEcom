package org.dinesh.PayloadObjects;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {

    private long userId ;
    private List<String> productSkuCode ;

    private List<Long> cartItemIds ;

}
