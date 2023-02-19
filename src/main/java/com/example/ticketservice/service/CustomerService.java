package com.example.ticketservice.service;

import com.example.ticketservice.entity.Customer;
import com.example.ticketservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(dummyCustomer());
    }

    private Customer dummyCustomer() {
        return new Customer(101L, "dummy customer", "Pune", "1234567890");
    }
}
