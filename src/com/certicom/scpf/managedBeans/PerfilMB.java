package com.certicom.scpf.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Sistema;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.PerfilServices;
import com.certicom.scpf.services.SistemaServices;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean
//@SessionScoped
@ViewScoped
public class PerfilMB extends GenericBeans {

	private Perfil perfil;
	private List<Perfil> listaPerfil;
	private RequestContext context;
	private PerfilServices perfilServices;
	private Boolean editar;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	
	
	
	

	public PerfilMB() throws Exception {
		;
	}
	
	@PostConstruct
	public void inicia(){
		//System.out.println("iniciando perfilMB");
		perfilServices = new PerfilServices();
		this.editar = Boolean.FALSE;
		menuServices = new MenuServices();
		this.context = RequestContext.getCurrentInstance();  
		perfil = new Perfil();
		try {
			listaPerfil = perfilServices.listPerfil();
			
		} catch (Exception e) {
			//System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:perfiles");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
	}
	
	/**
	 * Desc: metodo que envia un objeto Perfil a otro managebBean
	 * @param Objeto Perfil
	 * @return: cadena para el gestor de navegacion
	 */
	public String asignarPermisos(Perfil perf){
		String outcome = null;
		try {
			//System.out.println("perfil pasado:"+perf.getNombre());
    		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    		flash.put("perfil", perf);
    		//return "/faces/control_acceso/asignarPermisos?faces-redirect=true";
    		outcome="pretty:asignaPermisosPerfil";
    		log.setAccion("UPDATE");
	        log.setDescripcion("Se asignó nuevo permiso ");
	        logmb.insertarLog(log);
    		
		} catch (Exception e) {	
			//System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	} 
	
	
	/**
	 * @Desc:Cambia de estado
	 * @Auth:Will
	 * @param: Objeto de la clase Perfil
	 */
	public void cambiarEstado(Perfil perf){
		
		   if(perf.isInd_activo()){
			   //System.out.println("es igual a uno se pone a cero");
			   perf.setInd_activo(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   perf.setInd_activo(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.perfilServices.updatePerfil(perf);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de feril modificado correctamente",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + perf.isInd_activo());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}

	
	
	public void newInsert() {
		this.editar = Boolean.FALSE;
		this.perfil =new Perfil();
	}

	public void editarPerfil(Perfil perf) throws Exception {
		System.out.println("perfil seleccionado :"+perf.getNombre());
		this.editar= Boolean.TRUE;
		this.perfil = perf;
	}
	
	
	/**
	 * Desc:Metodo que edita un perfil
	 * @Auth: Will
	 * @throws Exception
	 */
	public void guardarPerfil(){
		if(this.editar){
			try {
				this.perfilServices.updatePerfil(perfil);
				FacesUtils.showFacesMessage("Perfil actualizado correctamente",Constante.INFORMACION);
				log.setAccion("UPDATE");
		        log.setDescripcion("Se actualizó el perfil de: "+this.perfil.getDescripcion());
		        logmb.insertarLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				perfilServices.insertPerfil(perfil);
				FacesUtils.showFacesMessage("Perfil creado correctamente",Constante.INFORMACION);
				log.setAccion("INSERT");
		        log.setDescripcion("Se a registrado  : " + this.perfil.getDescripcion());
		        logmb.insertarLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//relistando
		try {
			this.listaPerfil = this.perfilServices.listPerfil();
			context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*
	public void updatePerfil() throws Exception {
			perfil = this.getPerfil();
			perfilServices.updatePerfil(perfil);
			listaPerfil = perfilServices.listPerfil();
			FacesUtils.showFacesMessage("Perfil Guardado correctamente",Constante.INFORMACION);
	}
*/
	

	public void newDelete(Perfil perf) throws Exception {
		System.out.print("Codigo de Perfil: " + perf.getCod_perfil());
		this.perfil= perf;
	}
	
	public void deletePerfil() {
		try{
		perfilServices.deletePerfil(perfil.getCod_perfil());
		listaPerfil = perfilServices.listPerfil();
		FacesUtils.showFacesMessage("Perfil eliminado",Constante.INFORMACION);
		log.setAccion("DELETE");
        log.setDescripcion("Se ha eliminado  : " + this.perfil.getDescripcion());
        logmb.insertarLog(log);
		}catch(Exception e){
			System.out.println("Error eliminando perfil:"+ e.getMessage());
			FacesUtils.showFacesMessage("No se puede eliminar tiene permisos asignados, quite los permisos y vuelva a intentarlo",Constante.ERROR);
		}
	}



	/*###############-----gettres y setters--------######################*/	

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public PerfilServices getPerfilServices() {
		return perfilServices;
	}

	public void setPerfilServices(PerfilServices perfilServices) {
		this.perfilServices = perfilServices;
	}
	
}