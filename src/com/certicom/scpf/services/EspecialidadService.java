package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Especialidad;
import com.certicom.scpf.mapper.EspecialidadMapper;
import com.certicom.scpf.mapper.PacienteMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class EspecialidadService implements EspecialidadMapper{

	EspecialidadMapper especialidadMapper =(EspecialidadMapper) ServiceFinder.findBean("especialidadMapper");

	public List<Especialidad> findAll() {
		// TODO Auto-generated method stub
		return this.especialidadMapper.findAll();
	}

	public void actualizarEspecialidad(Especialidad especialidadSelec) {
		// TODO Auto-generated method stub
		this.especialidadMapper.actualizarEspecialidad(especialidadSelec);
	}

	public void crearEspecialidad(Especialidad especialidadSelec) {
		// TODO Auto-generated method stub
		this.especialidadMapper.crearEspecialidad(especialidadSelec);
	}

	public void eliminarEspecialidad(Integer id_especialidad) {
		// TODO Auto-generated method stub
		this.especialidadMapper.eliminarEspecialidad(id_especialidad);
	}
}
