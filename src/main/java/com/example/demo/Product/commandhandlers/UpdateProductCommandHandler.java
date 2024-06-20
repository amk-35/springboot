package com.example.demo.Product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, Product> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<Product> execute(UpdateProductCommand command) {
        Product product=command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
