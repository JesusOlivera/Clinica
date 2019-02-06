package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.mapper.TablaTablasDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class TablaTablasDetalleService implements TablaTablasDetalleMapper{

	TablaTablasDetalleMapper tablaTablasDetalleMapper =(TablaTablasDetalleMapper) ServiceFinder.findBean("tablaTablasDetalleMapper");

	@Override
	public TablaTablasDetalle findById(Integer codigoCodigo) throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasDetalleMapper.findById(codigoCodigo);
	}

	@Override
	public List<TablaTablasDetalle> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasDetalleMapper.findAll();
	}

	@Override
	public void crearTablaTablasDetalle(TablaTablasDetalle tablatablasdetalle)
			throws Exception {
		// TODO Auto-generated method stub
		tablaTablasDetalleMapper.crearTablaTablasDetalle(tablatablasdetalle);
	}

	@Override
	public void actualizarTablaTablasDetalle(
			TablaTablasDetalle tablatablasdetalle) throws Exception {
		// TODO Auto-generated method stub
		tablaTablasDetalleMapper.actualizarTablaTablasDetalle(tablatablasdetalle);
	}

	@Override
	public void eliminarTablaTablasDetalle(Integer id_codigo) throws Exception {
		// TODO Auto-generated method stub
		tablaTablasDetalleMapper.eliminarTablaTablasDetalle(id_codigo);
	}

	@Override
	public List<TablaTablasDetalle> findByIdMaestra(Integer codigoMaestra)
			throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasDetalleMapper.findByIdMaestra(codigoMaestra);
	}

	@Override
	public List<TablaTablasDetalle> findBySinIGVSinISC() throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasDetalleMapper.findBySinIGVSinISC();
	}

	@Override
	public List<TablaTablasDetalle> findByIdMaestraTotal(Integer codigoMaestra) throws Exception {
		// TODO Auto-generated method stub
		return tablaTablasDetalleMapper.findByIdMaestraTotal(codigoMaestra);
	}

	

}
