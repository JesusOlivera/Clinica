package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Comprobante {
	
	private Integer id_comprobante;
	private String tipo_comprobante;
	private Integer id_cliente;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal_cab;
	private String tipo_operacion_cab;
	private Date fecha_emision_cab;
	private Date hora_emision_cab;
	private Date fecha_vencimiento_cab;
	private String tipo_moneda_cab;
	private BigDecimal suma_tributos_cab;
	private BigDecimal total_valor_venta_cab;
	private BigDecimal total_precio_venta_cab;
	private BigDecimal total_descuentos_cab;
	private BigDecimal suma_otros_cargos_cab;
	private BigDecimal total_anticipos_cab;
	private BigDecimal importe_total_venta_cab;
	private String tipo_comprobante_afecta_not;
	private String archivo_plano_nombre;
	private String archivo_plano_contenido;
	private String estado_sunat;
	private String tipo_tipo_nota_not;
	private String numero_serie_documento_cab;
	private String descripcion_motivo_not;
	private Integer id_modo_pago;
	private Integer tipo_docu_iden_cab;
	
	/***MODIFICACION JCTV ***/
	private Boolean estado_comunicacion_baja;
	private Cliente cliente;
	private String descripcion_tipo_comprobante;
	private String descripcion_tipo_operacion;
	private DomicilioFiscal domicilioFiscal;
	private Integer correlativo;
	private String version_ubl;
	private String estado_respuesta_sunat;
	private String descripcion_respuesta_sunat;
	private Emisor emisor;
	private Integer id_ticket;
	
	private Integer id_vendedor; /*Vega.com*/
	private Vendedor vendedor; /*Vega.com*/
	private ModoPago modoPago; /*Modo de Pago */
	
	 //test
	public Comprobante(){
	}

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

	public String getTipo_operacion_cab() {
		return tipo_operacion_cab;
	}

	public void setTipo_operacion_cab(String tipo_operacion_cab) {
		this.tipo_operacion_cab = tipo_operacion_cab;
	}

	public Date getFecha_emision_cab() {
		return fecha_emision_cab;
	}

	public void setFecha_emision_cab(Date fecha_emision_cab) {
		this.fecha_emision_cab = fecha_emision_cab;
	}

	public Date getHora_emision_cab() {
		return hora_emision_cab;
	}

	public void setHora_emision_cab(Date hora_emision_cab) {
		this.hora_emision_cab = hora_emision_cab;
	}

	public Date getFecha_vencimiento_cab() {
		return fecha_vencimiento_cab;
	}

	public void setFecha_vencimiento_cab(Date fecha_vencimiento_cab) {
		this.fecha_vencimiento_cab = fecha_vencimiento_cab;
	}

	public String getTipo_moneda_cab() {
		return tipo_moneda_cab;
	}

	public void setTipo_moneda_cab(String tipo_moneda_cab) {
		this.tipo_moneda_cab = tipo_moneda_cab;
	}

	public BigDecimal getSuma_tributos_cab() {
		return suma_tributos_cab;
	}

	public void setSuma_tributos_cab(BigDecimal suma_tributos_cab) {
		this.suma_tributos_cab = suma_tributos_cab;
	}

	public BigDecimal getTotal_valor_venta_cab() {
		return total_valor_venta_cab;
	}

	public void setTotal_valor_venta_cab(BigDecimal total_valor_venta_cab) {
		this.total_valor_venta_cab = total_valor_venta_cab;
	}

	public BigDecimal getTotal_precio_venta_cab() {
		return total_precio_venta_cab;
	}

	public void setTotal_precio_venta_cab(BigDecimal total_precio_venta_cab) {
		this.total_precio_venta_cab = total_precio_venta_cab;
	}

	public BigDecimal getTotal_descuentos_cab() {
		return total_descuentos_cab;
	}

	public void setTotal_descuentos_cab(BigDecimal total_descuentos_cab) {
		this.total_descuentos_cab = total_descuentos_cab;
	}

	public BigDecimal getSuma_otros_cargos_cab() {
		return suma_otros_cargos_cab;
	}

	public void setSuma_otros_cargos_cab(BigDecimal suma_otros_cargos_cab) {
		this.suma_otros_cargos_cab = suma_otros_cargos_cab;
	}

	public BigDecimal getTotal_anticipos_cab() {
		return total_anticipos_cab;
	}

	public void setTotal_anticipos_cab(BigDecimal total_anticipos_cab) {
		this.total_anticipos_cab = total_anticipos_cab;
	}

	public BigDecimal getImporte_total_venta_cab() {
		return importe_total_venta_cab;
	}

	public void setImporte_total_venta_cab(BigDecimal importe_total_venta_cab) {
		this.importe_total_venta_cab = importe_total_venta_cab;
	}

	public String getTipo_comprobante_afecta_not() {
		return tipo_comprobante_afecta_not;
	}

	public void setTipo_comprobante_afecta_not(String tipo_comprobante_afecta_not) {
		this.tipo_comprobante_afecta_not = tipo_comprobante_afecta_not;
	}

	public String getArchivo_plano_nombre() {
		return archivo_plano_nombre;
	}

	public void setArchivo_plano_nombre(String archivo_plano_nombre) {
		this.archivo_plano_nombre = archivo_plano_nombre;
	}

	public String getArchivo_plano_contenido() {
		return archivo_plano_contenido;
	}

	public void setArchivo_plano_contenido(String archivo_plano_contenido) {
		this.archivo_plano_contenido = archivo_plano_contenido;
	}

	public String getEstado_sunat() {
		return estado_sunat;
	}

	public void setEstado_sunat(String estado_sunat) {
		this.estado_sunat = estado_sunat;
	}

	public String getTipo_tipo_nota_not() {
		return tipo_tipo_nota_not;
	}

	public void setTipo_tipo_nota_not(String tipo_tipo_nota_not) {
		this.tipo_tipo_nota_not = tipo_tipo_nota_not;
	}

	public String getNumero_serie_documento_cab() {
		return numero_serie_documento_cab;
	}

	public void setNumero_serie_documento_cab(String numero_serie_documento_cab) {
		this.numero_serie_documento_cab = numero_serie_documento_cab;
	}

	public String getDescripcion_motivo_not() {
		return descripcion_motivo_not;
	}

	public void setDescripcion_motivo_not(String descripcion_motivo_not) {
		this.descripcion_motivo_not = descripcion_motivo_not;
	}


	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}

	public Integer getTipo_docu_iden_cab() {
		return tipo_docu_iden_cab;
	}

	public void setTipo_docu_iden_cab(Integer tipo_docu_iden_cab) {
		this.tipo_docu_iden_cab = tipo_docu_iden_cab;
	}

	public Boolean getEstado_comunicacion_baja() {
		return estado_comunicacion_baja;
	}

	public void setEstado_comunicacion_baja(Boolean estado_comunicacion_baja) {
		this.estado_comunicacion_baja = estado_comunicacion_baja;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescripcion_tipo_comprobante() {
		return descripcion_tipo_comprobante;
	}

	public void setDescripcion_tipo_comprobante(String descripcion_tipo_comprobante) {
		this.descripcion_tipo_comprobante = descripcion_tipo_comprobante;
	}

	public String getDescripcion_tipo_operacion() {
		return descripcion_tipo_operacion;
	}

	public void setDescripcion_tipo_operacion(String descripcion_tipo_operacion) {
		this.descripcion_tipo_operacion = descripcion_tipo_operacion;
	}

	public DomicilioFiscal getDomicilioFiscal() {
		return domicilioFiscal;
	}

	public void setDomicilioFiscal(DomicilioFiscal domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}

	public String getEstado_respuesta_sunat() {
		return estado_respuesta_sunat;
	}

	public void setEstado_respuesta_sunat(String estado_respuesta_sunat) {
		this.estado_respuesta_sunat = estado_respuesta_sunat;
	}

	public String getDescripcion_respuesta_sunat() {
		return descripcion_respuesta_sunat;
	}

	public void setDescripcion_respuesta_sunat(String descripcion_respuesta_sunat) {
		this.descripcion_respuesta_sunat = descripcion_respuesta_sunat;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public String getVersion_ubl() {
		return version_ubl;
	}

	public void setVersion_ubl(String version_ubl) {
		this.version_ubl = version_ubl;
	}

	public Emisor getEmisor() {
		return emisor;
	}

	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public ModoPago getModoPago() {
		return modoPago;
	}

	public void setModoPago(ModoPago modoPago) {
		this.modoPago = modoPago;
	}

	public Integer getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(Integer id_ticket) {
		this.id_ticket = id_ticket;
	}	
	
	
	
}
