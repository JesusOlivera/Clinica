package com.certicom.scpf.services;




import com.certicom.scpf.domain.ComunicacionBaja;
import com.certicom.scpf.mapper.ComunicacionBajaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ComunicacionBajaService implements ComunicacionBajaMapper{

	ComunicacionBajaMapper comunicacionBajaMapper =(ComunicacionBajaMapper) ServiceFinder.findBean("comunicacionBajaMapper");

	@Override
	public void crearComunicacionBaja(ComunicacionBaja comunicacionBaja) throws Exception {
		// TODO Auto-generated method stub
		this.comunicacionBajaMapper.crearComunicacionBaja(comunicacionBaja);
	}

	
}
