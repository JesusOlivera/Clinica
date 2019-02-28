package com.certicom.scpf.domain;

import java.util.Date;

public class ConsultaMedica {

	private Integer id_consulta_medica;
	private Integer id_ticket;
	private Integer id_producto;
	private Integer id_tipo_servicio;
	private Integer id_medico;
	private Integer id_especialidad;
	private Integer id_paciente;
	private Integer id_cliente;
	private Date fecha_consulta;
	private Date hora_consulta;
	private String estado;
	private String anamesis;
	private String listado_problemas;
	private String impresion_diagnostica;
	private String diagnostico;
	private String resumen_hospitalizacion;
	
	private String nro_ticket;
	private String des_servicio;
	private String nombre_medico;
	
	private Producto producto;
	
	private String tipo_control;
	
	public Integer getId_consulta_medica() {
		return id_consulta_medica;
	}
	
	public void setId_consulta_medica(Integer id_consulta_medica) {
		this.id_consulta_medica = id_consulta_medica;
	}
	
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
	
	public Date getFecha_consulta() {
		return fecha_consulta;
	}
	
	public void setFecha_consulta(Date fecha_consulta) {
		this.fecha_consulta = fecha_consulta;
	}
	
	public Date getHora_consulta() {
		return hora_consulta;
	}
	
	public void setHora_consulta(Date hora_consulta) {
		this.hora_consulta = hora_consulta;
	}	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAnamesis() {
		return anamesis;
	}
	
	public void setAnamesis(String anamesis) {
		this.anamesis = anamesis;
	}
	
	public String getListado_problemas() {
		return listado_problemas;
	}
	
	public void setListado_problemas(String listado_problemas) {
		this.listado_problemas = listado_problemas;
	}
	
	public String getImpresion_diagnostica() {
		return impresion_diagnostica;
	}
	
	public void setImpresion_diagnostica(String impresion_diagnostica) {
		this.impresion_diagnostica = impresion_diagnostica;
	}
	
	public String getDiagnostico() {
		return diagnostico;
	}
	
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	public String getResumen_hospitalizacion() {
		return resumen_hospitalizacion;
	}
	
	public void setResumen_hospitalizacion(String resumen_hospitalizacion) {
		this.resumen_hospitalizacion = resumen_hospitalizacion;
	}

	public String getTipo_control() {
		return tipo_control;
	}

	public void setTipo_control(String tipo_control) {
		this.tipo_control = tipo_control;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNro_ticket() {
		return nro_ticket;
	}

	public void setNro_ticket(String nro_ticket) {
		this.nro_ticket = nro_ticket;
	}

	public String getDes_servicio() {
		return des_servicio;
	}

	public void setDes_servicio(String des_servicio) {
		this.des_servicio = des_servicio;
	}

	public String getNombre_medico() {
		return nombre_medico;
	}

	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}	
	
}
