package com.packt.webstore.service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kamil
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void processOrder(String productId, int count) {
        Product productById = productRepository.getProductById(productId);
        if(productById.getUnitsInStock() < count) {
            throw new IllegalArgumentException("Zbyt Mało towaru. Obecna liczba sztuk w magazynie: " + productById.getUnitsInStock());
        }
        productById.setUnitsInStock(productById.getUnitsInStock() - count);
    }
}
