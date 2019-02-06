package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Cliente;

public interface ClienteMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.* , (select d.descripcion_largo from t_tabla_tablas_detalle d where d.id_maestra = 6 and d.codigo_catalogo = cast(e.tipo_docu_iden_cab as varchar) ) as desTipoDocumento from t_cliente e where e.id_cliente = #{p_cliente}")
	public Cliente findById(@Param("p_cliente") Integer codigoCliente) throws Exception;
	
	@Select("select e.* from t_cliente e where e.nombre_cab like #{s_cliente}")
	public Cliente findByFiltro(@Param("s_cliente") String filtro) throws Exception;
	
	@Select("select e.* , (select d.descripcion_largo from t_tabla_tablas_detalle d where d.id_maestra = 6 and d.codigo_catalogo = cast(e.tipo_docu_iden_cab as varchar) ) as desTipoDocumento from t_cliente e order by e.id_cliente asc")
	public List<Cliente> findAll() throws Exception;
	
	@Insert("insert into t_cliente (tipo_docu_iden_cab, numero_docu_iden_cab, nombre_cab, direccion, telefono, email, contacto) values (#{tipo_docu_iden_cab}, #{numero_docu_iden_cab}, #{nombre_cab}, #{direccion}, #{telefono}, #{email}, #{contacto})")
	public void crearCliente(Cliente cliente) throws Exception;
	
	@Update("update t_cliente set tipo_docu_iden_cab = #{tipo_docu_iden_cab}, numero_docu_iden_cab = #{numero_docu_iden_cab}, nombre_cab = #{nombre_cab}, direccion = #{direccion}, telefono = #{telefono}, email = #{email}, contacto = #{contacto} where id_cliente= #{id_cliente}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCliente(Cliente cliente) throws Exception;
	
	@Delete("delete  from t_cliente  where id_cliente = #{id_cliente}")
	@Options(flushCache=true)
	public void eliminarCliente(@Param("id_cliente") Integer id_cliente) throws Exception;
	

	
}
