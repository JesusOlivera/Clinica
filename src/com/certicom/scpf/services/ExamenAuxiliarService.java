package com.certicom.scpf.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.mapper.ExamenAuxiliarMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ExamenAuxiliarService implements ExamenAuxiliarMapper{

	ExamenAuxiliarMapper examenAuxiliarMapper =(ExamenAuxiliarMapper) ServiceFinder.findBean("examenAuxiliarMapper");

	@Override
	public List<ExamenAuxiliar> findAll() {
		// TODO Auto-generated method stub
		return examenAuxiliarMapper.findAll();
	}

	@Override
	public void actualizarExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec) {
		// TODO Auto-generated method stub
		examenAuxiliarMapper.actualizarExamenAuxiliar(examenAuxiliarSelec);
	}

	@Override
	public void crearExamenAuxiliar(ExamenAuxiliar examenAuxiliarSelec) {
		// TODO Auto-generated method stub
		examenAuxiliarMapper.crearExamenAuxiliar(examenAuxiliarSelec);
	}

	@Override
	public void eliminarExamenAuxiliar(Integer id_examen_auxiliar) {
		// TODO Auto-generated method stub
		examenAuxiliarMapper.eliminarExamenAuxiliar(id_examen_auxiliar);
	}

	@Override
	public List<ExamenAuxiliar> findByConsulta(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		return this.examenAuxiliarMapper.findByConsulta(id_consulta_medica);
	}
	
	public void insertBatchExamenesAux(List<ExamenAuxiliar> listaExamenes, Integer id_consulta_medica) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExamenAuxiliarMapper daoObj= (ExamenAuxiliarMapper)sqlSession.getMapper(ExamenAuxiliarMapper.class);
		
		System.out.println("insertBatchExamenesAux--->id_consulta_medica==> "+id_consulta_medica+" Longitud: "+listaExamenes.size());
		for (ExamenAuxiliar exa:listaExamenes) {
			exa.setId_consulta_medica(id_consulta_medica);
			examenAuxiliarMapper.crearExamenAuxiliar(exa);
			
		}
		sqlSession.close();
	}
	
	public void eliminarExamenesByConsulta(Integer id_consulta_medica) {
		
			this.examenAuxiliarMapper.eliminarExamenesByConsulta(id_consulta_medica);
		
	}

	public void actualizarEstadoExamen(Integer id_examen) {
		// TODO Auto-generated method stub
		this.examenAuxiliarMapper.actualizarEstadoExamen(id_examen);
	}

}
