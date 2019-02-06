package com.certicom.scpf.mapper; 

import java.util.List; 

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Sistema;
import com.certicom.scpf.domain.UsuarioPerfil;

public interface MenuMapper{ 

	public List<Menu> listMenu() throws Exception;
	public void deleteMenu(Menu menu) throws Exception;
	public void updateMenu(Menu menu) throws Exception;
	public void insertMenu(Menu menu) throws Exception;
	public Menu findMenu(int id) throws Exception;
	public Menu findMenuxNombre(String nombreMenu) throws Exception;
	public List<Menu> findMenus(Menu menu) throws Exception;
	public List<Menu> listMenuxSistema(Perfil perfil) throws Exception;
	
	//recupera menus por sistema
	@Select(" select cod_menu,cod_sistema,cod_menu_padre,nombre,descripcion,ind_activo,fecha_registro,fecha_modif,usuario_registro,usuario_modif,icono,action from t_opcion_menu where cod_sistema = #{p_sistemaid}")
	public List<Menu> listMenuxSistemaId(@Param("p_sistemaid") Long sistemaid) throws Exception;
	
	//busca todos los sistemas/modulos implicados 
	@Select("select sis.cod_sistema, sis.nombre_sistema,sis.descripcion,sis.ind_activo from t_sistema sis where sis.cod_sistema in ( select distinct cod_sistema from  t_opcion_menu  menu ) and sis.ind_activo=1")
	public List<Sistema> listSistemas() throws Exception;
	
	public int opcionMenuByPrettyCod(String pretty) throws Exception;
	
	
	public Menu opcionMenuByPretty(String pretty) throws Exception;
} 
