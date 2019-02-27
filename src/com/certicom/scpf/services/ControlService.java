package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Control;
import com.certicom.scpf.mapper.ClienteMapper;
import com.certicom.scpf.mapper.ControlMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ControlService implements ControlMapper{
	
	ControlMapper controlMapper =(ControlMapper) ServiceFinder.findBean("controlMapper");

	@Override
	public Control findById(Integer id_control) throws Exception {
		// TODO Auto-generated method stub
		return controlMapper.findById(id_control);
	}

	@Override
	public List<Control> findAll() throws Exception {
		// TODO Auto-generated method stub
		return controlMapper.findAll();
	}

	@Override
	public void crearControl(Control Control) throws Exception {
		// TODO Auto-generated method stub
		controlMapper.crearControl(Control);
	}

	@Override
	public void actualizarControl(Control Control) throws Exception {
		// TODO Auto-generated method stub
		controlMapper.actualizarControl(Control);
	}

	@Override
	public void eliminarControl(Integer id_control) throws Exception {
		// TODO Auto-generated method stub
		controlMapper.eliminarControl(id_control);
	}

	@Override
	public List<Control> findByIdConsultaMedica(Integer id_consulta_medica)
			throws Exception {
		// TODO Auto-generated method stub
		return controlMapper.findByIdConsultaMedica(id_consulta_medica);
	}

}
