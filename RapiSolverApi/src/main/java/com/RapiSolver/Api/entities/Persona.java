package com.RapiSolver.Api.entities;

import javax.persistence.Column;


public  abstract class  Persona {

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



}
