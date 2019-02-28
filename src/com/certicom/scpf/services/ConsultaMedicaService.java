package com.certicom.scpf.services;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.ConsultaMedica;
import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.certicom.scpf.mapper.ConsultaMedicaMapper;
import com.certicom.scpf.mapper.ExamenAuxiliarMapper;
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

	@Override
	public ConsultaMedica findByTicket(Integer id_ticket) {
		// TODO Auto-generated method stub
		return consultaMedicaMapper.findByTicket(id_ticket);
	}

	@Override
	public List<ConsultaMedica> findByPaciente(Integer id_paciente, Integer first,
			Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder) {
		// TODO Auto-generated method stub
		return consultaMedicaMapper.findByPaciente(id_paciente, first, pageSize, filters, sortField, sortOrder);
	}

	@Override
	public Integer countByPaciente(Integer id_paciente) {
		// TODO Auto-generated method stub
		return consultaMedicaMapper.countByPaciente(id_paciente);
	}
	
	
	
}
