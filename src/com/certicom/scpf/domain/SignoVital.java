package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class SignoVital {

	  private Integer id_signo_vital ;
	  private Integer id_consulta_medica ;
	  private Integer id_producto ;
	  private Integer id_tipo_servicio ;
	  private Integer id_medico;
	  private Integer id_especialidad ;
	  private Integer id_paciente ;
	  private Integer id_cliente ;
	  private BigDecimal talla ;
	  private BigDecimal peso ;
	  private String ocupacion ;
	  private BigDecimal temperatura ;
	  private String alergia ;
	  private BigDecimal frecuencia_cardiaca ;
	  private BigDecimal frecuencia_respiratoria;
	  private String presion_arterial ;
	  private Boolean embarazo;
	  private String sat_oxigeno ;
	  private String otros1;
	  private String otros2 ;
	  private String otros3 ;


	  
	public Integer getId_signo_vital() {
		return id_signo_vital;
	}
	
	public void setId_signo_vital(Integer id_signo_vital) {
		this.id_signo_vital = id_signo_vital;
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
	
	public BigDecimal getTalla() {
		return talla;
	}
	
	public void setTalla(BigDecimal talla) {
		this.talla = talla;
	}
	
	public BigDecimal getPeso() {
		return peso;
	}
	
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	
	public String getOcupacion() {
		return ocupacion;
	}
	
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public BigDecimal getTemperatura() {
		return temperatura;
	}
	
	public void setTemperatura(BigDecimal temperatura) {
		this.temperatura = temperatura;
	}
	
	public String getAlergia() {
		return alergia;
	}
	
	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}
	
	public BigDecimal getFrecuencia_cardiaca() {
		return frecuencia_cardiaca;
	}
	
	public void setFrecuencia_cardiaca(BigDecimal frecuencia_cardiaca) {
		this.frecuencia_cardiaca = frecuencia_cardiaca;
	}
	
	public BigDecimal getFrecuencia_respiratoria() {
		return frecuencia_respiratoria;
	}
	
	public void setFrecuencia_respiratoria(BigDecimal frecuencia_respiratoria) {
		this.frecuencia_respiratoria = frecuencia_respiratoria;
	}
	
	public String getPresion_arterial() {
		return presion_arterial;
	}
	
	public void setPresion_arterial(String presion_arterial) {
		this.presion_arterial = presion_arterial;
	}
	
	public Boolean getEmbarazo() {
		return embarazo;
	}
	
	public void setEmbarazo(Boolean embarazo) {
		this.embarazo = embarazo;
	}
	
	public String getSat_oxigeno() {
		return sat_oxigeno;
	}
	
	public void setSat_oxigeno(String sat_oxigeno) {
		this.sat_oxigeno = sat_oxigeno;
	}
	
	public String getOtros1() {
		return otros1;
	}
	
	public void setOtros1(String otros1) {
		this.otros1 = otros1;
	}
	
	public String getOtros2() {
		return otros2;
	}
	
	public void setOtros2(String otros2) {
		this.otros2 = otros2;
	}
	
	public String getOtros3() {
		return otros3;
	}
	
	public void setOtros3(String otros3) {
		this.otros3 = otros3;
	}  
	  
}
