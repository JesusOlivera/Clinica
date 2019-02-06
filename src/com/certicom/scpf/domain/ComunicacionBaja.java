package com.certicom.scpf.domain;
import java.util.Date;
public class ComunicacionBaja {
	private Integer id_comprobante;
	private String tipo_comprobante;
	private Integer id_cliente;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_modo_pago;
	private Date fecha_comunicacion_cba;
	private String descripcion_motivo_cba;
	private String estado_sunat;
	
	private Date fecha_emision_cab;
	private String numero_serie_documento_cab;
	

	public Integer getId_comprobante() {
		return id_comprobante;
	}
	public void setId_comprobante(Integer id_comprobante) {
		this.id_comprobante = id_comprobante;
	}
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getId_emisor() {
		return id_emisor;
	}
	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}
	public Integer getId_domicilio_fiscal_cab() {
		return id_domicilio_fiscal_cab;
	}
	public void setId_domicilio_fiscal_cab(Integer id_domicilio_fiscal_cab) {
		this.id_domicilio_fiscal_cab = id_domicilio_fiscal_cab;
	}
	public Integer getId_modo_pago() {
		return id_modo_pago;
	}
	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}
	public Date getFecha_comunicacion_cba() {
		return fecha_comunicacion_cba;
	}
	public void setFecha_comunicacion_cba(Date fecha_comunicacion_cba) {
		this.fecha_comunicacion_cba = fecha_comunicacion_cba;
	}
	public String getDescripcion_motivo_cba() {
		return descripcion_motivo_cba;
	}
	public void setDescripcion_motivo_cba(String descripcion_motivo_cba) {
		this.descripcion_motivo_cba = descripcion_motivo_cba;
	}
	public String getEstado_sunat() {
		return estado_sunat;
	}
	public void setEstado_sunat(String estado_sunat) {
		this.estado_sunat = estado_sunat;
	}
	public Date getFecha_emision_cab() {
		return fecha_emision_cab;
	}
	public void setFecha_emision_cab(Date fecha_emision_cab) {
		this.fecha_emision_cab = fecha_emision_cab;
	}
	public String getNumero_serie_documento_cab() {
		return numero_serie_documento_cab;
	}
	public void setNumero_serie_documento_cab(String numero_serie_documento_cab) {
		this.numero_serie_documento_cab = numero_serie_documento_cab;
	}

	
}
