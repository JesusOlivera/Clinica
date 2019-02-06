package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Especialidad;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.EspecialidadService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="medicoMB")
@ViewScoped
public class MedicoMB extends GenericBeans implements Serializable{

	private Medico medicoSelec;
	private Boolean editarmedico;
	private List<Medico>listamedicos;
	private MedicoService medicoService;
    private Log log;
	private LogMB logmb;
	
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	private MenuServices menuServices;
	
	private EspecialidadService especialidadService;
	private List<Especialidad> listaEspecialidades;
	
	@PostConstruct
	public void inicia(){
		this.medicoSelec=new Medico();
		this.editarmedico = Boolean.FALSE;
		this.medicoService= new MedicoService();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		this.tablaTablasDetalleService=new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		this.especialidadService=new EspecialidadService();
		
		try {
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:medico");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
			this.listaEspecialidades=this.especialidadService.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.listamedicos=this.medicoService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevomedico(){
		this.medicoSelec = new Medico();
		this.editarmedico = Boolean.FALSE;
	}
	
	public void editarmedico(Medico medico){
		
		this.medicoSelec = medico;
		this.editarmedico = Boolean.TRUE;
	}
	
	public void eliminarmedico(Medico medico){
		this.medicoSelec = medico;
	}
	
	public void guardarmedico(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarmedico) {
				this.medicoService.actualizarmedico(this.medicoSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Vendedor : " + this.medicoSelec.getNombre_medico());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del medico ha sido actualizado", 3);
			}else{
				this.medicoService.crearmedico(this.medicoSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta medico: " + this.medicoSelec.getNombre_medico());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("medico ha sido creado", 3);
			}
			
			this.medicoSelec = new Medico();
			this.editarmedico = Boolean.FALSE;
			
			this.listamedicos = this.medicoService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarmedico(){
		try {
		
			this.medicoService.eliminarMedico(this.medicoSelec.getId_medico());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al medico: " + this.medicoSelec.getNombre_medico());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("medico ha sido eliminado", 3);
			
			this.listamedicos = this.medicoService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Medico getMedicoSelec() {
		return medicoSelec;
	}

	public void setMedicoSelec(Medico medicoSelec) {
		this.medicoSelec = medicoSelec;
	}

	public Boolean getEditarmedico() {
		return editarmedico;
	}

	public void setEditarmedico(Boolean editarmedico) {
		this.editarmedico = editarmedico;
	}

	public List<Medico> getListamedicos() {
		return listamedicos;
	}

	public void setListamedicos(List<Medico> listamedicos) {
		this.listamedicos = listamedicos;
	}

	public MedicoService getMedicoService() {
		return medicoService;
	}

	public void setMedicoService(MedicoService medicoService) {
		this.medicoService = medicoService;
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

	public EspecialidadService getEspecialidadService() {
		return especialidadService;
	}

	public void setEspecialidadService(EspecialidadService especialidadService) {
		this.especialidadService = especialidadService;
	}

	public List<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}
	
	
	
}
