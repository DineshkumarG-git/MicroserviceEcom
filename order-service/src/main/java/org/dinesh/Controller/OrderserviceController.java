package org.dinesh.Controller;

import lombok.RequiredArgsConstructor;
import org.dinesh.model.OrderDetails;
import org.dinesh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")

public class OrderserviceController {

    @Autowired
    public  OrderService orderService;
    @GetMapping("/test")
    public String getORderHistory()
    {
          return  orderService.printHello();
    }

}
