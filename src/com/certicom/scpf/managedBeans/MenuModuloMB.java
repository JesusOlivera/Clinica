package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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

@ManagedBean(name="menuModuloMB")
@ViewScoped
public class MenuModuloMB extends GenericBeans implements Serializable{

	private TreeNode raiz;
	private List<Menu> listaMenu;
	private MenuServices menuServices;
	private SistemaServices sistemasServices;
	private Sistema sistema;
	private Integer sistemaId;
	private TreeNode nodoSelec;
	private Menu menu;
	private Menu menuSelect;
	private Boolean padre;
	private String nombreMenuSelect;
	private Log log;
	private LogMB logmb;
	
	private Boolean editar;
	
	
	//private PerfilServices perfilServices;
	private Perfil perfil;
	
	
	public MenuModuloMB(){
		
	}
	
	@PostConstruct
	public void inicia(){
		System.out.println("iniciando añadir menu");
		this.padre = Boolean.FALSE;
		this.editar = Boolean.FALSE;
		this.menu = new Menu();
		this.menuSelect  = new Menu();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.sistemaId =(Integer) flash.get("sistemaId");
		this.menuServices = new MenuServices();
		this.sistemasServices = new SistemaServices();
		//System.out.println("menu id  aqui flash:"+id);
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:sistemas");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			this.listaMenu = this.menuServices.listMenuxSistemaId(new Long(this.sistemaId));
			this.sistema= this.sistemasServices.findSistemaPorCodigo(new Long(this.sistemaId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.generarTree(this.sistema.getNombre_sistema(),this.listaMenu);

	}
	
	
	public void generarTree(String nombreRaiz,List<Menu> listaM){
		this.raiz = new DefaultTreeNode("Root", null); 
		TreeNode node0 = new DefaultTreeNode(nombreRaiz, this.raiz);  
        for(Menu mn: listaM){
			//System.out.println("menu :"+mn.getDescripcion());
			new DefaultTreeNode(mn.getNombre(), node0);  
		}
	}

	
	public void agregarMenu(){
		System.out.println("agregando menu a modulo");
		System.out.println("el padre es: "+this.padre);

		if(this.padre){
			
			if(this.editar){
				//se van a la mismisima
			}else{//es nuevo menu
				this.menu.setCod_sistema(this.sistema.getCod_sistema());
				try {
					this.menuServices.insertMenu(this.menu);
					log.setAccion("INSERT");
			        log.setDescripcion ("Se ha creado el menu "+this.menu.getDescripcion());
			        logmb.insertarLog(log);
					FacesUtils.showFacesMessage("Menu agregado correctamente",Constante.INFORMACION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			
			if(this.editar){
				try {
					this.menuServices.updateMenu(this.menu);
					log.setAccion("UPDATE");
			        log.setDescripcion ("Se ha actualizado el menu "+this.menu.getDescripcion());
			        logmb.insertarLog(log);
					FacesUtils.showFacesMessage("Menu guardado correctamente",Constante.INFORMACION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		/*
		if(this.editar){
			if(!this.padre){//si no es padre se edita
				
				try {
					this.menuServices.updateMenu(this.menu);
					FacesUtils.showFacesMessage("Menu guardado correctamente",Constante.INFORMACION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}else{//nuevo
			if(this.padre){//si es padre se agrega menu
				this.menu.setCod_sistema(this.sistema.getCod_sistema());
				try {
					this.menuServices.insertMenu(this.menu);
					FacesUtils.showFacesMessage("Menu agregado correctamente",Constante.INFORMACION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{//si es hijo se va a la miel
				FacesUtils.showFacesMessage("no puedes agregar menus a un hijo",Constante.ERROR);
				 //context.update("sms");
			}
		}
		*/
		//regenerando el trees en cualquier caso
		try {
			this.listaMenu = this.menuServices.listMenuxSistemaId(new Long(this.sistemaId));
			this.generarTree(this.sistema.getNombre_sistema(),this.listaMenu);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.editar = Boolean.FALSE;
	}

	
	
	
	
	
	
	public void onNodeSelect(NodeSelectEvent event) {
		this.nombreMenuSelect = event.getTreeNode().getData().toString();
		//seteando menu
		try {
			this.menuSelect = this.menuServices.findMenuxNombre(this.nombreMenuSelect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.nodoSelec != null) {   
            System.out.println("nodo seleccionado :"+ this.nodoSelec.getData().toString());
        }  
		//System.out.println("el papa :"+this.nodoSelec.getParent().getData());
		
		if(this.nodoSelec.getParent().getData().toString().equals("Root")){//es el modulo
			//disponible solo agregar menu 
			//activando banderin pacre
			this.padre = Boolean.TRUE;
			
		}else{
			this.padre = Boolean.FALSE;
		}
		
	}
	
	
	public void eliminarMenu(){
		if(!this.padre){
			try {
				this.menuServices.deleteMenu(this.menuSelect);
				log.setAccion("DELETE");
		        log.setDescripcion ("Se ha eliminado el menu "+this.menuSelect.getDescripcion());
		        logmb.insertarLog(log);
				this.listaMenu = this.menuServices.listMenuxSistemaId(new Long(this.sistemaId));
				this.generarTree(this.sistema.getNombre_sistema(),this.listaMenu);
				FacesUtils.showFacesMessage("Se elimino correctamente",Constante.INFORMACION);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public void editarMenu(){
	
		System.out.println("editando el menu");
			this.editar = Boolean.TRUE;
			this.menu = this.menuSelect;
			System.out.println("el  menu select es "+ this.menuSelect.getDescripcion());
	}
	
	
	
	/*###############----gettres and setters-------######################*/

	public TreeNode getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public TreeNode getNodoSelec() {
		return nodoSelec;
	}

	public void setNodoSelec(TreeNode nodoSelec) {
		this.nodoSelec = nodoSelec;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Boolean getPadre() {
		return padre;
	}

	public void setPadre(Boolean padre) {
		this.padre = padre;
	}

	public String getNombreMenuSelect() {
		return nombreMenuSelect;
	}

	public void setNombreMenuSelect(String nombreMenuSelect) {
		this.nombreMenuSelect = nombreMenuSelect;
	}

	public Menu getMenuSelect() {
		return menuSelect;
	}

	public void setMenuSelect(Menu menuSelect) {
		this.menuSelect = menuSelect;
	}

	
}
