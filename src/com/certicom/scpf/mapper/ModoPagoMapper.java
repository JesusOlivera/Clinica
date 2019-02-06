package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ModoPago;


public interface ModoPagoMapper {
	
	
	@Select("select * from t_modo_pago e where e.id_modo_pago = #{p_modo_pago}")
	public ModoPago findById(@Param("p_modo_pago") Integer codigoModoPago) throws Exception;
	
	@Select("select * from t_modo_pago order by id_modo_pago asc")
	public List<ModoPago> findAll() throws Exception;
	
	@Insert("insert into t_modo_pago (descripcion_modo_pago, numero_dias) values (#{descripcion_modo_pago} , #{numero_dias})")
	public void crearModoPago(ModoPago modoPago) throws Exception;
	
	@Update("update t_modo_pago set descripcion_modo_pago = #{descripcion_modo_pago} , numero_dias = #{numero_dias} where id_modo_pago= #{id_modo_pago}")
	@Options(flushCache=true,useCache=true)
    public void actualizarModoPago(ModoPago modoPago) throws Exception;
	
	@Delete("delete from t_modo_pago  where id_modo_pago = #{id_modo_pago}")
	@Options(flushCache=true)
	public void eliminarModoPago(@Param("id_modo_pago") Integer id_modo_pago) throws Exception;

}
