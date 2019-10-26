package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Customer;
import com.RapiSolver.Api.repository.ICustomerRepository;
import com.RapiSolver.Api.services.ICustomerService;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	@Transactional
	public Customer save(Customer t) throws Exception {
		// TODO Auto-generated method stub
		return customerRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		customerRepository.deleteById(id);
	}

	@Override
	public Optional<Customer> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}
