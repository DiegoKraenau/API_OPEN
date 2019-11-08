package com.RapiSolver.Api.services;

import java.util.List;
import java.util.Optional;

import com.RapiSolver.Api.controller.ModelView.DetailModelView;
import com.RapiSolver.Api.entities.DetalleServiceSupplier;

public interface IDetalleServiceSupplierService extends CrudService<DetalleServiceSupplier>{
	
	Optional<DetalleServiceSupplier> findBydetailId(Integer id) throws Exception;
	
	List<DetailModelView> findByServiceName(String name) throws Exception;

}
