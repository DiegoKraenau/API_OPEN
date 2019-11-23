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
import com.RapiSolver.Api.entities.Usuario;
import com.RapiSolver.Api.repository.ICustomerRepository;
import com.RapiSolver.Api.repository.IRoleRepository;
import com.RapiSolver.Api.repository.ISupplierRepository;
import com.RapiSolver.Api.repository.IUsuarioRepository;
import com.RapiSolver.Api.services.ICustomerService;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	ISupplierRepository supplierRepository;

	
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

	@Transactional
	@Override
	public Boolean agregarMembresiaByUserId(Integer id) throws Exception {
		try {
		Customer c1=new Customer();
		List<Customer> customers=customerRepository.findAll();
		for (Customer customer : customers) {
			if(customer.getUsuario().getId()==id) {
				c1=customer;
			}
		}
		
		Usuario u1=usuarioRepository.findById(id).get();
		//u1.getRole().setId(2);
		u1.setRole(roleRepository.findById(2).get());
		u1=usuarioRepository.save(u1);
		c1.setUsuario(u1);
		
		
		Supplier s1=new Supplier();
		s1.setAge(c1.getAge());
		s1.setEmail(c1.getEmail());
		s1.setGender(c1.getGender());
		s1.setLastName(c1.getLastName());
		s1.setName(c1.getName());
		s1.setLocation(c1.getLocation());
		s1.setPhone(c1.getPhone());
		s1.setUsuario(c1.getUsuario());
	
		
		customerRepository.deleteById(c1.getId());
		
		s1=supplierRepository.save(s1);
		
		/*
		Customer c1=context.customers.Where(x=>x.UsuarioId==id).FirstOrDefault();
        Usuario u1=context.usuarios.Find(c1.UsuarioId);
        u1.RolId=2;
        u1.Rol=context.roles.Find(2);
        context.Update(u1);
        context.SaveChanges();
        Supplier s1=new Supplier();
        s1.Address=c1.Address;
        s1.Age=c1.Age;
        s1.City=c1.City;
        s1.Contraseña=c1.Contraseña;
        s1.Country=c1.Country;
        s1.Email=c1.Email;
        s1.Genger=c1.Genger;
        s1.LastName=c1.LastName;
        s1.Location=c1.Location;
        s1.LocationId=c1.LocationId;
        s1.Name=c1.Name;
        s1.Phone=c1.Phone;
        s1.State=c1.State;
        s1.Usuario=c1.Usuario;
        s1.UsuarioId=c1.UsuarioId;
        context.Remove(c1);
        context.SaveChanges();
        context.Add(s1);
        context.SaveChanges();
        */
		return true;
	}catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	}
	

}
