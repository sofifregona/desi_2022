package com.tsti.smn.capaServicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IEstadoRepo;
import com.tsti.smn.pojos.Estado;

@Service
public class EstadoServiceImpl implements EstadoService{

	@Autowired
	IEstadoRepo repo;
	
	@Override
	public List<Estado> getAll() {
		return repo.findAll();
	}

	@Override
	public Estado getById(Long id) {
		return repo.findById(id).get();
	}

}
