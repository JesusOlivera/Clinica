package com.certicom.scpf.domain;

import java.util.List;



public class Emisor {
	
	private Integer id_emisor;
	private Integer id_domicilio_fiscal_cab;
	private String ruc;
	private String razon_social;
	private String nombre_comercial;
	private String direccion;
	private String telefono;
	private String slogan;
	private String ruta_logo;
	private String ruta_descarga_archivos_planos;
	
    
	private byte[] imagen; /*Jesús*/

	
	 //test
	
	/* transitorios */
	private String desDomicilio;
	private String descripcion_domicilio_fiscal;  /*Jesús*/
	
		
	public Emisor(){
	}
	
	public Integer getId_emisor() {
		return id_emisor;
	}
	
	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}
	
	public Integer getId_domicilio_fiscal_cab() {
		return id_domicilio_fiscal_cab;
	}
	
	public void setId_domicilio_fiscal_cab(Integer id_domicilio_fiscal_cab) {
		this.id_domicilio_fiscal_cab = id_domicilio_fiscal_cab;
	}
	
	public String getRuc() {
		return ruc;
	}
	
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	public String getRazon_social() {
		return razon_social;
	}
	
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	
	public String getNombre_comercial() {
		return nombre_comercial;
	}
	
	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}	
	
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getRuta_logo() {
		return ruta_logo;
	}
	
	public void setRuta_logo(String ruta_logo) {
		this.ruta_logo = ruta_logo;
	}
	
	public String getRuta_descarga_archivos_planos() {
		return ruta_descarga_archivos_planos;
	}
	
	public void setRuta_descarga_archivos_planos(
			String ruta_descarga_archivos_planos) {
		this.ruta_descarga_archivos_planos = ruta_descarga_archivos_planos;
	}

	public String getDesDomicilio() {
		return desDomicilio;
	}

	public void setDesDomicilio(String desDomicilio) {
		this.desDomicilio = desDomicilio;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion_domicilio_fiscal() {
		return descripcion_domicilio_fiscal;
	}

	public void setDescripcion_domicilio_fiscal(String descripcion_domicilio_fiscal) {
		this.descripcion_domicilio_fiscal = descripcion_domicilio_fiscal;
	}

	
	
	
}
