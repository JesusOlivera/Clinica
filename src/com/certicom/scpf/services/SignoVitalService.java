package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.SignoVital;
import com.certicom.scpf.mapper.SignoVitalMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class SignoVitalService implements SignoVitalMapper{

	SignoVitalMapper signoVitalMapper =(SignoVitalMapper) ServiceFinder.findBean("signoVitalMapper");

	@Override
	public List<SignoVital> findAll() {
		// TODO Auto-generated method stub
		return signoVitalMapper.findAll();
	}

	@Override
	public void actualizarSignoVital(SignoVital signoVitalSelec) {
		// TODO Auto-generated method stub
		signoVitalMapper.actualizarSignoVital(signoVitalSelec);
	}

	@Override
	public void crearSignoVital(SignoVital signoVitalSelec) {
		// TODO Auto-generated method stub
		signoVitalMapper.crearSignoVital(signoVitalSelec);
	}

	@Override
	public void eliminarSignoVital(Integer id_signo_vital) {
		// TODO Auto-generated method stub
		signoVitalMapper.eliminarSignoVital(id_signo_vital);
	}

	@Override
	public Integer cantidadXConsultaMedica(Integer codigoConsultaMedica) {
		// TODO Auto-generated method stub
		return signoVitalMapper.cantidadXConsultaMedica(codigoConsultaMedica);
	}

	@Override
	public SignoVital findByConsulta(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		return signoVitalMapper.findByConsulta(id_consulta_medica);
	}

	@Override
	public void eliminarSignosByConsulta(Integer id_consulta_medica) {
		// TODO Auto-generated method stub
		this.signoVitalMapper.eliminarSignosByConsulta(id_consulta_medica);
	}

}
