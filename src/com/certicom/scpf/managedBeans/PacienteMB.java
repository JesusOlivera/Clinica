package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.PacienteService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="pacienteMB")
@ViewScoped
public class PacienteMB extends GenericBeans implements Serializable {
	
	private Paciente pacienteSelec;
	private Boolean editarPaciente;
	private List<Paciente>listaPacientes;
	private List<Paciente> listaPacientesFilter;
	private PacienteService pacienteService;
    private Log log;
	private LogMB logmb;
	
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	private MenuServices menuServices;
	
	private List<Cliente> listaClientes;
	private ClienteService clienteService;
	
	@PostConstruct
	public void inicia(){
		this.pacienteSelec=new Paciente();
		this.editarPaciente = Boolean.FALSE;
		this.pacienteService= new PacienteService();
		this.clienteService= new ClienteService();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		this.tablaTablasDetalleService=new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		try {
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:paciente");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.listaPacientes=this.pacienteService.findAll();
			this.listaClientes = this.clienteService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevoPaciente(){
		this.pacienteSelec = new Paciente();
		this.editarPaciente = Boolean.FALSE;
	}
	
	public void editarPaciente(Paciente paciente){
		
		this.pacienteSelec = paciente;
		this.editarPaciente = Boolean.TRUE;
	}
	
	public void eliminarPaciente(Paciente paciente){
		this.pacienteSelec = paciente;
	}
	
	public void guardarPaciente(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarPaciente) {
				this.pacienteService.actualizarPaciente(this.pacienteSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Vendedor : " + this.pacienteSelec.getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del paciente ha sido actualizado", 3);
			}else{
				this.pacienteService.crearPaciente(this.pacienteSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta Paciente: " + this.pacienteSelec.getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Paciente ha sido creado", 3);
			}
			
			this.pacienteSelec = new Paciente();
			this.editarPaciente = Boolean.FALSE;
			
			this.listaPacientes = this.pacienteService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarPaciente(){
		try {
		
			this.pacienteService.eliminarPaciente(this.pacienteSelec.getId_paciente());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al paciente: " + this.pacienteSelec.getNombre());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Paciente ha sido eliminado", 3);
			
			this.listaPacientes = this.pacienteService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Paciente getPacienteSelec() {
		return pacienteSelec;
	}

	public void setPacienteSelec(Paciente pacienteSelec) {
		this.pacienteSelec = pacienteSelec;
	}

	public Boolean getEditarPaciente() {
		return editarPaciente;
	}

	public void setEditarPaciente(Boolean editarPaciente) {
		this.editarPaciente = editarPaciente;
	}

	public List<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
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

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetalles() {
		return listTablaTablasDetalles;
	}

	public void setListTablaTablasDetalles(List<TablaTablasDetalle> listTablaTablasDetalles) {
		this.listTablaTablasDetalles = listTablaTablasDetalles;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Paciente> getListaPacientesFilter() {
		return listaPacientesFilter;
	}

	public void setListaPacientesFilter(List<Paciente> listaPacientesFilter) {
		this.listaPacientesFilter = listaPacientesFilter;
	}
	
	

}
