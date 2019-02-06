package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class TributoProducto {
	
	private Integer id_producto;
	private Integer tipo_tributo_det;
	private Integer tipo_afectacion_igv_det;
	private Integer tipo_sistema_isc_det;
	private BigDecimal porcentaje_det;
	private String tipo_tributo_inter_det;
	private Boolean asignado;
	private String tipo_tributo_nombre_det;
	private String tipo_tributo_codigo_det;
	
	/* transitorio */
	private String descTT;
	
	 //test
	public TributoProducto(){
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getTipo_tributo_det() {
		return tipo_tributo_det;
	}

	public void setTipo_tributo_det(Integer tipo_tributo_det) {
		this.tipo_tributo_det = tipo_tributo_det;
	}

	public Integer getTipo_sistema_isc_det() {
		return tipo_sistema_isc_det;
	}

	public void setTipo_sistema_isc_det(Integer tipo_sistema_isc_det) {
		this.tipo_sistema_isc_det = tipo_sistema_isc_det;
	}

	public BigDecimal getPorcentaje_det() {
		return porcentaje_det;
	}

	public void setPorcentaje_det(BigDecimal porcentaje_det) {
		this.porcentaje_det = porcentaje_det;
	}	

	public Integer getTipo_afectacion_igv_det() {
		return tipo_afectacion_igv_det;
	}

	public void setTipo_afectacion_igv_det(Integer tipo_afectacion_igv_det) {
		this.tipo_afectacion_igv_det = tipo_afectacion_igv_det;
	}

	public String getTipo_tributo_inter_det() {
		return tipo_tributo_inter_det;
	}

	public void setTipo_tributo_inter_det(String tipo_tributo_inter_det) {
		this.tipo_tributo_inter_det = tipo_tributo_inter_det;
	}

	public String getTipo_tributo_nombre_det() {
		return tipo_tributo_nombre_det;
	}

	public void setTipo_tributo_nombre_det(String tipo_tributo_nombre_det) {
		this.tipo_tributo_nombre_det = tipo_tributo_nombre_det;
	}

	public String getTipo_tributo_codigo_det() {
		return tipo_tributo_codigo_det;
	}

	public void setTipo_tributo_codigo_det(String tipo_tributo_codigo_det) {
		this.tipo_tributo_codigo_det = tipo_tributo_codigo_det;
	}

	public Boolean getAsignado() {
		return asignado;
	}

	public void setAsignado(Boolean asignado) {
		this.asignado = asignado;
	}

	public String getDescTT() {
		return descTT;
	}

	public void setDescTT(String descTT) {
		this.descTT = descTT;
	}
	
	
	
}
