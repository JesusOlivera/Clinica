package com.certicom.scpf.domain;

import java.io.Serializable;
import java.util.Date;

public class UsuarioPerfil implements Serializable{
	
	private static final long serialVersionUID = 5775669960242427243L;
	
	private Integer idusuario;
	private Integer cod_perfil;
	private String nombre;
	private String descripcion;
	private Boolean ind_activo;
	private Date fecha_iniciovig;
	private Date fecha_finvig;
	private Date fecha_registro;
	private Date fecha_modif;
	private String usuario_registro;
	private String usuario_modif;
	

	

	public UsuarioPerfil(){
		
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}


	public Integer getCod_perfil() {
		return cod_perfil;
	}


	public void setCod_perfil(Integer cod_perfil) {
		this.cod_perfil = cod_perfil;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Boolean getInd_activo() {
		return ind_activo;
	}


	public void setInd_activo(Boolean ind_activo) {
		this.ind_activo = ind_activo;
	}


	public Date getFecha_iniciovig() {
		return fecha_iniciovig;
	}


	public void setFecha_iniciovig(Date fecha_iniciovig) {
		this.fecha_iniciovig = fecha_iniciovig;
	}


	public Date getFecha_finvig() {
		return fecha_finvig;
	}


	public void setFecha_finvig(Date fecha_finvig) {
		this.fecha_finvig = fecha_finvig;
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

}
