package com.certicom.scpf.services;

import org.apache.ibatis.annotations.Param;

import com.certicom.scpf.domain.MenuXPerfil;
import com.certicom.scpf.mapper.MenuPerfilMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class MenuPerfilServices implements MenuPerfilMapper{

	MenuPerfilMapper menuPerfilMapper = (MenuPerfilMapper)ServiceFinder.findBean("menuPerfilMapper");
	
	//busca
	@Override
	public MenuXPerfil buscarMenuPerfil(Long idMenu, Integer idPerfil) throws Exception {
		return menuPerfilMapper.buscarMenuPerfil(idMenu, idPerfil);
	}

	@Override
	public void crearMenuPerfil(Long idMenu, Integer idperfil, Boolean activo) throws Exception {
		menuPerfilMapper.crearMenuPerfil(idMenu, idperfil, activo);
	}

	@Override
	public void eliminarPerfilUsuario(Long idMenu,Integer idperfil) throws Exception {
		
		menuPerfilMapper.eliminarPerfilUsuario(idMenu, idperfil);
		
	}



}
