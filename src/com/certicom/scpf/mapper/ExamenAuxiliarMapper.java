package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ExamenAuxiliar;


public interface ExamenAuxiliarMapper {

	@Select("select * from t_examen_auxiliar order by id_examen_auxiliar asc")
	List<ExamenAuxiliar> findAll();

	@Update("update t_examen_auxiliar set  id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, id_consulta_medica = #{id_consulta_medica}, id_cliente = #{id_cliente}, resultado = #{resultado}, detalle = #{detalle}  "			
			+ "where id_examen_auxiliar= #{id_examen_auxiliar}")
	@Options(flushCache=true,useCache=true)
	void actualizarExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec);

	@Insert("insert into t_examen_auxiliar (id_producto, id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_consulta_medica, id_cliente, resultado, detalle) "
			+ " values ( #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, #{id_consulta_medica}, #{id_cliente}, #{resultado}, #{detalle})")
	void crearExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec);

	@Delete("delete from t_examen_auxiliar  where id_examen_auxiliar = #{id_examen_auxiliar}")
	@Options(flushCache=true)
	void eliminarExamenAuxiliar(Integer id_examen_auxiliar);

}
