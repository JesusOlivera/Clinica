package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Receta;
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
	public void actualizarReceta(Receta especialidadSelec) {
		// TODO Auto-generated method stub
		recetaMapper.actualizarReceta(especialidadSelec);
	}

	@Override
	public void crearReceta(Receta especialidadSelec) {
		// TODO Auto-generated method stub
		recetaMapper.crearReceta(especialidadSelec);
	}

	@Override
	public void eliminarReceta(Integer id_especialidad) {
		// TODO Auto-generated method stub
		recetaMapper.eliminarReceta(id_especialidad);
	}

	
}
