package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.mapper.ModoPagoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ModoPagoService implements ModoPagoMapper{
	
	ModoPagoMapper modoPagoMapper =(ModoPagoMapper) ServiceFinder.findBean("modoPagoMapper");

	@Override
	public ModoPago findById(Integer codigoModoPago) throws Exception {

		return modoPagoMapper.findById(codigoModoPago);
	}

	@Override
	public List<ModoPago> findAll() throws Exception {

		return modoPagoMapper.findAll();
	}

	@Override
	public void crearModoPago(ModoPago modoPago) throws Exception {
         
		modoPagoMapper.crearModoPago(modoPago);
		
	}

	@Override
	public void actualizarModoPago(ModoPago modoPago) throws Exception {

		modoPagoMapper.actualizarModoPago(modoPago);
		
	}

	@Override
	public void eliminarModoPago(Integer id_modo_pago) throws Exception {

		modoPagoMapper.eliminarModoPago(id_modo_pago);
	}

}
