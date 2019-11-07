package com.RapiSolver.Api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Reserva;


@Repository
public interface IReservasRepository extends JpaRepository<Reserva, Integer> {
	public List<Reserva> findByFechaIngresoBetween(Date fechaInicio, Date fechaFin) throws Exception;
}
