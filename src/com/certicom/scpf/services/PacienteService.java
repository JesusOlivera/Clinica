package com.certicom.scpf.services;


import java.util.List;

import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.mapper.PacienteMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class PacienteService  implements PacienteMapper{

	PacienteMapper pacienteMapper =(PacienteMapper) ServiceFinder.findBean("pacienteMapper");

	@Override
	public List<Paciente> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.pacienteMapper.findAll();
	}

	public void actualizarPaciente(Paciente pacienteSelec) {
		
		 this.pacienteMapper.actualizarPaciente(pacienteSelec);
	}

	public void crearPaciente(Paciente pacienteSelec) {
		// TODO Auto-generated method stub
		 this.pacienteMapper.crearPaciente(pacienteSelec);
	}

	@Override
	public void eliminarPaciente(Integer id_paciente) throws Exception {
		// TODO Auto-generated method stub
		this.pacienteMapper.eliminarPaciente(id_paciente);
	}

	@Override
	public Paciente obtenerPaciente(Paciente paciente) throws Exception {
		// TODO Auto-generated method stub
		return this.pacienteMapper.obtenerPaciente(paciente);
	}

	@Override
	public Paciente findById(Integer id_paciente) {
		// TODO Auto-generated method stub
		return this.pacienteMapper.findById(id_paciente);
	}
}
