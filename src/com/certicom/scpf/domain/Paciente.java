package com.certicom.scpf.domain;

import java.sql.Date;

public class Paciente {
	
	private Integer id_paciente;
	private Integer id_cliente;
	private Integer id_tipo_documento;
	private String numero_documento; 
	private String nombre;
	private Date fecha_nacimiento;
	private String direccion;
	private String telefono;
	private String email;
	private String tipo_sangre;
	private Boolean es_cliente;

	private Cliente cliente;

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

	public Integer getId_tipo_documento() {
		return id_tipo_documento;
	}

	public void setId_tipo_documento(Integer id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo_sangre() {
		return tipo_sangre;
	}

	public void setTipo_sangre(String tipo_sangre) {
		this.tipo_sangre = tipo_sangre;
	}

	public Boolean getEs_cliente() {
		return es_cliente;
	}

	public void setEs_cliente(Boolean es_cliente) {
		this.es_cliente = es_cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
