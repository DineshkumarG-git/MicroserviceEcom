package org.dinesh.service;

import lombok.RequiredArgsConstructor;
import org.dinesh.PayloadObjects.CurdProductPayLoad;
import org.dinesh.model.CartDetails;
import org.dinesh.model.CartItems;
import org.dinesh.repository.CartDetailsRepository;
import org.dinesh.repository.CartItemsRepository;
import org.dinesh.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private CartDetailsRepository cartDetailsRepository ;
    private CartItemsRepository cartItemsRepository ;

    private ProductRepository productRepository ;
    public  List<CartItems> getCartItems(long userId ,int pageNumber,int pageSize)
    {
       var cartDetailsId = getUserDetailsId(userId);
        if(cartDetailsId!=0)
        {
            cartItemsRepository.findByCartDetails(cartDetailsId ,  PageRequest.of(pageNumber,pageSize));
        }


        return null;
    }

    public  boolean curdCart(CurdProductPayLoad curdProductPayLoad )
    {
        try {
            switch (curdProductPayLoad.getOperation()) {
                case ADDITEM:


                    break;
                case REMOVEITEM:
                    break;
                case UPDATEITEM:
                    break;
                case DELETEITEM:
                    break;
                default:
                    return false;


            }
        }
        catch (Exception e)
        {

        }
        return false ;

    }

    private boolean addItems( CurdProductPayLoad curdProductPayLoad)
    {
        try {
            var cartDetaisId = getUserDetailsId(curdProductPayLoad.getUserId());

            var productDetails = productRepository.findBySkuCode(curdProductPayLoad.getProductSkuCode());
            if (productDetails == null && productDetails.isEmpty())
                return false;
            var product = productDetails.get(0);
            var cartDetails = new CartDetails();
            cartDetails.setCartDetailsId(cartDetaisId); // it used to insert data into the cart items by the same id of the cartdetails

            var cartItem = CartItems.builder().cartDetails(cartDetails).price(product.getPrice()).product(product).build();
            cartItemsRepository.save(cartItem);
            return true;
        }
        catch(Exception e)
        {

        }
        return  false;


    }

    private boolean removeItems(CurdProductPayLoad curdProductPayLoad)
    {
        try{
          var cartDetailsId  = getUserDetailsId(curdProductPayLoad.getUserId());
          var cartItems = cartItemsRepository.findByCartDetailsIdAndSkuCode(cartDetailsId,curdProductPayLoad.getProductSkuCode());
          if(cartItems!=null && !cartItems.isEmpty())
          cartItemsRepository.delete(cartItems.get(0));
        }
        catch (Exception e)
        {

        }
        return false ;
    }





    private long  getUserDetailsId(long userId)
    {
            var cartDetails  = getUserCartDetails(userId);
            return  cartDetails !=null ? cartDetails.getCartDetailsId() : null;
    }

    private CartDetails getUserCartDetails(long userId)
    {
        try {
            var cartDetails = cartDetailsRepository.getUserDetailsIdByUserId(userId);
            if (cartDetails != null && cartDetails.isEmpty())
                return cartDetails.get(0);
        }
        catch (Exception e)
        {

        }
        return  null;
    }


    private boolean updateItem(CurdProductPayLoad curdProductPayLoad){
       try {
           if(curdProductPayLoad.getQuantiy()==0)
               return  removeItems(curdProductPayLoad);
           var cartDetailsId = getUserDetailsId(curdProductPayLoad.getUserId());
           var cartItems = cartItemsRepository.findByCartDetailsIdAndSkuCode(cartDetailsId, curdProductPayLoad.getProductSkuCode());
           if (cartItems != null && cartItems.isEmpty()) {
               var cartItem = cartItems.get(0);
               var product = productRepository.findBySkuCode(curdProductPayLoad.getProductSkuCode()).get(0);
               var price =  (product.getPrice().multiply(BigDecimal.valueOf(product.getDiscount()/100))).multiply(BigDecimal.valueOf(curdProductPayLoad.getQuantiy())) ;
               cartItem.setQuantiy(curdProductPayLoad.getQuantiy());
               cartItem.setPrice(price);
               cartItemsRepository.save(cartItem);

           }
       }
       catch (Exception e)
       {

       }
       return false ;
    }

    public CartDetails getCheckoutResponse(long userId) { // it is used by order service to get the cart details of the customer
        try {
             return  cartDetailsRepository.findByUserId(userId);

        } catch (Exception e) {
        }

        return null ;

    }

    public boolean checkOut(List<Long> cartItemsIds) // this is used to delete the cart items that purchased by order service
    {
        try{

                 var cartItems=  cartItemsIds.stream().map(x-> CartItems.builder().id(x).build()).toList();
                 cartItemsRepository.deleteAll(cartItems);

        }catch (Exception e)
        {

        }
             return  false ;
    }












}
