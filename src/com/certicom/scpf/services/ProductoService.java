package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.mapper.ProductoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ProductoService implements ProductoMapper{

	ProductoMapper productoMapper =(ProductoMapper) ServiceFinder.findBean("productoMapper");

	@Override
	public Producto findById(Integer codigoProducto) throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findById(codigoProducto);
	}

	@Override
	public List<Producto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findAll();
	}

	@Override
	public void crearProducto(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.crearProducto(producto);
	}

	@Override
	public void actualizarProducto(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.actualizarProducto(producto);
	}

	@Override
	public void eliminarProducto(Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.eliminarProducto(id_producto);
	}

	@Override
	public List<Producto> findByIdTipoServicio(Integer id_tipo_servicio)
			throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findByIdTipoServicio(id_tipo_servicio);
	}

	@Override
	public List<Producto> findByIdTipoServicioEX(Integer id_tipo_servicio)
			throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findByIdTipoServicioEX(id_tipo_servicio);
	}

	@Override
	public Producto findByDescripcionControl(String descripcion_prod_det) throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findByDescripcionControl(descripcion_prod_det);
	}

	@Override
	public void actualizarPrecioEditable(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		this.productoMapper.actualizarPrecioEditable(producto);
	}

	
	
}
