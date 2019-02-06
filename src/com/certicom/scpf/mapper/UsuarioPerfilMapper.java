package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.UsuarioPerfil;


public interface UsuarioPerfilMapper {
	
	public List<UsuarioPerfil> listarPerfilesPorUsuario(Integer usuarioId) throws Exception;
	
	public void insertUsuarioPeril(UsuarioPerfil usuarioPerfil) throws Exception;
	
	@Select("select * from t_usuarioxperfil p where p.idusuario = #{p_usuarioId} and p.cod_perfil = #{p_perfilId}")
	public UsuarioPerfil buscarPerfilUsuario(@Param("p_usuarioId") Integer idusuario,@Param("p_perfilId") Integer idperfil) throws Exception;
	
	@Delete("delete  from t_usuarioxperfil  where idusuario = #{p_usuarioId} and cod_perfil = #{p_perfilId}")
	@Options(flushCache=true)
	public void eliminarPerfilUsuario(@Param("p_usuarioId") Integer idusuario,@Param("p_perfilId") Integer idperfil) throws Exception;
	
	@Select("select p.cod_perfil, p.descripcion, p.proceso from t_usuarioxperfil u inner join t_perfil p on u.cod_perfil = p.cod_perfil  where idusuario = #{idusuario}")
	public List<Perfil> obtenerPerfilxUsuario(@Param("idusuario") Integer idusuario )throws Exception;
	
}

	
	

	


