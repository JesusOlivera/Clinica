package com.certicom.scpf.helper;

import java.util.List;


public class ColumnasCartera {

	private String descripcion;
	private Integer orden;
	private String tipo;
	
	public ColumnasCartera(){;}
	
	public List<String> extractor(List<String> listaData ,Integer nroColumnaaExtraer)
	{
		return null;
	}
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
