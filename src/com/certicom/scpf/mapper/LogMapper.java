package com.certicom.scpf.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Log;

public interface LogMapper {
	
	/**
	 * @Desc: inserta un cliente, los parametros debe ser exatamente iguales en nombre que los atributos de la clase
	 * para poder pasar el objeto, de lo contrario habra que pasar parametro por parametro
	 * @param cliente: objeto cliente
	 * @return: void
	 * @throws Exception
	 */
	@Insert("insert into t_log (idusuario, cod_menu,accion,descripcion,ip_client,host_client,fecha,hora,anio) " +
			"values (#{idusuario},#{cod_menu},#{accion},#{descripcion},#{ip_client},#{host_client},now(),localtime, extract(year from now()))")
	public void insertLog(Log log) throws Exception;
	
	/**
	 * Obtener los anios que hay en la BD
	 **/
	@Select ("select distinct extract(year from fecha) from t_log")
	public List<Integer> getAniosRegistrados() throws Exception;
	
	/**
	 * Obtener el Log filtrado por mes y anio
	 * @param anio
	 * @param mes
	 **/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "+
			  "FROM  t_log a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "+
			  "LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "+
			  "LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) "+
			  "WHERE extract(month from a.fecha)::int = #{p_mes}")
	public List<Log> getLogFiltradoPeriodoAnio (@Param("p_mes") Integer mes, @Param("p_anio") Integer anio) throws Exception;
	
	
	/*Para el mes de Enero*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_ene a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Ene() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ene a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Ene_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ene a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Ene_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ene a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Ene_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Febrero*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_feb a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Feb() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_feb a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Feb_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_feb a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Feb_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_feb a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Feb_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Marzo*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_mar a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Mar() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_mar a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Mar_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_mar a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Mar_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_mar a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Mar_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Abril*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_abr a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Abr() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_abr a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Abr_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_abr a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Abr_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_abr a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Abr_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Mayo*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_may a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_May() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_may a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_May_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_may a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_May_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_may a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_May_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Junio*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio, a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_jun a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Jun() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jun a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Jun_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jun a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Jun_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jun a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Jun_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	/*Para el mes de Julio*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jul a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) ")
	public List<Log> getLog_Jul() throws Exception;
	
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jul a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Jul_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jul a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Jul_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_jul a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ "                             LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Jul_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	/*Para el mes de Agosto*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ "  FROM  t_log_ago a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ "  LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ "  LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Ago() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ago a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Ago_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ago a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Ago_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_ago a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Ago_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	/*Para el mes de Setiembre*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_set a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Set() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_set a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Set_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_set a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Set_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_set a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Set_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Octubre*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_oct a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Oct() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_oct a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Oct_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_oct a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Oct_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_oct a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Oct_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*Para el mes de Noviembre*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_nov a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Nov() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_nov a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Nov_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_nov a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Nov_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_nov a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Nov_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	/*Para el mes de Diciembre*/
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo"
			+ " FROM  t_log_dic a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario)"
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu)"
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema)")
	public List<Log> getLog_Dic() throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_dic a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where b.login like '%' || #{p_login} || '%'")
	public List<Log> getLog_Dic_By_Login(@Param("p_login") String login) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ "FROM  t_log_dic a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ "LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ "LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where a.accion  like '%' || #{p_accion} || '%'")
	public List<Log> getLog_Dic_By_Accion(@Param("p_accion") String accion) throws Exception;
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log_dic a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) where c.nombre  like '%' || #{p_opcion} || '%'")
	public List<Log> getLog_Dic_By_OpcionMenu(@Param("p_opcion") String opcion) throws Exception;
	
	
	
	/*De la tabla Log*/
	
	@Select("SELECT a.pk_log_id,a.accion, a.descripcion, a.ip_client, a.fecha, a.hora, a.anio,a.ip_server_img, b.login , c.nombre as nombre_opMenu , d.nombre_sistema as modulo "
			+ " FROM  t_log a LEFT OUTER JOIN t_usuario b on (a.idusuario = b.idusuario) "
			+ " LEFT OUTER JOIN t_opcion_menu c on (a.cod_menu = c.cod_menu) "
			+ " LEFT OUTER JOIN t_sistema d on (c.cod_sistema = d.cod_sistema) WHERE a.fecha  BETWEEN #{p_fecha_inicio} and #{p_fecha_fin}")
	public List<Log> getLog_DateInterval(@Param("p_fecha_inicio") Date fecha_inicio,@Param("p_fecha_fin") Date fecha_fin) throws Exception;
	

}
