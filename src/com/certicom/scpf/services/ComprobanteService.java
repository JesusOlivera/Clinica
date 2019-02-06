package com.certicom.scpf.services;

import java.util.List;
import java.util.Map;

import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.mapper.ComprobanteMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ComprobanteService implements ComprobanteMapper{

	ComprobanteMapper comprobanteMapper =(ComprobanteMapper) ServiceFinder.findBean("comprobanteMapper");

	@Override
	public Comprobante findById(Integer codigoCliente) throws Exception {
		// TODO Auto-generated method stub
		return comprobanteMapper.findById(codigoCliente);
	}

	@Override
	public List<Comprobante> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comprobanteMapper.findAll();
	}

	@Override
	public int crearComprobante(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		return comprobanteMapper.crearComprobante(comprobante);
	}

	@Override
	public void actualizarComprobante(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		comprobanteMapper.actualizarComprobante(comprobante);
	}

	@Override
	public void eliminarComprobante(Integer id_cliente) throws Exception {
		// TODO Auto-generated method stub
		comprobanteMapper.eliminarComprobante(id_cliente);
	}

	/*** MODIFICACION JCTV***/
	@Override
	public void actualizarEstadoBaja(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		this.comprobanteMapper.actualizarEstadoBaja(comprobante);
	}

	@Override
	public List<Comprobante> listaComprobantes() throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.listaComprobantes();
	}

	@Override
	public int getSecIdComprobante() {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.getSecIdComprobante();
	}

	@Override
	public void crearComprobanteSec(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		this.comprobanteMapper.crearComprobanteSec(comprobante);
	}

	@Override
	public List<Comprobante> listaComprobantesLecturaRespuesta() throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.listaComprobantesLecturaRespuesta();
	}

	@Override
	public int getCorrelativoComprobante(String tipo_comprobante) throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.getCorrelativoComprobante(tipo_comprobante);
	}

	@Override
	public void actualizarEstadoEnvioSunatComprobante(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		this.comprobanteMapper.actualizarEstadoEnvioSunatComprobante(comprobante);
	}

	@Override
	public void actualizarEstadoRespuestaSunatComprobante(Comprobante comprobante) throws Exception {
		// TODO Auto-generated method stub
		this.comprobanteMapper.actualizarEstadoRespuestaSunatComprobante(comprobante);
	}

	@Override
	public Integer countCompByAnioMesTipoPAGINATOR(Integer annio, Integer mes, String tipo_comprobante,
			Map<String, Object> filters) throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.countCompByAnioMesTipoPAGINATOR(annio, mes, tipo_comprobante, filters);
	}

	@Override
	public List<Comprobante> listComprobantesByAnioMesTipoPAGINATOR(Integer annio, Integer mes,
			String tipo_comprobante, Integer first, Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder) throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.listComprobantesByAnioMesTipoPAGINATOR(annio, mes, tipo_comprobante, first, pageSize, filters, sortField, sortOrder);
	}

	public Comprobante getByNumeroSerieComprobante(String numero_serie_documento_cab) {
		// TODO Auto-generated method stub
		return this.comprobanteMapper.getByNumeroSerieComprobante(numero_serie_documento_cab);
	}
	
}
