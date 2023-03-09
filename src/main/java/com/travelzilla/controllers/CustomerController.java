package com.travelzilla.controllers;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelzilla.exceptions.AdminException;
import com.travelzilla.exceptions.CustomerException;
import com.travelzilla.exceptions.SessionException;
import com.travelzilla.models.Customer;
import com.travelzilla.models.Session;
import com.travelzilla.models.UserType;
import com.travelzilla.services.CustomerServices;
import com.travelzilla.services.SessionServices;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins="*")
public class CustomerController {

	
	@Autowired
	private CustomerServices customerServices;
	
	@Autowired
	private SessionServices sServices;
	
	@PostMapping(path="/addcustomer",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws CustomerException{
		
		return new ResponseEntity<Customer>(customerServices.addCustomer(customer),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(path="/deletecustomer/{cid}",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<Customer> deleteCustomerbyIdHandler(@PathVariable Integer customerId, @RequestParam("sessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sServices.getASessionByKey(sessionKey);
		if(session.getUserId()==customerId && session.getUserType()==UserType.CUSTOMER) {
			Customer dc = customerServices.deleteCustomer(customerId) ;
			return new ResponseEntity<Customer>(dc, HttpStatus.OK);
		}
		throw new CustomerException("Please login with the correct credentials");
	}
	
	@PutMapping(path="/updatecustomer",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam("sessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sServices.getASessionByKey(sessionKey);
		if(session.getUserId()==customer.getCustomerId() && session.getUserType()==UserType.CUSTOMER) {
			Customer updatedCustomer= customerServices.updateCustomer(customer);
		
			return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.ACCEPTED);
		}
		throw new CustomerException("Please login with the correct credentials");
	}
	
	@GetMapping(path="/viewcustomer/{id}",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") Integer id, @RequestParam("sessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sServices.getASessionByKey(sessionKey);
		if(session.getUserId()== id && session.getUserType()==UserType.CUSTOMER) {
			return new ResponseEntity<>(customerServices.viewCustomer(id), HttpStatus.OK);
		}
		
		throw new CustomerException("Please login with the correct credentials");
		
	}
	
	@GetMapping(path="/viewallcustomers",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<List<Customer>> viewAllCustomers(@RequestParam("sessionKey")String sessionKey) throws CustomerException, SessionException{
		Session session = sServices.getASessionByKey(sessionKey);
		if(session.getUserType() == UserType.ADMIN) {
			return new ResponseEntity<List<Customer>>(customerServices.viewAllCustomer(),HttpStatus.OK);
		}
		throw new CustomerException("Access Denied! Only admin can view all customers.");
	}
	
	

	
}
