package com.certicom.scpf.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import sun.security.util.PropertyExpander.ExpandException;


import com.certicom.scpf.domain.Perfil;

import com.certicom.scpf.domain.Usuario;

public interface UsuarioMapper {
    public List<Usuario> getlistaUsuario();
    public List<Usuario> getlistaEjecutivo();
    public List<Usuario> getlistaEjecutivoByNegocio(@Param("idNegocio") Integer idNegocio);
	public List<Usuario> buscarPorLoginClave(Usuario usuario);
	public List<Usuario> buscarPorDNI(String dni);
	public List<Usuario> buscarPorNombre(String nombre);
	public List<Usuario> buscarPorApellido(String apellido);
	public List<Usuario> buscarusuario(Usuario usuario);
	public Usuario buscarPorId(int idusuario);
	
	public void insertUsuario(Usuario usuario);
	
	public void actualizarEstado(Usuario usuario) throws Exception;
	public void actualizar(Usuario usuario);
	public void actualizarLogin(Usuario usuario);
	public void actualizarPassword(Usuario usuario);
	public void actualizarFechaAcceso(Usuario usuario);

	public List<Usuario> getlistaUsuario_byCentroAtencion(Integer id_centro_atencion);
	
	public List<Usuario> getlistaUsuario_Perfil(Usuario usuario);
	
	public Usuario buscarPorLogin(String string) throws Exception;
	
	@Select("select * from t_usuario where dni =#{dni}")
	public Usuario buscarPorDni(@Param("dni") String dni) throws Exception;
	
	public void deleteUsuario(Integer idusuario) throws Exception;
	
	public List<Usuario> listarUsuariosXPerfilProceso(String proceso) throws Exception;
	
	public List<Usuario> obtenerUsuariosxOficina(Integer idoficina)throws Exception;
	
	@Update("update t_usuario set password = #{login} where idusuario = #{idusuario}")
	public void resetearPassword(Usuario usuario)throws Exception;
	
	@Update("update t_usuario set imagen = #{imagen} where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void actualizarImagen(Usuario usuario)throws Exception;
	
	@Select("select * from t_usuario")
	public List<Usuario> listUsuario() throws Exception;	
	
	@Select("SELECT idusuario,nombre,apellido_paterno,apellido_materno,dni,email,direccion,login,password,estado,fecha_registro,fecha_mod,es_nuevo,id_centro_atencion, "
			+ "id_cargo,id_planilla from t_usuario")
	public List<Usuario> listUsuarioActivos() throws Exception;	

	@Update("update t_usuario set fecha_ingreso = #{fecha_ingreso}, departamento = #{departamento}, "
			+ "provincia = #{provincia}, distrito = #{distrito}, "
			+ "sid_ubigeo = #{sid_ubigeo},id_oficina = #{id_oficina}, "
			+ "id_coordinador= #{id_coordinador}, id_supervisor=#{id_supervisor},"
			+ "id_cargo = #{id_cargo}, id_planilla = #{id_planilla}, "
			+ "fecha_cese = #{fecha_cese},motivo_cese = #{motivo_cese}, "
			+ "funcion = #{funcion}, rpm =#{rpm}, rpc =#{rpc}, telefono = #{telefono}, " 
			+ "estado_planilla = #{estado_planilla}, codigo_banco=#{codigoBanco}, "
			+ "codigo_banco_supervisor=#{codigoBancoSupervisor} "
			+ "where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void insertaDatosEjecutivoPlanilla(Usuario usuario) throws Exception;
	
	//lista de Supervisor	
	@Select("select "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as des_responsable, "
			+ "nr.idusuario  from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo)"
			+ "where n.id_negocio = #{id_negocio} and c.id_cargo = 1")
	public List<Usuario> listaSupervisor(@Param("id_negocio") Integer id_negocio) throws Exception;	
	
	//lista de Coordinador
	@Select("select  nr.idusuario, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as des_responsable "
			+ " from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo)"
			+ "where n.id_negocio = #{id_negocio} and c.id_cargo = 2")
	public List<Usuario> listaCoordinador(@Param("id_negocio") Integer id_negocio) throws Exception;		
	
	
	@Update("update t_usuario set id_cargo = #{id_cargo} where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void actualizarCargo(@Param("idusuario") Integer idusuario,@Param("id_cargo") Integer id_cargo)throws Exception;
	
	

	
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nomSupervisor ,u.id_coordinador ,u.dni,u.fecha_ingreso,u.login,u.password,u.funcion,"
			+ " ub.sdepartamento as departamento, ub.sprovincia  as provincia ,ub.sdistrito as distrito,  tp.descripcion as desTipoPlanilla   "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ "inner join t_usuario sp on sp.idusuario = u.id_supervisor "
			+ "left join t_ubigeo ub on ub.sid_ubigeo = u.sid_ubigeo "
			+ "left join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ " where u.id_negocio = #{id_negocio} "
			+ " and (c.id_cargo = 4 or  c.id_cargo = 9) "
			+ "and u.id_supervisor = #{id_supervisor} order by  u.apellido_paterno ,u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, u.departamento, u.provincia, u.distrito, u.funcion,  "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and c.id_cargo = 4 "
			+ "and u.id_supervisor = #{id_supervisor} order by u.apellido_paterno, u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorCoordinadorxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or c.id_cargo = 9) "
			+ "and u.id_supervisor = #{id_supervisor}")
	public List<Usuario> listaPromotorBackxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,u.dni, u.fecha_ingreso,  c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "ub.sdepartamento as departamento, ub.sprovincia  as provincia ,ub.sdistrito as distrito, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nomSupervisor ,u.id_coordinador,u.login,u.password,u.funcion ,tp.descripcion as desTipoPlanilla  "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_supervisor "
			+ "left join t_ubigeo ub on ub.sid_ubigeo = u.sid_ubigeo "
			+ "left join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or  c.id_cargo = 9) order by u.id_supervisor ")
	public List<Usuario> listarEjecutivosxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio "
			+ "from (t_usuario u inner join t_negocio_responsables nr on nr.idusuario = u.idusuario) where nr.id_negocio = #{id_negocio} and nr.id_cargo = 1 ")
	public List<Usuario> listaSupervisorxIdNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	

	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio "
			+ "from (t_usuario u inner join t_negocio_responsables nr on nr.idusuario = u.idusuario) where nr.id_negocio = #{id_negocio} and nr.id_cargo = 2 ")
	public List<Usuario> listaCoordinadorxIdNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select count(*) from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ " "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or  c.id_cargo = 9 or c.id_cargo = 1 or c.id_cargo = 2) ")
	public Integer getEjecutivoxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select * from t_usuario where idusuario =  #{idusuario}")
	public Usuario getUsuario_byIdUsuario(Integer idusuario);
	
	@Select("select COUNT(*) from t_usuario where dni = #{dniUsuario}")
	public int verificarDniCarnetRepetidos(@Param("dniUsuario")String dniUsuario)throws Exception;

	@Select("select COUNT(*) from t_usuario where login = #{login}")
	public int verificarloginRepetido(@Param("login")String login)throws Exception;
	
	@Select("select u.idusuario, u.nombre,u.apellido_paterno,u.apellido_materno "
			+ "from t_usuario u  where u.id_cargo = 4 and u.idusuario !=#{idusuario} ")
	public List<Usuario> listarUsuarioReasignar(@Param("idusuario") Integer idusuario) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, u.departamento, u.provincia, u.distrito, u.funcion,  "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and c.id_cargo = 4 and u.idusuario !=#{idusuario} "
			+ "and u.id_supervisor = #{id_supervisor} order by u.apellido_paterno, u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorCoordinadorxNegocioReasignar(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor, @Param("idusuario") Integer idusuario) throws Exception;
	
	@Select("SELECT u.idusuario,u.nombre,u.apellido_paterno,u.apellido_materno,u.dni,u.email,u.direccion,u.login,u.password,u.estado,u.fecha_registro,u.fecha_mod,u.es_nuevo,u.id_centro_atencion, "
			+ "u.id_cargo,u.id_planilla, n.descripcion as  desNegocio ,c.descripcion as desCargo from t_usuario u inner join t_negocio n on u.id_negocio = n.id_negocio "
			+ "left join t_cargo c  on u.id_cargo = c.id_cargo "
			+ " ")
	public List<Usuario> listUsuarioActivosNoCesados() throws Exception;
	
	@Select("select max(idusuario) from t_usuario ") 
	public Integer getLastUser() throws Exception;
	
	
}
