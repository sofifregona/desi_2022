package com.tsti.smn.capaServicios;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IPersonaRepo;
import com.tsti.smn.capaPresentacion.personas.PersonasBuscarForm;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {

//	Logger LOG = LoggerFactory.getLogger(PersonaService.class);

	
	@Autowired
	IPersonaRepo repo;
	
	@Override
	public List<Persona> getAll() {
		
		return repo.findAll();
	}

	@Override
	public List<Persona> filter(PersonasBuscarForm filter) throws Excepcion {
		
//		//ver https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
		if(filter.getNombre()==null && filter.getDni()==null && filter.getIdCiudadSeleccionada()==null)
			throw new Excepcion("Es necesario al menos un filtro");
		else
			return repo.findByNombreOrIdCiudad(filter.getNombre(),filter.getDni(),filter.getIdCiudadSeleccionada());
		
		
		
	}

	@Override
	public void save(Persona persona) throws Excepcion {
		
		GregorianCalendar gc =new GregorianCalendar();
		gc.set(Calendar.YEAR, 2000);
		gc.set(Calendar.DATE, 1);
		gc.set(Calendar.MONTH, 1);
		
		if(persona.getDni()<35000000 && persona.getFechaNacimiento().after(gc.getTime()))
			throw new Excepcion("El dni no corresponde a la fecha de nacimiento indicada");  //error global mostrado arriba
		else if(repo.existsById(persona.getDni()))
			throw new Excepcion("El dni ya se encuentra asociado a otra persona", "dni");  //error asociado al campo dni
		else
			repo.save(persona);
		
	}

	@Override
	public Persona getPersonaById(Long idPersona) throws Exception {

		Optional<Persona> p = repo.findById(idPersona);
		
		if(p!=null) {
			return p.get();
		} else {
			throw new Exception("No existe la persona con el id="+idPersona);
		}
	}

	@Override
	public void deletePersonaByid(Long id) {
		repo.deleteById(id);
		
	}

	
}
