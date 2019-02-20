package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ExamenAuxiliar;


public interface ExamenAuxiliarMapper {

	
	public List<ExamenAuxiliar> findAll();

	@Update("update t_examen_auxiliar set  id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, "
			+ "id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, "
			+ "id_consulta_medica = #{id_consulta_medica}, id_cliente = #{id_cliente}, "
			+ "resultado = #{resultado}, detalle = #{detalle}, ticket_generado= #{ticket_generado} "			
			+ "where id_examen_auxiliar= #{id_examen_auxiliar}")
	@Options(flushCache=true,useCache=true)
	void actualizarExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec);

	@Insert("insert into t_examen_auxiliar (id_producto, id_tipo_servicio, id_medico, id_especialidad, id_paciente, "
			+ "id_consulta_medica, id_cliente, resultado, detalle) "
			+ " values ( #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, "
			+ "#{id_consulta_medica}, #{id_cliente}, #{resultado}, #{detalle})")
	void crearExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec);

	@Delete("delete from t_examen_auxiliar  where id_examen_auxiliar = #{id_examen_auxiliar}")
	@Options(flushCache=true)
	void eliminarExamenAuxiliar(@Param("id_examen_auxiliar") Integer id_examen_auxiliar);
	
	public List<ExamenAuxiliar> findByConsulta(@Param("id_consulta_medica")Integer id_consulta_medica);
	
	@Delete("delete from t_examen_auxiliar where id_consulta_medica=#{id_consulta_medica} and ticket_generado='FALSE' ")
	@Options(flushCache=true)
	public void eliminarExamenesByConsulta(@Param("id_consulta_medica")Integer id_consulta_medica);

	@Update("update t_examen_auxiliar set   ticket_generado= 'FALSE' "			
			+ "where id_examen_auxiliar= #{id_examen_auxiliar}")
	@Options(flushCache=true,useCache=true)
	public void actualizarEstadoExamen(@Param("id_examen_auxiliar") Integer id_examen_auxiliar);

}
