package com.certicom.scpf.domain;

import java.util.Date;

public class Log {
	
	private long pk_log_id;
	private int idusuario;
	private int cod_menu;
	private String accion;
	private String descripcion;
	private String ip_client;
	private String host_client;
	private Date fecha;
	private String hora;
	private int anio;
	private String ip_server_img;
	
	/*Para Tabla Log*/
	private String login;
	private String nombre_opMenu;
	private String modulo;
	
	/*Para mes */
	private int mes;
	
	private boolean band=false;
	
	public long getPk_log_id() {
		return pk_log_id;
	}
	public void setPk_log_id(long pk_log_id) {
		this.pk_log_id = pk_log_id;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public int getCod_menu() {
		return cod_menu;
	}
	public void setCod_menu(int cod_menu) {
		this.cod_menu = cod_menu;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getIp_client() {
		return ip_client;
	}
	public void setIp_client(String ip_client) {
		this.ip_client = ip_client;
	}

	public String getHost_client() {
		return host_client;
	}
	public void setHost_client(String host_client) {
		this.host_client = host_client;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public boolean isBand() {
		return band;
	}
	public void setBand(boolean band) {
		this.band = band;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNombre_opMenu() {
		return nombre_opMenu;
	}
	public void setNombre_opMenu(String nombre_opMenu) {
		this.nombre_opMenu = nombre_opMenu;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getIp_server_img() {
		return ip_server_img;
	}
	public void setIp_server_img(String ip_server_img) {
		this.ip_server_img = ip_server_img;
	}
	
	

}
