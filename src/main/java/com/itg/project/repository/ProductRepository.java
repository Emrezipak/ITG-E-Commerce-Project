package com.itg.project.repository;

import com.itg.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product,String> {

    boolean existsByProductCode(String productCode);

    Optional<Product> getProductByIdEqualsIgnoreCase(String id);

    Optional<Product> getProductByProductCodeEqualsIgnoreCase(String productCode);

    @Query("select a from Product a where a.productCode LIKE %:productCode%")
    Stream<Product> searchByProductCodeLike(@Param("productCode") String productCode);

}
