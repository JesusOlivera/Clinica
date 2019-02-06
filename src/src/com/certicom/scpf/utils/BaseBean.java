package src.com.certicom.scpf.utils;

public class BaseBean {
	
	// datos para feed back
	private boolean estado_trabajo;
	private String descDatoContacto;
	private String descripcion;
	
	//deciles
	private int inicioNumero;
	private int finNumero;
	
	public boolean isEstado_trabajo() {
		return estado_trabajo;
	}
	public void setEstado_trabajo(boolean estado_trabajo) {
		this.estado_trabajo = estado_trabajo;
	}
	public String getDescDatoContacto() {
		return descDatoContacto;
	}
	public void setDescDatoContacto(String descDatoContacto) {
		this.descDatoContacto = descDatoContacto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getInicioNumero() {
		return inicioNumero;
	}
	public void setInicioNumero(int inicioNumero) {
		this.inicioNumero = inicioNumero;
	}
	public int getFinNumero() {
		return finNumero;
	}
	public void setFinNumero(int finNumero) {
		this.finNumero = finNumero;
	}
	
	
	

}
