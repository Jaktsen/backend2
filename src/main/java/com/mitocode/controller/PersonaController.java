package com.mitocode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@RestController
@RequestMapping(value = "/personas")
//@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public List<Persona> listar(){
		return service.listar();
	}
	
	@GetMapping(value = "/{id}")
	public Persona listarPorId(@PathVariable("id") Integer id){
		Optional<Persona> op = service.listarPorId(id);
		return op.isPresent() ? op.get() : new Persona();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Persona registrar(@RequestBody Persona persona) {
		return service.registrar(persona);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Persona modificar(@RequestBody Persona persona) {
		return service.modificar(persona);
	}

	@DeleteMapping(value = "/{id}")
	public Integer eliminar(@PathVariable("id") Integer id) {
		Optional<Persona> opt = service.listarPorId(id);
		if (opt.isPresent()) {
			Persona per = new Persona();
			per.setId(id);
			service.eliminar(per);
			return 1;
		}
		return 0;
	}
	
	
}
