package com.certicom.scpf.mapper;

import java.util.List;

import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Sistema;

public interface SistemaMapper {
    
    public List<Sistema> listSistema() throws Exception;
    
    // public void deleteSistema(Sistema sistema) throws Exception;
    public void deleteSistema(Long cod_sistema) throws Exception;
    
    public void updateSistema(Sistema sistema) throws Exception;
    
    // public void updateSistema(int cod_sistema) throws Exception;
    
    public void insertSistema(Sistema sistema) throws Exception;
    
    // public void insertSistema(int cod_sistema) throws Exception;
    
    public Sistema findSistema(Sistema sistema) throws Exception;
    
    public Sistema findSistemaPorCodigo(Long cod_sistema) throws Exception;
    
    public List<Sistema> listSistemaxPerfil(Perfil perfil) throws Exception;	

}
