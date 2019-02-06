package com.certicom.scpf.domain;

public class MenuXPerfil {

	private Integer cod_menu;
	private Integer cod_perfil;
	private Boolean ind_activo;
	
	public MenuXPerfil(){
		
	}

	public Integer getCod_menu() {
		return cod_menu;
	}

	public Integer getCod_perfil() {
		return cod_perfil;
	}

	public Boolean getInd_activo() {
		return ind_activo;
	}

	public void setCod_menu(Integer cod_menu) {
		this.cod_menu = cod_menu;
	}

	public void setCod_perfil(Integer cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public void setInd_activo(Boolean ind_activo) {
		this.ind_activo = ind_activo;
	}
}
