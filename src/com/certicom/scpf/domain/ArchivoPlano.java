package com.certicom.scpf.domain;

public class ArchivoPlano {
	
   /*Everd Jesús Estructura de archivos planos en el orden*/
	
	/*.CAB*/
	
	private String tipo_operacion_cab; 
	private String fecha_emision_cab; 
	private String hora_emision_cab; 
	private String fecha_vencimiento_cab; 
	private String domicilio_fiscal_cab; 
	private String tipo_docu_iden_cab; 
	private String numero_docu_iden_cab;
	private String nombre_cab;
	private String tipo_moneda_cab; 
	private String suma_tributos_cab;
	private String total_valor_venta_cab; 
	private String total_precio_venta_cab; 
	private String total_descuentos_cab; 
	private String suma_otros_cargos_cab; 
	private String total_anticipos_cab; 
	private String importe_total_venta_cab; 
	private String version_ubl_cab; 
	private String customizacion_documento_cab; 
	
	
	/* .DET */
    private String unidad_medida_det; 
    private String cant_unidades_item_det; 
    private String cod_prod_det; 
    private String tipo_prod_sunat_det; 
    private String descripcion_prod_det;
    private String valor_unitario_prod_det; 
    private String suma_tributos_det; 
    
    private String tipo_tributo_igv_det; 
    private String monto_tributo_igv_det; 
    private String base_imponible_igv_det; 
    private String tipo_tributo_nombre_igv_det; 
    private String tipo_tributo_inter_igv_det; 
    private String tipo_afectacion_igv_det; 
    private String porcentaje_igv_det; 
    
    private String tipo_tributo_isc_det; 
    private String monto_tributo_isc_det; 
    private String base_imponible_isc_det; 
    private String tipo_tributo_nombre_isc_det; 
    private String tipo_tributo_inter_isc_det; 
    private String tipo_sistema_isc_det; 
    private String porcentaje_isc_det; 
    
    private String tipo_tributo_otros_det; 
    private String monto_tributo_otros_det; 
    private String base_imponible_otros_det; 
    private String tipo_tributo_nombre_otros_det; 
    private String tipo_tributo_inter_otros_det; 
    private String porcentaje_otros_det; 
    
    private String precio_venta_unitario_det; 
    private String valor_venta_item_det; 
    private String valor_referencial_unit_det; 
     
	
    /* .CBA */
    
    private String fecha_emision_cba; 
    private String fecha_comunicacion_cba; 
    private String tipo_comprobante_cba; 
    private String numero_serie_documento_cba; 
    private String descripcion_motivo_cba; 
    
    
    /* .NOT */
    
    private String tipo_operacion_not; 
    private String fecha_emision_not;
    private String hora_emision_not; 
    private String domicilio_fiscal_not; 
    private String tipo_docu_iden_not; 
    private String numero_docu_iden_not; 
    private String nombre_not; 
    private String tipo_moneda_not; 
    private String tipo_tipo_nota_not; 
    private String descripcion_motivo_not; 
    private String tipo_comprobante_afecta_not; 
    private String numero_serie_documento_not; 
    private String suma_tributos_not; 
    private String total_valor_venta_not; 
    private String total_precio_venta_not; 
    private String total_descuentos_not; 
    private String suma_otros_cargos_not; 
    private String total_anticipos_not; 
    private String importe_total_venta_not; 
    private String version_ubl_not; 
    private String customizacion_documento_not; 
    
    /* .TRI */
    
    private String codigo_catalogo_tri; 
    private String descripcion_corto_tri; 
    private String atributo_1_tri; 
    private String total_valor_venta_tri; 
    private String suma_tributos_tri; 
    
    /* .LEY */
    
    private String codigo_catalogo_ley;
    private String descripcion_leyenda_ley;
    
    
    
    /*Set and Get*/
    
    
	public String getTipo_operacion_cab() {
		return tipo_operacion_cab;
	}
	public void setTipo_operacion_cab(String tipo_operacion_cab) {
		this.tipo_operacion_cab = tipo_operacion_cab;
	}
	public String getFecha_emision_cab() {
		return fecha_emision_cab;
	}
	public void setFecha_emision_cab(String fecha_emision_cab) {
		this.fecha_emision_cab = fecha_emision_cab;
	}
	public String getHora_emision_cab() {
		return hora_emision_cab;
	}
	public void setHora_emision_cab(String hora_emision_cab) {
		this.hora_emision_cab = hora_emision_cab;
	}
	public String getFecha_vencimiento_cab() {
		return fecha_vencimiento_cab;
	}
	public void setFecha_vencimiento_cab(String fecha_vencimiento_cab) {
		this.fecha_vencimiento_cab = fecha_vencimiento_cab;
	}
	public String getDomicilio_fiscal_cab() {
		return domicilio_fiscal_cab;
	}
	public void setDomicilio_fiscal_cab(String domicilio_fiscal_cab) {
		this.domicilio_fiscal_cab = domicilio_fiscal_cab;
	}
	public String getTipo_docu_iden_cab() {
		return tipo_docu_iden_cab;
	}
	public void setTipo_docu_iden_cab(String tipo_docu_iden_cab) {
		this.tipo_docu_iden_cab = tipo_docu_iden_cab;
	}
	public String getNumero_docu_iden_cab() {
		return numero_docu_iden_cab;
	}
	public void setNumero_docu_iden_cab(String numero_docu_iden_cab) {
		this.numero_docu_iden_cab = numero_docu_iden_cab;
	}
	public String getNombre_cab() {
		return nombre_cab;
	}
	public void setNombre_cab(String nombre_cab) {
		this.nombre_cab = nombre_cab;
	}
	public String getTipo_moneda_cab() {
		return tipo_moneda_cab;
	}
	public void setTipo_moneda_cab(String tipo_moneda_cab) {
		this.tipo_moneda_cab = tipo_moneda_cab;
	}
	public String getSuma_tributos_cab() {
		return suma_tributos_cab;
	}
	public void setSuma_tributos_cab(String suma_tributos_cab) {
		this.suma_tributos_cab = suma_tributos_cab;
	}
	public String getTotal_valor_venta_cab() {
		return total_valor_venta_cab;
	}
	public void setTotal_valor_venta_cab(String total_valor_venta_cab) {
		this.total_valor_venta_cab = total_valor_venta_cab;
	}
	public String getTotal_precio_venta_cab() {
		return total_precio_venta_cab;
	}
	public void setTotal_precio_venta_cab(String total_precio_venta_cab) {
		this.total_precio_venta_cab = total_precio_venta_cab;
	}
	public String getTotal_descuentos_cab() {
		return total_descuentos_cab;
	}
	public void setTotal_descuentos_cab(String total_descuentos_cab) {
		this.total_descuentos_cab = total_descuentos_cab;
	}
	public String getSuma_otros_cargos_cab() {
		return suma_otros_cargos_cab;
	}
	public void setSuma_otros_cargos_cab(String suma_otros_cargos_cab) {
		this.suma_otros_cargos_cab = suma_otros_cargos_cab;
	}
	public String getTotal_anticipos_cab() {
		return total_anticipos_cab;
	}
	public void setTotal_anticipos_cab(String total_anticipos_cab) {
		this.total_anticipos_cab = total_anticipos_cab;
	}
	public String getImporte_total_venta_cab() {
		return importe_total_venta_cab;
	}
	public void setImporte_total_venta_cab(String importe_total_venta_cab) {
		this.importe_total_venta_cab = importe_total_venta_cab;
	}
	public String getVersion_ubl_cab() {
		return version_ubl_cab;
	}
	public void setVersion_ubl_cab(String version_ubl_cab) {
		this.version_ubl_cab = version_ubl_cab;
	}
	public String getCustomizacion_documento_cab() {
		return customizacion_documento_cab;
	}
	public void setCustomizacion_documento_cab(String customizacion_documento_cab) {
		this.customizacion_documento_cab = customizacion_documento_cab;
	}
	public String getUnidad_medida_det() {
		return unidad_medida_det;
	}
	public void setUnidad_medida_det(String unidad_medida_det) {
		this.unidad_medida_det = unidad_medida_det;
	}
	public String getCant_unidades_item_det() {
		return cant_unidades_item_det;
	}
	public void setCant_unidades_item_det(String cant_unidades_item_det) {
		this.cant_unidades_item_det = cant_unidades_item_det;
	}
	public String getCod_prod_det() {
		return cod_prod_det;
	}
	public void setCod_prod_det(String cod_prod_det) {
		this.cod_prod_det = cod_prod_det;
	}
	public String getTipo_prod_sunat_det() {
		return tipo_prod_sunat_det;
	}
	public void setTipo_prod_sunat_det(String tipo_prod_sunat_det) {
		this.tipo_prod_sunat_det = tipo_prod_sunat_det;
	}
	public String getDescripcion_prod_det() {
		return descripcion_prod_det;
	}
	public void setDescripcion_prod_det(String descripcion_prod_det) {
		this.descripcion_prod_det = descripcion_prod_det;
	}
	public String getValor_unitario_prod_det() {
		return valor_unitario_prod_det;
	}
	public void setValor_unitario_prod_det(String valor_unitario_prod_det) {
		this.valor_unitario_prod_det = valor_unitario_prod_det;
	}
	public String getSuma_tributos_det() {
		return suma_tributos_det;
	}
	public void setSuma_tributos_det(String suma_tributos_det) {
		this.suma_tributos_det = suma_tributos_det;
	}
	public String getTipo_tributo_igv_det() {
		return tipo_tributo_igv_det;
	}
	public void setTipo_tributo_igv_det(String tipo_tributo_igv_det) {
		this.tipo_tributo_igv_det = tipo_tributo_igv_det;
	}
	public String getMonto_tributo_igv_det() {
		return monto_tributo_igv_det;
	}
	public void setMonto_tributo_igv_det(String monto_tributo_igv_det) {
		this.monto_tributo_igv_det = monto_tributo_igv_det;
	}
	public String getBase_imponible_igv_det() {
		return base_imponible_igv_det;
	}
	public void setBase_imponible_igv_det(String base_imponible_igv_det) {
		this.base_imponible_igv_det = base_imponible_igv_det;
	}
	public String getTipo_tributo_nombre_igv_det() {
		return tipo_tributo_nombre_igv_det;
	}
	public void setTipo_tributo_nombre_igv_det(String tipo_tributo_nombre_igv_det) {
		this.tipo_tributo_nombre_igv_det = tipo_tributo_nombre_igv_det;
	}
	public String getTipo_tributo_inter_igv_det() {
		return tipo_tributo_inter_igv_det;
	}
	public void setTipo_tributo_inter_igv_det(String tipo_tributo_inter_igv_det) {
		this.tipo_tributo_inter_igv_det = tipo_tributo_inter_igv_det;
	}
	public String getTipo_afectacion_igv_det() {
		return tipo_afectacion_igv_det;
	}
	public void setTipo_afectacion_igv_det(String tipo_afectacion_igv_det) {
		this.tipo_afectacion_igv_det = tipo_afectacion_igv_det;
	}
	public String getPorcentaje_igv_det() {
		return porcentaje_igv_det;
	}
	public void setPorcentaje_igv_det(String porcentaje_igv_det) {
		this.porcentaje_igv_det = porcentaje_igv_det;
	}
	public String getTipo_tributo_isc_det() {
		return tipo_tributo_isc_det;
	}
	public void setTipo_tributo_isc_det(String tipo_tributo_isc_det) {
		this.tipo_tributo_isc_det = tipo_tributo_isc_det;
	}
	public String getMonto_tributo_isc_det() {
		return monto_tributo_isc_det;
	}
	public void setMonto_tributo_isc_det(String monto_tributo_isc_det) {
		this.monto_tributo_isc_det = monto_tributo_isc_det;
	}
	public String getBase_imponible_isc_det() {
		return base_imponible_isc_det;
	}
	public void setBase_imponible_isc_det(String base_imponible_isc_det) {
		this.base_imponible_isc_det = base_imponible_isc_det;
	}
	public String getTipo_tributo_nombre_isc_det() {
		return tipo_tributo_nombre_isc_det;
	}
	public void setTipo_tributo_nombre_isc_det(String tipo_tributo_nombre_isc_det) {
		this.tipo_tributo_nombre_isc_det = tipo_tributo_nombre_isc_det;
	}
	public String getTipo_tributo_inter_isc_det() {
		return tipo_tributo_inter_isc_det;
	}
	public void setTipo_tributo_inter_isc_det(String tipo_tributo_inter_isc_det) {
		this.tipo_tributo_inter_isc_det = tipo_tributo_inter_isc_det;
	}
	public String getTipo_sistema_isc_det() {
		return tipo_sistema_isc_det;
	}
	public void setTipo_sistema_isc_det(String tipo_sistema_isc_det) {
		this.tipo_sistema_isc_det = tipo_sistema_isc_det;
	}
	public String getPorcentaje_isc_det() {
		return porcentaje_isc_det;
	}
	public void setPorcentaje_isc_det(String porcentaje_isc_det) {
		this.porcentaje_isc_det = porcentaje_isc_det;
	}
	public String getTipo_tributo_otros_det() {
		return tipo_tributo_otros_det;
	}
	public void setTipo_tributo_otros_det(String tipo_tributo_otros_det) {
		this.tipo_tributo_otros_det = tipo_tributo_otros_det;
	}
	public String getMonto_tributo_otros_det() {
		return monto_tributo_otros_det;
	}
	public void setMonto_tributo_otros_det(String monto_tributo_otros_det) {
		this.monto_tributo_otros_det = monto_tributo_otros_det;
	}
	public String getBase_imponible_otros_det() {
		return base_imponible_otros_det;
	}
	public void setBase_imponible_otros_det(String base_imponible_otros_det) {
		this.base_imponible_otros_det = base_imponible_otros_det;
	}
	public String getTipo_tributo_nombre_otros_det() {
		return tipo_tributo_nombre_otros_det;
	}
	public void setTipo_tributo_nombre_otros_det(String tipo_tributo_nombre_otros_det) {
		this.tipo_tributo_nombre_otros_det = tipo_tributo_nombre_otros_det;
	}
	public String getTipo_tributo_inter_otros_det() {
		return tipo_tributo_inter_otros_det;
	}
	public void setTipo_tributo_inter_otros_det(String tipo_tributo_inter_otros_det) {
		this.tipo_tributo_inter_otros_det = tipo_tributo_inter_otros_det;
	}
	public String getPorcentaje_otros_det() {
		return porcentaje_otros_det;
	}
	public void setPorcentaje_otros_det(String porcentaje_otros_det) {
		this.porcentaje_otros_det = porcentaje_otros_det;
	}
	public String getPrecio_venta_unitario_det() {
		return precio_venta_unitario_det;
	}
	public void setPrecio_venta_unitario_det(String precio_venta_unitario_det) {
		this.precio_venta_unitario_det = precio_venta_unitario_det;
	}
	public String getValor_venta_item_det() {
		return valor_venta_item_det;
	}
	public void setValor_venta_item_det(String valor_venta_item_det) {
		this.valor_venta_item_det = valor_venta_item_det;
	}
	public String getValor_referencial_unit_det() {
		return valor_referencial_unit_det;
	}
	public void setValor_referencial_unit_det(String valor_referencial_unit_det) {
		this.valor_referencial_unit_det = valor_referencial_unit_det;
	}
	public String getFecha_emision_cba() {
		return fecha_emision_cba;
	}
	public void setFecha_emision_cba(String fecha_emision_cba) {
		this.fecha_emision_cba = fecha_emision_cba;
	}
	public String getFecha_comunicacion_cba() {
		return fecha_comunicacion_cba;
	}
	public void setFecha_comunicacion_cba(String fecha_comunicacion_cba) {
		this.fecha_comunicacion_cba = fecha_comunicacion_cba;
	}
	public String getTipo_comprobante_cba() {
		return tipo_comprobante_cba;
	}
	public void setTipo_comprobante_cba(String tipo_comprobante_cba) {
		this.tipo_comprobante_cba = tipo_comprobante_cba;
	}
	public String getNumero_serie_documento_cba() {
		return numero_serie_documento_cba;
	}
	public void setNumero_serie_documento_cba(String numero_serie_documento_cba) {
		this.numero_serie_documento_cba = numero_serie_documento_cba;
	}
	public String getDescripcion_motivo_cba() {
		return descripcion_motivo_cba;
	}
	public void setDescripcion_motivo_cba(String descripcion_motivo_cba) {
		this.descripcion_motivo_cba = descripcion_motivo_cba;
	}
	public String getTipo_operacion_not() {
		return tipo_operacion_not;
	}
	public void setTipo_operacion_not(String tipo_operacion_not) {
		this.tipo_operacion_not = tipo_operacion_not;
	}
	public String getFecha_emision_not() {
		return fecha_emision_not;
	}
	public void setFecha_emision_not(String fecha_emision_not) {
		this.fecha_emision_not = fecha_emision_not;
	}
	public String getHora_emision_not() {
		return hora_emision_not;
	}
	public void setHora_emision_not(String hora_emision_not) {
		this.hora_emision_not = hora_emision_not;
	}
	public String getDomicilio_fiscal_not() {
		return domicilio_fiscal_not;
	}
	public void setDomicilio_fiscal_not(String domicilio_fiscal_not) {
		this.domicilio_fiscal_not = domicilio_fiscal_not;
	}
	public String getTipo_docu_iden_not() {
		return tipo_docu_iden_not;
	}
	public void setTipo_docu_iden_not(String tipo_docu_iden_not) {
		this.tipo_docu_iden_not = tipo_docu_iden_not;
	}
	public String getNumero_docu_iden_not() {
		return numero_docu_iden_not;
	}
	public void setNumero_docu_iden_not(String numero_docu_iden_not) {
		this.numero_docu_iden_not = numero_docu_iden_not;
	}
	public String getNombre_not() {
		return nombre_not;
	}
	public void setNombre_not(String nombre_not) {
		this.nombre_not = nombre_not;
	}
	public String getTipo_moneda_not() {
		return tipo_moneda_not;
	}
	public void setTipo_moneda_not(String tipo_moneda_not) {
		this.tipo_moneda_not = tipo_moneda_not;
	}
	public String getTipo_tipo_nota_not() {
		return tipo_tipo_nota_not;
	}
	public void setTipo_tipo_nota_not(String tipo_tipo_nota_not) {
		this.tipo_tipo_nota_not = tipo_tipo_nota_not;
	}
	public String getDescripcion_motivo_not() {
		return descripcion_motivo_not;
	}
	public void setDescripcion_motivo_not(String descripcion_motivo_not) {
		this.descripcion_motivo_not = descripcion_motivo_not;
	}
	public String getTipo_comprobante_afecta_not() {
		return tipo_comprobante_afecta_not;
	}
	public void setTipo_comprobante_afecta_not(String tipo_comprobante_afecta_not) {
		this.tipo_comprobante_afecta_not = tipo_comprobante_afecta_not;
	}

	public String getNumero_serie_documento_not() {
		return numero_serie_documento_not;
	}
	public void setNumero_serie_documento_not(String numero_serie_documento_not) {
		this.numero_serie_documento_not = numero_serie_documento_not;
	}
	public String getSuma_tributos_not() {
		return suma_tributos_not;
	}
	public void setSuma_tributos_not(String suma_tributos_not) {
		this.suma_tributos_not = suma_tributos_not;
	}
	public String getTotal_valor_venta_not() {
		return total_valor_venta_not;
	}
	public void setTotal_valor_venta_not(String total_valor_venta_not) {
		this.total_valor_venta_not = total_valor_venta_not;
	}
	public String getTotal_precio_venta_not() {
		return total_precio_venta_not;
	}
	public void setTotal_precio_venta_not(String total_precio_venta_not) {
		this.total_precio_venta_not = total_precio_venta_not;
	}
	public String getTotal_descuentos_not() {
		return total_descuentos_not;
	}
	public void setTotal_descuentos_not(String total_descuentos_not) {
		this.total_descuentos_not = total_descuentos_not;
	}
	public String getSuma_otros_cargos_not() {
		return suma_otros_cargos_not;
	}
	public void setSuma_otros_cargos_not(String suma_otros_cargos_not) {
		this.suma_otros_cargos_not = suma_otros_cargos_not;
	}
	public String getTotal_anticipos_not() {
		return total_anticipos_not;
	}
	public void setTotal_anticipos_not(String total_anticipos_not) {
		this.total_anticipos_not = total_anticipos_not;
	}
	public String getImporte_total_venta_not() {
		return importe_total_venta_not;
	}
	public void setImporte_total_venta_not(String importe_total_venta_not) {
		this.importe_total_venta_not = importe_total_venta_not;
	}
	public String getVersion_ubl_not() {
		return version_ubl_not;
	}
	public void setVersion_ubl_not(String version_ubl_not) {
		this.version_ubl_not = version_ubl_not;
	}
	public String getCustomizacion_documento_not() {
		return customizacion_documento_not;
	}
	public void setCustomizacion_documento_not(String customizacion_documento_not) {
		this.customizacion_documento_not = customizacion_documento_not;
	}
	public String getCodigo_catalogo_tri() {
		return codigo_catalogo_tri;
	}
	public void setCodigo_catalogo_tri(String codigo_catalogo_tri) {
		this.codigo_catalogo_tri = codigo_catalogo_tri;
	}
	public String getDescripcion_corto_tri() {
		return descripcion_corto_tri;
	}
	public void setDescripcion_corto_tri(String descripcion_corto_tri) {
		this.descripcion_corto_tri = descripcion_corto_tri;
	}
	public String getAtributo_1_tri() {
		return atributo_1_tri;
	}
	public void setAtributo_1_tri(String atributo_1_tri) {
		this.atributo_1_tri = atributo_1_tri;
	}
	public String getTotal_valor_venta_tri() {
		return total_valor_venta_tri;
	}
	public void setTotal_valor_venta_tri(String total_valor_venta_tri) {
		this.total_valor_venta_tri = total_valor_venta_tri;
	}
	public String getSuma_tributos_tri() {
		return suma_tributos_tri;
	}
	public void setSuma_tributos_tri(String suma_tributos_tri) {
		this.suma_tributos_tri = suma_tributos_tri;
	}
	public String getCodigo_catalogo_ley() {
		return codigo_catalogo_ley;
	}
	public void setCodigo_catalogo_ley(String codigo_catalogo_ley) {
		this.codigo_catalogo_ley = codigo_catalogo_ley;
	}
	public String getDescripcion_leyenda_ley() {
		return descripcion_leyenda_ley;
	}
	public void setDescripcion_leyenda_ley(String descripcion_leyenda_ley) {
		this.descripcion_leyenda_ley = descripcion_leyenda_ley;
	}
    
    
    
    
    
	

}
