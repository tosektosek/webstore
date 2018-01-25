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

    List<Customer> listOfCustomers = new ArrayList<>();

    public InMemoryCustomerRepository() {

        Customer michal_hajzer = new Customer("P12", "Michał Hajzer", "Wysoka 17", 3);
        Customer karolina_wasala = new Customer("P32", "Karolina Wąsala", "Krótka 1", 5);
        Customer kamil_tos = new Customer("P42", "Kamil Toś", "Oboźna 29/12", 2);

        listOfCustomers.add(michal_hajzer);
        listOfCustomers.add(kamil_tos);
        listOfCustomers.add(karolina_wasala);
    }

    public List<Customer> getAllCustomers() {
        return listOfCustomers;
    }
}
