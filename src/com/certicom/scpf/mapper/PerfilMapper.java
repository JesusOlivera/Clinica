package com.certicom.scpf.mapper; 

import java.util.List; 

import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Usuario;

public interface PerfilMapper{ 

	public List<Perfil> listPerfil() throws Exception;
	//public void deletePerfil(Perfil perfil) throws Exception;
	public void deletePerfil(int cod_perfil) throws Exception;
	public void updatePerfil(Perfil perfil) throws Exception;
	public void insertPerfil(Perfil perfil) throws Exception;
	public Perfil findPerfil(Perfil perfil) throws Exception;
	public Perfil findPerfilPorCodigo(int cod_perfil) throws Exception;
	public void insertUsuarioPerfil(Perfil perfil) throws Exception;
	public List<Perfil> listarPerfilesxUsuario(Usuario usuario) throws Exception;
} 
