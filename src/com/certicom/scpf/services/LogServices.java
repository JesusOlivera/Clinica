package com.certicom.scpf.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.mapper.LogMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class LogServices implements LogMapper {
	
	LogMapper logMapper = (LogMapper)ServiceFinder.findBean("logMapper");

	@Override
	public void insertLog(Log log) throws Exception {
		// TODO Auto-generated method stub
		logMapper.insertLog(log);
	}

	@Override
	public List<Log> getLog_Ene() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ene();
	}

	@Override
	public List<Log> getLog_Feb() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Feb();
	}

	@Override
	public List<Log> getLog_Mar() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Mar();
	}

	@Override
	public List<Log> getLog_Abr() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Abr();
	}

	@Override
	public List<Log> getLog_May() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_May();
	}

	@Override
	public List<Log> getLog_Jun() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jun();
	}

	@Override
	public List<Log> getLog_Jul() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jul();
	}

	@Override
	public List<Log> getLog_Ago() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ago();
	}

	@Override
	public List<Log> getLog_Set() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Set();
	}

	@Override
	public List<Log> getLog_Oct() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Oct();
	}

	@Override
	public List<Log> getLog_Nov() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Nov();
	}

	@Override
	public List<Log> getLog_Dic() throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Dic();
	}

	@Override
	public List<Log> getLog_Ene_By_Login(String login) throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ene_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Jul_By_Login(String login) throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jul_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Jul_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jul_By_Accion(accion); 
	}

	@Override
	public List<Log> getLog_Jul_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jul_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_Ene_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ene_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Ene_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ene_By_OpcionMenu(opcion);
	}

	@Override
	public List<Log> getLog_Feb_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Feb_By_Login(login);
	}

	@Override
	public List<Log> getLog_Feb_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Feb_By_Accion(accion); 
	}

	@Override
	public List<Log> getLog_Feb_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Feb_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_Mar_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Mar_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Mar_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Mar_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Mar_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Mar_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_Abr_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Abr_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Abr_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Abr_By_Accion(accion); 
	}

	@Override
	public List<Log> getLog_Abr_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Abr_By_OpcionMenu(opcion);		
	}

	@Override
	public List<Log> getLog_May_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_May_By_Login(login); 
	}

	@Override
	public List<Log> getLog_May_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_May_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_May_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_May_By_OpcionMenu(opcion);
	}

	@Override
	public List<Log> getLog_Jun_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jun_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Jun_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jun_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Jun_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Jun_By_OpcionMenu(opcion);
	}

	@Override
	public List<Log> getLog_Ago_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ago_By_Login(login);
	}

	@Override
	public List<Log> getLog_Ago_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ago_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Ago_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Ago_By_OpcionMenu(opcion);
	}

	@Override
	public List<Log> getLog_Set_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Set_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Set_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Set_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Set_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Set_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_Oct_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Oct_By_Login(login);
	}

	@Override
	public List<Log> getLog_Oct_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Oct_By_Accion(accion);
	}

	@Override
	public List<Log> getLog_Oct_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Oct_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_Nov_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Nov_By_Login(login) ;
	}

	@Override
	public List<Log> getLog_Nov_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Nov_By_Accion(accion); 
	}

	@Override
	public List<Log> getLog_Nov_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Nov_By_OpcionMenu(opcion);
	}

	@Override
	public List<Log> getLog_Dic_By_Login(@Param("p_login") String login)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Dic_By_Login(login); 
	}

	@Override
	public List<Log> getLog_Dic_By_Accion(@Param("p_accion") String accion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Dic_By_Accion(accion); 
	}

	@Override
	public List<Log> getLog_Dic_By_OpcionMenu(@Param("p_opcion") String opcion)
			throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_Dic_By_OpcionMenu(opcion); 
	}

	@Override
	public List<Log> getLog_DateInterval(
			@Param("p_fecha_inicio") Date fecha_inicio,
			@Param("p_fecha_fin") Date fecha_fin) throws Exception {
		// TODO Auto-generated method stub
		return logMapper.getLog_DateInterval(fecha_inicio, fecha_fin); 
	}

	@Override
	public List<Integer> getAniosRegistrados() throws Exception {
		return logMapper.getAniosRegistrados();
	}

	@Override
	public List<Log> getLogFiltradoPeriodoAnio(Integer mes, Integer anio)
			throws Exception {
		return logMapper.getLogFiltradoPeriodoAnio(mes, anio);
	}

	
}
