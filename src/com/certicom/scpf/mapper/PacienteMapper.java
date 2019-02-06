package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Paciente;


public interface PacienteMapper {
	
	
	public List<Paciente> findAll() throws Exception;
	
	
	@Update("update t_paciente set  id_cliente = #{id_cliente}, id_tipo_documento = #{id_tipo_documento}, numero_documento = #{numero_documento},"
			+ " nombre = #{nombre}, fecha_nacimiento = #{fecha_nacimiento}, direccion = #{direccion}, telefono = #{telefono}, "
			+ " email = #{email}, tipo_sangre = #{tipo_sangre}, es_cliente = #{es_cliente}   "
			+ "where id_paciente= #{id_paciente}")
	@Options(flushCache=true,useCache=true)
	public void actualizarPaciente(Paciente pacienteSelec);

	@Insert("insert into t_paciente (id_cliente,id_tipo_documento,numero_documento,nombre,fecha_nacimiento,direccion,telefono,"
								  +" email,tipo_sangre,es_cliente  ) "
					     + " values ( #{id_cliente},#{id_tipo_documento},#{numero_documento},#{nombre},#{fecha_nacimiento},#{direccion},#{telefono},"
					     		+ "  #{email},#{tipo_sangre},#{es_cliente})")
	public void crearPaciente(Paciente pacienteSelec);
	
	@Delete("delete from t_paciente  where id_paciente = #{id_paciente}")
	@Options(flushCache=true)
	public void eliminarPaciente(@Param("id_paciente") Integer id_paciente) throws Exception;

}
