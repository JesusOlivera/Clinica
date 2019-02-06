package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ComprobanteDetalle;


public interface ComprobanteDetalleMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.* from t_detalle_comprobante e where e.id_cliente = #{p_cliente}")
	public ComprobanteDetalle findById(@Param("p_cliente") Integer codigoCliente) throws Exception;
	
	@Select("select e.* from t_detalle_comprobante e order by e.id_cliente asc")
	public List<ComprobanteDetalle> findAll() throws Exception;
	
	@Insert("insert into t_detalle_comprobante (id_comprobante, tipo_comprobante, id_cliente, id_producto, id_emisor, id_domicilio_fiscal_cab, tipo_unidad_medida_det, cant_unidades_item_det, suma_tributos_det, precio_venta_unitario_det, valor_venta_item_det, valor_referencial_unit_det, archivo_plano_nombre, archivo_plano_contenido, id_modo_pago) values "
			+ "(#{id_comprobante}, #{tipo_comprobante}, #{id_cliente}, #{id_producto}, #{id_emisor}, #{id_domicilio_fiscal_cab}, #{tipo_unidad_medida_det}, #{cant_unidades_item_det}, #{suma_tributos_det}, #{precio_venta_unitario_det}, #{valor_venta_item_det}, #{valor_referencial_unit_det}, #{archivo_plano_nombre}, #{archivo_plano_contenido} , #{id_modo_pago})")
	public void crearComprobanteDetalle(ComprobanteDetalle comprobanteDetalle) throws Exception;
	
	@Update("update t_detalle_comprobante set id_comprobante = #{id_comprobante}, tipo_comprobante = #{tipo_comprobante}, id_cliente = #{id_cliente}, id_producto = #{id_producto}, id_emisor = #{id_emisor}, id_domicilio_fiscal_cab = #{id_domicilio_fiscal_cab}, tipo_unidad_medida_det = #{tipo_unidad_medida_det}, cant_unidades_item_det = #{cant_unidades_item_det}, suma_tributos_det = #{suma_tributos_det}, precio_venta_unitario_det = #{precio_venta_unitario_det}, valor_venta_item_det = #{valor_venta_item_det}, valor_referencial_unit_det = #{valor_referencial_unit_det}, archivo_plano_nombre = #{archivo_plano_nombre}, archivo_plano_contenido = #{archivo_plano_contenido} where id_cliente= #{id_cliente}")
	@Options(flushCache=true,useCache=true)
    public void actualizarComprobanteDetalle(ComprobanteDetalle comprobanteDetalle) throws Exception;
	
	@Delete("delete  from t_detalle_comprobante  where id_cliente = #{id_cliente}")
	@Options(flushCache=true)
	public void eliminarComprobanteDetalle(@Param("id_cliente") Integer id_comprobante) throws Exception;
	
	
	public List<ComprobanteDetalle> findByIdComprobante(@Param("id_comprobante") Integer id_comprobante) throws Exception;
	
}
