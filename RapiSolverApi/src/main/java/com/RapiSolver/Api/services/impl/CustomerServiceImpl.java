package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.CustomerModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.Customer;
import com.RapiSolver.Api.entities.Supplier;
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

	@Override
	public CustomerModelView findCustomerByUserId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Customer> customers=customerRepository.findAll();
		Customer customer=new Customer();
		CustomerModelView s1=new CustomerModelView();
		
		for (Customer s2 : customers) {
			if(s2.getUsuario().getId()==id) {
				customer=s2;
			}
		}
		
		s1.setId(customer.getId());
		s1.setAge(customer.getAge());
		
		s1.setEmail(customer.getEmail());
		s1.setGender(customer.getGender());
		s1.setLastName(customer.getLastName());
		s1.setName(customer.getName());
		
		s1.setPhone(customer.getPhone());
		s1.setUsuarioId(customer.getUsuario().getId());
		/*
		s1.setAddress(customer.getLocation().getAddress());

		s1.setCity(customer.getLocation().getCity());
		
		s1.setCountry(customer.getLocation().getCountry());
		s1.setState(customer.getLocation().getState());
		*/
		return s1;
	}

}
