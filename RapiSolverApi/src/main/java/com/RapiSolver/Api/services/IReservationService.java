package com.RapiSolver.Api.services;

import java.util.List;

import com.RapiSolver.Api.controller.ModelView.RecommendationModelView;
import com.RapiSolver.Api.controller.ModelView.ReservationModelView;
import com.RapiSolver.Api.entities.Reservation;

public interface IReservationService extends CrudService<Reservation>{
	
	List<ReservationModelView> findReservationByUserId(Integer id) throws Exception;

}
