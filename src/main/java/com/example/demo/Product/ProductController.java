package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.demo.Product.commandhandlers.UpdateProductCommandHandler;
import com.example.demo.Product.queryhandlers.GetAllProductsQueryHandler;
import com.example.demo.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired
    private GetProductQueryHandler getProductQueryHandler;
    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;
    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        return getProductQueryHandler.execute(id);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
      return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        UpdateProductCommand command=new UpdateProductCommand(id,product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
