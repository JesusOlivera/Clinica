package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.TablaTablas;
import com.certicom.scpf.mapper.TablaTablasMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class TablaTablasService implements TablaTablasMapper{

	TablaTablasMapper tablaTablasMapper =(TablaTablasMapper) ServiceFinder.findBean("tablaTablasMapper");

	@Override
	public TablaTablas findById(Integer codigoMaestra) throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasMapper.findById(codigoMaestra);
	}

	@Override
	public List<TablaTablas> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasMapper.findAll();
	}

	@Override
	public void crearTablaTablas(TablaTablas tablatablas) throws Exception {
		// TODO Auto-generated method stub
		tablaTablasMapper.crearTablaTablas(tablatablas);
	}

	@Override
	public void actualizarTablaTablas(TablaTablas tablatablas) throws Exception {
		// TODO Auto-generated method stub
		tablaTablasMapper.actualizarTablaTablas(tablatablas);
	}

	@Override
	public void eliminarTablaTablas(Integer id_maestra) throws Exception {
		// TODO Auto-generated method stub
		tablaTablasMapper.eliminarTablaTablas(id_maestra);
	}	

}
