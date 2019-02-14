package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ConsultaMedica;


public interface ConsultaMedicaMapper {

	@Select("select * from t_consulta_medica order by id_consulta_medica asc")
	List<ConsultaMedica> findAll();

	@Update("update t_consulta_medica set  id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, fecha_consulta = #{fecha_consulta}, hora_consulta = #{hora_consulta}, estado = #{estado}, anamesis = #{anamesis}, listado_problemas = #{listado_problemas}, impresion_diagnostica = #{impresion_diagnostica}, diagnostico = #{diagnostico}, resumen_hospitalizacion = #{resumen_hospitalizacion}"			
			+ "where id_consulta_medica= #{id_consulta_medica}")
	@Options(flushCache=true,useCache=true)
	void actualizarConsultaMedica(ConsultaMedica consultaMedica);

	@Insert("insert into t_consulta_medica (id_producto, id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_cliente, fecha_consulta, hora_consulta, estado, anamesis, listado_problemas, impresion_diagnostica, diagnostico, resumen_hospitalizacion) "
			+ " values ( #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, #{id_cliente}, #{fecha_consulta}, #{hora_consulta}, #{estado}, #{anamesis}, #{listado_problemas}, #{impresion_diagnostica}, #{diagnostico}, #{resumen_hospitalizacion})")
	void crearConsultaMedica(ConsultaMedica consultaMedica);

	@Delete("delete from t_consulta_medica where id_consulta_medica = #{id_consulta_medica}")
	@Options(flushCache=true)
	void eliminarConsultaMedica(Integer id_consulta_medica);

}
