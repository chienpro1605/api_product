package com.example.apiproduct.controller;

import com.example.apiproduct.model.Product;
import com.example.apiproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

   @GetMapping()
    public ResponseEntity<Iterable<Product>> findAll() {
       return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
   }

   @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
   }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editById(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        product.setId(productOptional.get().getId());
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if(!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
