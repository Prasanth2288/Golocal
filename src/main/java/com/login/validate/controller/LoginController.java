package com.login.validate.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.validate.bean.Customer;
import com.login.validate.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping(path = "/registerCustomer", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Customer registerCustomer(@RequestBody Customer customerLoginInfo) {

		StringBuilder userName =  new StringBuilder();
		Random random=new Random();
		int num = random.nextInt(90) + 10;
		userName.append(customerLoginInfo.getCustomerName()).append(String.valueOf(num));
		customerLoginInfo.setUsername(userName.toString());
		Customer saveCustomer = loginService.SaveCustomer(customerLoginInfo);
		return saveCustomer;

	}
	
	@GetMapping(path = "/customer", produces = "application/json")
	@ResponseBody
	public ArrayList<Customer> retrieveCustomerById(@RequestParam(value = "id", required = false) String id) {

		ArrayList<Customer> allCustomers = new ArrayList<Customer>();

		if (id != null) {
			Optional<Customer> customerById = loginService.getCustomerById(String.valueOf(id));
			ArrayList<Customer> list = (ArrayList<Customer>) customerById.stream().collect(Collectors.toList());
			allCustomers.addAll(list);
		} else {
			allCustomers = (ArrayList<Customer>) loginService.getAllCustomers(String.valueOf(id));

		}

		return allCustomers;

	}
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Customer ValidateLogin(@RequestBody Customer customerLoginInfo) {

		Customer customerValid = null;
		Optional<Customer> customerById = loginService.getCustomerByUserName(customerLoginInfo.getUsername());
		if(customerById.isPresent() && customerById.get().getPassword().equals(customerLoginInfo.getPassword())) {
			customerValid = customerById.get();
		}
		return customerValid;

	}
	
	@GetMapping(path = "/", produces = "application/json")
	@ResponseBody
	public String basicTest() {


		return "Success";

	}

}
