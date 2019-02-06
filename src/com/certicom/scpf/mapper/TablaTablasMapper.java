package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.TablaTablas;




public interface TablaTablasMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_tabla_tablas e where e.id_maestra = #{p_maestra}")
	public TablaTablas findById(@Param("p_maestra") Integer codigoMaestra) throws Exception;
	
	@Select("select * from t_tabla_tablas order by id_maestra asc")
	public List<TablaTablas> findAll() throws Exception;
	
	@Insert("insert into t_tabla_tablas (descripcion_maestra, nro_catalogo) values (#{descripcion_maestra}, #{nro_catalogo})")
	public void crearTablaTablas(TablaTablas tablatablas) throws Exception;
	
	@Update("update t_tabla_tablas set descripcion_maestra = #{descripcion_maestra}, nro_catalogo = #{nro_catalogo} where id_maestra= #{id_maestra}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTablaTablas(TablaTablas tablatablas) throws Exception;
	
	@Delete("delete  from t_tabla_tablas  where id_maestra = #{id_maestra}")
	@Options(flushCache=true)
	public void eliminarTablaTablas(@Param("id_maestra") Integer id_maestra) throws Exception;
	

	
}
