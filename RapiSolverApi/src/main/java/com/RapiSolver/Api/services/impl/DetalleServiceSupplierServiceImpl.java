package com.RapiSolver.Api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.DetailModelView;
import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.repository.IDetalleServiceSupplierRepository;
import com.RapiSolver.Api.services.IDetalleServiceSupplierService;

@Service
@Transactional(readOnly = true)
public class DetalleServiceSupplierServiceImpl implements IDetalleServiceSupplierService {
	
	@Autowired
	private IDetalleServiceSupplierRepository detalleServiceSupplierRepository;

	@Override
	@Transactional
	public DetalleServiceSupplier save(DetalleServiceSupplier t) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.save(t);
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		detalleServiceSupplierRepository.deleteById(id);
	}

	@Override
	public Optional<DetalleServiceSupplier> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findById(id);
	}

	@Override
	public List<DetalleServiceSupplier> findAll() throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findAll();
	}

	@Override
	public Optional<DetalleServiceSupplier> findBydetailId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findBydetailId(id);
	}

	@Override
	public List<DetailModelView> findByServiceName(String name) throws Exception {
		 List<DetalleServiceSupplier> detalles= detalleServiceSupplierRepository.findAll();
		 List<DetailModelView> detallesModels=new ArrayList<>();
		 
		 for (DetalleServiceSupplier detalle : detalles) {
			if(detalle.getServicioDetail().getName().equals(name)) {
				DetailModelView d1=new DetailModelView();
				
				d1.setServiceDetailsId(detalle.getDetailId());
				d1.setSupplierId(detalle.getSupplierDetail().getId());
				d1.setServicioId(detalle.getServicioDetail().getId());
				d1.setName(detalle.getSupplierDetail().getName());
				d1.setLastNam(detalle.getSupplierDetail().getLastName());
				d1.setEmail(detalle.getSupplierDetail().getEmail());
				d1.setPhone(detalle.getSupplierDetail().getPhone());
				d1.setAge(detalle.getSupplierDetail().getAge());
				d1.setGenger(detalle.getSupplierDetail().getGender());
				d1.setUsuarioId(detalle.getSupplierDetail().getUsuario().getId());
				d1.setLocationId(detalle.getSupplierDetail().getLocation().getId());
				d1.setUserName(detalle.getSupplierDetail().getUsuario().getUserName());
				d1.setCountry(detalle.getSupplierDetail().getLocation().getCountry());
				d1.setServiceName(detalle.getServicioDetail().getName());
				d1.setDescription(detalle.getServicioDetail().getDescription());
				d1.setCost(detalle.getServicioDetail().getCost());
				d1.setServiceCategoryId(detalle.getServicioDetail().getCategory().getId());
				d1.setCategoryName(detalle.getServicioDetail().getCategory().getCategoryName());
				
				detallesModels.add(d1);
			}
			
		}
		 return detallesModels;
		
	}

}
