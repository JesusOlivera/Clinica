package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Ticket;


public interface TicketMapper {

	@Select("select t.*, pa.nombre as desPaciente, pr.descripcion_prod_det as desServicio, m.nombre_medico as desMedico, tts.descripcion_tipo as desTipoServicio from (((t_ticket t inner join t_paciente pa on pa.id_paciente = t.id_paciente) inner join t_producto pr on pr.id_producto = t.id_producto) inner join t_medico m on m.id_medico = t.id_medico) inner join t_tipo_servicio tts on tts.id_tipo_servicio = t.id_tipo_servicio order by t.id_ticket desc ")
	List<Ticket> findAll();
	
	@Select("select count(*) from t_ticket")
	Integer obtenerMax();

	@Update("update t_ticket set id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, fecha_ticket = #{fecha_ticket}, hora_ticket = #{hora_ticket}, estado = #{estado}, integrado_sunat = #{integrado_sunat}, encolado = #{encolado}, flag_externo = #{flag_externo}, nro_ticket = #{nro_ticket} "			
			+ "where id_ticket= #{id_ticket}")
	@Options(flushCache=true,useCache=true)
	void actualizarTicket(Ticket ticketSelec);

	@Insert("insert into t_ticket (id_producto,id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_cliente, fecha_ticket, hora_ticket, estado, integrado_sunat, encolado, flag_externo, nro_ticket ) "
			+ " values ( #{id_producto},#{id_tipo_servicio},#{id_medico},#{id_especialidad},#{id_paciente},#{id_cliente},#{fecha_ticket},#{hora_ticket},#{estado},#{integrado_sunat},#{encolado},#{flag_externo},#{nro_ticket})")
	void crearTicket(Ticket ticketSelec);

	@Delete("delete from t_ticket  where id_ticket = #{id_ticket}")
	@Options(flushCache=true)
	void eliminarTicket(Integer id_ticket);

}
