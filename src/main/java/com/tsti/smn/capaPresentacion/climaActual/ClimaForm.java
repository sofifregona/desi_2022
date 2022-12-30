package com.tsti.smn.capaPresentacion.climaActual;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.tsti.smn.pojos.ClimaActual;

public class ClimaForm {

	private Long id;
	@NotNull
	@Range(min=0, message = "La temperatura no puede ser menor a 0")
	private int temperatura;
	@NotNull
	@Range(min=0, message = "La humedad no puede ser menor a 0")
	private int humedad;
	@NotNull
	private Long idEstado;
	@NotNull
	private Long idCiudad;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	public int getHumedad() {
		return humedad;
	}
	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
		
	
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
	public ClimaForm() {
		super();
	}
	
	public ClimaForm(ClimaActual c) {
		super();
		this.id = c.getId();
		this.temperatura = c.getTemperatura();
		this.humedad = c.getHumedad();
		this.idCiudad = c.getCiudad().getId();
		//this.idEstado = c.getEstado().getId();
	}
	
	public ClimaActual toPojo()
	{
		ClimaActual c = new ClimaActual();
		c.setId(this.id);
		c.setTemperatura(this.temperatura);
		c.setHumedad(this.humedad);
		return c;
	}
}
