package com.certicom.scpf.domain;

import java.util.Date;

public class Ticket {
	
	private Integer id_ticket;
	private Integer id_producto;
	private Integer id_tipo_servicio;
	private Integer id_medico;
	private Integer id_especialidad;
	private Integer id_paciente;
	private Integer id_cliente;
	private Date fecha_ticket;
	private boolean integrado_sunat;
	private Date hora_ticket;
	private String estado;
	private Boolean encolado;
	private Boolean flag_externo;
	private String nro_ticket;
	
	private Paciente paciente;
	private Medico medico;
	private Producto producto;
	
	/* transitorios */
	private String desServicio;
	private String desMedico;
	private String desPaciente;
	
	public Integer getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(Integer id_ticket) {
		this.id_ticket = id_ticket;
	}

	public Integer getId_producto() {
		return id_producto;
	}
	
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	
	public Integer getId_tipo_servicio() {
		return id_tipo_servicio;
	}
	
	public void setId_tipo_servicio(Integer id_tipo_servicio) {
		this.id_tipo_servicio = id_tipo_servicio;
	}
	
	public Integer getId_medico() {
		return id_medico;
	}
	
	public void setId_medico(Integer id_medico) {
		this.id_medico = id_medico;
	}
	
	public Integer getId_especialidad() {
		return id_especialidad;
	}
	
	public void setId_especialidad(Integer id_especialidad) {
		this.id_especialidad = id_especialidad;
	}
	
	public Integer getId_paciente() {
		return id_paciente;
	}
	
	public void setId_paciente(Integer id_paciente) {
		this.id_paciente = id_paciente;
	}
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Date getFecha_ticket() {
		return fecha_ticket;
	}
	
	public void setFecha_ticket(Date fecha_ticket) {
		this.fecha_ticket = fecha_ticket;
	}
	
	public Date getHora_ticket() {
		return hora_ticket;
	}
	
	public void setHora_ticket(Date hora_ticket) {
		this.hora_ticket = hora_ticket;
	}	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getEncolado() {
		return encolado;
	}
	
	public void setEncolado(Boolean encolado) {
		this.encolado = encolado;
	}
	
	public Boolean getFlag_externo() {
		return flag_externo;
	}
	
	public void setFlag_externo(Boolean flag_externo) {
		this.flag_externo = flag_externo;
	}

	public String getNro_ticket() {
		return nro_ticket;
	}

	public void setNro_ticket(String nro_ticket) {
		this.nro_ticket = nro_ticket;
	}

	public String getDesServicio() {
		return desServicio;
	}

	public void setDesServicio(String desServicio) {
		this.desServicio = desServicio;
	}

	public String getDesMedico() {
		return desMedico;
	}

	public void setDesMedico(String desMedico) {
		this.desMedico = desMedico;
	}

	public String getDesPaciente() {
		return desPaciente;
	}

	public void setDesPaciente(String desPaciente) {
		this.desPaciente = desPaciente;
	}

	public boolean isIntegrado_sunat() {
		return integrado_sunat;
	}

	public void setIntegrado_sunat(boolean integrado_sunat) {
		this.integrado_sunat = integrado_sunat;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	
}
