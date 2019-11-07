package com.RapiSolver.Api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="cliente")
@Data
@NamedQuery(name="Cliente.findByDni", query="Select c from Cliente c Where c.dni=?1")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=3, message= "los nombres no deben tener menos de 3 caracteres")
	@Column(name="nombres", nullable = false, length = 40)
	private String nombres;
	@Size(min=3, message= "los apellidos no deben tener menos de 3 caracteres")
	@Column(name="apellidos", nullable = false, length = 40)
	private String apellidos;
	@Size(min=8, message= "el dni no debe tener menos de 8 caracteres")
	@Column(name="dni", nullable = false, length = 8)
	private String dni;
	@Column(name="direccion", nullable = true, length = 150)
	private String direccon;
	@Column(name="telefono", nullable = true, length = 9)
	private String telefono;
	@Column(name="email", nullable = true, length = 50)
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	private List<Reserva> reservas;
}
