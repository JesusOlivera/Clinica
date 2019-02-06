package com.certicom.scpf.domain;

import java.util.List;

public class DomicilioFiscal {
	
	private Integer id_domicilio_fiscal_cab;
	private String ubigeo;
	private String domicilio;
	private String descripcion_domicilio; /* Jesús */
	
	
	 //test
	public DomicilioFiscal(){
	}	
	
	public Integer getId_domicilio_fiscal_cab() {
		return id_domicilio_fiscal_cab;
	}

	public void setId_domicilio_fiscal_cab(Integer id_domicilio_fiscal_cab) {
		this.id_domicilio_fiscal_cab = id_domicilio_fiscal_cab;
	}

	public String getUbigeo() {
		return ubigeo;
	}
	
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDescripcion_domicilio() {
		return descripcion_domicilio;
	}

	public void setDescripcion_domicilio(String descripcion_domicilio) {
		this.descripcion_domicilio = descripcion_domicilio;
	}
	
	
	
}
