package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.VendedorService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="controlMB")
@ViewScoped
public class ControlMB extends GenericBeans implements Serializable{

	private Vendedor vendedorSelec;
	private List<Vendedor> listaVendedor;
	private Boolean editarVendedor;
	private MenuServices menuServices;
	private VendedorService vendedorService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ControlMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.vendedorSelec = new Vendedor();
		this.vendedorService = new VendedorService();
		this.menuServices=new MenuServices();
		
		this.editarVendedor = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaVendedor = this.vendedorService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:vendedor");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarVendedor(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarVendedor) {
				this.vendedorService.actualizarVendedor(this.vendedorSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Vendedor : " + this.vendedorSelec.getDescripcion_vendedor());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del vendedor ha sido actualizado", 3);
			}else{
				this.vendedorService.crearVendedor(this.vendedorSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta vendedor: " + this.vendedorSelec.getDescripcion_vendedor());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Vendedor ha sido creado", 3);
			}
			
			this.vendedorSelec = new Vendedor();
			this.editarVendedor = Boolean.FALSE;
			
			this.listaVendedor = this.vendedorService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoVendedor(){
		this.vendedorSelec = new Vendedor();
		this.editarVendedor = Boolean.FALSE;
	}

	public void editarVendedor(Vendedor vendedor){
		this.vendedorSelec = vendedor;
		this.editarVendedor = Boolean.TRUE;
	}
	
	public void eliminarVendedor(Vendedor vendedor){
		this.vendedorSelec = vendedor;
	}
	
	
	public void confirmaEliminarVendedor(){
		try {
		
			this.vendedorService.eliminarVendedor(this.vendedorSelec.getId_vendedor());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina vendedor: " + this.vendedorSelec.getDescripcion_vendedor());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Vendedor ha sido eliminado", 3);
			
			this.listaVendedor = this.vendedorService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Vendedor getVendedorSelec() {
		return vendedorSelec;
	}

	public void setVendedorSelec(Vendedor vendedorSelec) {
		this.vendedorSelec = vendedorSelec;
	}

	public List<Vendedor> getListaVendedor() {
		return listaVendedor;
	}

	public void setListaVendedor(List<Vendedor> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}

	public Boolean getEditarVendedor() {
		return editarVendedor;
	}

	public void setEditarVendedor(Boolean editarVendedor) {
		this.editarVendedor = editarVendedor;
	}
}
