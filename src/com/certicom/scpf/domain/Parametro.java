package com.certicom.scpf.domain; 

import java.util.Date;

public class Parametro implements java.io.Serializable 
{ 
	private static final long serialVersionUID = 1L; 
	private int cod_parametro; 
	private String nombre_parametro; 
	private String descripcion; 
	private Integer valor; 
	private boolean ind_activo; 
	private Date fecha_registro; 
	private Date fecha_modif; 

	public int getCod_parametro() {
		return cod_parametro;
	}

	public void setCod_parametro(int cod_parametro) {
		this.cod_parametro = cod_parametro;
	}

	public String getNombre_parametro() 
	{return nombre_parametro;} 
 
	public void setNombre_parametro(String nombre_parametro) 
	{this.nombre_parametro=nombre_parametro;} 
 
	public String getDescripcion() 
	{return descripcion;} 
 
	public void setDescripcion(String descripcion) 
	{this.descripcion=descripcion;} 
 
	public Integer getValor() 
	{return valor;} 
 
	public void setValor(Integer valor) 
	{this.valor=valor;} 
 
	public Date getFecha_registro() 
	{return fecha_registro;} 
 
	public void setFecha_registro(Date fecha_registro) 
	{this.fecha_registro=fecha_registro;} 
 
	public Date getFecha_modif() 
	{return fecha_modif;} 
 
	public void setFecha_modif(Date fecha_modif) 
	{this.fecha_modif=fecha_modif;}

	public boolean isInd_activo() {
		return ind_activo;
	}

	public void setInd_activo(boolean ind_activo) {
		this.ind_activo = ind_activo;
	} 
 
} 
