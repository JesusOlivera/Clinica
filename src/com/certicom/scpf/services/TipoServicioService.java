package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.mapper.TipoServicioMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class TipoServicioService implements TipoServicioMapper{

	TipoServicioMapper tipoServicioMapper =(TipoServicioMapper) ServiceFinder.findBean("tipoServicioMapper");

	public List<TipoServicio> findAll() {
		// TODO Auto-generated method stub
		return this.tipoServicioMapper.findAll();
	}

	public void actualizartipoServicio(TipoServicio tipoServicioSelec) {
		// TODO Auto-generated method stub
		this.tipoServicioMapper.actualizartipoServicio(tipoServicioSelec);
	}

	public void creartipoServicio(TipoServicio tipoServicioSelec) {
		// TODO Auto-generated method stub
		this.tipoServicioMapper.creartipoServicio(tipoServicioSelec);
	}

	public void eliminartipoServicio(Integer id_tipo_servicio) {
		// TODO Auto-generated method stub
		this.tipoServicioMapper.eliminartipoServicio(id_tipo_servicio);
	}

	@Override
	public List<TipoServicio> findAllForTicket() {
		// TODO Auto-generated method stub
		return this.tipoServicioMapper.findAllForTicket();
	}
}
