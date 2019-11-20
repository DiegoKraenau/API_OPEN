package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Reservation;
import com.RapiSolver.Api.repository.IReservationRepository;
import com.RapiSolver.Api.services.IReservationService;

@Service
@Transactional(readOnly = true)
public class ReservacionServiceImpl implements IReservationService {

	@Autowired
	IReservationRepository reservationRepository;
	

	
	@Override
	@Transactional
	public Reservation save(Reservation t) throws Exception {
		// TODO Auto-generated method stub
		return reservationRepository.save(t);
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Reservation> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
