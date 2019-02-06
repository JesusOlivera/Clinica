package com.certicom.scpf.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.MenuXPerfil;
import com.certicom.scpf.domain.UsuarioPerfil;

public interface MenuPerfilMapper {

	
	//busca 
	@Select("select * from t_opcion_menuxperfil mp where mp.cod_menu=#{p_codMenu} and mp.cod_perfil=#{p_cod_perfil}")
	public MenuXPerfil buscarMenuPerfil(@Param("p_codMenu") Long idMenu,@Param("p_cod_perfil") Integer idPerfil)throws Exception;
	
	//insertar
	@Insert("insert into t_opcion_menuxperfil values(#{p_codMenu},#{p_cod_perfil},#{p_ind_activo})")
	public void crearMenuPerfil(@Param("p_codMenu") Long idMenu,@Param("p_cod_perfil") Integer idperfil,@Param("p_ind_activo") Boolean activo) throws Exception;
	
	
	//eliminar
	@Delete("delete from  t_opcion_menuxperfil where cod_menu=#{p_codMenu} and cod_perfil=#{p_cod_perfil}")
	@Options(flushCache=true)
	public void eliminarPerfilUsuario(@Param("p_codMenu") Long idMenu,@Param("p_cod_perfil") Integer idperfil) throws Exception;
	

}
