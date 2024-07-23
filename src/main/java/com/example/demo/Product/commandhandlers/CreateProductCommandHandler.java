package com.example.demo.Product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, Product> {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger= LoggerFactory.getLogger(CreateProductCommandHandler.class);

    @Override
    public ResponseEntity<Product> execute(Product product) {
        logger.info("Executing {} with {}", getClass(), product.toString());
        //business logic
        validateProduct(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    public void validateProduct(Product product){
        if(StringUtils.isBlank(product.getName())) {
            logger.error("product not valid exception(invalid name) was thrown with {}", product.toString());
            throw new ProductNotValidException("Product name cannot be empty");
        }

        if(StringUtils.isBlank(product.getDescription())) {
            logger.error("product not valid exception(invalid description) was thrown with {}", product.toString());
            throw new ProductNotValidException("Product description cannot be empty");
        }

        if(product.getPrice()<=0.0) {
            logger.error("product not valid exception(invalid price) was thrown with {}", product.toString());
            throw new ProductNotValidException("Product price cannot be negative");
        }

        if(product.getQuantity()<=0){
            logger.error("product not valid exception(invalid quantity) was thrown with {}", product.toString());
            throw new ProductNotValidException("Product quantity cannot be zero");
        }
    }
}
