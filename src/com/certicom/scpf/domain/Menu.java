package com.certicom.scpf.domain;

import java.util.Date;

public class Menu implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long cod_menu;
	private Long cod_sistema;
	private Long cod_menu_padre;
	private String nombre;
	private String descripcion;
	private Long ind_activo;
	private Date fecha_registro;
	private Date fecha_modif;
	private String usuario_registro;
	private String usuario_modif;
	private Menu menuPadre;
	private boolean est;
	private String icono;
	private String action;
	
	//banderin indicador de precencia/ausencia  tabla t:opcion_menuxperfil
	private Boolean banderin;

	public Long getCod_menu() {
		return cod_menu;
	}

	public void setCod_menu(Long cod_menu) {
		this.cod_menu = cod_menu;
	}

	public Long getCod_sistema() {
		return cod_sistema;
	}

	public void setCod_sistema(Long cod_sistema) {
		this.cod_sistema = cod_sistema;
	}

	public Long getCod_menu_padre() {
		return cod_menu_padre;
	}

	public void setCod_menu_padre(Long cod_menu_padre) {
		this.cod_menu_padre = cod_menu_padre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getInd_activo() {
		return ind_activo;
	}

	public void setInd_activo(Long ind_activo) {
		this.ind_activo = ind_activo;
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

	public String getUsuario_registro() {
		return usuario_registro;
	}

	public void setUsuario_registro(String usuario_registro) {
		this.usuario_registro = usuario_registro;
	}

	public String getUsuario_modif() {
		return usuario_modif;
	}

	public void setUsuario_modif(String usuario_modif) {
		this.usuario_modif = usuario_modif;
	}

	public Menu getMenuPadre() {
		return menuPadre;
	}

	public void setMenuPadre(Menu menuPadre) {
		this.menuPadre = menuPadre;
	}

	public boolean isEst() {
		return est;
	}

	public void setEst(boolean est) {
		this.est = est;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Boolean getBanderin() {
		return banderin;
	}

	public void setBanderin(Boolean banderin) {
		this.banderin = banderin;
	}
	
	

}
