package com.certicom.scpf.services; 

import java.util.List; 

import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Sistema;
import com.certicom.scpf.mapper.SistemaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class SistemaServices implements SistemaMapper{

	SistemaMapper sistemaMapper = (SistemaMapper)ServiceFinder.findBean("sistemaMapper");

	public List<Sistema> listSistema() throws Exception{
	    return sistemaMapper.listSistema();
	}

	public void insertSistema(Sistema sistema) throws Exception{
	    sistemaMapper.insertSistema(sistema);
	}

	public Sistema findSistema(Sistema sistema)  throws Exception{
	    return sistemaMapper.findSistema(sistema);
	}

    @Override
    public void deleteSistema(Long cod_sistema) throws Exception {
        // TODO Auto-generated method stub
        sistemaMapper.deleteSistema(cod_sistema);
    }

    @Override
    public Sistema findSistemaPorCodigo(Long cod_sistema) throws Exception {
        // TODO Auto-generated method stub
        return sistemaMapper.findSistemaPorCodigo(cod_sistema);
    }

    @Override
    public void updateSistema(Sistema sistema) throws Exception {
        // TODO Auto-generated method stub
        sistemaMapper.updateSistema(sistema);
    }

    public List<Sistema> listSistemaxPerfil(Perfil perfil) throws Exception{
	    return sistemaMapper.listSistemaxPerfil(perfil);
	}
} 
