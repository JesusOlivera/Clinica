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

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="clienteMB")
@ViewScoped
public class ClienteMB extends GenericBeans implements Serializable{

	private Cliente clienteSelec;
	private List<Cliente> listaClientes;
	private Boolean editarCliente;
	private MenuServices menuServices;
	private ClienteService clienteService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ClienteMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.clienteSelec = new Cliente();
		this.clienteService = new ClienteService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		this.editarCliente = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaClientes = this.clienteService.findAll();
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:cliente");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}		
	
	/* para tabla maestra */
	
	public void guardarCliente(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarCliente) {
				this.clienteService.actualizarCliente(this.clienteSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el cliente : " + this.clienteSelec.getNombre_cab());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El cliente ha sido actualizado", 3);
			}else{
				this.clienteService.crearCliente(this.clienteSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el tabla maestra : " + this.clienteSelec.getNombre_cab());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Cliente ha sido creado", 3);
			}
			
			this.clienteSelec = new Cliente();
			this.editarCliente = Boolean.FALSE;
			
			this.listaClientes = this.clienteService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoCliente(){
		this.clienteSelec = new Cliente();
		this.editarCliente = Boolean.FALSE;
	}

	public void editarCliente(Cliente cliente){
		this.clienteSelec = cliente;
		this.editarCliente = Boolean.TRUE;
	}
	
	public void eliminarCliente(Cliente cliente){
		this.clienteSelec = cliente;
	}
	
	
	public void confirmaEliminarCliente(){
		try {
		
			this.clienteService.eliminarCliente(this.clienteSelec.getId_cliente());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina el cliente: " + this.clienteSelec.getNombre_cab());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Cliente ha sido eliminado", 3);
			
			this.listaClientes = this.clienteService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	public Cliente getClienteSelec() {
		return clienteSelec;
	}

	public void setClienteSelec(Cliente clienteSelec) {
		this.clienteSelec = clienteSelec;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Boolean getEditarCliente() {
		return editarCliente;
	}

	public void setEditarCliente(Boolean editarCliente) {
		this.editarCliente = editarCliente;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetalles() {
		return listTablaTablasDetalles;
	}

	public void setListTablaTablasDetalles(
			List<TablaTablasDetalle> listTablaTablasDetalles) {
		this.listTablaTablasDetalles = listTablaTablasDetalles;
	}

	
}
