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

import com.RapiSolver.Api.controller.ModelView.DetailModelView;
import com.RapiSolver.Api.entities.Customer;
import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.entities.Role;
import com.RapiSolver.Api.entities.Servicio;
import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.services.ICategoryService;
import com.RapiSolver.Api.services.IDetalleServiceSupplierService;
import com.RapiSolver.Api.services.IServicioService;
import com.RapiSolver.Api.services.ISupplierService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/detalles")
@Api(tags="DetalleServiceSupplier", value="Servicio Web RESTFull de Detalle")
public class DetalleServiceSupplierController {
	
	@Autowired
	private IDetalleServiceSupplierService detalleService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Detalles", notes="Método para listar todos los Detalles")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalles encontrados"),
		@ApiResponse(code=404, message="Detalles no encontrados")
	})
	public ResponseEntity<List<DetailModelView>>findAll(){
		try {
			List<DetalleServiceSupplier> detalles = new ArrayList<>();
			detalles = detalleService.findAll();
			List<DetailModelView> detallesGroup=new ArrayList<>();
			
			for (DetalleServiceSupplier detalle : detalles) {
				DetailModelView d1=new DetailModelView();
				d1.setServiceDetailsId(detalle.getDetailId());
				d1.setSupplierId(detalle.getSupplierDetail().getId());
				d1.setServicioId(detalle.getServicioDetail().getId());
				d1.setName(detalle.getSupplierDetail().getName());
				d1.setLastNam(detalle.getSupplierDetail().getLastName());
				d1.setEmail(detalle.getSupplierDetail().getEmail());
				d1.setPhone(detalle.getSupplierDetail().getPhone());
				d1.setAge(detalle.getSupplierDetail().getAge());
				d1.setGenger(detalle.getSupplierDetail().getGender());
				d1.setUsuarioId(detalle.getSupplierDetail().getUsuario().getId());
				d1.setLocationId(detalle.getSupplierDetail().getLocation().getId());
				d1.setUserName(detalle.getSupplierDetail().getUsuario().getUserName());
				d1.setCountry(detalle.getSupplierDetail().getLocation().getCountry());
				d1.setServiceName(detalle.getServicioDetail().getName());
				d1.setDescription(detalle.getServicioDetail().getDescription());
				d1.setCost(detalle.getServicioDetail().getCost());
				d1.setServiceCategoryId(detalle.getServicioDetail().getCategory().getId());
				d1.setCategoryName(detalle.getServicioDetail().getCategory().getCategoryName());
				
				detallesGroup.add(d1);
			}
			
			return new ResponseEntity<List<DetailModelView>>(detallesGroup, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<DetailModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Detalle por Id", notes="Método para buscar Detalle por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle encontrado"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetailModelView> findById(@PathVariable("id") Integer id){
		try {
			Optional<DetalleServiceSupplier> detalle1 = detalleService.findById(id);
			DetalleServiceSupplier detalle=detalle1.get();
			DetailModelView d1=new DetailModelView();
			d1.setServiceDetailsId(detalle.getDetailId());
			d1.setSupplierId(detalle.getSupplierDetail().getId());
			d1.setServicioId(detalle.getServicioDetail().getId());
			d1.setName(detalle.getSupplierDetail().getName());
			d1.setLastNam(detalle.getSupplierDetail().getLastName());
			d1.setEmail(detalle.getSupplierDetail().getEmail());
			d1.setPhone(detalle.getSupplierDetail().getPhone());
			d1.setAge(detalle.getSupplierDetail().getAge());
			d1.setGenger(detalle.getSupplierDetail().getGender());
			d1.setUsuarioId(detalle.getSupplierDetail().getUsuario().getId());
			d1.setLocationId(detalle.getSupplierDetail().getLocation().getId());
			d1.setUserName(detalle.getSupplierDetail().getUsuario().getUserName());
			d1.setCountry(detalle.getSupplierDetail().getLocation().getCountry());
			d1.setServiceName(detalle.getServicioDetail().getName());
			d1.setDescription(detalle.getServicioDetail().getDescription());
			d1.setCost(detalle.getServicioDetail().getCost());
			d1.setServiceCategoryId(detalle.getServicioDetail().getCategory().getId());
			d1.setCategoryName(detalle.getServicioDetail().getCategory().getCategoryName());
			
			if(!detalle1.isPresent()) {
				return new ResponseEntity<DetailModelView>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<DetailModelView>(d1, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetailModelView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Detalle", notes="Método para crear Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<DetailModelView> insertDetalle(@Valid @RequestBody DetailModelView detalle){
	
		try {
			DetalleServiceSupplier detalleNew = new DetalleServiceSupplier();
			//Agregamos el servicio
			Servicio s1=new Servicio();
			Servicio s1_nuevo=new Servicio();
			s1.setId(detalle.getServicioId());
			s1.setCost(detalle.getCost());
			s1.setDescription(detalle.getDescription());
			s1.setName(detalle.getServiceName());
			s1.setCategory(categoryService.findByCategoryName(detalle.getCategoryName()));
			s1_nuevo=servicioService.save(s1);
			
	
			//Obtenemos el supplier que tiene el ID user
			List<Supplier> suppliers=new ArrayList<>();
			suppliers=supplierService.findAll();
			Supplier sup1=new Supplier();
			for (Supplier supplier : suppliers) {
				
				if(supplier.getUsuario().getId()==detalle.getUsuarioId()) {
					
					sup1=supplier;
				}
			}
			
			
			
			detalleNew.setServicioDetail(s1_nuevo);
			detalleNew.setSupplierDetail(sup1);
			
			
			DetalleServiceSupplier detalleNew2 = new DetalleServiceSupplier();
			
			detalleNew2 = detalleService.save(detalleNew);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(detalleNew2.getDetailId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<DetailModelView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Detalle", notes="Método para actualizar un Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle actualizado correctamente"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetalleServiceSupplier> updateDetalle(@Valid @RequestBody DetalleServiceSupplier detalle){
		try {
			detalleService.save(detalle);
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Detalle", notes="Método para eliminar un Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle eliminado correctamente"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetalleServiceSupplier> deleteDetalle(@PathVariable("id") Integer id){
		try {
			Optional<DetalleServiceSupplier> detalle = detalleService.findById(id);
			if(!detalle.isPresent()) {
				return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.NOT_FOUND);
			}
			detalleService.deleteById(id);
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="searchByServiceName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar por nombre del servicio" ,notes="Metodo para buscar un detail por el nombre del servicio")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicios encontrados"),
		@ApiResponse(code=404, message="Servicios no encontrado")
	})
	public ResponseEntity<List<DetailModelView>> findByServiceName(@PathVariable("name") String name){
		try {
			List<DetailModelView> detalles = new ArrayList<>();
			detalles = detalleService.findByServiceName(name);
			
			return new ResponseEntity<List<DetailModelView>>(detalles, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<DetailModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
