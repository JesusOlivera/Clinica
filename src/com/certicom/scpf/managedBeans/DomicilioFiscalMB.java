package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.MenuServices;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="domicilioFiscalMB")
@ViewScoped
public class DomicilioFiscalMB extends GenericBeans implements Serializable{

	private DomicilioFiscal domicilioFiscalSelec;
	private List<DomicilioFiscal> listaDomicilioFiscal;
	private Boolean editarDomicilioFiscal;
	private MenuServices menuServices;
	private DomicilioFiscalService domicilioFiscalService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public DomicilioFiscalMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.domicilioFiscalSelec = new DomicilioFiscal();
		this.domicilioFiscalService = new DomicilioFiscalService();
		this.menuServices=new MenuServices();
		
		this.editarDomicilioFiscal = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaDomicilioFiscal = this.domicilioFiscalService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:domiciliofiscal");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarDomicilioFiscal(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarDomicilioFiscal) {
				this.domicilioFiscalService.actualizarDomicilioFiscal(this.domicilioFiscalSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza la sucursal : " + this.domicilioFiscalSelec.getDomicilio());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Sucursal ha sido actualizado", 3);
			}else{
				this.domicilioFiscalService.crearDomicilioFiscal(this.domicilioFiscalSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta la sucursal : " + this.domicilioFiscalSelec.getDomicilio());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Sucursal ha sido creado", 3);
			}
			
			this.domicilioFiscalSelec = new DomicilioFiscal();
			this.editarDomicilioFiscal = Boolean.FALSE;
			
			this.listaDomicilioFiscal = this.domicilioFiscalService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoDomicilioFiscal(){
		this.domicilioFiscalSelec = new DomicilioFiscal();
		this.editarDomicilioFiscal = Boolean.FALSE;
	}

	public void editarDomicilioFiscal(DomicilioFiscal domiciliofiscal){
		this.domicilioFiscalSelec = domiciliofiscal;
		this.editarDomicilioFiscal = Boolean.TRUE;
	}
	
	public void eliminarDomicilioFiscal(DomicilioFiscal domiciliofiscal){
		this.domicilioFiscalSelec = domiciliofiscal;
	}
	
	
	public void confirmaEliminarDomicilioFiscal(){
		try {
		
			this.domicilioFiscalService.eliminarDomicilioFiscal(this.domicilioFiscalSelec.getId_domicilio_fiscal_cab());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la sucursal: " + this.domicilioFiscalSelec.getDomicilio());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Sucursal ha sido eliminado", 3);
			
			this.listaDomicilioFiscal = this.domicilioFiscalService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}		
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
	public DomicilioFiscal getDomicilioFiscalSelec() {
		return domicilioFiscalSelec;
	}

	public void setDomicilioFiscalSelec(DomicilioFiscal domicilioFiscalSelec) {
		this.domicilioFiscalSelec = domicilioFiscalSelec;
	}

	public List<DomicilioFiscal> getListaDomicilioFiscal() {
		return listaDomicilioFiscal;
	}

	public void setListaDomicilioFiscal(List<DomicilioFiscal> listaDomicilioFiscal) {
		this.listaDomicilioFiscal = listaDomicilioFiscal;
	}

	public Boolean getEditarDomicilioFiscal() {
		return editarDomicilioFiscal;
	}

	public void setEditarDomicilioFiscal(Boolean editarDomicilioFiscal) {
		this.editarDomicilioFiscal = editarDomicilioFiscal;
	}
	
}
