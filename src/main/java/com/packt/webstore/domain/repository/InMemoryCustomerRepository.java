package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamil
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private List<Customer> listOfCustomers = new ArrayList<>();

    public InMemoryCustomerRepository() {

        Customer michalHajzer = new Customer("P12", "Michał Hajzer", "Wysoka 17", 3);
        Customer karolinaWasala = new Customer("P32", "Karolina Wąsala", "Krótka 1", 5);
        Customer kamilTos = new Customer("P42", "Kamil Toś", "Oboźna 29/12", 2);

        listOfCustomers.add(michalHajzer);
        listOfCustomers.add(kamilTos);
        listOfCustomers.add(karolinaWasala);
    }

    public List<Customer> getAllCustomers() {
        return listOfCustomers;
    }
}
