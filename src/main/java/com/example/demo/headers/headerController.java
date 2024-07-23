package com.example.demo.headers;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductController;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class headerController {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/header")
    public String getRegionalRequest(@RequestHeader(required = false, defaultValue = "CA") String region) {
        if (region.equals("US")) {
            return "US";
        }
        if (region.equals("CA")) {
            return "CA";
        }
        return "Country not supported :(";
    }

    @GetMapping(value = "/header",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> header(){
        Product product = productRepository.findById(3).get();
        return ResponseEntity.ok(product);
    }
}
