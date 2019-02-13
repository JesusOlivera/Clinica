package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class Receta {

	  private Integer id_receta ;
	  private Integer id_consulta_medica ;
	  private Integer id_producto ;
	  private Integer id_tipo_servicio ;
	  private Integer id_medico ;
	  private Integer id_especialidad ;
	  private Integer id_paciente;
	  private Integer id_cliente ;
	  private String medicamento ;
	  private BigDecimal cantidad ;
	  private String dosis;
	  private BigDecimal horas ;
	  private BigDecimal duracion ;
	  
	  private Producto producto;
	  private TipoServicio tipoServicio;
	  private Medico medico;
	  private Especialidad especialidad;
	  private Paciente paciente;
	  private Cliente cliente;
	public Integer getId_receta() {
		return id_receta;
	}
	public void setId_receta(Integer id_receta) {
		this.id_receta = id_receta;
	}
	public Integer getId_consulta_medica() {
		return id_consulta_medica;
	}
	public void setId_consulta_medica(Integer id_consulta_medica) {
		this.id_consulta_medica = id_consulta_medica;
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
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}
	public BigDecimal getDuracion() {
		return duracion;
	}
	public void setDuracion(BigDecimal duracion) {
		this.duracion = duracion;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	  
	  
	  
}
