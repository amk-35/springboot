package com.example.demo.Product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, Product> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;
    @Override
    public ResponseEntity<Product> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }

        Product existingProduct = productOptional.get();
        Product updatedProduct = command.getProduct();

        // Update only the fields provided in the command
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getPrice() != 0) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getQuantity() != 0) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        // Repeat for other fields as needed
        createProductCommandHandler.validateProduct(existingProduct);
        productRepository.save(existingProduct);

        return ResponseEntity.ok(existingProduct);
    }

}
