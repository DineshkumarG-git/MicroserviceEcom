package org.dinesh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dinesh.Repository.OrderDetailsRepository;
import org.dinesh.dto.CartDetailsDto;
import org.dinesh.dto.CartItemsDto;
import org.dinesh.dto.PlaceOrderDto;
import org.dinesh.model.OrderDetails;
import org.dinesh.model.OrderItems;
import org.dinesh.model.PaymentDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    public  SessionFactory sessionFactory;

    @Autowired
   public  OrderDetailsRepository orderDetailsRepository;

    private Session getSession()
    {
        try {
            return sessionFactory.openSession();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return  null ;
    }
     public  String printHello()
     {
         var session = getSession();
         var criteria  =  session.getCriteriaBuilder().createQuery(OrderDetails.class);
          Root<OrderDetails> root =  criteria.from(OrderDetails.class);
         Join<OrderDetails,OrderItems> join1 = root.join("paymentDetails"); // should use the varible name that used to map in parent table
          criteria.select(root);
         var count =  session.createQuery(criteria).getResultList().stream().count();
         return  String.valueOf(count);

       //  return  "hello";
     }

     public List<OrderDetails> getOrderedDetails(int limit , int offset)
     {
         Session session = null;
         try {
            session =  getSession();
             var criteriaBuilder  =  session.getCriteriaBuilder();
             var criteria = criteriaBuilder.createQuery(OrderDetails.class);
             var root =  criteria.from(OrderDetails.class);
             Join<OrderDetails, PaymentDetails> join1 = root.join("paymentDetails"); // should use the varible name that used to map in parent table
             Join<OrderDetails, OrderItems> join2 = root.join("orderItems");
             var order1=   criteriaBuilder.asc(root.get("orderTime"));
             var order2 = criteriaBuilder.asc(join1.get("skuCode"));
             criteria.orderBy(order1,order2);
             criteria.select(root);
             var count =  session.createQuery(criteria).setMaxResults(limit).setFirstResult(offset).getResultList().stream().count();


         }
         catch (Exception e)
         {

         }
         finally {
             if(session!=null && session.isOpen())
                 session.close();
         }

         return null ;
     }
     public List<OrderDetails> getCancelledOrders(int limit , int offset)
     {
         Session session = null ;
         try {
             session = getSession();
             var criteriaBuilder = session.getCriteriaBuilder();
             var criteria = criteriaBuilder.createQuery(OrderDetails.class);
             var root =   criteria.from(OrderDetails.class);
             Join<OrderDetails,PaymentDetails> join1= root.join("paymentDetails");
             Join<OrderDetails,OrderItems > join2 = root.join("orderItems");
             Predicate condition = criteriaBuilder.equal(root.get("isCancelled"), true);
             var order1=   criteriaBuilder.asc(root.get("orderTime"));
             var order2 = criteriaBuilder.asc(join1.get("skuCode"));
             criteria.where(condition);
             criteria.orderBy(order1,order2);
             return    session.createQuery(criteria).setMaxResults(limit).setFirstResult(offset).getResultList();




         }catch (Exception e)
         {

         }
         finally {
             if(session!=null && session.isOpen())
                 session.close();
         }
         return null;
     }

     public List<OrderDetails> getDeliverdOrders(int limit , int offset )
     {
         Session session = null;
         try {
             session = getSession();

             var criteriaBuilder = session.getCriteriaBuilder();
             var criteria = criteriaBuilder.createQuery(OrderDetails.class);
             var root = criteria.from(OrderDetails.class);
             Join<OrderDetails, PaymentDetails> join1 = root.join("paymentDetails");
             Join<OrderDetails, OrderItems> join2 = root.join("orderItems");
             Predicate condition = criteriaBuilder.equal(root.get("isDelivered"), true);
             var order1 = criteriaBuilder.asc(root.get("orderTime"));
             var order2 = criteriaBuilder.asc(join1.get("skuCode"));
             criteria.where(condition);
             criteria.orderBy(order1, order2);
             return session.createQuery(criteria).setMaxResults(limit).setFirstResult(offset).getResultList();
         }
         catch (Exception e)
         {

         }
         finally {
             if(session!=null && session.isOpen())
                 session.close();
         }

         return null ;
     }
     public List<OrderDetails> getUnDelieverd(int limit ,int offset)
     {

         Session session = null;
         try {
             session = getSession();

             var criteriaBuilder = session.getCriteriaBuilder();
             var criteria = criteriaBuilder.createQuery(OrderDetails.class);
             var root = criteria.from(OrderDetails.class);
             Join<OrderDetails, PaymentDetails> join1 = root.join("paymentDetails");
             Join<OrderDetails, OrderItems> join2 = root.join("orderItems");
             Predicate condition = criteriaBuilder.equal(root.get("isDelivered"), false);
             Predicate condition2  = criteriaBuilder.equal(root.get("isCanceled"),false);
             var finalCondition = criteriaBuilder.and(condition2,condition) ;
             var order1 = criteriaBuilder.asc(root.get("orderTime"));
             var order2 = criteriaBuilder.asc(join1.get("skuCode"));
             criteria.where(finalCondition);
             criteria.orderBy(order1, order2);
             return session.createQuery(criteria).setMaxResults(limit).setFirstResult(offset).getResultList();
         }
         catch (Exception e)
         {

         }
         finally {
             if(session!=null && session.isOpen())
                 session.close();
         }
         return null ;
     }

     public  CartDetailsDto getOrdersFromCartService(long userId)
     {
         WebClient webClient = WebClient.create();


         String apiUrl = "http://cart-service/api/cart/checkOutResponse";
         UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                 .queryParam("userId", userId);
         URI uri = builder.build().toUri();
         Mono<String> responseMono = webClient.get().uri(uri).retrieve().bodyToMono(String.class);


         CartDetailsDto result = responseMono.flatMap(response -> {
             try {
                 ObjectMapper objectMapper = new ObjectMapper();
                 return Mono.just(objectMapper.readValue(response, CartDetailsDto.class));
             } catch (JsonProcessingException e) {
                 return Mono.error(e);
             }
         }).block();
         return result ;
     }

     public Boolean palceOrder(PlaceOrderDto placeOrderDto)
     {
         try{
         var cartDetails = getOrdersFromCartService(placeOrderDto.getUserId());
         var payementDetails  =  PaymentDetails.builder().amount(placeOrderDto.getAmount()).paymentMethod(placeOrderDto.getPaymentMethod())
                 .status(placeOrderDto.getStatus()).payementTime(Timestamp.valueOf(LocalDateTime.now())).modifiedTime(Timestamp.valueOf(LocalDateTime.now())).build();
          List<OrderItems>orderItems = cartDetails.getCartItems().stream().map(x-> mapToOrderItemsFromCartItemsDto(x) ).toList();
          OrderDetails orderDetails = OrderDetails.builder().orderTime(Timestamp.valueOf(LocalDateTime.now())).state(placeOrderDto.getState()).address(placeOrderDto.getAddress())
                  .country(placeOrderDto.getCountry()).updatedTime(Timestamp.valueOf(LocalDateTime.now())).street(placeOrderDto.getStreet()).userId(placeOrderDto.getUserId())
                  .paymentDetails(payementDetails).orderItems(orderItems).build();
             orderDetailsRepository.save(orderDetails);

         return  true ;
         }catch (Exception e)
         {
             log.error(e.getMessage());
         }
         return  false;
     }
     public  OrderItems mapToOrderItemsFromCartItemsDto(CartItemsDto cartItemsDto)
     {
         var orderItems =  OrderItems.builder().price(cartItemsDto.getPrice()).brand(cartItemsDto.getProduct().getBrand()).Category(cartItemsDto.getProduct().getCategory())
                 .productName(cartItemsDto.getProduct().getProductName()).skuCode(cartItemsDto.getProduct().getSkuCode()).description(cartItemsDto.getProduct().getDescription())
                 .discount(cartItemsDto.getProduct().getDiscount()).specification(cartItemsDto.getProduct().getSpecification()).Quantity(cartItemsDto.getQuantiy()).build();
         return  orderItems ;
     }








}
