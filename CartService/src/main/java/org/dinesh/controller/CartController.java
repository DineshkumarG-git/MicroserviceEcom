package org.dinesh.controller;

import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.dinesh.PayloadObjects.CheckOutPayLoad;
import org.dinesh.PayloadObjects.CheckoutResponse;
import org.dinesh.PayloadObjects.CurdProductPayLoad;
import org.dinesh.model.CartDetails;
import org.dinesh.model.CartItems;
import org.dinesh.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private static CartService cartService ;
    @GetMapping("/getCartItems")
    public List<CartItems> getCardItems(@RequestParam("userId") long userId, @RequestParam("limit") int limit , @RequestParam("offset") int offset )
    {
            return cartService.getCartItems( userId,limit,offset);
    }

    @PostMapping("/curdcart")
    public boolean addCartItems(@RequestBody CurdProductPayLoad payload   )
    {
         return  cartService.curdCart(payload);
    }


    @GetMapping("/checkOutResponse")
    public CartDetails checkoutResponse(@RequestParam("userId") long userId) // used by order service get th
    {
          return  cartService.getCheckoutResponse(userId) ;
    }

    @GetMapping("/checkOut")
    public  boolean checkOut(@RequestBody List<Long> checkoutResponse) // it is used by order service to delete the purchased product from the cart
    {
          return  cartService.checkOut(checkoutResponse);
    }





}
