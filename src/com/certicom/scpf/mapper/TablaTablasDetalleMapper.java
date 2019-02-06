package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.TablaTablasDetalle;


public interface TablaTablasDetalleMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_tabla_tablas_detalle e where e.id_codigo = #{p_codigo}")
	public TablaTablasDetalle findById(@Param("p_codigo") Integer codigoCodigo) throws Exception;
	
	@Select("select * from t_tabla_tablas_detalle e where e.id_maestra = #{p_maestra} and e.ind_activo ='TRUE'")
	public List<TablaTablasDetalle> findByIdMaestra(@Param("p_maestra") Integer codigoMaestra) throws Exception;
	
	@Select("select * from t_tabla_tablas_detalle e where e.id_maestra = #{p_maestra}")
	public List<TablaTablasDetalle> findByIdMaestraTotal(@Param("p_maestra") Integer codigoMaestra) throws Exception;
	
	@Select("select * from t_tabla_tablas_detalle e where e.id_maestra = 5 and id_codigo not in (12,14) ")
	public List<TablaTablasDetalle> findBySinIGVSinISC() throws Exception;
	
	@Select("select * from t_tabla_tablas_detalle order by id_codigo asc")
	public List<TablaTablasDetalle> findAll() throws Exception;
	
	@Insert("insert into t_tabla_tablas_detalle (id_maestra, descripcion_largo, descripcion_corto, valor, atributo_1, atributo_2, atributo_3) values (#{id_maestra},#{descripcion_largo}, #{descripcion_corto}, #{valor}, #{atributo_1}, #{atributo_2}, #{atributo_3})")
	public void crearTablaTablasDetalle(TablaTablasDetalle tablatablasdetalle) throws Exception;
	
	@Update("update t_tabla_tablas_detalle set id_maestra = #{id_maestra}, descripcion_largo = #{descripcion_largo}, descripcion_corto = #{descripcion_corto}, valor = #{valor}, atributo_1 = #{atributo_1}, atributo_2 = #{atributo_2}, atributo_3 = #{atributo_3} , ind_activo= #{ind_activo} where id_codigo= #{id_codigo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTablaTablasDetalle(TablaTablasDetalle tablatablasdetalle) throws Exception;
	
	@Delete("delete  from t_tabla_tablas_detalle where id_codigo = #{id_codigo}")
	@Options(flushCache=true)
	public void eliminarTablaTablasDetalle(@Param("id_codigo") Integer id_codigo) throws Exception;
	

	
}
