package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.DomicilioFiscal;

public interface DomicilioFiscalMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_domicilio_fiscal e where e.id_domicilio_fiscal_cab = #{p_domicilio_fiscal_cab}")
	public DomicilioFiscal findById(@Param("p_domicilio_fiscal_cab") Integer codigoDomicilioFiscal) throws Exception;
	
	@Select("select * from t_domicilio_fiscal order by id_domicilio_fiscal_cab asc")
	public List<DomicilioFiscal> findAll() throws Exception;
	
	/*Jesús*/
	@Insert("insert into t_domicilio_fiscal (ubigeo, domicilio, descripcion_domicilio) values (#{ubigeo}, #{domicilio}, #{descripcion_domicilio})")
	public void crearDomicilioFiscal(DomicilioFiscal domiciliofiscal) throws Exception;
	
	/*Jesús*/
	@Update("update t_domicilio_fiscal set ubigeo = #{ubigeo}, domicilio = #{domicilio}, descripcion_domicilio = #{descripcion_domicilio}  where id_domicilio_fiscal_cab= #{id_domicilio_fiscal_cab}")
	@Options(flushCache=true,useCache=true)
    public void actualizarDomicilioFiscal(DomicilioFiscal domiciliofiscal) throws Exception;
	
	@Delete("delete from t_domicilio_fiscal  where id_domicilio_fiscal_cab = #{id_domicilio_fiscal_cab}")
	@Options(flushCache=true)
	public void eliminarDomicilioFiscal(@Param("id_domicilio_fiscal_cab") Integer id_domicilio_fiscal) throws Exception;
	

	
}
