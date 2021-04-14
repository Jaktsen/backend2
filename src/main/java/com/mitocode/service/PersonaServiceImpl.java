package com.mitocode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IPersonaDAO;
import com.mitocode.model.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDAO dao;

	@Override
	public List<Persona> listar() {
		return dao.findAll();
	}

	@Override
	public Optional<Persona> listarPorId(Integer id) {		
		return dao.findById(id);
	}

	@Override
	public Persona registrar(Persona per) {
		return dao.save(per);
	}

	@Override
	public Persona modificar(Persona per) {
		Optional<Persona> persona = dao.findById(per.getId());
		
		if (persona.isPresent()) {
			return dao.save(per);
		}
		return new Persona();
	}

	@Override
	public void eliminar(Persona per) {
		dao.delete(per);

	}

}
