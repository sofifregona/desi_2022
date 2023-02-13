/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaPresentacion.verClima;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class VerClimaForm {
	
	//Atributos
	@NotNull
	private Long id;
	
	private String nombre;
	
	private LocalDate fechaADiezDias;

	
	//Getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaADiezDias() {
		return fechaADiezDias;
	}
	public void setId(LocalDate fechaADiezDias) {
		this.fechaADiezDias = fechaADiezDias;
	}

}