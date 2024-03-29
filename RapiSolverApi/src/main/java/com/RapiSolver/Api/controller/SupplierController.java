package com.RapiSolver.Api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RapiSolver.Api.controller.ModelView.ServicioModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.services.ISupplierService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/suppliers")
@Api(tags="Supplier", value="Servicio Web RESTFull de suppliers")
public class SupplierController {

	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Proveedores", notes="Método para listar todos los proveedores")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedores encontrados"),
		@ApiResponse(code=404, message="Proveedores no encontrados")
	})
	public ResponseEntity<List<SupplierModelView>>findAll(){
		try {
			List<Supplier> suppliers = new ArrayList<>();
			suppliers = supplierService.findAll();
			List<SupplierModelView> suppliersGroup=new ArrayList<>();
			for (Supplier supplier : suppliers) {
				SupplierModelView s1=new SupplierModelView();
				s1.setId(supplier.getId());
				s1.setAge(supplier.getAge());
				s1.setEmail(supplier.getEmail());
				s1.setGender(supplier.getGender());
				s1.setLastName(supplier.getLastName());
				s1.setName(supplier.getName());
				s1.setPhone(supplier.getPhone());
				s1.setUsuarioId(supplier.getUsuario().getId());
				s1.setAddress(supplier.getLocation().getAddress());
				s1.setCity(supplier.getLocation().getCity());
				s1.setCountry(supplier.getLocation().getCountry());
				s1.setState(supplier.getLocation().getState());
				
				suppliersGroup.add(s1);
				
			}
			return new ResponseEntity<List<SupplierModelView>>(suppliersGroup, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<SupplierModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Proveedor por Id", notes="Método para buscar proveedor por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedores encontrados"),
		@ApiResponse(code=404, message="Proveedores no encontrados")
	})
	public ResponseEntity<Supplier> findById(@PathVariable("id") Integer id){
		try {
			Optional<Supplier> supplier = supplierService.findById(id);
			if(!supplier.isPresent()) {
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Supplier>(supplier.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Supplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Proveedor", notes="Método para crear proveedores")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedor creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Supplier> insertSupplier(@Valid @RequestBody Supplier supplier){
		try {
			Supplier supplierNuevo = new Supplier();
			supplierNuevo = supplierService.save(supplier);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(supplierNuevo.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Supplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Proveedor", notes="Método para actualizar un proveedor")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedor actualizado correctamente"),
		@ApiResponse(code=404, message="Proveedor no encontrado")
	})
	public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier supplier){
		try {
			supplierService.save(supplier);
			return new ResponseEntity<Supplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Supplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Cliente", notes="Método para eliminar una cliente")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedor eliminado correctamente"),
		@ApiResponse(code=404, message="Proveedor no encontrado")
	})
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Integer id){
		try {
			Optional<Supplier> supplier = supplierService.findById(id);
			if(!supplier.isPresent()) {
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			}
			supplierService.deleteById(id);
			return new ResponseEntity<Supplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Supplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/serviciosByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar servicios correspondientes a un supplier", notes="Método para encontrar los servicios de un supplier por el codigo de Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicios encontrados"),
		@ApiResponse(code=404, message="Servicios no encontrados")
	})
	public ResponseEntity<List<ServicioModelView>> serviciosByUserId(@PathVariable("id") Integer id){
		try {
		
			List<ServicioModelView> grupoServicios =supplierService.findServiciosByUserId(id);
			
			
			return new ResponseEntity<List<ServicioModelView>>(grupoServicios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<ServicioModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/searchByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Proveedor por codigo de Usuario", notes="Método para buscar proveedor por codigo de Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Proveedor encontrados"),
		@ApiResponse(code=404, message="Proveedor no encontrados")
	})
	public ResponseEntity<SupplierModelView> searchByUserId(@PathVariable("id") Integer id){
		try {
			SupplierModelView supplier = supplierService.findSupplierByUserId(id);
			
			return new ResponseEntity<SupplierModelView>(supplier, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<SupplierModelView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
