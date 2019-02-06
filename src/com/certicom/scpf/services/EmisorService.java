package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.TablaTablas;
import com.certicom.scpf.mapper.EmisorMapper;
import com.certicom.scpf.mapper.TablaTablasMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class EmisorService implements EmisorMapper{

	EmisorMapper emisorMapper =(EmisorMapper) ServiceFinder.findBean("emisorMapper");

	@Override
	public Emisor findById(Integer codigoEmisor) throws Exception {
		// TODO Auto-generated method stub
		return emisorMapper.findById(codigoEmisor);
	}

	@Override
	public List<Emisor> findAll() throws Exception {
		// TODO Auto-generated method stub
		return emisorMapper.findAll();
	}

	@Override
	public void crearEmisor(Emisor emisor) throws Exception {
		// TODO Auto-generated method stub
		emisorMapper.crearEmisor(emisor);
	}

	@Override
	public void actualizarEmisor(Emisor emisor) throws Exception {
		// TODO Auto-generated method stub
		emisorMapper.actualizarEmisor(emisor);
	}

	@Override
	public void eliminarEmisor(Integer id_emisor) throws Exception {
		// TODO Auto-generated method stub
		emisorMapper.eliminarEmisor(id_emisor);
	}		

}
