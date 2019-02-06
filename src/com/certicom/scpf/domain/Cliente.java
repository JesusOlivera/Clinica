package com.certicom.scpf.domain;


public class Cliente {
	
	private Integer id_cliente;
	private Integer tipo_docu_iden_cab;
	private String numero_docu_iden_cab;
	private String nombre_cab;
	private String direccion;
	private String telefono;
	private String email;
	private String contacto;
	
	/*Comentario de Prueba Everd Jesus*/
	/*juank**/
	
	/* transitorio */
	private String desTipoDocumento;
	
	 //test
	public Cliente(){
	}
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Integer getTipo_docu_iden_cab() {
		return tipo_docu_iden_cab;
	}
	
	public void setTipo_docu_iden_cab(Integer tipo_docu_iden_cab) {
		this.tipo_docu_iden_cab = tipo_docu_iden_cab;
	}
	
	public String getNumero_docu_iden_cab() {
		return numero_docu_iden_cab;
	}
	
	public void setNumero_docu_iden_cab(String numero_docu_iden_cab) {
		this.numero_docu_iden_cab = numero_docu_iden_cab;
	}
	
	public String getNombre_cab() {
		return nombre_cab;
	}
	
	public void setNombre_cab(String nombre_cab) {
		this.nombre_cab = nombre_cab;
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
	
	public String getContacto() {
		return contacto;
	}
	
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getDesTipoDocumento() {
		return desTipoDocumento;
	}

	public void setDesTipoDocumento(String desTipoDocumento) {
		this.desTipoDocumento = desTipoDocumento;
	}	
	
}
