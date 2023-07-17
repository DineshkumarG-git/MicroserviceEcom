package org.dinesh.repository;


import org.dinesh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findBySkuCode(String skuCode);
}
