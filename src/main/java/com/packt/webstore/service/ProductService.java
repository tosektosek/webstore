package com.packt.webstore.service;

import com.packt.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    List<Product> getProductsByManufacturer(String manufacturer);
    List<Product> getProductsByPriceFilter(Map<String, String> price);
    Set<Product> getProductsByFilter(Set<Product> toFiltr, List<Product> productsByCategory
            , List<Product>productsByPrice,List<Product>productsByManufacturer);
}
