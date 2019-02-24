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

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.TablaTablas;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TablaTablasService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="tablaMaestraMB")
@ViewScoped
public class TablaMaestraMB extends GenericBeans implements Serializable{

	private TablaTablas tablaTablasSelec;
	private TablaTablasDetalle tablaTablasDetalleSelec;
	private List<TablaTablas> listaTablaTablas;
	private List<TablaTablas> listaTablaTablasFilter;
	public List<TablaTablas> getListaTablaTablasFilter() {
		return listaTablaTablasFilter;
	}

	public void setListaTablaTablasFilter(List<TablaTablas> listaTablaTablasFilter) {
		this.listaTablaTablasFilter = listaTablaTablasFilter;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public TablaTablasService getTablaTablasService() {
		return tablaTablasService;
	}

	public void setTablaTablasService(TablaTablasService tablaTablasService) {
		this.tablaTablasService = tablaTablasService;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
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

	private List<TablaTablasDetalle> listaTablaTablasDetalle;
	private Boolean editarTablaTablas;
	private Boolean editarTablaTablasDetalle;
	private MenuServices menuServices;
	private TablaTablasService tablaTablasService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public TablaMaestraMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.tablaTablasSelec = new TablaTablas();
		this.tablaTablasDetalleSelec = new TablaTablasDetalle();
		this.tablaTablasService = new TablaTablasService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		this.editarTablaTablas = Boolean.FALSE;
		this.editarTablaTablasDetalle = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaTablaTablas = this.tablaTablasService.findAll();
			this.listaTablaTablas = poblarTablas(listaTablaTablas);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:tablamaestra");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public List<TablaTablas> poblarTablas(List<TablaTablas> listaTablaTablas) throws Exception{
		
		for (TablaTablas tablasTablas : listaTablaTablas) {
			
			tablasTablas.setListaTablaTablasDetalle(this.tablaTablasDetalleService.findByIdMaestraTotal(tablasTablas.getId_maestra()));
			
			/*for (CiudadSede ciudadSede : paisSede.getListaCiudadesSede()) {
				
				ciudadSede.setListaAgencias(this.agenciaServices.findByIdPaisSedeByIdCiudadSede(paisSede.getId_pais_sede(), ciudadSede.getId_ciudad_sede()));
				
			}*/
			
		}
		
		return listaTablaTablas;
		
	}
	
	
	/* para tabla maestra */
	
	public void guardarTablaTablas(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarTablaTablas) {
				this.tablaTablasService.actualizarTablaTablas(this.tablaTablasSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el tabla maestra : " + this.tablaTablasSelec.getDescripcion_maestra());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La tabla maestra ha sido actualizado", 3);
			}else{
				this.tablaTablasService.crearTablaTablas(this.tablaTablasSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el tabla maestra : " + this.tablaTablasSelec.getDescripcion_maestra());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Tabla maestra ha sido creada", 3);
			}
			
			this.tablaTablasSelec = new TablaTablas();
			this.editarTablaTablas = Boolean.FALSE;
			
			this.listaTablaTablas = this.tablaTablasService.findAll();
			this.listaTablaTablas = poblarTablas(listaTablaTablas);
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstado(TablaTablasDetalle detalle){
		
		   if(detalle.isInd_activo()){
			   //System.out.println("es igual a uno se pone a cero");
			   detalle.setInd_activo(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   detalle.setInd_activo(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.tablaTablasDetalleService.actualizarTablaTablasDetalle(detalle);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de tabla detalle correctamente",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + detalle.isInd_activo());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void nuevaTablaTablas(){
		this.tablaTablasSelec = new TablaTablas();
		this.editarTablaTablas = Boolean.FALSE;
	}

	public void editarTablaTablas(TablaTablas tablatablas){
		this.tablaTablasSelec = tablatablas;
		this.editarTablaTablas = Boolean.TRUE;
	}
	
	public void eliminarTablaTablas(TablaTablas tablatablas){
		this.tablaTablasSelec = tablatablas;
	}
	
	
	public void confirmaEliminarTablaTablas(){
		try {
		
			this.tablaTablasService.eliminarTablaTablas(this.tablaTablasSelec.getId_maestra());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la tabla maestra: " + this.tablaTablasSelec.getDescripcion_maestra());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Tabla maestra ha sido eliminado", 3);
			
			this.listaTablaTablas = this.tablaTablasService.findAll();
			this.listaTablaTablas = poblarTablas(listaTablaTablas);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
/* para tabla maestra detalle */
	
	public void guardarTablaTablasDetalle(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarTablaTablasDetalle) {
				System.out.println("ID MAESTRA: "+tablaTablasDetalleSelec.getAtributo_1());
				this.tablaTablasDetalleService.actualizarTablaTablasDetalle(this.tablaTablasDetalleSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el tabla maestra : " + this.tablaTablasSelec.getDescripcion_maestra());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La tabla maestra ha sido actualizado", 3);
			}else{
				System.out.println("this.tablaTablasSelec.getId_maestra() :"+this.tablaTablasSelec.getId_maestra());
				this.tablaTablasDetalleSelec.setId_maestra(this.tablaTablasSelec.getId_maestra());
				this.tablaTablasDetalleService.crearTablaTablasDetalle(this.tablaTablasDetalleSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el tabla maestra : " + this.tablaTablasSelec.getDescripcion_maestra());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Tabla maestra ha sido creada", 3);
			}
			
			this.tablaTablasSelec = new TablaTablas();
			this.editarTablaTablas = Boolean.FALSE;
			
			this.listaTablaTablas = this.tablaTablasService.findAll();
			this.listaTablaTablas = poblarTablas(listaTablaTablas);
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoTablaTablasDetalle(TablaTablas tablaTablas){
		this.tablaTablasDetalleSelec = new TablaTablasDetalle();
		System.out.println("ID MAESTRA: "+tablaTablas.getId_maestra());
		this.tablaTablasSelec = tablaTablas;
		this.editarTablaTablasDetalle = Boolean.FALSE;
	}

	public void editarTablaTablasDetalle(TablaTablasDetalle tablatablasdetalle){
		System.out.println("SE VA A ACUTALIZAR");
		System.out.println("ID MAESTRA: "+tablatablasdetalle.getId_maestra());
		System.out.println("ID CODIGO: "+tablatablasdetalle.getId_codigo());
		this.tablaTablasDetalleSelec = tablatablasdetalle;
		this.editarTablaTablasDetalle = Boolean.TRUE;
	}
	
	public void eliminarTablaTablasDetalle(TablaTablasDetalle tablatablasdetalle){
		this.tablaTablasDetalleSelec = tablatablasdetalle;
	}
	
	
	public void confirmaEliminarTablaTablasDetalle(){
		try {
			System.out.println("this.tablaTablasDetalleSelec.getId_codigo()"+this.tablaTablasDetalleSelec.getId_codigo());
			this.tablaTablasDetalleService.eliminarTablaTablasDetalle(this.tablaTablasDetalleSelec.getId_codigo());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la tabla maestra detalle: " + this.tablaTablasDetalleSelec.getDescripcion_corto());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Tabla maestra detalle ha sido eliminada", 3);
			
			this.listaTablaTablasDetalle = this.tablaTablasDetalleService.findAll();
			this.listaTablaTablas = poblarTablas(listaTablaTablas);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

		
	
	/* para ciudad sede */
	
	
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
	public TablaTablas getTablaTablasSelec() {
		return tablaTablasSelec;
	}

	public void setTablaTablasSelec(TablaTablas tablaTablasSelec) {
		this.tablaTablasSelec = tablaTablasSelec;
	}

	public List<TablaTablas> getListaTablaTablas() {
		return listaTablaTablas;
	}

	public void setListaTablaTablas(List<TablaTablas> listaTablaTablas) {
		this.listaTablaTablas = listaTablaTablas;
	}

	public Boolean getEditarTablaTablas() {
		return editarTablaTablas;
	}

	public void setEditarTablaTablas(Boolean editarTablaTablas) {
		this.editarTablaTablas = editarTablaTablas;
	}

	public List<TablaTablasDetalle> getListaTablaTablasDetalle() {
		return listaTablaTablasDetalle;
	}

	public void setListaTablaTablasDetalle(
			List<TablaTablasDetalle> listaTablaTablasDetalle) {
		this.listaTablaTablasDetalle = listaTablaTablasDetalle;
	}

	public Boolean getEditarTablaTablasDetalle() {
		return editarTablaTablasDetalle;
	}

	public void setEditarTablaTablasDetalle(Boolean editarTablaTablasDetalle) {
		this.editarTablaTablasDetalle = editarTablaTablasDetalle;
	}

	public TablaTablasDetalle getTablaTablasDetalleSelec() {
		return tablaTablasDetalleSelec;
	}

	public void setTablaTablasDetalleSelec(
			TablaTablasDetalle tablaTablasDetalleSelec) {
		this.tablaTablasDetalleSelec = tablaTablasDetalleSelec;
	}	
	
}
