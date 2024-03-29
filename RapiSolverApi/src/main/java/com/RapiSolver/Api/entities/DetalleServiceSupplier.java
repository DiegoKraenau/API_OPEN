package com.RapiSolver.Api.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="detailServiceSupplier")
@Data
public class DetalleServiceSupplier implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer detailId;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	private Servicio servicioDetail;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplierDetail;

}
