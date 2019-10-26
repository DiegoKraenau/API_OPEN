package com.RapiSolver.Api.services;

import java.util.Optional;

import com.RapiSolver.Api.entities.DetalleServiceSupplier;

public interface IDetalleServiceSupplierService extends CrudService<DetalleServiceSupplier>{
	
	Optional<DetalleServiceSupplier> findBydetailId(Integer id) throws Exception;

}
