package com.RapiSolver.Api.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="reserva")
@Data
public class Reserva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="cantidad_personas", nullable = false)
	private int cantidadPersonas;
	@Column(name="descripcion", nullable = true, length = 150)
	private String descripcion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso", nullable = false)
	private Date fechaIngreso;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_salida", nullable = false)
	private Date fechaSalida;
	@ManyToOne
	@JoinColumn(name="cliente_id",nullable = false)
	private Cliente cliente;
}
