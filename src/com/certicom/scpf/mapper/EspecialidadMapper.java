package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Especialidad;

public interface EspecialidadMapper {

	@Select("select * from t_especialidad order by id_especialidad asc")
	List<Especialidad> findAll();

	@Update("update t_especialidad set  descripcion_especialidad = #{descripcion_especialidad}, estado_especialidad = #{estado_especialidad} "			
			+ "where id_especialidad= #{id_especialidad}")
	@Options(flushCache=true,useCache=true)
	void actualizarEspecialidad(Especialidad especialidadSelec);

	@Insert("insert into t_especialidad (descripcion_especialidad,estado_especialidad ) "
			+ " values ( #{descripcion_especialidad},#{estado_especialidad})")
	void crearEspecialidad(Especialidad especialidadSelec);

	@Delete("delete from t_especialidad  where id_especialidad = #{id_especialidad}")
	@Options(flushCache=true)
	void eliminarEspecialidad(Integer id_especialidad);

}
