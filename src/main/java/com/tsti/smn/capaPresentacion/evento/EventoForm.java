package com.tsti.smn.capaPresentacion.evento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tsti.smn.pojos.Evento;

public class EventoForm {

	
	private Long id;
	@NotNull
	private Long idCiudad;
	@NotNull
	@Size (max = 500)
	private String descripcion;
	
	private String fechaEvento;
	
	

	public EventoForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public EventoForm(Evento e) {
		super();
		this.id = e.getId();
		this.idCiudad = e.getCiudad().getId();
		this.descripcion = e.getDescripcion();
		this.fechaEvento = e.getFechaEvento();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Evento toPojo() {
		
		Evento e = new Evento();
		e.setId(this.id);
		e.setDescripcion(this.getDescripcion());
		e.setFechaEvento(this.getFechaEvento());
		return e;
		
	}
	
	
	
}
