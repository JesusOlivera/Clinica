package com.certicom.scpf.mapper; 

import java.util.List; 

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Parametro;

public interface ParametroMapper{ 

	public List<Parametro> listParametro() throws Exception;
	//public void deleteParametro(Parametro parametro) throws Exception;
	public void deleteParametro(int cod_parametro) throws Exception;
	public void updateParametro(Parametro parametro) throws Exception;
	public void insertParametro(Parametro parametro) throws Exception;
	public Parametro findParametro(Parametro parametro) throws Exception;
	public List<Parametro> findParametros(Parametro parametro) throws Exception;
	public Parametro findParametroPorCodigo(int cod_parametro) throws Exception;
	
	@Select("select valor from t_parametro where nombre_parametro = #{p_nombre_parametro}")
	public String findParametro_byNombre(@Param("p_nombre_parametro") String nombre_parametro) throws Exception;
} 
