package com.certicom.scpf.services;

import java.util.List;

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

}
