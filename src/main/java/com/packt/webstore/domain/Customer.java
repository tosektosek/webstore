package com.packt.webstore.domain;

import lombok.Data;

/**
 * @author Kamil
 */

@Data
public class Customer {

    private String customerId;
    private String name;
    private String address;
    private int noOfOrdersMade;

    public Customer(String customerId, String name, String address, int noOfOrdersMade) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.noOfOrdersMade = noOfOrdersMade;
    }
}
