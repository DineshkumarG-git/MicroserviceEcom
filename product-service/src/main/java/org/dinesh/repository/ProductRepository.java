package org.dinesh.repository;

import org.dinesh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value="SELECT p FROM Product p ORDER BY p.id LIMIT :limit OFFSET :offset")
    public List<Product> getRandom(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value="SELECT p FROM Product p WHERE p.category = :category ORDER BY p.id LIMIT :limit OFFSET :offset")
    public List<Product> getByCategory(@Param("category") String category, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value="SELECT p FROM Product p WHERE p.name ILIKE %:name% ORDER BY p.name LIMIT :limit OFFSET :offset")
    public List<Product> getByLikeName(@Param("name") String name, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value="SELECT p FROM Product p WHERE p.name = :name ORDER BY p.name LIMIT :limit OFFSET :offset")
    public List<Product> getProductByName(@Param("name") String name, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value="SELECT p FROM Product p WHERE p.name = :name AND p.category = :category ORDER BY p.name LIMIT :limit OFFSET :offset")
    public List<Product> getProductbyNameWithCategory(@Param("name") String name, @Param("category") String category, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value="SELECT p FROM Product p WHERE p.name ILIKE %:name% AND p.category = :category ORDER BY p.name LIMIT :limit OFFSET :offset")
    public List<Product> getProductbyLikeNameWithCategory(@Param("name") String name, @Param("category") String category, @Param("limit") int limit, @Param("offset") int offset);

}
