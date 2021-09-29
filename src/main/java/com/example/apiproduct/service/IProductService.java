package com.example.apiproduct.service;

import com.example.apiproduct.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(long id);
    Product save (Product product);
    void delete(long id);
}
