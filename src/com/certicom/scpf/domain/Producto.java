package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class Producto {
	
	private Integer id_producto;
	private String cod_prod_det;
	private Integer tipo_prod_sunat_det;
	private String descripcion_prod_det;
	private BigDecimal valor_unitario_prod_det;
	private String unidad_medida_det; //Jesús
	private Integer stock;
	private boolean valor_unit_incluye_impuestos;
	
	/* transitorio */
	
	private String desProductoSunat;
	private String desUnidadMedida;
	private BigDecimal precio_final_editado_cliente; //Jesús
	
	private Boolean genera_cola;
	private Boolean  genera_historia_clinica;
	private Boolean  genera_ticket ;
	private Boolean  examen_auxiliar;
	
	private Integer id_tipo_servicio;
	private TipoServicio tipoServicio;
	
	 //test
	public Producto(){
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getCod_prod_det() {
		return cod_prod_det;
	}

	public void setCod_prod_det(String cod_prod_det) {
		this.cod_prod_det = cod_prod_det;
	}

	public Integer getTipo_prod_sunat_det() {
		return tipo_prod_sunat_det;
	}

	public void setTipo_prod_sunat_det(Integer tipo_prod_sunat_det) {
		this.tipo_prod_sunat_det = tipo_prod_sunat_det;
	}

	public String getDescripcion_prod_det() {
		return descripcion_prod_det;
	}

	public void setDescripcion_prod_det(String descripcion_prod_det) {
		this.descripcion_prod_det = descripcion_prod_det;
	}

	public BigDecimal getValor_unitario_prod_det() {
		return valor_unitario_prod_det;
	}

	public void setValor_unitario_prod_det(BigDecimal valor_unitario_prod_det) {
		this.valor_unitario_prod_det = valor_unitario_prod_det;
	}

    

	public String getUnidad_medida_det() {
		return unidad_medida_det;
	}

	public void setUnidad_medida_det(String unidad_medida_det) {
		this.unidad_medida_det = unidad_medida_det;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDesProductoSunat() {
		return desProductoSunat;
	}

	public void setDesProductoSunat(String desProductoSunat) {
		this.desProductoSunat = desProductoSunat;
	}

	public String getDesUnidadMedida() {
		return desUnidadMedida;
	}

	public void setDesUnidadMedida(String desUnidadMedida) {
		this.desUnidadMedida = desUnidadMedida;
	}

	public boolean isValor_unit_incluye_impuestos() {
		return valor_unit_incluye_impuestos;
	}

	public void setValor_unit_incluye_impuestos(boolean valor_unit_incluye_impuestos) {
		this.valor_unit_incluye_impuestos = valor_unit_incluye_impuestos;
	}

	public BigDecimal getPrecio_final_editado_cliente() {
		return precio_final_editado_cliente;
	}

	public void setPrecio_final_editado_cliente(BigDecimal precio_final_editado_cliente) {
		this.precio_final_editado_cliente = precio_final_editado_cliente;
	}

	public Boolean getGenera_cola() {
		return genera_cola;
	}

	public void setGenera_cola(Boolean genera_cola) {
		this.genera_cola = genera_cola;
	}

	public Boolean getGenera_historia_clinica() {
		return genera_historia_clinica;
	}

	public void setGenera_historia_clinica(Boolean genera_historia_clinica) {
		this.genera_historia_clinica = genera_historia_clinica;
	}

	public Boolean getGenera_ticket() {
		return genera_ticket;
	}

	public void setGenera_ticket(Boolean genera_ticket) {
		this.genera_ticket = genera_ticket;
	}

	public Boolean getExamen_auxiliar() {
		return examen_auxiliar;
	}

	public void setExamen_auxiliar(Boolean examen_auxiliar) {
		this.examen_auxiliar = examen_auxiliar;
	}

	public Integer getId_tipo_servicio() {
		return id_tipo_servicio;
	}

	public void setId_tipo_servicio(Integer id_tipo_servicio) {
		this.id_tipo_servicio = id_tipo_servicio;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	
	
	
	
}
