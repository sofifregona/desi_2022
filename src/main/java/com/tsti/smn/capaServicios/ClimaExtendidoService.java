/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaServicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tsti.smn.capaPresentacion.climaExtendido.ClimaExtendidoBuscarForm;
import com.tsti.smn.capaPresentacion.verClima.VerClimaForm;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaExtendido;

public interface ClimaExtendidoService {

	/**
	 * Obtiene la lista completa de registros para clima extendido
	 * @return Todas las ciudades
	 */
	
	List<ClimaExtendido> getAll();
	
	/**
	 * Obtiene un determinado registro de ClimaExtendido
	 * @param idClimaExtendido Identificador del clima buscado
	 * @return ClimaExtendido encontrado
	 */
	
	ClimaExtendido getById(Long idClimaExtendido);
	ClimaExtendido getByFechaAndIdCiudad(LocalDate fecha, Long idCiudad);


	List<ClimaExtendido> filter2(VerClimaForm filter);
	List<ClimaExtendido> filter(ClimaExtendidoBuscarForm filter) throws Excepcion;
	

	void save(ClimaExtendido ce) throws Excepcion;

	void update(ClimaExtendido ce);

	

	

}