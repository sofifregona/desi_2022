package com.tsti.smn.capaServicios;

import java.util.List;

import com.tsti.smn.pojos.Estado;

public interface EstadoService {
	
	List<Estado> getAll();
	
	Estado getById(Long id);
}
