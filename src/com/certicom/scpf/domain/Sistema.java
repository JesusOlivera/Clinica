package com.certicom.scpf.domain;

import java.util.Date;
import java.util.List;

public class Sistema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long cod_sistema;
	private String nombre_sistema;
	private String descripcion;
	private Integer ind_activo;
	private Date fecha_registro;
	private Date fecha_modif;
	//lista de modulos
	private List<Menu> listaMenu;


	public String getNombre_sistema() {
		return nombre_sistema;
	}

	public void setNombre_sistema(String nombre_sistema) {
		this.nombre_sistema = nombre_sistema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Date getFecha_modif() {
		return fecha_modif;
	}

	public void setFecha_modif(Date fecha_modif) {
		this.fecha_modif = fecha_modif;
	}

	public Integer getInd_activo() {
		return ind_activo;
	}

	public void setInd_activo(Integer ind_activo) {
		this.ind_activo = ind_activo;
	}

	public Long getCod_sistema() {
		return cod_sistema;
	}

	public void setCod_sistema(Long cod_sistema) {
		this.cod_sistema = cod_sistema;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}


}
