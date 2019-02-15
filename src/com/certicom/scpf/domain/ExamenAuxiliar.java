package com.certicom.scpf.domain;

public class ExamenAuxiliar {

	private Integer id_examen_auxiliar;
	private Integer id_producto;
	private Integer id_tipo_servicio;
	private Integer id_medico;
	private Integer id_especialidad;
	private Integer id_paciente;
	private Integer id_consulta_medica;
	private Integer id_cliente;
	private String  resultado;
	private String  detalle;
	
	public Integer getId_examen_auxiliar() {
		return id_examen_auxiliar;
	}
	
	public void setId_examen_auxiliar(Integer id_examen_auxiliar) {
		this.id_examen_auxiliar = id_examen_auxiliar;
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
	
	public Integer getId_consulta_medica() {
		return id_consulta_medica;
	}
	
	public void setId_consulta_medica(Integer id_consulta_medica) {
		this.id_consulta_medica = id_consulta_medica;
	}
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}	
	
}
