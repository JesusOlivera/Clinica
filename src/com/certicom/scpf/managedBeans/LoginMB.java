package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.log4j.Level;
import org.primefaces.component.panelmenu.PanelMenu;
import org.primefaces.extensions.model.layout.LayoutOptions;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Perfil;
import com.certicom.scpf.domain.Sistema;
import com.certicom.scpf.domain.Usuario;
import com.certicom.scpf.domain.UsuarioPerfil;
import com.certicom.scpf.services.MenuServices;

import com.certicom.scpf.services.ParametroServices;
import com.certicom.scpf.services.PerfilServices;
import com.certicom.scpf.services.SistemaServices;
import com.certicom.scpf.services.UsuarioPerfilServices;
import com.certicom.scpf.services.UsuarioServices;
import com.ocpsoft.shade.org.apache.commons.beanutils.PropertyUtils;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.ExportarArchivo;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB  extends GenericBeans implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String login;
	private String password;
	private List<Perfil> listaPerfiles;
	private List<Sistema> listaModulos;
	private List<Menu> listaMenu;
	private MenuModel model;
	private MenuModel modelSalir;
	private String fechaAcceso;
	private String centroAtencion;
	private String negocio;
	private String nuevoPass;
	private String nuevoPassRe;
	private int tiempo_cambio_password;
	private int idUsuario;
	private String loginUsuario;
	private Perfil perfilUsuario;
	private StreamedContent archivoFoto;

	public StreamedContent getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(StreamedContent archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	UsuarioServices usuarioServices;
	ParametroServices parametroServices;
	PerfilServices perfilServices;
	SistemaServices	sistemaServices;
	MenuServices menuServices;
	UsuarioPerfilServices usuarioPerfilServices;
	
	
	private Log log;
	private LogMB logmb;

	public LoginMB(){
		
	}

	@PostConstruct
	public void inicia(){
		System.out.println("Hola login jsf");
		usuario = new Usuario();
		perfilUsuario = new Perfil();
		usuarioServices = new UsuarioServices();
		perfilServices = new PerfilServices();
		sistemaServices=new SistemaServices();
		menuServices=new MenuServices();
		parametroServices = new ParametroServices();
		usuarioPerfilServices = new UsuarioPerfilServices();
	
	}
	
	public Date sumaDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_YEAR, dias);
		return cal.getTime();
	}
	

	
	public String efectuarLogin(String ipClient) {
		String retorno = null;
		log = new Log();
		logmb = new LogMB();

		try {
				String valor = parametroServices.findParametro_byNombre("TIEMPO_CAMBIO_PASSWORD");
				tiempo_cambio_password = Integer.parseInt(valor);
				
				List<Usuario> list = usuarioServices.buscarPorLoginClave(new Usuario(login, password));
				Usuario user = usuarioServices.buscarPorLogin(login);
				//String localHost= String.valueOf(InetAddress.getLocalHost().getHostAddress());
				getMacAddres(ipClient);
				/**Para Log**/
				
				log.setCod_menu(0);
				log.setAccion("LOGIN");
				log.setIp_client(ipClient);
				setBean(log);
				Log logEnSesion = (Log)getSpringBean(Constante.SESSION_LOG);
				PropertyUtils.copyProperties(logEnSesion, log);
				System.out.println("3");
				listaPerfiles = new ArrayList<Perfil>();
				listaModulos=new ArrayList<Sistema>();	
				listaMenu=new ArrayList<Menu>();
				if (list.size() == 0) {
					FacesUtils.showFacesMessage("Usuario y/o clave incorrectos",Constante.ERROR);
					log.setDescripcion("Usuario "+ login + " no pudo logearse");
					logmb.insertarLog(log);
				} else {					
					log.setIdusuario(user.getIdusuario());
					if (list.get(0).getEstado()) {
						
						usuario = (list.get(0));

						setBean(usuario);
						idUsuario = usuario.getIdusuario();
						loginUsuario = usuario.getLogin();
						Usuario usuarioEnSesion = (Usuario)getSpringBean(Constante.SESSION_USER);
						PropertyUtils.copyProperties(usuarioEnSesion, usuario);
						/******************************************************************************/
						
						
						FacesUtils.setUsuarioLogueado(usuario);

						if(this.usuario.getFecha_acceso()!=null){
							String date =  sumaDias(this.usuario.getFecha_acceso(),tiempo_cambio_password).toString().substring(0, 10);
							String fechaActual = new Date().toString().substring(0, 10); 
							if(fechaActual.equals(date)){
								this.usuario.setEs_nuevo(Boolean.TRUE); 
							}							
							

						}
						//actualizar fecha de ultimo acceso
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						usuario.setFecha_acceso(new Date());
						usuarioServices.actualizarFechaAcceso(usuario);
						this.fechaAcceso = df.format(usuario.getFecha_acceso());
						
						
						// Listar los perfiles del usuario
						listaPerfiles = perfilServices.listarPerfilesxUsuario(usuario);
						
						List<Sistema> lista=new ArrayList<Sistema>();
						List<Menu> lista2=new ArrayList<Menu>();
						
						//Listar los modulos que tiene el usuario
						if(listaPerfiles.size()>0){											
							for(int k=0;k<listaPerfiles.size();k++){
								lista=sistemaServices.listSistemaxPerfil(getListaPerfiles().get(k));
								lista2=menuServices.listMenuxSistema(getListaPerfiles().get(k));
								
								//A�adir cada modulo
									//listaModulos
								if(lista.size()>0){
									if(listaModulos.size()>0){
										//Comparar que no se repitan los modulos
										for(int p=0;p<lista.size();p++){
											for(int o=0;o<listaModulos.size();o++){											
												if(getListaModulos().get(o).getCod_sistema()==lista.get(p).getCod_sistema()){
													break;
												}else{
													if(o==(listaModulos.size()-1))listaModulos.add(lista.get(p));else continue;												
												}
											}										
										}
									}else{
										for(int m=0;m<lista.size();m++){
											listaModulos.add(lista.get(m));										
										}
									}
								}
																
								if(lista2.size()>0){
									if(listaMenu.size()>0){
										//Comparar que no se repitan los modulos
										for(int p=0;p<lista2.size();p++){
											for(int o=0;o<listaMenu.size();o++){											
												if(getListaMenu().get(o).getCod_menu()==lista2.get(p).getCod_menu()){
													break;
												}else{
													if(o==(listaMenu.size()-1))listaMenu.add(lista2.get(p));else continue;												
												}
											}										
										}
									}else {
										for(int m=0;m<lista2.size();m++){
											listaMenu.add(lista2.get(m));										
										}
									}
								}									
							}
							
							
							model = new DefaultMenuModel();
							
							DefaultMenuItem item = new DefaultMenuItem();
							DefaultSubMenu submenu = new DefaultSubMenu();
							
							List<Menu> listaSubMenu=new ArrayList();
							listaSubMenu=listaMenu;
							MethodExpression methodExpression;
							for(int k=0;k<listaModulos.size();k++){
								
								 DefaultSubMenu menu = new DefaultSubMenu(listaModulos.get(k).getNombre_sistema());
								for(int m=0;m<listaMenu.size();m++){
									if(listaMenu.get(m).getCod_sistema()==listaModulos.get(k).getCod_sistema()){
										boolean genSubmenu=false,gen=false;
										submenu=new DefaultSubMenu("");
										for(int p=0;p<listaSubMenu.size();p++){										
												if(listaSubMenu.get(p).getCod_menu_padre()==listaMenu.get(m).getCod_menu()){
													Menu men=new Menu();												
													men=listaMenu.get(p);men.setEst(true);listaMenu.set(p, men);
													men=new Menu();
													men=listaMenu.get(m);men.setEst(true);listaMenu.set(m, men);
													genSubmenu=true;
													gen=true;

													DefaultMenuItem mi = new DefaultMenuItem(listaSubMenu.get(p).getNombre());
											        mi.setOutcome(listaSubMenu.get(p).getAction());
												    mi.setIcon(listaSubMenu.get(p).getIcono());
											        submenu.addElement(mi);
												}
											
												if(p==(listaSubMenu.size()-1)){
													if(gen){												
															if(genSubmenu){										
																submenu.setLabel(listaMenu.get(m).getNombre());															
																submenu.setIcon(listaMenu.get(m).getIcono());
																menu.addElement(submenu);
															}else{
																item = new DefaultMenuItem();
																item.setValue(listaMenu.get(m).getNombre());
																item.setOutcome(listaMenu.get(m).getAction());
																item.setIcon(listaMenu.get(m).getIcono());
																menu.addElement(item);										
															}
														genSubmenu=false;
														gen=false;
													}else if(listaMenu.get(m).isEst()==false){
														item = new DefaultMenuItem();
														item.setValue(listaMenu.get(m).getNombre());
														item.setOutcome(listaMenu.get(m).getAction());
														item.setIcon(listaMenu.get(m).getIcono());
														menu.addElement(item);		
													}
												}
										}								
									}						
								}
								model.addElement(menu);
							}
							
							DefaultSubMenu menu=new DefaultSubMenu();
							menu.setLabel("Opciones");
							item = new DefaultMenuItem();
							item.setValue("Cambiar Password");item.setIcon("icon-password");										
							item.setCommand("#{loginMB.iniciarCambioPassword_Voluntario}");
							menu.addElement(item);
						    
							//A�adir el log off
							
							item = new DefaultMenuItem();
							item.setValue("Salir");item.setIcon("icon-exit");										
							item.setCommand("#{loginMB.efectuarLogoff}");
						    menu.addElement(item);
						    model.addElement(menu);
    
						    //A�adir Home
						    /*
							item = new MenuItem();
							item.setValue("Principal");item.setIcon("icon-home");item.setUrl("/");	
							menu.getChildren().add(item);
						    model.addSubmenu(menu);					  
						 	*/
							log.setDescripcion("Usuario "+ login + " Logeado correctamente");
							System.out.println("---------Log:-------- \n Usuario:" + log.getIdusuario()+"\n Ip: "+log.getIp_client());
							
							logmb.insertarLog(log);
						    System.out.println("Login usuario:"+ this.usuario.getLogin());
						    setModel(model);
						    
						    //VERIFICANDO CUAL ES EL MAYOR PERFIL QUE TIENE EL USUARIO:(nO cambiar el orden de los for)
						    if(listaPerfiles.size()>1){
								for(Perfil p: listaPerfiles){
									if(p.getCod_perfil().equals(Constante.PERFIL_COD_EJECUTIVO)||p.getCod_perfil().equals(Constante.PERFIL_COD_BACKOFFICE)){
										perfilUsuario = p;
									}
								}
								
								for(Perfil p: listaPerfiles){
									if(p.getCod_perfil().equals(Constante.PERFIL_COD_SUPERVISOR)){
										perfilUsuario = p;
									}
								}
								
								for(Perfil p: listaPerfiles){
									if(p.getCod_perfil().equals(Constante.PERFIL_COD_COORDINADOR)){
										perfilUsuario = p;
									}
								}
								
								for(Perfil p: listaPerfiles){
									if(p.getCod_perfil().equals(Constante.PERFIL_COD_GERENTE_PROYECTO)){
										perfilUsuario = p;
									}
								}
							}else
								perfilUsuario = listaPerfiles.get(0);
						    
						    
							retorno = "pretty:principal";
		
						}else{
							FacesUtils.showFacesMessage("Usuario no tiene ningun perfil asociado",Constante.ERROR);
							return retorno;
							}						
					} else {
						System.out.println("usuario bloqueado");
						FacesUtils.showFacesMessage("Usuario bloqueado",Constante.ERROR);
					}
				}
			return retorno;			
		} catch (Exception e) {
			FacesUtils.showFacesMessage("Ocurrio un problema de conexion, intentelo mas tarde",Constante.ERROR);
			e.printStackTrace();
			return retorno;
		}

	}
		
	public void getMacAddres(String ip){
		try {
            InetAddress address = InetAddress.getByName("10.162.170.103");

            NetworkInterface ni2 = 
                    NetworkInterface.getByName("eth0");
            
            NetworkInterface ni =
                    NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        System.out.format("Mac: %02X%s",
                                mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                } else {
                    System.out.println("Address doesn't exist or is not " +
                            "accessible.");
                }
            } else {
                System.out.println("Network Interface for the specified " +
                        "address is not found.");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
	}


	public MethodExpression generarActionMenu(String action){
		ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		return factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(),action, String.class, new Class[]{});
	}
	

	public String efectuarLogoff() {
		
		FacesUtils.removeUsuarioLogueado();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		session.invalidate();
		return "pretty:login";
	}
		 
	public String iniciaCambiarPassword(){
		String outcome=null;
		this.usuario.setEs_nuevo(Boolean.TRUE);
		this.usuarioServices.actualizar(this.usuario);
		outcome = "pretty:principal";
		return outcome;
	}	
	
	
	public String iniciarCambioPassword_Voluntario(){
		return "pretty:cambioPass";
	}
	
	
	public void cambiarPassword_Voluntario(){
		//String outcome = null;
		//comparando y cambiando
		if(this.nuevoPass.equals(this.nuevoPassRe)){ //ok
			if(nuevoPass.length()>=6){
				this.usuario.setPassword(this.nuevoPass);
				this.usuario.setFecha_acceso(new Date());
				this.usuario.setEs_nuevo(Boolean.FALSE);
				this.usuario.setFecha_cambio_password(new Date()); 
				this.usuarioServices.actualizarPassword(this.usuario);
				this.usuarioServices.actualizarFechaAcceso(this.usuario); 
				//Insertar en la tabla de Log
				//log.setAccion("Cambio Password");
				//log.setDescripcion("Usuario: "+login+" cambió su password correctamente");
				//logmb.insertarLog(log);
				
				nuevoPass ="";
				nuevoPassRe ="";
				//matar sesion:			
				//outcome="pretty:cambioPass";		
				//this.efectuarLogoff();
				
				FacesUtils.showFacesMessage("El password se ha cambiado correctamente", Constante.INFORMACION);
			}else{
				FacesUtils.showFacesMessage("Por seguridad, la contraseña debe tener al menos 6 caracteres", Constante.ADVERTENCIA);				
			}
			
		}else{
			//mostrar alerta
			FacesUtils.showFacesMessage("Contraseñas no coinciden", Constante.ADVERTENCIA);
		}		
		//return outcome;
	}
	

	/**
	 * Desc: cambia password
	 */
	public String cambiarPassword(){
		String outcome = null;
		//comparando y cambiando
		if(this.nuevoPass.equals(this.nuevoPassRe)){ //ok
			this.usuario.setPassword(this.nuevoPass);
			this.usuario.setFecha_acceso(new Date());
			this.usuario.setEs_nuevo(Boolean.FALSE);
			this.usuario.setFecha_cambio_password(new Date());
			
			this.usuarioServices.actualizarPassword(this.usuario);
			this.usuarioServices.actualizarFechaAcceso(this.usuario); 
		
			//matar sesion:
			
			outcome="pretty:login";		
			this.efectuarLogoff();		
		}else{
			//mostrar alerta
			FacesUtils.showFacesMessage("Contrase�as no coinciden", Constante.ADVERTENCIA);
		}		
		return outcome;
	}
	
	/*###########-------setters y getters-------##################*/
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	
	public Perfil getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(Perfil perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	
	public List<Sistema> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<Sistema> listaModulos) {
		this.listaModulos = listaModulos;
	}	
	
	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}	

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	public String getFechaAcceso() {
		return fechaAcceso;
		
	}

	public void setFechaAcceso(String fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public MenuModel getModelSalir() {
		return modelSalir;
	}

	public void setModelSalir(MenuModel modelSalir) {
		this.modelSalir = modelSalir;
	}
	
	public String getNuevoPass() {
		return nuevoPass;
	}

	public String getNuevoPassRe() {
		return nuevoPassRe;
	}

	public void setNuevoPass(String nuevoPass) {
		this.nuevoPass = nuevoPass;
	}

	public void setNuevoPassRe(String nuevoPassRe) {
		this.nuevoPassRe = nuevoPassRe;
	}

	public int getTiempo_cambio_password() {
		return tiempo_cambio_password;
	}

	public void setTiempo_cambio_password(int tiempo_cambio_password) {
		this.tiempo_cambio_password = tiempo_cambio_password;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCentroAtencion() {
		return centroAtencion;
	}

	public void setCentroAtencion(String centroAtencion) {
		this.centroAtencion = centroAtencion;
	}

	public String getNegocio() {
		return negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	

}