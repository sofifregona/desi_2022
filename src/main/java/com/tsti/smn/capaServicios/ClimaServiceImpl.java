package com.tsti.smn.capaServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IClimaRepo;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaActual;

@Service
public class ClimaServiceImpl implements ClimaService{
	
	@Autowired
	IClimaRepo repo;

	@Override
	public ClimaActual findByIdCiudad(Long idCiudad) {
		return repo.findByIdCiudad(idCiudad);
	}

	@Override
	public ClimaActual getById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void save(ClimaActual c) {
		repo.save(c);
		
	}

	
}
