package com.RapiSolver.Api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.ReservationModelView;
import com.RapiSolver.Api.entities.Reservation;
import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.entities.Usuario;
import com.RapiSolver.Api.repository.IReservationRepository;
import com.RapiSolver.Api.repository.ISupplierRepository;
import com.RapiSolver.Api.repository.IUsuarioRepository;
import com.RapiSolver.Api.services.IReservationService;

@Service
@Transactional(readOnly = true)
public class ReservacionServiceImpl implements IReservationService {

	@Autowired
	IReservationRepository reservationRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	ISupplierRepository supplierRepository;
	

	
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

	@Override
	public List<ReservationModelView> findReservationByUserId(Integer id) throws Exception {
		Usuario u1=usuarioRepository.findById(id).get();
		List<ReservationModelView> grupoReservations=new ArrayList<>();
		
		if(u1.getRole().getId()==2) {
			List<Supplier> suppliers=supplierRepository.findAll();
			Supplier supplier=new Supplier();
			for (Supplier supplier2 : suppliers) {
				if(supplier2.getUsuario().getId()==id) {
					supplier=supplier2;
				}
			}
			
			List<Reservation> reservations=reservationRepository.findAll();
			
			
			for (Reservation reserva : reservations) {
				if(reserva.getUsuario().getId()==id || reserva.getSupplier().getId()==supplier.getId()) {
					ReservationModelView r1=new ReservationModelView();
					r1.setId(reserva.getId());
					r1.setFecha(reserva.getFecha());
					r1.setNote(reserva.getNote());
					r1.setCorreoSolicitante(reserva.getUsuario().getUserName());
					r1.setNombreProveedor(reserva.getSupplier().getName());
					r1.setNombreServicio(reserva.getServicio().getName());
					grupoReservations.add(r1);
				}
			}
		}else {
			List<Reservation> reservations=reservationRepository.findAll();
			
			
			for (Reservation reserva : reservations) {
				if(reserva.getUsuario().getId()==id ) {
					ReservationModelView r1=new ReservationModelView();
					r1.setId(reserva.getId());
					r1.setFecha(reserva.getFecha());
					r1.setNote(reserva.getNote());
					r1.setCorreoSolicitante(reserva.getUsuario().getUserName());
					r1.setNombreProveedor(reserva.getSupplier().getName());
					r1.setNombreServicio(reserva.getServicio().getName());
					grupoReservations.add(r1);
				}
			}
		}
		
		return grupoReservations;
		
	
	}

}
