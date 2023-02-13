/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaDaos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaExtendido;

@Repository
public interface IClimaExtendidoRepo extends JpaRepository<ClimaExtendido, Long> {

	@Query("SELECT ce FROM ClimaExtendido ce WHERE ce.ciudad.id like ?1 and ce.fecha > curdate()")
	List<ClimaExtendido> findByIdCiudad(Long idCiudadSeleccionada);
	
	@Query("SELECT ce FROM ClimaExtendido ce WHERE ce.ciudad.id like ?1 and ce.fecha > curdate() and ce.fecha < ?2")
	List<ClimaExtendido> findByIdCiudadForUser(Long idCiudadSeleccionada, LocalDate fechaADiezDias);
	
	@Query("SELECT ce FROM ClimaExtendido ce WHERE ce.fecha like ?1 and ce.ciudad.id like ?2")
	ClimaExtendido findByFechaAndIdCiudad(LocalDate fecha, Long idCiudadSeleccionada);
	
}