package com.example.demo.Product.queryhandlers;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, Product> {
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<Product> execute(Integer id){
        Optional<Product> product= productRepository.findById(id);
        if(product.isEmpty()){
            throw new IllegalStateException(new RuntimeException("product not found"));
        }
        return ResponseEntity.ok(product.get());
    }
}
