package com.tsti.smn.capaDaos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsti.smn.pojos.Evento;

@Repository
public interface IEventoRepo extends JpaRepository<Evento, Long> {
	

}
