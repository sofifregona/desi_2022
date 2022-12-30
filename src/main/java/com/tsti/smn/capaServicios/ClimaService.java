package com.tsti.smn.capaServicios;

import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.ClimaActual;

public interface ClimaService {
	
	ClimaActual getById(Long id) ;
	
	ClimaActual findByIdCiudad(Long idCiudad) ;
	
	void save(ClimaActual c);

}
