package org.dinesh.PayloadObjects;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutPayLoad {

    private long userId;
    private List<Long> cartItemsId ;




}
