package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Vendedor;

public interface VendedorMapper {
	

	@Select("select * from t_vendedor e where e.id_vendedor = #{p_id_vendedor}")
	public Vendedor findById(@Param("p_id_vendedor") Integer id_vendedor) throws Exception;
	
	@Select("select * from t_vendedor order by id_vendedor asc")
	public List<Vendedor> findAll() throws Exception;
	

	@Insert("insert into t_vendedor (descripcion_vendedor) values ( #{descripcion_vendedor})")
	public void crearVendedor(Vendedor vendedor) throws Exception;
	

	@Update("update t_vendedor set  descripcion_vendedor = #{descripcion_vendedor}  where id_vendedor= #{id_vendedor}")
	@Options(flushCache=true,useCache=true)
    public void actualizarVendedor(Vendedor vendedor) throws Exception;
	
	@Delete("delete from t_vendedor  where id_vendedor = #{id_vendedor}")
	@Options(flushCache=true)
	public void eliminarVendedor(@Param("id_vendedor") Integer id_vendedor) throws Exception;

}
