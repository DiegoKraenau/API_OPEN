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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="supplier")
@Data
public class Supplier implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", nullable = false)
	private String name;

	@Column(name="lastName", nullable = false)
    private String lastName;

	@Column(name="email", nullable = false)
    private String email;

	@Column(name="phone", nullable = false)
    private String phone;

	@Column(name="age", nullable = false)
    private int age;

	@Column(name="gender", nullable = false)
    private String gender;

	
	@OneToOne
	@JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name="location_id", nullable = false)
	private Location location;
	
	@OneToMany(mappedBy = "supplierDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DetalleServiceSupplier> listaDetails;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Recommendation> listRecommendations;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reservation> reservas;
}
