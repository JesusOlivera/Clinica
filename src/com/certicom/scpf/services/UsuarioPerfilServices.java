package com.certicom.scpf.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Usuario;
import com.certicom.scpf.domain.UsuarioPerfil;
import com.certicom.scpf.mapper.UsuarioMapper;
import com.certicom.scpf.mapper.UsuarioPerfilMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class UsuarioPerfilServices implements UsuarioPerfilMapper{

	UsuarioPerfilMapper usuarioPerfilMapper = (UsuarioPerfilMapper)ServiceFinder.findBean("usuarioPerfilMapper");

	@Override
	public List<UsuarioPerfil> listarPerfilesPorUsuario(Integer usuarioId)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioPerfilMapper.listarPerfilesPorUsuario(usuarioId);
	}

	@Override
	public UsuarioPerfil buscarPerfilUsuario(Integer idusuario,Integer idperfil) throws Exception {
		return usuarioPerfilMapper.buscarPerfilUsuario(idusuario, idperfil);
	}

	@Override
	public void eliminarPerfilUsuario(Integer idusuario, Integer idperfil) throws Exception {
		usuarioPerfilMapper.eliminarPerfilUsuario(idusuario, idperfil);
	}

	@Override
	public void insertUsuarioPeril(UsuarioPerfil usuarioPerfil) throws Exception{
		usuarioPerfilMapper.insertUsuarioPeril(usuarioPerfil);
	}

	@Override
	public List<Perfil> obtenerPerfilxUsuario(Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return usuarioPerfilMapper.obtenerPerfilxUsuario(idusuario);
	}

}
