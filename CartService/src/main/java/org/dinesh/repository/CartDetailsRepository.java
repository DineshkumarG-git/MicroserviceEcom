package org.dinesh.repository;

import org.dinesh.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {


//    public List<CartDetails> findByUserId(long userId, Pageable pageable);

    public CartDetails findByUserId(long userId);

    @Query(value = "Select u from cart_details u where u.userId = :userId")
    public List<CartDetails> getUserDetailsIdByUserId(@Param("userId") long userId);

}




