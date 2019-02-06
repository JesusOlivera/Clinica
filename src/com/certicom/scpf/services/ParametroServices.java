package com.certicom.scpf.services; 

import java.util.List; 

import org.apache.ibatis.annotations.Param;

import com.certicom.scpf.domain.Parametro;
import com.certicom.scpf.mapper.ParametroMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ParametroServices implements ParametroMapper{

	ParametroMapper parametroMapper = (ParametroMapper)ServiceFinder.findBean("parametroMapper");

	public List<Parametro> listParametro() throws Exception{
	    return parametroMapper.listParametro();
	}

	public void updateParametro(Parametro parametro) throws Exception{
	    parametroMapper.updateParametro(parametro);
	}

	public void insertParametro(Parametro parametro) throws Exception{
	    parametroMapper.insertParametro(parametro);
	}

	public Parametro findParametro(Parametro parametro)  throws Exception{
	    return parametroMapper.findParametro(parametro);
	}

	public List<Parametro>  findParametros(Parametro parametro)  throws Exception{
	    return parametroMapper.findParametros(parametro);
	}

	@Override
	public Parametro findParametroPorCodigo(int cod_parametro) throws Exception {
		// TODO Auto-generated method stub
		return parametroMapper.findParametroPorCodigo(cod_parametro);
	}

	@Override
	public void deleteParametro(int cod_parametro) throws Exception {
		parametroMapper.deleteParametro(cod_parametro);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String findParametro_byNombre(@Param("p_nombre_parametro") String nombre_parametro)
			throws Exception {
		// TODO Auto-generated method stub
		return parametroMapper.findParametro_byNombre(nombre_parametro); 
	}

} 
