package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Receta;


public interface RecetaMapper {

	@Select("select * from t_receta order by id_receta asc")
	List<Receta> findAll();

	@Update("update t_receta set  id_consulta_medica = #{id_consulta_medica}, id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, medicamento = #{medicamento}, cantidad = #{cantidad}, dosis = #{dosis}, horas = #{horas}, duracion = #{duracion} "			
			+ "where id_receta= #{id_receta}")
	@Options(flushCache=true,useCache=true)
	void actualizarReceta(Receta receta);

	@Insert("insert into t_receta (id_consulta_medica, id_producto, id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_cliente, medicamento, cantidad, dosis, horas, duracion) "
			+ " values (#{id_consulta_medica}, #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, #{id_paciente}, #{id_cliente}, #{medicamento}, #{cantidad}, #{dosis}, #{horas}, #{duracion})")
	void crearReceta(Receta receta);

	@Delete("delete from t_receta  where id_receta = #{id_receta}")
	@Options(flushCache=true)
	void eliminarReceta(@Param("id_receta")Integer id_receta);
	
	public List<Receta>findByConsulta(@Param("id_consulta_medica")Integer id_consulta_medica);

	@Delete("delete from t_receta  where id_consulta_medica = #{id_consulta_medica}")
	@Options(flushCache=true)
	public void eliminarRecetasByConsulta(@Param("id_consulta_medica")Integer id_consulta_medica);

}
