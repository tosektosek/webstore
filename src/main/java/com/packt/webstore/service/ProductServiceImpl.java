package com.packt.webstore.service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Kamil
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByFilter(filterParams);
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        return productRepository.getProductsByManufacturer(manufacturer);
    }

    @Override
    public List<Product> getProductsByPriceFilter(Map<String, String> price) {
        return productRepository.getProductsByPriceFilter(price);
    }

    @Override
    public Set<Product> getProductsByFilter(Set<Product> toFiltr, List<Product> productsByCategory, List<Product> productsByPrice, List<Product> productsByManufacturer) {
        Set<Product> result = new HashSet<>();
        toFiltr.stream().filter(product -> productsByCategory.contains(product))
                .filter(product -> productsByManufacturer.contains(product))
                .filter(product -> productsByPrice.contains(product))
                .forEach(result::add);
        return result;
    }
    public Set<Product> getProductsByFilter(Set<Product> toFiltr, List<Product>... products){
        Set<Product> result = new HashSet<>();
        int counter = products.length - 1;

        toFiltr.stream().filter(products[counter]::contains)
                .filter(products[counter - 1]::contains)
                .filter(products[counter - 2]::contains)
                .forEach(result::add);
        return result;
    }

}
