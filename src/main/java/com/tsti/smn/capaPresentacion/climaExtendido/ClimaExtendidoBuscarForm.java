/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaPresentacion.climaExtendido;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ClimaExtendidoBuscarForm {
	
	//Atributos
	@NotNull
	private Long id;
	
	private Long idCiudadSeleccionada;
	
	private LocalDate fecha;
	
	//Getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCiudadSeleccionada() {
		return idCiudadSeleccionada;
	}
	public void setIdCiudadSeleccionada(Long idCiudadSeleccionada) {
		this.idCiudadSeleccionada = idCiudadSeleccionada;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}