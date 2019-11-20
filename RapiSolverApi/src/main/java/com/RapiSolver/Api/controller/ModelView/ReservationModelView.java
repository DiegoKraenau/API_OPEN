package com.RapiSolver.Api.controller.ModelView;


import lombok.Data;

@Data
public class ReservationModelView {


	private Integer id;
	

	private int servicioId;
	
	
	private int usuarioId;
	

	private int supplierId;
	

	private String fecha;
	


	private String note;
}
