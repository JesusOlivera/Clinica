package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Especialidad;
import com.certicom.scpf.services.EspecialidadService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="especialidadMB")
@ViewScoped
public class EspecialidadMB extends GenericBeans implements Serializable {

	private Especialidad especialidadSelec;
	private Boolean editarEspecialidad;
	private List<Especialidad>listaEspecialidades;
	private EspecialidadService especialidadService;
    private Log log;
	private LogMB logmb;
	
	@PostConstruct
	public void inicia(){
		this.especialidadSelec=new Especialidad();
		this.editarEspecialidad = Boolean.FALSE;
		this.especialidadService= new EspecialidadService();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaEspecialidades=this.especialidadService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevoEspecialidad(){
		this.especialidadSelec = new Especialidad();
		this.editarEspecialidad = Boolean.FALSE;
	}
	
	public void editarEspecialidad(Especialidad Especialidad){
		
		this.especialidadSelec = Especialidad;
		this.editarEspecialidad = Boolean.TRUE;
	}
	
	public void eliminarEspecialidad(Especialidad Especialidad){
		this.especialidadSelec = Especialidad;
	}
	
	public void guardarEspecialidad(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarEspecialidad) {
				this.especialidadService.actualizarEspecialidad(this.especialidadSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza la Especialidad : " + this.especialidadSelec.getDescripcion_especialidad());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion de la especialidad ha sido actualizado", 3);
			}else{
				this.especialidadService.crearEspecialidad(this.especialidadSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta la especialidad: " + this.especialidadSelec.getDescripcion_especialidad());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Especialidad ha sido creada", 3);
			}
			
			this.especialidadSelec = new Especialidad();
			this.editarEspecialidad = Boolean.FALSE;
			
			this.listaEspecialidades = this.especialidadService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarEspecialidad(){
		try {
		
			this.especialidadService.eliminarEspecialidad(this.especialidadSelec.getId_especialidad());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la Especialidad: " + this.especialidadSelec.getDescripcion_especialidad());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Especialidad ha sido eliminado", 3);
			
			this.listaEspecialidades = this.especialidadService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void cambiarEstado(Especialidad especialidad){
		
		   if(especialidad.getEstado_especialidad()){
			   especialidad.setEstado_especialidad(Boolean.FALSE);
		   }else{
			   especialidad.setEstado_especialidad(Boolean.TRUE);
		   }
		   
		   try {
			   this.especialidadService.actualizarEspecialidad(especialidad);
			   FacesUtils.showFacesMessage("Estado especialidad actualizado correctamente",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + especialidad.getEstado_especialidad());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}

	public Especialidad getEspecialidadSelec() {
		return especialidadSelec;
	}

	public void setEspecialidadSelec(Especialidad especialidadSelec) {
		this.especialidadSelec = especialidadSelec;
	}

	public Boolean getEditarEspecialidad() {
		return editarEspecialidad;
	}

	public void setEditarEspecialidad(Boolean editarEspecialidad) {
		this.editarEspecialidad = editarEspecialidad;
	}

	public List<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public EspecialidadService getEspecialidadService() {
		return especialidadService;
	}

	public void setEspecialidadService(EspecialidadService especialidadService) {
		this.especialidadService = especialidadService;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public LogMB getLogmb() {
		return logmb;
	}

	public void setLogmb(LogMB logmb) {
		this.logmb = logmb;
	}
	
	

}
