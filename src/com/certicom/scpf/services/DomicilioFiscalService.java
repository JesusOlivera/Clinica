package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.TablaTablas;
import com.certicom.scpf.mapper.DomicilioFiscalMapper;
import com.certicom.scpf.mapper.TablaTablasMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class DomicilioFiscalService implements DomicilioFiscalMapper{

	DomicilioFiscalMapper domicilioFiscalMapper =(DomicilioFiscalMapper) ServiceFinder.findBean("domicilioFiscalMapper");

	@Override
	public DomicilioFiscal findById(Integer codigoDomicilioFiscal)
			throws Exception {
		// TODO Auto-generated method stub
		return domicilioFiscalMapper.findById(codigoDomicilioFiscal);
	}

	@Override
	public List<DomicilioFiscal> findAll() throws Exception {
		// TODO Auto-generated method stub
		return domicilioFiscalMapper.findAll();
	}

	@Override
	public void crearDomicilioFiscal(DomicilioFiscal domiciliofiscal)
			throws Exception {
		// TODO Auto-generated method stub
		domicilioFiscalMapper.crearDomicilioFiscal(domiciliofiscal);
	}

	@Override
	public void actualizarDomicilioFiscal(DomicilioFiscal domiciliofiscal)
			throws Exception {
		// TODO Auto-generated method stub
		domicilioFiscalMapper.actualizarDomicilioFiscal(domiciliofiscal);
	}

	@Override
	public void eliminarDomicilioFiscal(Integer id_domicilio_fiscal)
			throws Exception {
		// TODO Auto-generated method stub
		domicilioFiscalMapper.eliminarDomicilioFiscal(id_domicilio_fiscal);
	}

		

}
