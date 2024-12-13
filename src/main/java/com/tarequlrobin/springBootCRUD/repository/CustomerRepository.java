package com.tarequlrobin.springBootCRUD.repository;

import com.tarequlrobin.springBootCRUD.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
