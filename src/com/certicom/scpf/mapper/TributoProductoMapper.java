package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.TributoProducto;


public interface TributoProductoMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.* from t_tributos_producto e where e.id_producto = #{p_producto} and e.tipo_tributo_det = #{p_tipo_tributo}")
	public TributoProducto findById(@Param("p_producto") Integer codigoProducto, @Param("p_tipo_tributo") Integer codigoTipoTributo) throws Exception;

	@Select("select e.* from t_tributos_producto e where e.id_producto = #{p_producto}")
	public List<TributoProducto> findByIdProducto(@Param("p_producto") Integer codigoProducto) throws Exception;
	
	@Select("select e.* from t_tributos_producto e order by e.id_producto asc")
	public List<TributoProducto> findAll() throws Exception;
	
	@Insert("insert into t_tributos_producto (id_producto, tipo_tributo_det, tipo_afectacion_igv_det, tipo_sistema_isc_det, porcentaje_det, tipo_tributo_inter_det, asignado, tipo_tributo_nombre_det, tipo_tributo_codigo_det) "
			+ "values (#{id_producto}, #{tipo_tributo_det}, #{tipo_afectacion_igv_det}, #{tipo_sistema_isc_det}, #{porcentaje_det}, #{tipo_tributo_inter_det}, #{asignado}, #{tipo_tributo_nombre_det}, #{tipo_tributo_codigo_det})")
	public void crearTributoProducto(TributoProducto tributoProducto) throws Exception;
	

	@Update("update t_tributos_producto set tipo_tributo_det = #{p_tipo_tributo}, tipo_afectacion_igv_det = #{tipo_afectacion_igv_det}, tipo_sistema_isc_det = #{tipo_sistema_isc_det}, porcentaje_det = #{porcentaje_det}, tipo_tributo_inter_det = #{tipo_tributo_inter_det}, asignado = #{asignado}, tipo_tributo_nombre_det = #{tipo_tributo_nombre_det}, tipo_tributo_codigo_det = #{tipo_tributo_codigo_det} where id_producto = #{id_producto} and tipo_tributo_det = #{tipo_tributo_det}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTributoProductoOtros(TributoProducto tributoProducto, @Param("p_tipo_tributo") Integer codigoTipoTributo ) throws Exception;
	

	@Update("update t_tributos_producto set tipo_afectacion_igv_det = #{tipo_afectacion_igv_det	}, "
			+ "tipo_sistema_isc_det = #{tipo_sistema_isc_det}, "
			+ "porcentaje_det = #{porcentaje_det}, "
			+ "tipo_tributo_inter_det = #{tipo_tributo_inter_det}, "
			+ "asignado = #{asignado}, "
			+ "tipo_tributo_nombre_det = #{tipo_tributo_nombre_det}, "
			+ "tipo_tributo_codigo_det = #{tipo_tributo_codigo_det} "
			+ "where id_producto= #{id_producto} and tipo_tributo_det= #{tipo_tributo_det}")

	@Options(flushCache=true,useCache=true)
    public void actualizarTributoProducto(TributoProducto tributoProducto) throws Exception;
	
	@Delete("delete  from t_tributos_producto  where id_producto = #{id_producto} and tipo_tributo_det = #{tipo_tributo_det}")
	@Options(flushCache=true)
	public void eliminarTributoProducto(@Param("id_producto") Integer id_producto,@Param("tipo_tributo_det") Integer tipo_tributo_det) throws Exception;
		
}
