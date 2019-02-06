package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ModoPagoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;


@ManagedBean(name="modoPagoMB")
@ViewScoped
public class ModoPagoMB extends GenericBeans implements Serializable{
	
	
	private ModoPago modoPagoSelec;
	private List<ModoPago> listaModoPago;
	private Boolean editarModoPago;
	private MenuServices menuServices;
	private ModoPagoService modoPagoService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ModoPagoMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.modoPagoSelec = new ModoPago();
		this.modoPagoService = new ModoPagoService();
		this.menuServices=new MenuServices();
		
		this.editarModoPago = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaModoPago = this.modoPagoService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:domiciliofiscal");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	

	
	public void guardarModoPago(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarModoPago) {
				this.modoPagoService.actualizarModoPago(this.modoPagoSelec);   
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Modo : " + this.modoPagoSelec.getDescripcion_modo_pago());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Modo de Pago ha sido actualizado", 3);
			}else{
				this.modoPagoService.crearModoPago(this.modoPagoSelec); 
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta la modalidad : " + this.modoPagoSelec.getDescripcion_modo_pago());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Modalidad ha sido creado", 3);
			}
			
			this.modoPagoSelec = new ModoPago();
			this.editarModoPago = Boolean.FALSE;
			
			this.listaModoPago = this.modoPagoService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoModoPago(){
		this.modoPagoSelec = new ModoPago();
		this.editarModoPago = Boolean.FALSE;
	}

	public void editarModoPago(ModoPago modoPago){
		this.modoPagoSelec = modoPago;
		this.editarModoPago = Boolean.TRUE;
	}
	
	public void eliminarModoPago(ModoPago modoPago){
		this.modoPagoSelec = modoPago;
	}
	
	
	public void confirmaEliminarModoPago(){
		try {
		
			this.modoPagoService.eliminarModoPago(this.modoPagoSelec.getId_modo_pago());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la modalidad: " + this.modoPagoSelec.getDescripcion_modo_pago());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Modalidad ha sido eliminada", 3);
			
			this.listaModoPago = this.modoPagoService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
	

	public ModoPago getModoPagoSelec() {
		return modoPagoSelec;
	}

	public void setModoPagoSelec(ModoPago modoPagoSelec) {
		this.modoPagoSelec = modoPagoSelec;
	}

	public List<ModoPago> getListaModoPago() {
		return listaModoPago;
	}

	public void setListaModoPago(List<ModoPago> listaModoPago) {
		this.listaModoPago = listaModoPago;
	}

	public Boolean getEditarModoPago() {
		return editarModoPago;
	}

	public void setEditarModoPago(Boolean editarModoPago) {
		this.editarModoPago = editarModoPago;
	}		
	
	
	
	

}
