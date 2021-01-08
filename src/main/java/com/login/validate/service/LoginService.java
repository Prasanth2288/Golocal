package com.login.validate.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.validate.bean.Customer;
import com.login.validate.repository.CustomerRepository;
@Service  
public class LoginService {
	
	@Autowired
	CustomerRepository customerRepo;

	public Customer SaveCustomer(Customer customerLoginInfo) {
		Customer customer = customerRepo.save(customerLoginInfo);
		return customer;
		
	}

	public Optional<Customer> getCustomerById(String id) {
		Optional<Customer> customer = customerRepo.findById(Integer.parseInt(id));
		return customer;
		
	}

	public Iterable<Customer> getAllCustomers(String id) {
		Iterable<Customer> customers = customerRepo.findAll();
		return customers;
		
	}

	public Optional<Customer> getCustomerByUserName(String username) {
		Optional<Customer> findByusername = customerRepo.findByusername(username);
		return findByusername;
	}

}
