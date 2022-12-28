package com.tsti.smn.excepciones;

public class Excepcion extends Exception{

	private static final long serialVersionUID = 3941221036411842318L;

	/*
	 * Atributo al que está asociado el error
	 */
	private  String atributo;
	
	public Excepcion() {
		super();
		
	}

	public Excepcion(String mensaje) {
		super(mensaje);
	}

	public Excepcion(String mensaje, String atributo) {
		super(mensaje);
		this.atributo=atributo;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	

}
