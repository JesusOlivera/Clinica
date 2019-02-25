package com.certicom.scpf.services;

import java.util.List;
import java.util.Map;

import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.mapper.TicketMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class TicketService implements TicketMapper{

	TicketMapper ticketMapper =(TicketMapper) ServiceFinder.findBean("ticketMapper");

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketMapper.findAll();
	}

	@Override
	public Integer obtenerMax() {
		// TODO Auto-generated method stub
		return ticketMapper.obtenerMax();
	}

	@Override
	public void actualizarTicket(Ticket ticketSelec) {
		// TODO Auto-generated method stub
		ticketMapper.actualizarTicket(ticketSelec);
	}

	@Override
	public void crearTicket(Ticket ticketSelec) {
		// TODO Auto-generated method stub
		ticketMapper.crearTicket(ticketSelec);
	}

	@Override
	public void eliminarTicket(Integer id_ticket) {
		// TODO Auto-generated method stub
		ticketMapper.eliminarTicket(id_ticket);
	}

	@Override
	public Integer obtenerMaxCF() {
		// TODO Auto-generated method stub
		return ticketMapper.obtenerMaxCF();
	}

	@Override
	public Integer obtenerMaxEX() {
		// TODO Auto-generated method stub
		return ticketMapper.obtenerMaxEX();
	}

	@Override
	public List<Ticket> findAllPAGINATOR(Integer anio, Integer mes, Integer id_medico,Integer first, Integer pageSize,
			Map<String, Object> filters, String sortField, String sortOrder)
			throws Exception {
		// TODO Auto-generated method stub
		return ticketMapper.findAllPAGINATOR(anio,mes, id_medico,first, pageSize, filters, sortField, sortOrder);
	}

	@Override
	public Integer countTicketPAGINATOR(Integer anio, Integer mes, Integer id_medico,Map<String, Object> filters)
			throws Exception {
		// TODO Auto-generated method stub
		return ticketMapper.countTicketPAGINATOR(anio,mes, id_medico, filters);
	}

	
}
