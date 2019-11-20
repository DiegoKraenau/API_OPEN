package com.RapiSolver.Api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RapiSolver.Api.controller.ModelView.RecommendationModelView;
import com.RapiSolver.Api.controller.ModelView.ReservationModelView;
import com.RapiSolver.Api.entities.Recommendation;
import com.RapiSolver.Api.entities.Reservation;
import com.RapiSolver.Api.services.IRecommendationService;
import com.RapiSolver.Api.services.IReservationService;
import com.RapiSolver.Api.services.IServicioService;
import com.RapiSolver.Api.services.ISupplierService;
import com.RapiSolver.Api.services.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/reservations")
@Api(tags="Reservations", value="Servicio Web RESTFull de Reservas")
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Reserva", notes="Método para crear Reserva")
	@ApiResponses({
		@ApiResponse(code=201, message="Reserva creada correctamente"),
		@ApiResponse(code=400, message="Solicitud de Reserva inválida")
	})
	public ResponseEntity<Reservation> insertReservation(@Valid @RequestBody ReservationModelView reservationModelView){
		try {
			
			Reservation reservationNew = new Reservation();
			reservationNew.setFecha(reservationModelView.getFecha());
			reservationNew.setNote(reservationModelView.getNote());
			reservationNew.setServicio(servicioService.findById(reservationModelView.getServicioId()).get());
			reservationNew.setSupplier(supplierService.findById(reservationModelView.getSupplierId()).get());
			reservationNew.setUsuario(usuarioService.findById(reservationModelView.getUsuarioId()).get());
			Reservation reserva=new Reservation();
			reserva = reservationService.save(reservationNew);
			
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(reserva.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
