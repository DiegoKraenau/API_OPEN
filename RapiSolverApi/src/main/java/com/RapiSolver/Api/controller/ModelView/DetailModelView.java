package com.RapiSolver.Api.controller.ModelView;


import lombok.Data;

@Data
public class DetailModelView {
	
	 private int ServiceDetailsId;

	 private int SupplierId;

	 private int ServicioId;

	 private String Name;

    
	 private String LastNam;

   
	 private String Email;

  
	 private String Phone;

      
	 private int Age;

      
	 private String Genger;

	 private int UsuarioId;

	 private int LocationId;

	 private String UserName;

	 private String Country;



     //SERVICIO

      public String ServiceName;

    
     public String Description;

     
     public String Cost;

     public int ServiceCategoryId;

     public String CategoryName;

}
