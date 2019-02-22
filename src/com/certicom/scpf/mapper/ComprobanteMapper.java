package com.certicom.scpf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;

public interface ComprobanteMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.* from t_comprobante e where e.id_cliente = #{p_cliente}")
	public Comprobante findById(@Param("p_cliente") Integer codigoCliente) throws Exception;
	
	@Select("select e.* from t_comprobante e inner join t_tabla_tablas_detalle ttd on e.tipo_docu_iden_cab = ttd.id_codigo order by e.id_cliente asc")
	public List<Comprobante> findAll() throws Exception;
	
	@Insert("insert into t_comprobante (tipo_comprobante, id_cliente, id_emisor, id_domicilio_fiscal_cab, tipo_operacion_cab, fecha_emision_cab, hora_emision_cab, fecha_vencimiento_cab, tipo_moneda_cab, suma_tributos_cab, total_valor_venta_cab, total_precio_venta_cab, total_descuentos_cab, suma_otros_cargos_cab, total_anticipos_cab, importe_total_venta_cab, tipo_comprobante_afecta_not, archivo_plano_nombre, archivo_plano_contenido, estado_sunat, numero_serie_documento_cab, tipo_tipo_nota_not, descripcion_motivo_not, id_modo_pago, tipo_docu_iden_cab, estado_comunicacion_baja, estado_respuesta_sunat, descripcion_respuesta_sunat, correlativo, tipo_moneda_cab, version_ubl, id_vendedor, id_ticket) values "
			+ "(#{tipo_comprobante}, #{id_cliente}, #{id_emisor}, #{id_domicilio_fiscal_cab}, #{tipo_operacion_cab}, #{fecha_emision_cab}, #{hora_emision_cab}, #{fecha_vencimiento_cab}, #{tipo_moneda_cab}, #{suma_tributos_cab}, #{total_valor_venta_cab}, #{total_precio_venta_cab}, #{total_descuentos_cab}, #{suma_otros_cargos_cab}, #{total_anticipos_cab}, #{importe_total_venta_cab}, #{tipo_comprobante_afecta_not}, #{archivo_plano_nombre}, #{archivo_plano_contenido}, #{estado_sunat}, #{numero_serie_documento_cab}, #{tipo_tipo_nota_not}, #{descripcion_motivo_not}, #{id_modo_pago}, #{tipo_docu_iden_cab}, #{estado_comunicacion_baja}, #{estado_respuesta_sunat}, #{descripcion_respuesta_sunat}, #{correlativo}, #{tipo_moneda_cab}, #{version_ubl}, #{id_vendedor}, #{id_ticket})")
	@SelectKey(statement="SELECT nextval('sec_comprobante')", keyProperty="id_comprobante", before=true, resultType=int.class)
	public int crearComprobante(Comprobante comprobante) throws Exception;
	
	@Update("update t_comprobante set tipo_comprobante = #{tipo_comprobante}, id_cliente = #{id_cliente}, id_emisor = #{id_emisor}, id_domicilio_fiscal_cab = #{id_domicilio_fiscal_cab}, tipo_operacion_cab = #{tipo_operacion_cab}, fecha_emision_cab = #{fecha_emision_cab}, hora_emision_cab = #{hora_emision_cab}, fecha_vencimiento_cab = #{fecha_vencimiento_cab}, tipo_moneda_cab = #{tipo_moneda_cab}, suma_tributos_cab = #{suma_tributos_cab}, total_valor_venta_cab = #{total_valor_venta_cab}, total_precio_venta_cab = #{total_precio_venta_cab}, total_descuentos_cab = #{total_descuentos_cab}, suma_otros_cargos_cab = #{suma_otros_cargos_cab}, total_anticipos_cab = #{total_anticipos_cab}, importe_total_venta_cab = #{importe_total_venta_cab}, "
			+ "importe_total_venta_cab = #{importe_total_venta_cab}, tipo_comprobante_afecta_not = #{tipo_comprobante_afecta_not}, archivo_plano_nombre = #{archivo_plano_nombre}, archivo_plano_nombre = #{archivo_plano_nombre}, archivo_plano_contenido = #{archivo_plano_contenido}, estado_sunat = #{estado_sunat}, numero_serie_documento_cab = #{numero_serie_documento_cab}, tipo_tipo_nota_not = #{tipo_tipo_nota_not}, descripcion_motivo_not = #{descripcion_motivo_not}, id_modo_pago = #{id_modo_pago}, tipo_docu_iden_cab = #{tipo_docu_iden_cab}, estado_comunicacion_baja = #{estado_comunicacion_baja}, estado_respuesta_sunat = #{estado_respuesta_sunat}, descripcion_respuesta_sunat = #{descripcion_respuesta_sunat}, correlativo = #{correlativo}, tipo_moneda_cab = #{tipo_moneda_cab}, version_ubl = #{version_ubl}, id_vendedor = #{id_vendedor}, id_ticket = #{id_ticket} where id_comprobante= #{id_comprobante}")
	@Options(flushCache=true,useCache=true)
    public void actualizarComprobante(Comprobante comprobante) throws Exception;
	
	@Delete("delete  from t_comprobante  where id_cliente = #{id_cliente}")
	@Options(flushCache=true)
	public void eliminarComprobante(@Param("id_cliente") Integer id_cliente) throws Exception;
	
	/**MODIFICACION JCTV ***/
	@Update("update t_comprobante set estado_comunicacion_baja=#{estado_comunicacion_baja} where id_comprobante= #{id_comprobante}")
	@Options(flushCache=true,useCache=true)
	public void actualizarEstadoBaja(Comprobante comprobante)throws Exception;
	
	public List<Comprobante> listaComprobantes() throws Exception;
	
	@Select("SELECT nextval('sec_comprobante')")
	public int getSecIdComprobante();
	
	@Insert("insert into t_comprobante ("
			+ "tipo_comprobante, id_cliente, id_emisor, id_domicilio_fiscal_cab, tipo_operacion_cab, fecha_emision_cab, "
			+ "hora_emision_cab, fecha_vencimiento_cab, tipo_moneda_cab, suma_tributos_cab, total_valor_venta_cab, "
			+ "total_precio_venta_cab, total_descuentos_cab, suma_otros_cargos_cab, total_anticipos_cab, "
			+ "importe_total_venta_cab, tipo_comprobante_afecta_not, archivo_plano_nombre, archivo_plano_contenido, "
			+ "estado_sunat, numero_serie_documento_cab, tipo_tipo_nota_not, descripcion_motivo_not, id_modo_pago, "
			+ "tipo_docu_iden_cab, estado_comunicacion_baja, correlativo, version_ubl, id_vendedor) values "
			+ "(#{tipo_comprobante}, #{id_cliente}, #{id_emisor}, #{id_domicilio_fiscal_cab}, #{tipo_operacion_cab},"
			+ " #{fecha_emision_cab}, #{hora_emision_cab}, #{fecha_vencimiento_cab}, #{tipo_moneda_cab}, #{suma_tributos_cab},"
			+ " #{total_valor_venta_cab}, #{total_precio_venta_cab}, #{total_descuentos_cab}, #{suma_otros_cargos_cab}, "
			+ "#{total_anticipos_cab}, #{importe_total_venta_cab}, #{tipo_comprobante_afecta_not}, #{archivo_plano_nombre}, "
			+ "#{archivo_plano_contenido}, #{estado_sunat}, #{numero_serie_documento_cab}, #{tipo_tipo_nota_not},"
			+ " #{descripcion_motivo_not}, #{id_modo_pago}, #{tipo_docu_iden_cab}, #{estado_comunicacion_baja}, #{correlativo}, #{version_ubl}, #{id_vendedor})")	
	public void crearComprobanteSec(Comprobante comprobante) throws Exception;
	
	public List<Comprobante> listaComprobantesLecturaRespuesta() throws Exception;
	
	@Select("SELECT (coalesce(MAX(correlativo), 0)+1) AS maxmcorrelativo "
			+ " from t_comprobante where tipo_comprobante=#{tipo_comprobante}")
	public int getCorrelativoComprobante(@Param("tipo_comprobante") String tipo_comprobante)throws Exception;
	
	
	@Update("Update t_comprobante set estado_sunat= #{estado_sunat} where id_comprobante= #{id_comprobante}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoEnvioSunatComprobante(Comprobante comprobante) throws Exception;
	
	@Update("Update t_comprobante set estado_respuesta_sunat= #{estado_respuesta_sunat}, descripcion_respuesta_sunat=#{descripcion_respuesta_sunat} where id_comprobante= #{id_comprobante}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoRespuestaSunatComprobante(Comprobante comprobante) throws Exception;
	
	
	public Integer countCompByAnioMesTipoPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes,@Param("tipo_comprobante") String tipo_comprobante, @Param("filters") Map<String,Object> filters)throws Exception;

	public List<Comprobante> listComprobantesByAnioMesTipoPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes,@Param("tipo_comprobante") String tipo_comprobante, @Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;

	public Comprobante getByNumeroSerieComprobante(@Param("numero_serie_documento_cab") String numero_serie_documento_cab);
	

}
