package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerRepository.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());

    }

    @PostMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }
}
