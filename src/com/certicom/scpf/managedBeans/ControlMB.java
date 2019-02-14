package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Control;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ControlService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="controlMB")
@ViewScoped
public class ControlMB extends GenericBeans implements Serializable{

	private Control controlSelec;
	private List<Control> listaControl;
	private Boolean editarControl;
	private MenuServices menuServices;
	private ControlService controlService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ControlMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.controlSelec = new Control();
		this.controlService = new ControlService();
		this.menuServices=new MenuServices();
		
		this.editarControl = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaControl = this.controlService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:control");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarControl(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarControl) {
				this.controlService.actualizarControl(this.controlSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Control : " + this.controlSelec.getPaciente().getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del Control ha sido actualizado", 3);
			}else{
				this.controlService.crearControl(this.controlSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta Control: " + this.controlSelec.getPaciente().getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Control ha sido creado", 3);
			}
			
			this.controlSelec = new Control();
			this.editarControl = Boolean.FALSE;
			
			this.listaControl = this.controlService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoControl(){
		this.controlSelec = new Control();
		this.editarControl = Boolean.FALSE;
	}

	public void editarControl(Control Control){
		this.controlSelec = Control;
		this.editarControl = Boolean.TRUE;
	}
	
	public void eliminarControl(Control Control){
		this.controlSelec = Control;
	}
	
	
	public void confirmaEliminarControl(){
		try {
		
			this.controlService.eliminarControl(this.controlSelec.getId_control());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina Control: " + this.controlSelec.getPaciente().getNombre());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Control ha sido eliminado", 3);
			
			this.listaControl = this.controlService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Control getControlSelec() {
		return controlSelec;
	}

	public void setControlSelec(Control ControlSelec) {
		this.controlSelec = ControlSelec;
	}

	public List<Control> getListaControl() {
		return listaControl;
	}

	public void setListaControl(List<Control> listaControl) {
		this.listaControl = listaControl;
	}

	public Boolean getEditarControl() {
		return editarControl;
	}

	public void setEditarControl(Boolean editarControl) {
		this.editarControl = editarControl;
	}
}
