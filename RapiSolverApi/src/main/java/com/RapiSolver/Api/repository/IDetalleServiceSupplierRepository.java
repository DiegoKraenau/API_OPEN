package com.RapiSolver.Api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.DetalleServiceSupplier;

@Repository
public interface IDetalleServiceSupplierRepository extends JpaRepository<DetalleServiceSupplier, Integer>{
	
	Optional<DetalleServiceSupplier> findBydetailId(Integer id) throws Exception;
	

}
