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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="location")
@Data
public class Location implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="country", nullable = false)
	private String country;
	
	@Column(name="city", nullable = false)
	private String city;
	
	@Column(name="state", nullable = false)
	private String state;
	
	@Column(name="address", nullable = false)
	private String address;
	
	
	@OneToMany(mappedBy = "location",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Supplier> suppliers;
    
	@OneToMany(mappedBy = "location",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;
    
    

}
