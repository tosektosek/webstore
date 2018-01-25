package com.packt.webstore.service;

/**
 * @author Kamil
 */
public interface OrderService {
    void processOrder(String productId, int count);
}
