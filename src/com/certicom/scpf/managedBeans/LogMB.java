package com.certicom.scpf.managedBeans;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.services.LogServices;

public class LogMB {
	private LogServices logServices;
	
	
	public LogMB(){
		logServices = new LogServices();
	}
	/*
	@PostConstruct
	public void inicia(){
		logServices = new LogServices();
	}*/
	
	public void insertarLog(Log log){
		try {
			 	this.logServices.insertLog(log); 
		} catch (Exception e) {
			System.out.println("Error al insertar log"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*Solo para pruebas*/
	public static String dameMiIp(String host){
		String hostName="";
		hostName=host.substring(host.indexOf("/")+1);
		return hostName;
	}

}
