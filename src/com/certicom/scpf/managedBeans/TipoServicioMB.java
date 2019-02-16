package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.services.TipoServicioService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="tipoServicioMB")
@ViewScoped
public class TipoServicioMB extends GenericBeans implements Serializable{

	private TipoServicio tipoServicioSelec;
	private Boolean editartipoServicio;
	private List<TipoServicio>listatipoServicios;
	private TipoServicioService tipoServicioService;
    private Log log;
	private LogMB logmb;
	
	@PostConstruct
	public void inicia(){
		this.tipoServicioSelec=new TipoServicio();
		this.editartipoServicio = Boolean.FALSE;
		this.tipoServicioService= new TipoServicioService();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listatipoServicios=this.tipoServicioService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevotipoServicio(){
		this.tipoServicioSelec = new TipoServicio();
		this.editartipoServicio = Boolean.FALSE;
	}
	
	public void editartipoServicio(TipoServicio tipoServicio){
		
		this.tipoServicioSelec = tipoServicio;
		this.editartipoServicio = Boolean.TRUE;
	}
	
	public void eliminartipoServicio(TipoServicio tipoServicio){
		this.tipoServicioSelec = tipoServicio;
	}
	
	public void guardartipoServicio(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editartipoServicio) {
				this.tipoServicioService.actualizartipoServicio(this.tipoServicioSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el tipo de Servicio : " + this.tipoServicioSelec.getDescripcion_tipo());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del tipo de Servicio ha sido actualizado", 3);
			}else{
				this.tipoServicioService.creartipoServicio(this.tipoServicioSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta vendedor: " + this.tipoServicioSelec.getDescripcion_tipo());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Tipo de Servicio ha sido creado", 3);
			}
			
			this.tipoServicioSelec = new TipoServicio();
			this.editartipoServicio = Boolean.FALSE;
			
			this.listatipoServicios = this.tipoServicioService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstadoGeneraTicket(TipoServicio tipoServicio){
		
		   if(tipoServicio.getGenera_ticket()){
			   //System.out.println("es igual a uno se pone a cero");
			   tipoServicio.setGenera_ticket(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   tipoServicio.setGenera_ticket(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.tipoServicioService.actualizartipoServicio(tipoServicio);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de Genera Ticket",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + tipoServicio.getGenera_ticket());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void confirmaEliminartipoServicio(){
		try {
		
			this.tipoServicioService.eliminartipoServicio(this.tipoServicioSelec.getId_tipo_servicio());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al Tipo Servicio: " + this.tipoServicioSelec.getDescripcion_tipo());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Tipo Servicio ha sido eliminado", 3);
			
			this.listatipoServicios = this.tipoServicioService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public TipoServicio getTipoServicioSelec() {
		return tipoServicioSelec;
	}

	public void setTipoServicioSelec(TipoServicio tipoServicioSelec) {
		this.tipoServicioSelec = tipoServicioSelec;
	}

	public Boolean getEditartipoServicio() {
		return editartipoServicio;
	}

	public void setEditartipoServicio(Boolean editartipoServicio) {
		this.editartipoServicio = editartipoServicio;
	}

	public List<TipoServicio> getListatipoServicios() {
		return listatipoServicios;
	}

	public void setListatipoServicios(List<TipoServicio> listatipoServicios) {
		this.listatipoServicios = listatipoServicios;
	}

	public TipoServicioService getTipoServicioService() {
		return tipoServicioService;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
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
