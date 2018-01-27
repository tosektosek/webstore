package com.packt.webstore.service;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Kamil
 */
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void test() {
        List<Customer> customers = new ArrayList<>();
        when(customerRepository.getAllCustomers()).thenReturn(customers);
        assertEquals(customers, customerService.getAllCustomers());

        verify(customerRepository, times(1)).getAllCustomers();

    }
}
