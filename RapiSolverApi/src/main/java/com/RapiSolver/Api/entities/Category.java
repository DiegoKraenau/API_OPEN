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
@Table(name="category")
@Data
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="categoryName", nullable = false)
	private String categoryName;
	
	@Column(name="categoryDescription", nullable = false)
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Servicio> servicios;
	
}
