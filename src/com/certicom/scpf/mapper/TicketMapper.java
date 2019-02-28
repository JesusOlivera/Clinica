package com.certicom.scpf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Ticket;


public interface TicketMapper {

	List<Ticket> findAll();
	
	@Select("select count(*) from t_ticket")
	Integer obtenerMax();
	
	@Select("select count(*) from t_ticket where tipo_ticket = 'CF'")
	Integer obtenerMaxCF();
	
	@Select("select count(*) from t_ticket where tipo_ticket = 'EX'")
	Integer obtenerMaxEX();
	
	public List<Ticket> findAllPAGINATOR(@Param("anio") Integer anio, @Param("mes") Integer mes, @Param("id_medico") Integer id_medico,@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;

	public Integer countTicketPAGINATOR(@Param("anio") Integer anio, @Param("mes") Integer mes, @Param("id_medico") Integer id_medico,@Param("filters") Map<String,Object> filters)throws Exception;
	
	public List<Ticket> findByPaciente(@Param("id_paciente")Integer id_paciente,@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder);
	public Integer countByPaciente(@Param("id_paciente")Integer id_paciente);
	
	@Update("update t_ticket set id_producto = #{id_producto}, id_tipo_servicio = #{id_tipo_servicio}, id_medico = #{id_medico}, id_especialidad = #{id_especialidad}, id_paciente = #{id_paciente}, id_cliente = #{id_cliente}, fecha_ticket = #{fecha_ticket}, hora_ticket = #{hora_ticket}, estado = #{estado}, integrado_sunat = #{integrado_sunat}, encolado = #{encolado}, flag_externo = #{flag_externo}, nro_ticket = #{nro_ticket}, tipo_ticket = #{tipo_ticket} "			
			+ "where id_ticket= #{id_ticket}")
	@Options(flushCache=true,useCache=true)
	void actualizarTicket(Ticket ticketSelec);

	@Insert("insert into t_ticket (id_producto,id_tipo_servicio, id_medico, id_especialidad, id_paciente, id_cliente, fecha_ticket, hora_ticket, estado, integrado_sunat, encolado, flag_externo, nro_ticket, tipo_ticket ) "
			+ " values ( #{id_producto},#{id_tipo_servicio},#{id_medico},#{id_especialidad},#{id_paciente},#{id_cliente},#{fecha_ticket},#{hora_ticket},#{estado},#{integrado_sunat},#{encolado},#{flag_externo},#{nro_ticket},#{tipo_ticket})")
	void crearTicket(Ticket ticketSelec);

	@Delete("delete from t_ticket  where id_ticket = #{id_ticket}")
	@Options(flushCache=true)
	void eliminarTicket(Integer id_ticket);

}
