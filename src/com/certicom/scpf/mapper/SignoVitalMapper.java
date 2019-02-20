package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.SignoVital;


public interface SignoVitalMapper {

	@Select("select * from t_signo_vital order by id_signo_vital asc")
	List<SignoVital> findAll();
	
	@Select("select count(*) from t_signo_vital where id_consulta_medica = #{p_id_consulta_medica} order by id_signo_vital asc")
	Integer cantidadXConsultaMedica(@Param("p_id_consulta_medica") Integer codigoConsultaMedica);

	@Update("update t_signo_vital set  id_consulta_medica = #{id_consulta_medica}, id_producto = #{id_producto}, "
			+ "id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, "
			+ "id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, talla = #{talla}, peso = #{peso}, "
			+ "ocupacion = #{ocupacion}, temperatura = #{temperatura}, alergia = #{alergia}, "
			+ "frecuencia_cardiaca = #{frecuencia_cardiaca}, frecuencia_respiratoria = #{frecuencia_respiratoria}, "
			+ "presion_arterial = #{presion_arterial}, embarazo = #{embarazo}, sat_oxigeno = #{sat_oxigeno}, "
			+ "otros1 = #{otros1}, otros2 = #{otros2}, otros3 = #{otros3} "			
			+ "where id_signo_vital= #{id_signo_vital}")
	@Options(flushCache=true,useCache=true)
	void actualizarSignoVital(SignoVital signoVitalSelec);

	@Insert("insert into t_signo_vital (id_consulta_medica, id_producto, id_tipo_servicio, id_medico, id_especialidad,"
			+ "id_paciente, id_cliente, talla, peso, ocupacion, temperatura, alergia, frecuencia_cardiaca, "
			+ "frecuencia_respiratoria, presion_arterial, embarazo, sat_oxigeno, otros1, otros2, otros3) "
			+ " values ( #{id_consulta_medica}, #{id_producto}, #{id_tipo_servicio}, #{id_medico}, #{id_especialidad}, "
			+ "#{id_paciente}, #{id_cliente}, #{talla}, #{peso}, #{ocupacion}, #{temperatura}, #{alergia}, #{frecuencia_cardiaca}, "
			+ "#{frecuencia_respiratoria}, #{presion_arterial}, #{embarazo}, #{sat_oxigeno}, #{otros1}, #{otros2}, #{otros3})")
	void crearSignoVital(SignoVital signoVitalSelec);

	@Delete("delete from t_signo_vital  where id_signo_vital = #{id_signo_vital}")
	@Options(flushCache=true)
	void eliminarSignoVital(@Param("id_signo_vital") Integer id_signo_vital);
	
	@Select("select * from t_signo_vital where id_consulta_medica = #{id_consulta_medica} ")
	public SignoVital findByConsulta(@Param("id_consulta_medica") Integer id_consulta_medica);
	
	@Delete("delete from t_signo_vital  where id_consulta_medica = #{id_consulta_medica}")
	@Options(flushCache=true)
	public void eliminarSignosByConsulta(@Param("id_consulta_medica") Integer id_consulta_medica);

}
