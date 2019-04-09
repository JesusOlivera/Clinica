package com.certicom.scpf.domain;

import java.math.BigDecimal;
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
	private String tipo_ticket;
	
	private Paciente paciente;
	private Medico medico;
	private Producto producto;
	private TipoServicio tipoServicio;
	private Cliente cliente;
	
	private Integer id_examen;
	
	/* transitorios */
	private String des_servicio;
	private String des_medico;
	private String des_paciente;
	private String des_tipo_servicio;
	private Date fecha_consulta;
	private Date hora_consulta;
	private Boolean estado_cm;
	private String nombre_medico;
	private BigDecimal precio_final_editado_cliente;
	
	
	public Ticket() {
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

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Integer getId_examen() {
		return id_examen;
	}

	public void setId_examen(Integer id_examen) {
		this.id_examen = id_examen;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipo_ticket() {
		return tipo_ticket;
	}

	public void setTipo_ticket(String tipo_ticket) {
		this.tipo_ticket = tipo_ticket;
	}

	public String getDes_servicio() {
		return des_servicio;
	}

	public void setDes_servicio(String des_servicio) {
		this.des_servicio = des_servicio;
	}

	public String getDes_medico() {
		return des_medico;
	}

	public void setDes_medico(String des_medico) {
		this.des_medico = des_medico;
	}

	public String getDes_paciente() {
		return des_paciente;
	}

	public void setDes_paciente(String des_paciente) {
		this.des_paciente = des_paciente;
	}

	public String getDes_tipo_servicio() {
		return des_tipo_servicio;
	}

	public void setDes_tipo_servicio(String des_tipo_servicio) {
		this.des_tipo_servicio = des_tipo_servicio;
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

	public Boolean getEstado_cm() {
		return estado_cm;
	}

	public void setEstado_cm(Boolean estado_cm) {
		this.estado_cm = estado_cm;
	}

	public String getNombre_medico() {
		return nombre_medico;
	}

	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}

	public BigDecimal getPrecio_final_editado_cliente() {
		return precio_final_editado_cliente;
	}

	public void setPrecio_final_editado_cliente(BigDecimal precio_final_editado_cliente) {
		this.precio_final_editado_cliente = precio_final_editado_cliente;
	}	
	
}
