/**
 * @author sofifregona
 *
 */
package com.tsti.smn.capaPresentacion.climaExtendido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.tsti.smn.pojos.ClimaExtendido;

/**
 * Objeto necesario para insertar o eliminar una registro de clima extendido. 
 * Nótese que en lugar de referenciar al objeto ClimaExtendido, reemplaza ese atributo por el idClimaExtendido, 
 * lo cual resulta mas sencillo de representar en JSON
 */

public class ClimaExtendidoForm {

	private Long id;

	@NotNull(message = "Debe seleccionar una fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message = "Sólo puede seleccionar una fecha futura")
	private LocalDate fecha;
	
	@NotNull(message = "El campo de porcentje de lluvia no puede quedar vacío")
	@Range(min=0, message = "El porcentaje de lluvia no puede ser menor a 0")
	private int porcentajeLluvia;
	
	@Range(min=0, message = "Los mm de lluvia no pueden ser menores a 0")
	private int mmLluvia;
	
	@Size(max=500, message = "La descripción no puede contener más de 500 caracteres")
	private String descripcion;
	
	@NotNull(message = "El campo ciudad no puede estar en blanco")
	private Long idCiudad;
	
	//Constructor vacío
	public ClimaExtendidoForm() {
		super();
	}
	
	//Constructor con parámetros
	public ClimaExtendidoForm(ClimaExtendido ce) {
		super();
		this.fecha=ce.getFecha();
		this.porcentajeLluvia=ce.getPorcentajeLluvia();
		this.mmLluvia=ce.getMmLluvia();
		this.idCiudad=ce.getCiudad().getId();
	}
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	public ClimaExtendido toPojo()
	{
		ClimaExtendido ce = new ClimaExtendido();
		ce.setFecha(this.getFecha());
		ce.setPorcentajeLluvia(this.getPorcentajeLluvia());
		ce.setMmLluvia(this.getMmLluvia());
		ce.setDescripcion(this.getDescripcion());
		return ce;
	}
	
	public LocalDate parseStringToLocalDate(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha_conv = LocalDate.parse(fecha, formatter);
        return fecha_conv;
	}
}