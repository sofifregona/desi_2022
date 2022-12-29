package com.tsti.smn.capaServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IEventoRepo;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Evento;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	IEventoRepo repo;

	@Override
	public void save(Evento evento) throws Excepcion {
		
		repo.save(evento);
		
	}
	

}
