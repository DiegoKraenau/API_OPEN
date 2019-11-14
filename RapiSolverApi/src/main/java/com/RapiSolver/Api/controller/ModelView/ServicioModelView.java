package com.RapiSolver.Api.controller.ModelView;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.RapiSolver.Api.entities.Category;

import lombok.Data;

@Data
public class ServicioModelView {


	
	private Integer id;
	
	
	private String name;

	private String description;
	

	private String cost;
	

	private String nombreCategoria;
}
