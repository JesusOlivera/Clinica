package com.certicom.scpf.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.domain.Receta;
import com.certicom.scpf.mapper.ExamenAuxiliarMapper;
import com.certicom.scpf.mapper.RecetaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class RecetaService implements RecetaMapper{

	RecetaMapper recetaMapper =(RecetaMapper) ServiceFinder.findBean("recetaMapper");

	@Override
	public List<Receta> findAll() {
		// TODO Auto-generated method stub
		return recetaMapper.findAll();
	}

	@Override
	public void actualizarReceta(Receta receta) {
		// TODO Auto-generated method stub
		recetaMapper.actualizarReceta(receta);
	}

	@Override
	public void crearReceta(Receta receta) {
		// TODO Auto-generated method stub
		recetaMapper.crearReceta(receta);
	}

	@Override
	public void eliminarReceta(Integer id_receta) {
		// TODO Auto-generated method stub
		recetaMapper.eliminarReceta(id_receta);
	}

	@Override
	public List<Receta> findByConsulta(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		return recetaMapper.findByConsulta(id_consulta_medica);
	}
	
	public void insertBatchRecetas(List<Receta> listaRecetas, Integer id_consulta_medica) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		RecetaMapper daoObj= (RecetaMapper)sqlSession.getMapper(RecetaMapper.class);
		
		System.out.println("insertBatchRecetas--->id_consulta_medica==> "+id_consulta_medica+" Longitud: "+listaRecetas.size());
		for (Receta rec:listaRecetas) {
			rec.setId_consulta_medica(id_consulta_medica);
			recetaMapper.crearReceta(rec);
			
		}
		sqlSession.close();
	}

	public void eliminarRecetasByConsulta(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		recetaMapper.eliminarRecetasByConsulta(id_consulta_medica);
	}

	
}
