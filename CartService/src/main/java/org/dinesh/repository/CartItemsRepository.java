package org.dinesh.repository;

import org.dinesh.model.CartItems;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface CartItemsRepository extends JpaRepository<CartItems,Long> {

    @Query(value = "select p from cart_items p where p.cartDetails = :cartDetailsId and p.product = :productSkuCode")
    public List<CartItems> findByCartDetailsIdAndSkuCode(@Param("cartDetailsId") long cartDetailsId , @Param("productSkuCode") String productSkuCode);

    @Query(value = "select p from cart_items p where p.cartDetails = :cartDetailsId order by p.id limit :limit  offset :offset ")
    public List<CartItems> findbyCartDetailsIdUsingLimit(@Param("cartDetailsId") long cartDetailsId , @Param("limit") int limit, @Param("offset") int offset);
    public List<CartItems> findByCartDetails(long cartDetailsId);

    public  List<CartItems> findByCartDetails(long cartDetailsid , Pageable pageable);
}
