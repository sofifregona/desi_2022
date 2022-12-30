package com.tsti.smn.capaDaos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsti.smn.pojos.ClimaActual;

@Repository
public interface IClimaRepo extends JpaRepository <ClimaActual, Long>{

	@Query("SELECT p FROM ClimaActual p WHERE p.id like ?1")
	ClimaActual findByIdCiudad(Long idCiudad);
}
