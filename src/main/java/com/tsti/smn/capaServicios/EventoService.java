package com.tsti.smn.capaServicios;


import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Evento;

public interface EventoService {
	
	
	/**
	 * Si la persona existe la actualizará, sino la creará en BD
	 * @param persona
	 * @throws Exception 
	 */
	void save(Evento evento) throws Excepcion;


}
