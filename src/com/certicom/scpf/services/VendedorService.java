package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.mapper.VendedorMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

/*Vega.com*/
public class VendedorService implements VendedorMapper{
	
	VendedorMapper vendedorMapper = (VendedorMapper)ServiceFinder.findBean("vendedorMapper"); 

	@Override
	public Vendedor findById(Integer id_vendedor) throws Exception {
		
		return vendedorMapper.findById(id_vendedor);
	}

	@Override
	public List<Vendedor> findAll() throws Exception {
		
		return vendedorMapper.findAll();
	}

	@Override
	public void crearVendedor(Vendedor vendedor) throws Exception {
		
		vendedorMapper.crearVendedor(vendedor);
	}

	@Override
	public void actualizarVendedor(Vendedor vendedor) throws Exception {
		vendedorMapper.actualizarVendedor(vendedor);
		
	}

	@Override
	public void eliminarVendedor(Integer id_vendedor) throws Exception {
		vendedorMapper.eliminarVendedor(id_vendedor);
		
	}

}
