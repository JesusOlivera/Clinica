package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.mapper.MedicoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class MedicoService implements MedicoMapper{
	
	MedicoMapper medicoMapper =(MedicoMapper) ServiceFinder.findBean("medicoMapper");

	public List<Medico> findAll() {
		// TODO Auto-generated method stub
		return medicoMapper.findAll();
	}

	public void actualizarmedico(Medico medicoSelec) {
		// TODO Auto-generated method stub
		medicoMapper.actualizarmedico(medicoSelec);
	}

	public void crearmedico(Medico medicoSelec) {
		// TODO Auto-generated method stub
		medicoMapper.crearmedico(medicoSelec);
	}

	public void eliminarMedico(Integer id_medico) {
		// TODO Auto-generated method stub
		medicoMapper.eliminarMedico(id_medico);
	}

}
