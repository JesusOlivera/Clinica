package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.TablaTablas;
import com.certicom.scpf.mapper.ClienteMapper;
import com.certicom.scpf.mapper.TablaTablasMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ClienteService implements ClienteMapper{

	ClienteMapper clienteMapper =(ClienteMapper) ServiceFinder.findBean("clienteMapper");

	@Override
	public Cliente findById(Integer codigoCliente) throws Exception {
		// TODO Auto-generated method stub
		return clienteMapper.findById(codigoCliente);
	}

	@Override
	public List<Cliente> findAll() throws Exception {
		// TODO Auto-generated method stub
		return clienteMapper.findAll();
	}

	@Override
	public void crearCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		clienteMapper.crearCliente(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		clienteMapper.actualizarCliente(cliente);
	}

	@Override
	public void eliminarCliente(Integer id_cliente) throws Exception {
		// TODO Auto-generated method stub
		clienteMapper.eliminarCliente(id_cliente);
	}

	@Override
	public Cliente findByFiltro(String filtro) throws Exception {
		// TODO Auto-generated method stub
		return clienteMapper.findByFiltro(filtro);
	}
	
}
