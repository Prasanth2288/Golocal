package com.login.validate.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.login.validate.bean.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	Optional<Customer> findByusername(String username);

}
