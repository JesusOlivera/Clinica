package com.certicom.scpf.domain;

import java.util.List;

public class TablaTablas {
	
	private Integer id_maestra;
	private String descripcion_maestra;
	private List<TablaTablasDetalle> listaTablaTablasDetalle;
	private Integer nro_catalogo;
	 //test
	public TablaTablas(){
	}
	
	public Integer getId_maestra() {
		return id_maestra;
	}
	public void setId_maestra(Integer id_maestra) {
		this.id_maestra = id_maestra;
	}
	public String getDescripcion_maestra() {
		return descripcion_maestra;
	}
	public void setDescripcion_maestra(String descripcion_maestra) {
		this.descripcion_maestra = descripcion_maestra;
	}

	public List<TablaTablasDetalle> getListaTablaTablasDetalle() {
		return listaTablaTablasDetalle;
	}

	public void setListaTablaTablasDetalle(
			List<TablaTablasDetalle> listaTablaTablasDetalle) {
		this.listaTablaTablasDetalle = listaTablaTablasDetalle;
	}

	public Integer getNro_catalogo() {
		return nro_catalogo;
	}

	public void setNro_catalogo(Integer nro_catalogo) {
		this.nro_catalogo = nro_catalogo;
	}
	
	
}
