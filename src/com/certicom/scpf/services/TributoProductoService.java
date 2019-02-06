package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.TributoProducto;
import com.certicom.scpf.mapper.TributoProductoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class TributoProductoService implements TributoProductoMapper{

	TributoProductoMapper tributoProductoMapper =(TributoProductoMapper) ServiceFinder.findBean("tributoProductoMapper");

	@Override
	public TributoProducto findById(Integer codigoProducto,
			Integer codigoTipoTributo) throws Exception {
		// TODO Auto-generated method stub
		return tributoProductoMapper.findById(codigoProducto, codigoTipoTributo);
	}

	@Override
	public List<TributoProducto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tributoProductoMapper.findAll();
	}

	@Override
	public void crearTributoProducto(TributoProducto tributoProducto)
			throws Exception {
		// TODO Auto-generated method stub
		tributoProductoMapper.crearTributoProducto(tributoProducto);
	}

	@Override
	public void actualizarTributoProducto(TributoProducto tributoProducto)
			throws Exception {
		// TODO Auto-generated method stub
		tributoProductoMapper.actualizarTributoProducto(tributoProducto);
	}

	@Override
	public void eliminarTributoProducto(Integer id_producto,
			Integer tipo_tributo_det) throws Exception {
		// TODO Auto-generated method stub
		tributoProductoMapper.eliminarTributoProducto(id_producto, tipo_tributo_det);
	}

	@Override
	public List<TributoProducto> findByIdProducto(Integer codigoProducto)
			throws Exception {
		// TODO Auto-generated method stub
		return tributoProductoMapper.findByIdProducto(codigoProducto);
	}

	@Override
	public void actualizarTributoProductoOtros(TributoProducto tributoProducto,
			Integer codigoTipoTributo) throws Exception {
		// TODO Auto-generated method stub
		tributoProductoMapper.actualizarTributoProductoOtros(tributoProducto, codigoTipoTributo);
	}

	
	
}
