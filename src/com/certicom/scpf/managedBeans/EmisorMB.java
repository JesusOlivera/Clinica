package com.certicom.scpf.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

import src.com.certicom.scpf.utils.Utils;

@ManagedBean(name="emisorMB")
@ViewScoped
public class EmisorMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2677047494679885154L;
	private Emisor emisorSelec;
	private List<Emisor> listaEmisores;
	private List<DomicilioFiscal> listaDomicilioFiscales;
	private Boolean editarEmisor;
	private MenuServices menuServices;
	private EmisorService emisorService;
	private DomicilioFiscalService domicilioFiscalService;
	private String destination = "D://tmp/";
	private StreamedContent dbImage;
	private File archivo;
	private byte[] archibytes;
	private Boolean nuevoEmisor;
	private Boolean editarEliminarEmisor;
	
	private String imagenLogo; 
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public EmisorMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.emisorSelec = new Emisor();
		this.emisorService = new EmisorService();
		this.menuServices=new MenuServices();
		this.domicilioFiscalService = new DomicilioFiscalService();
		
		this.editarEmisor = Boolean.FALSE;
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaEmisores = this.emisorService.findAll();
			if(!this.listaEmisores.isEmpty()){
				if(this.listaEmisores.size()>0){
					this.emisorSelec=this.listaEmisores.get(0);
					this.nuevoEmisor=Boolean.TRUE;
					this.editarEliminarEmisor=Boolean.FALSE; 
					/* Jesus
					if(this.emisorSelec.getRuta_logo()!=null){
						File f = new File (this.emisorSelec.getRuta_logo());
						archibytes = Files.readAllBytes(f.toPath());
					} */
				}else{
					this.emisorSelec=new Emisor();
					this.nuevoEmisor=Boolean.FALSE;
					this.editarEliminarEmisor=Boolean.TRUE;
				}
				
			}else{
				this.emisorSelec=new Emisor();
				this.nuevoEmisor=Boolean.FALSE;
				this.editarEliminarEmisor=Boolean.TRUE;
			}
			
			this.listaDomicilioFiscales = this.domicilioFiscalService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:emisor");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}		
	
	/* para tabla maestra */
	
	public void guardarEmisor(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarEmisor) {
				this.emisorService.actualizarEmisor(this.emisorSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el emisor : " + this.emisorSelec.getNombre_comercial());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El emisor ha sido actualizado", 3);
			}else{
				this.emisorService.crearEmisor(this.emisorSelec);
				this.nuevoEmisor=Boolean.TRUE;
				this.editarEliminarEmisor=Boolean.FALSE;
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el emisor : " + this.emisorSelec.getNombre_comercial());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Emisor ha sido creado", 3);
			}
			
//			this.emisorSelec = new Emisor();
			this.editarEmisor = Boolean.FALSE;
			
			this.listaEmisores = this.emisorService.findAll();
			if(!this.listaEmisores.isEmpty()){
				if(this.listaEmisores.size()>0){
					this.emisorSelec=this.listaEmisores.get(0);
					this.nuevoEmisor=Boolean.TRUE;
					this.editarEliminarEmisor=Boolean.FALSE;
				}else{
					this.emisorSelec=new Emisor();
					this.nuevoEmisor=Boolean.FALSE;
					this.editarEliminarEmisor=Boolean.TRUE;
				}
				
			}else{
				this.emisorSelec=new Emisor();
				this.nuevoEmisor=Boolean.FALSE;
				this.editarEliminarEmisor=Boolean.TRUE;
			}
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoEmisor(){
		this.emisorSelec = new Emisor();
		this.editarEmisor = Boolean.FALSE;
	}

	public void editarEmisor() throws IOException{
//		this.emisorSelec = emisor;
		//System.out.println("BYTEEES: "+this.emisorSelec.getSlogan());
		
		//InputStream in = new ByteArrayInputStream(this.emisorSelec.getSlogan());
		//System.out.println("InputStream: "+in);
		//dbImage = new DefaultStreamedContent(new FileInputStream(f), new MimetypesFileTypeMap().getContentType(f));
		//System.out.println("dbImage: "+dbImage);
		try{
			this.editarEmisor = Boolean.TRUE;
			/*
			if(this.emisorSelec.getRuta_logo()!=null){
				File f = new File (this.emisorSelec.getRuta_logo());
				archibytes = Files.readAllBytes(f.toPath());
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		this.editarEmisor = Boolean.TRUE;
	}
	
	public void eliminarEmisor(){
//		this.emisorSelec = emisor;
	}	
	
	public void confirmaEliminarEmisor(){
		try {
			System.out.println("Emisor :"+this.emisorSelec.getId_emisor());
			this.emisorService.eliminarEmisor(this.emisorSelec.getId_emisor());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina el emisor: " + this.emisorSelec.getNombre_comercial());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Emisor ha sido eliminado", 3);
			
			this.listaEmisores = this.emisorService.findAll();
			this.emisorSelec= new Emisor();
			this.nuevoEmisor=Boolean.FALSE;
			this.editarEliminarEmisor=Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void upload(FileUploadEvent event) {
		try {
			
			String fileName  = event.getFile().getFileName();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String newFileName = servletContext.getRealPath("") + File.separator + "upload" +    File.separator+ fileName;
			System.out.println("newFileName:"+newFileName);	
		UploadedFile archivoCarga=event.getFile();
		System.out.println("Ruta:"+FilenameUtils.getFullPath(event.getFile().getFileName()));
		this.destination=FilenameUtils.getFullPath(event.getFile().getFileName());
		System.out.println("Name:"+archivoCarga.getFileName());
		copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
		FacesMessage message = new FacesMessage("El archivo se ha subido con éxito!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
		e.printStackTrace();
		}

	}
	
	public void copyFile(String fileName, InputStream in) {
		try {
		OutputStream out = new FileOutputStream(new File(this.destination + fileName));
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = in.read(bytes)) != -1) {
		out.write(bytes, 0, read);
		}
		in.close();
		out.flush();
		out.close();
		//this.emisorSelec.setSlogan(bytes);
		System.out.println("El archivo se ha creado con éxito!");

		DateFormat dateFormat = new SimpleDateFormat("yyyy–MM–dd HH_mm_ss");
		Date date = new Date();
		String ruta1 = destination + fileName;
		String ruta2 = destination + dateFormat.format(date)+"–"+fileName;
		//System.out.println("Archivo: "+ruta1+" Renombrado a: "+ruta2);
		File archivo=new File(ruta1);
		this.emisorSelec.setRuta_logo(ruta1);
		//archivo.renameTo(new File(ruta2));
		} catch (IOException e) {
		System.out.println("Error :"+e.getMessage());
		}
	}
	
	
	/*Jesús*/
	public void subirImagen (FileUploadEvent event){
		
		FacesMessage message = new FacesMessage();
				
		try{
			System.out.println("1111 !");
			
			this.emisorSelec.setImagen(event.getFile().getContents());
			imagenLogo = Utils.guardaBlobEnFicheroTemporal(this.emisorSelec.getImagen(), event.getFile().getFileName());
			
			System.out.println("imagenProducto " + imagenLogo);
			System.out.println("imagen " + this.emisorSelec.getImagen());
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Imagen subida exitosamente");
			
		}catch ( Exception e){
			System.out.println("22222 !");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Problemas al subir la imagen");
		}
		
		FacesContext.getCurrentInstance().addMessage("Mensaje", message);
		
	}
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
	public Emisor getEmisorSelec() {
		return emisorSelec;
	}

	public void setEmisorSelec(Emisor emisorSelec) {
		this.emisorSelec = emisorSelec;
	}

	public List<Emisor> getListaEmisores() {
		return listaEmisores;
	}

	public void setListaEmisores(List<Emisor> listaEmisores) {
		this.listaEmisores = listaEmisores;
	}

	public Boolean getEditarEmisor() {
		return editarEmisor;
	}

	public void setEditarEmisor(Boolean editarEmisor) {
		this.editarEmisor = editarEmisor;
	}

	public List<DomicilioFiscal> getListaDomicilioFiscales() {
		return listaDomicilioFiscales;
	}

	public void setListaDomicilioFiscales(
			List<DomicilioFiscal> listaDomicilioFiscales) {
		this.listaDomicilioFiscales = listaDomicilioFiscales;
	}

	public StreamedContent getDbImage() {
		return dbImage;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public byte[] getArchibytes() {
		return archibytes;
	}

	public void setArchibytes(byte[] archibytes) {
		this.archibytes = archibytes;
	}

	public Boolean getNuevoEmisor() {
		return nuevoEmisor;
	}

	public void setNuevoEmisor(Boolean nuevoEmisor) {
		this.nuevoEmisor = nuevoEmisor;
	}

	public String getImagenLogo() {
		return imagenLogo;
	}

	public void setImagenLogo(String imagenLogo) {
		this.imagenLogo = imagenLogo;
	}

	public Boolean getEditarEliminarEmisor() {
		return editarEliminarEmisor;
	}

	public void setEditarEliminarEmisor(Boolean editarEliminarEmisor) {
		this.editarEliminarEmisor = editarEliminarEmisor;
	}

    
	
	
}
