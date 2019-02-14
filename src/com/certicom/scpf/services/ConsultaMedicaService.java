package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.ConsultaMedica;
import com.certicom.scpf.mapper.ConsultaMedicaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ConsultaMedicaService implements ConsultaMedicaMapper{

	ConsultaMedicaMapper consultaMedicaMapper =(ConsultaMedicaMapper) ServiceFinder.findBean("consultaMedicaMapper");

	@Override
	public List<ConsultaMedica> findAll() {
		// TODO Auto-generated method stub
		return consultaMedicaMapper.findAll();
	}

	@Override
	public void actualizarConsultaMedica(ConsultaMedica consultaMedica) {
		// TODO Auto-generated method stub
		consultaMedicaMapper.actualizarConsultaMedica(consultaMedica);
	}

	@Override
	public void crearConsultaMedica(ConsultaMedica consultaMedica) {
		// TODO Auto-generated method stub
		consultaMedicaMapper.crearConsultaMedica(consultaMedica);
	}

	@Override
	public void eliminarConsultaMedica(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		consultaMedicaMapper.eliminarConsultaMedica(id_consulta_medica);
	}

	
}
