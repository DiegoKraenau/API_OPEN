package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}
