package com.certicom.scpf.domain;

public class TablaTablasDetalle {
	
	private Integer id_codigo;
	private Integer id_maestra;
	private String descripcion_largo;
	private String descripcion_corto;
	private String valor;
	private String atributo_1;
	private String atributo_2;
	private String atributo_3;
	
	private String codigo_catalogo; //Jesús
	private boolean ind_activo;
	
	 //test
	public TablaTablasDetalle(){
	}
	
	public Integer getId_maestra() {
		return id_maestra;
	}
	public void setId_maestra(Integer id_maestra) {
		this.id_maestra = id_maestra;
	}

	public Integer getId_codigo() {
		return id_codigo;
	}

	public void setId_codigo(Integer id_codigo) {
		this.id_codigo = id_codigo;
	}

	public String getDescripcion_largo() {
		return descripcion_largo;
	}

	public void setDescripcion_largo(String descripcion_largo) {
		this.descripcion_largo = descripcion_largo;
	}

	public String getDescripcion_corto() {
		return descripcion_corto;
	}

	public void setDescripcion_corto(String descripcion_corto) {
		this.descripcion_corto = descripcion_corto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getAtributo_1() {
		return atributo_1;
	}

	public void setAtributo_1(String atributo_1) {
		this.atributo_1 = atributo_1;
	}

	public String getAtributo_2() {
		return atributo_2;
	}

	public void setAtributo_2(String atributo_2) {
		this.atributo_2 = atributo_2;
	}

	public String getAtributo_3() {
		return atributo_3;
	}

	public void setAtributo_3(String atributo_3) {
		this.atributo_3 = atributo_3;
	}

	public String getCodigo_catalogo() {
		return codigo_catalogo;
	}

	public void setCodigo_catalogo(String codigo_catalogo) {
		this.codigo_catalogo = codigo_catalogo;
	}

	public boolean isInd_activo() {
		return ind_activo;
	}

	public void setInd_activo(boolean ind_activo) {
		this.ind_activo = ind_activo;
	}
	
	
	
}
