package com.RapiSolver.Api.services;

import java.util.List;
import java.util.Optional;

import com.RapiSolver.Api.controller.ModelView.DetailModelView;
import com.RapiSolver.Api.controller.ModelView.ServicioModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.entities.Supplier;

public interface ISupplierService extends CrudService<Supplier>{

	List<ServicioModelView> findServiciosByUserId(Integer id) throws Exception;
	
	SupplierModelView findSupplierByUserId(Integer id) throws Exception;
	
	Supplier findByUserId(Integer id) throws Exception;
}
