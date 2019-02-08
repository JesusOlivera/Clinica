package com.certicom.scpf.services;

import java.util.List;

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

	
}
