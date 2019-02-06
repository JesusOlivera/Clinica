package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Emisor;

public interface EmisorMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	
	/*Inicio Jesus*/
	@Select("select e.*, df.domicilio as desDomicilio, df.descripcion_domicilio as descripcion_domicilio_fiscal from t_emisor e inner join t_domicilio_fiscal df on df.id_domicilio_fiscal_cab = e.id_domicilio_fiscal_cab where e.id_emisor = #{p_emisor}")
	public Emisor findById(@Param("p_emisor") Integer codigoEmisor) throws Exception;
	
	@Select("select e.*, df.domicilio as desDomicilio, df.descripcion_domicilio as descripcion_domicilio_fiscal from t_emisor e inner join t_domicilio_fiscal df on df.id_domicilio_fiscal_cab = e.id_domicilio_fiscal_cab order by e.id_emisor asc")
	public List<Emisor> findAll() throws Exception;
	
	/*Fin Jesus*/
	
	@Insert("insert into t_emisor (id_domicilio_fiscal_cab, ruc, razon_social, nombre_comercial, direccion, telefono, slogan, ruta_logo, ruta_descarga_archivos_planos) values (#{id_domicilio_fiscal_cab}, #{ruc}, #{razon_social}, #{nombre_comercial}, #{direccion}, #{telefono}, #{slogan}, #{ruta_logo}, #{ruta_descarga_archivos_planos})")
	public void crearEmisor(Emisor emisor) throws Exception;
	
	@Update("update t_emisor set id_domicilio_fiscal_cab = #{id_domicilio_fiscal_cab}, ruc = #{ruc}, razon_social = #{razon_social}, nombre_comercial = #{nombre_comercial}, direccion = #{direccion}, telefono = #{telefono}, slogan = #{slogan}, ruta_logo = #{ruta_logo}, ruta_descarga_archivos_planos = #{ruta_descarga_archivos_planos} where id_emisor= #{id_emisor}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEmisor(Emisor emisor) throws Exception;
	
	@Delete("delete from t_emisor where id_emisor = #{id_emisor}")
	@Options(flushCache=true)
	public void eliminarEmisor(@Param("id_emisor") Integer id_emisor) throws Exception;
	

	
}
