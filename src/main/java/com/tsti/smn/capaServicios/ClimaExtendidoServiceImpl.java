/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaServicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IClimaExtendidoRepo;
import com.tsti.smn.capaPresentacion.climaExtendido.ClimaExtendidoBuscarForm;
import com.tsti.smn.capaPresentacion.verClima.VerClimaForm;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.ClimaExtendido;

@Service
public class ClimaExtendidoServiceImpl implements ClimaExtendidoService {

	@Autowired
	IClimaExtendidoRepo repo;

	@Override
	public List<ClimaExtendido> getAll() {		
		return repo.findAll();
	}

	@Override
	public ClimaExtendido getById(Long idClimaExtendido) {
		return repo.findById(idClimaExtendido).get();
	}
	
	@Override
	public ClimaExtendido getByFechaAndIdCiudad(LocalDate fecha, Long idCiudad) {
			return repo.findByFechaAndIdCiudad(fecha, idCiudad);
	}
	
	@Override
	public List<ClimaExtendido> filter2 (VerClimaForm filter2){
		return repo.findByIdCiudadForUser(filter2.getId(), filter2.getFechaADiezDias());
	}
	
	@Override
	public List<ClimaExtendido> filter(ClimaExtendidoBuscarForm filter) throws Excepcion
	{
		if(filter.getIdCiudadSeleccionada()==null)
			throw new Excepcion("Se necesita el filtro");
		else
			return repo.findByIdCiudad(filter.getIdCiudadSeleccionada());	
	}

	@Override
	public void save(ClimaExtendido ce) throws Excepcion {
			repo.save(ce);	
	}

	@Override 
	public void update(ClimaExtendido ce) {
		repo.save(ce);
	}

}