package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class ComprobanteDetalle {
	
	private Integer id_comprobante;
	private String tipo_comprobante;
	private Integer id_cliente;
	private Integer id_producto;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal_cab;
	private Integer tipo_unidad_medida_det;
	private BigDecimal cant_unidades_item_det;
	private BigDecimal suma_tributos_det;
	private BigDecimal precio_venta_unitario_det;
	private BigDecimal valor_venta_item_det;
	private BigDecimal valor_referencial_unit_det;
	private String archivo_plano_nombre;
	private String archivo_plano_contenido;
	
	private Integer id_modo_pago;
	/* transitorio */
	
	private Producto producto;
	private TributoProducto tpISC;
	private TributoProducto tpIGV;
	private TributoProducto tpOtros;
	private BigDecimal montoISC;
	private BigDecimal montoIGV;
	private BigDecimal montoOtros;
	
	private String numeroSerie; 
	
	 //test
	public ComprobanteDetalle(){
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

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
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

	public Integer getTipo_unidad_medida_det() {
		return tipo_unidad_medida_det;
	}

	public void setTipo_unidad_medida_det(Integer tipo_unidad_medida_det) {
		this.tipo_unidad_medida_det = tipo_unidad_medida_det;
	}

     

	public BigDecimal getCant_unidades_item_det() {
		return cant_unidades_item_det;
	}

	public void setCant_unidades_item_det(BigDecimal cant_unidades_item_det) {
		this.cant_unidades_item_det = cant_unidades_item_det;
	}

	public BigDecimal getSuma_tributos_det() {
		return suma_tributos_det;
	}

	public void setSuma_tributos_det(BigDecimal suma_tributos_det) {
		this.suma_tributos_det = suma_tributos_det;
	}

	public BigDecimal getPrecio_venta_unitario_det() {
		return precio_venta_unitario_det;
	}

	public void setPrecio_venta_unitario_det(BigDecimal precio_venta_unitario_det) {
		this.precio_venta_unitario_det = precio_venta_unitario_det;
	}

	public BigDecimal getValor_venta_item_det() {
		return valor_venta_item_det;
	}

	public void setValor_venta_item_det(BigDecimal valor_venta_item_det) {
		this.valor_venta_item_det = valor_venta_item_det;
	}

	public BigDecimal getValor_referencial_unit_det() {
		return valor_referencial_unit_det;
	}

	public void setValor_referencial_unit_det(BigDecimal valor_referencial_unit_det) {
		this.valor_referencial_unit_det = valor_referencial_unit_det;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public TributoProducto getTpISC() {
		return tpISC;
	}

	public void setTpISC(TributoProducto tpISC) {
		this.tpISC = tpISC;
	}

	public TributoProducto getTpIGV() {
		return tpIGV;
	}

	public void setTpIGV(TributoProducto tpIGV) {
		this.tpIGV = tpIGV;
	}

	public TributoProducto getTpOtros() {
		return tpOtros;
	}

	public void setTpOtros(TributoProducto tpOtros) {
		this.tpOtros = tpOtros;
	}

	public BigDecimal getMontoISC() {
		return montoISC;
	}

	public void setMontoISC(BigDecimal montoISC) {
		this.montoISC = montoISC;
	}

	public BigDecimal getMontoIGV() {
		return montoIGV;
	}

	public void setMontoIGV(BigDecimal montoIGV) {
		this.montoIGV = montoIGV;
	}

	public BigDecimal getMontoOtros() {
		return montoOtros;
	}

	public void setMontoOtros(BigDecimal montoOtros) {
		this.montoOtros = montoOtros;
	}

	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}	
	
	
	
}
