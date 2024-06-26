package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.price < :maxPrice")
    List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("select new com.example.demo.Product.Model.ProductDTO (p.name,p.description,p.price) from Product p")
    List<ProductDTO> getAllProductDTOS();

    //CustomQuery
    @Query("select p from Product p where p.description like %:desc%")
    List<Product> findProductsWithDescriptionLike(@Param("desc") String desc);

    //Spring Data JPA to auto generate
    List<Product> findByDescriptionContaining(String description);
}
