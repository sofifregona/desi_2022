/**
 * @author kuttel
 *
 */
package com.tsti.smn.capaServicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.ICiudadRepo;
import com.tsti.smn.capaPresentacion.ciudades.CiudadesBuscarForm;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;

@Service
public class CiudadServiceImpl implements CiudadService {
//	Logger LOG = LoggerFactory.getLogger(CiudadService.class);
//	
	@Autowired
	ICiudadRepo repo;

	@Override
	public List<Ciudad> getAll() {
		
		return repo.findAll();
	}



	@Override
	public Ciudad getById(Long idCiudad) {

		return repo.findById(idCiudad).get();
	}
	
	@Override
	public List<Ciudad> filter(CiudadesBuscarForm filter) throws Excepcion
	{
		//ver https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
		if(filter.getNombre()==null && filter.getProvinciaSeleccionada()==null)
			//return repo.findAll();
			throw new Excepcion("Es necesario al menos un filtro");
		else
			return repo.findByNombreOrIdProvincia(filter.getNombre(),filter.getProvinciaSeleccionada());
				
	}



	@Override
	public void deleteByid(Long id) {
		repo.deleteById(id);
		
	}



	@Override
	public void save(Ciudad c) throws Excepcion {
		if(c.getId()==null && !repo.findByNombreAndIdProvincia(c.getNombre(), c.getProvincia().getId()).isEmpty()) //estoy dando de alta una nueva ciudad y ya existe una igual?
			throw new Excepcion("Ya existe una ciudad con el mismo nombre, para la misma provincia");  
		else
			repo.save(c);
		
	}

}
