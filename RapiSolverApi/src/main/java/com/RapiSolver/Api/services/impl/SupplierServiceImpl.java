package com.RapiSolver.Api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.ServicioModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.entities.Servicio;
import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.repository.ISupplierRepository;
import com.RapiSolver.Api.services.ISupplierService;

@Service
@Transactional(readOnly = true)
public class SupplierServiceImpl implements ISupplierService{

	@Autowired
	ISupplierRepository suppRepository;
	
	@Override
	@Transactional
	public Supplier save(Supplier t) throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		suppRepository.deleteById(id);
	}

	@Override
	public Optional<Supplier> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.findById(id);
	}

	@Override
	public List<Supplier> findAll() throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.findAll();
	}

	@Override
	public List<ServicioModelView> findServiciosByUserId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Supplier> suppliers=suppRepository.findAll();
		Supplier s1=new Supplier();
		
		for (Supplier supplier : suppliers) {
			if(supplier.getUsuario().getId()==id) {
				s1=supplier;
			}
		}
		
		List<DetalleServiceSupplier> detalles=s1.getListaDetails();
		List<ServicioModelView> servicios=new ArrayList<>();
		
		for (DetalleServiceSupplier detalle : detalles) {
			ServicioModelView smw=new ServicioModelView();
			smw.setCost(detalle.getServicioDetail().getCost());
			smw.setDescription(detalle.getServicioDetail().getDescription());
			smw.setId(detalle.getServicioDetail().getId());
			smw.setName(detalle.getServicioDetail().getName());
			smw.setNombreCategoria(detalle.getServicioDetail().getCategory().getCategoryName());
		
			servicios.add(smw);
		}
		
		return servicios;
	}

	@Override
	public SupplierModelView findSupplierByUserId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Supplier> suppliers=suppRepository.findAll();
		Supplier supplier=new Supplier();
		SupplierModelView s1=new SupplierModelView();
		
		for (Supplier s2 : suppliers) {
			if(s2.getUsuario().getId()==id) {
				supplier=s2;
			}
		}
		
		s1.setId(supplier.getId());
		s1.setAge(supplier.getAge());
		s1.setEmail(supplier.getEmail());
		s1.setGender(supplier.getGender());
		s1.setLastName(supplier.getLastName());
		s1.setName(supplier.getName());
		s1.setPhone(supplier.getPhone());
		s1.setUsuarioId(supplier.getUsuario().getId());
		s1.setAddress(supplier.getLocation().getAddress());
		s1.setCity(supplier.getLocation().getCity());
		s1.setCountry(supplier.getLocation().getCountry());
		s1.setState(supplier.getLocation().getState());
		
		return s1;
	}

}
