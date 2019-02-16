package com.certicom.scpf.domain;

public class TipoServicio {

	private Integer id_tipo_servicio;
	private String descripcion_tipo;
	private Boolean genera_ticket;
	
	public Integer getId_tipo_servicio() {
		return id_tipo_servicio;
	}
	public void setId_tipo_servicio(Integer id_tipo_servicio) {
		this.id_tipo_servicio = id_tipo_servicio;
	}
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}
	public Boolean getGenera_ticket() {
		return genera_ticket;
	}
	public void setGenera_ticket(Boolean genera_ticket) {
		this.genera_ticket = genera_ticket;
	}

	
}
