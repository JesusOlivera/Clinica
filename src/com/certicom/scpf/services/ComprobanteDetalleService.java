package com.certicom.scpf.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ComprobanteDetalleService implements ComprobanteDetalleMapper{

	ComprobanteDetalleMapper comprobanteDetalleMapper =(ComprobanteDetalleMapper) ServiceFinder.findBean("comprobanteDetalleMapper");

	@Override
	public ComprobanteDetalle findById(Integer codigoCliente) throws Exception {
		// TODO Auto-generated method stub
		return comprobanteDetalleMapper.findById(codigoCliente);
	}
	
	@Override
	public List<ComprobanteDetalle> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comprobanteDetalleMapper.findAll();
	}

	@Override
	public void crearComprobanteDetalle(ComprobanteDetalle comprobanteDetalle)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.crearComprobanteDetalle(comprobanteDetalle);
	}

	@Override
	public void actualizarComprobanteDetalle(
			ComprobanteDetalle comprobanteDetalle) throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.actualizarComprobanteDetalle(comprobanteDetalle);
	}

	@Override
	public void eliminarComprobanteDetalle(Integer id_comprobante)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.eliminarComprobanteDetalle(id_comprobante);
	}	
	
	public void insertBatchComprobanteDetalle(List<ComprobanteDetalle> cDetalle, Integer id_comprobante) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ComprobanteDetalleMapper daoObj= (ComprobanteDetalleMapper)sqlSession.getMapper(ComprobanteDetalleMapper.class);
		
		System.out.println("id_comprobante==> "+id_comprobante+" Longitud: "+cDetalle.size());
		for (ComprobanteDetalle fpd:cDetalle) {
			fpd.setId_comprobante(id_comprobante);
			System.out.println("MODO PGO :"+fpd.getId_modo_pago());
			this.comprobanteDetalleMapper.crearComprobanteDetalle(fpd);
			
		}
		sqlSession.close();
//		
//		try{
//		
//			//sqlSession.commit(true);
//			for (ComprobanteDetalle fpd:cDetalle) {
//				fpd.setId_comprobante(id_comprobante);
//				daoObj.crearComprobanteDetalle(fpd);
//				
//			}
//			resultado=Boolean.TRUE;
//			//sqlSession.commit();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			resultado=Boolean.FALSE;
//		}finally{
//			sqlSession.close();
//		}
		
		//return resultado;
		//facturacionPlanillaDetalleMapper.eliminarFacturacionPlanillaDetalle(codFacturacionPlanillaDetalle);
	}

	@Override
	public List<ComprobanteDetalle> findByIdComprobante(Integer id_comprobante) throws Exception {
		// TODO Auto-generated method stub
		return this.comprobanteDetalleMapper.findByIdComprobante(id_comprobante);
	}




	
}
