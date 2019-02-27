	package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Control;

public interface ControlMapper {
	

	@Select("select * from t_control e where e.id_control = #{id_control}")
	public Control findById(@Param("id_control") Integer id_control) throws Exception;
	
	
	public List<Control> findAll() throws Exception;
	
	@Select("select * from t_control e where e.id_consulta_medica = #{id_consulta_medica}")
	public List<Control> findByIdConsultaMedica(@Param("id_consulta_medica") Integer id_consulta_medica) throws Exception;

	@Insert("insert into t_control (id_producto,id_consulta_medica,id_tipo_servicio,id_medico,id_especialidad,id_paciente, id_cliente,fecha_inicio,fecha_fin,fecha_control) "
	 	 + " values ( #{id_producto},#{id_consulta_medica}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, #{id_cliente}, #{fecha_inicio}, #{fecha_fin}, #{fecha_control})")
	public void crearControl(Control Control) throws Exception;
	

	@Update("update t_control set  id_producto = #{id_producto},id_consulta_medica = #{id_consulta_medica}, "
			+ "id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad},"
			+ " id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, fecha_inicio = #{fecha_inicio}, fecha_control = #{fecha_control}"
			+ " fecha_fin = #{fecha_fin}"
			+ " where id_control= #{id_control}")
	@Options(flushCache=true,useCache=true)
    public void actualizarControl(Control Control) throws Exception;
	
	@Delete("delete from t_control  where id_control = #{id_control}")
	@Options(flushCache=true)
	public void eliminarControl(@Param("id_control") Integer id_control) throws Exception;

}
