package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Integer>{

}
