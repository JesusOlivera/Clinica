package com.certicom.scpf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ConsultaMedica;


public interface ConsultaMedicaMapper {

	@Select("select * from t_consulta_medica order by id_consulta_medica asc")
	List<ConsultaMedica> findAll();

	@Update("update t_consulta_medica set  id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, "
			+ "id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, "
			+ "id_cliente = #{id_cliente}, fecha_consulta = #{fecha_consulta}, hora_consulta = #{hora_consulta}, "
			+ "estado = #{estado}, anamesis = #{anamesis}, listado_problemas = #{listado_problemas}, "
			+ "impresion_diagnostica = #{impresion_diagnostica}, diagnostico = #{diagnostico}, "
			+ "resumen_hospitalizacion = #{resumen_hospitalizacion},id_ticket= #{id_ticket}, tipo_control= #{tipo_control}, examen_auxiliar= #{examen_auxiliar}"			
			+ "where id_consulta_medica= #{id_consulta_medica}")
	@Options(flushCache=true,useCache=true)
	void actualizarConsultaMedica(ConsultaMedica consultaMedica);

	@Insert("insert into t_consulta_medica (id_producto, id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_cliente, "
			+ "	fecha_consulta, hora_consulta, estado, anamesis, listado_problemas, impresion_diagnostica, diagnostico, "
			+ " resumen_hospitalizacion, id_ticket, tipo_control, examen_auxiliar) "
			+ " values ( #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, #{id_cliente}, "
			+ "#{fecha_consulta}, #{hora_consulta}, #{estado}, #{anamesis}, #{listado_problemas}, #{impresion_diagnostica}, "
			+ "#{diagnostico}, #{resumen_hospitalizacion},#{id_ticket}, #{tipo_control}, #{examen_auxiliar})")
	void crearConsultaMedica(ConsultaMedica consultaMedica);

	@Delete("delete from t_consulta_medica where id_consulta_medica = #{id_consulta_medica}")
	@Options(flushCache=true)
	void eliminarConsultaMedica(@Param("id_consulta_medica")Integer id_consulta_medica);
	
	
	public ConsultaMedica findByTicket(@Param("id_ticket")Integer id_ticket);
	public List<ConsultaMedica> findByPaciente(@Param("id_paciente")Integer id_paciente,@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder);
	public Integer countByPaciente(@Param("id_paciente")Integer id_paciente);

}
