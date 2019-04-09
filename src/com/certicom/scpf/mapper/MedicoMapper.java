package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Medico;


public interface MedicoMapper {

	
	List<Medico> findAll();

	@Update("update t_medico set  id_especialidad = #{id_especialidad}, tipo_documento = #{tipo_documento}, numero_documento = #{numero_documento},"
			+ " nombre_medico = #{nombre_medico}, estado = #{estado}, fecha_nacimiento = #{fecha_nacimiento}, direccion = #{direccion}, "
			+ " telefono = #{telefono}, email = #{email}, celular = #{celular} "
			+ "where id_medico= #{id_medico}")
	@Options(flushCache=true,useCache=true)
	void actualizarmedico(Medico medicoSelec);

	@Insert("insert into t_medico (id_especialidad, tipo_documento, numero_documento, nombre_medico, estado, fecha_nacimiento, direccion,"
			  			+" telefono,email, celular) "
			  			+ " values ( #{id_especialidad},#{tipo_documento},#{numero_documento},#{nombre_medico},#{estado},#{fecha_nacimiento}, #{direccion},"
			  			+ "  #{telefono},#{email}, #{celular})")
	void crearmedico(Medico medicoSelec);

	@Delete("delete from t_medico  where id_medico = #{id_medico}")
	@Options(flushCache=true)
	void eliminarMedico(@Param("id_medico")Integer id_medico);
	
	@Select("select * from t_medico where id_medico= #{id_medico}")
	public Medico findById(@Param("id_medico")Integer id_medico);

}
