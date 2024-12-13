package com.tarequlrobin.springBootCRUD.service;

import com.tarequlrobin.springBootCRUD.model.Customer;
import com.tarequlrobin.springBootCRUD.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomer(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(Long id){
        return Optional.ofNullable(customerRepository.findById(id).orElse(null));
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomer(){
        customerRepository.deleteAll();
    }
}
