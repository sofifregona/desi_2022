/**
4z * @author sofifregona
 *
 */
package com.tsti.smn.pojos;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClimaExtendido {
	
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	private int porcentajeLluvia;
	
	private int mmLluvia;
	
	private String descripcion;
	
	@ManyToOne
	private Ciudad ciudad;
	
	//Getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getPorcentajeLluvia() {
		return porcentajeLluvia;
	}
	public void setPorcentajeLluvia(int porcentajeLluvia) {
		this.porcentajeLluvia = porcentajeLluvia;
	}
	public int getMmLluvia() {
		return mmLluvia;
	}
	public void setMmLluvia(int mmLluvia) {
		this.mmLluvia = mmLluvia;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}