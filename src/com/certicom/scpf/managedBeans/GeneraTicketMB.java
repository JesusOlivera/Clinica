package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.PacienteService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TicketService;
import com.certicom.scpf.services.TipoServicioService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="generaTicketMB")
@ViewScoped
public class GeneraTicketMB extends GenericBeans implements Serializable {

	
	private List<TipoServicio> listTipoServicios;
	private List<Producto> listProductos;
	private List<Medico> listMedicos;
	private List<Ticket> listTickets;
	private Ticket ticketSelected;
	private Paciente paciente;
	private Paciente pacienteParam;
	private Producto producto;
	private PacienteService pacienteService;
	private TipoServicioService tipoServicioService;
	private ProductoService productoService;
	private MedicoService medicoService;
	private TicketService ticketService;
	private Boolean editarTicket;
    private Log log;
	private LogMB logmb;
	
	@PostConstruct
	public void inicia(){
		
		this.editarTicket = Boolean.FALSE;
		this.ticketSelected = new Ticket();
		this.paciente = new Paciente();
		this.pacienteParam = new Paciente();
		this.producto = new Producto();
		this.productoService = new ProductoService();
		this.tipoServicioService = new TipoServicioService();
		this.ticketService = new TicketService();		
		this.medicoService = new MedicoService();
		this.pacienteService = new PacienteService();
		this.listTipoServicios = new ArrayList<TipoServicio>();
		this.listMedicos = new ArrayList<Medico>();
		this.listTickets = new ArrayList<Ticket>();
		this.listProductos = new ArrayList<Producto>();
		this.listMedicos = this.medicoService.findAll();
		this.listTipoServicios = this.tipoServicioService.findAll();	
		this.listTickets = this.ticketService.findAll();
		
		
		
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarTicket(Ticket ticket){
		System.out.println("Editar Ticket : "+ticket.getNro_ticket());
		this.ticketSelected= ticket;
		this.editarTicket = Boolean.TRUE;
		try {
			this.listProductos=this.productoService.findAll();
			this.listMedicos=this.medicoService.findAll();
			this.producto=this.productoService.findById(this.ticketSelected.getId_producto());
			this.pacienteParam= this.pacienteService.findById(this.ticketSelected.getId_paciente());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarTicket(Ticket ticket){
		this.ticketSelected = ticket;
	}
	
	public void confirmaEliminarTicket(){
		try {
		
			this.ticketService.eliminarTicket(this.ticketSelected.getId_ticket());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al ticket: " + this.ticketSelected.getNro_ticket());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Ticket ha sido eliminado", 3);
			
			this.listTickets = this.ticketService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void nuevoTicket(){
		
		Integer cifras=6;		
		Integer max = this.ticketService.obtenerMax();
		this.ticketSelected = new Ticket();
		this.ticketSelected.setNro_ticket(generarNroTicket(max, cifras));
		this.ticketSelected.setFecha_ticket(new Date());
		this.ticketSelected.setHora_ticket(new Date());
	}
	
	public void cargarServicios(){
		try {
			this.listProductos = this.productoService.findByIdTipoServicio(this.ticketSelected.getId_tipo_servicio());
			this.pacienteParam=new Paciente();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String generarNroTicket(Integer max, Integer cifras){
		
		String smax = "", nroTicket="CF";
			
			max = max + 1;
			
			smax = Integer.toString(max);
			
			for (int i = 0; i < (cifras - smax.length()) ; i++) {
				nroTicket = nroTicket + "0";
			}
			
			nroTicket = nroTicket + smax;

		
		return nroTicket;
	}
	
	public void obtenerPaciente(){
		
		System.out.println("hola");
		try {
		Paciente p1 = new Paciente();
		
		p1.setNumero_documento(this.pacienteParam.getNumero_documento());
		p1.setNombre(this.pacienteParam.getNombre());
		
		System.out.println("Nro documento: "+this.pacienteParam.getNumero_documento());
//		System.out.println("Nombre: "+this.pacienteParam.getNombre());
		
		this.paciente = this.pacienteService.obtenerPaciente(p1);
		
		this.pacienteParam = this.paciente;
		
		this.ticketSelected.setId_cliente(this.paciente.getId_cliente());
		this.ticketSelected.setId_paciente(this.paciente.getId_paciente());
			
		if(this.paciente == null){
			System.out.println("Es nulo");
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cargaPrecio(){
		
		try {
			this.producto = this.productoService.findById(this.ticketSelected.getId_producto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cargaEspecialidad(){
		System.out.println("cargaEspecialidad ----------> "+this.ticketSelected.getId_medico());
		Medico medico= new Medico();
		medico=this.medicoService.findById(this.ticketSelected.getId_medico());		
		this.ticketSelected.setId_especialidad(medico.getId_especialidad());
		
	}
	
	public void guardarTicket(){
		
		
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarTicket) {
				this.ticketService.actualizarTicket(this.ticketSelected);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Ticket : " + this.ticketSelected.getNro_ticket());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El ticket ha sido actualizado", 3);
			}else{
				this.ticketService.crearTicket(this.ticketSelected);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta ticket: " + this.ticketSelected.getNro_ticket());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("el Ticket ha sido creado", 3);
			}
			
			this.ticketSelected = new Ticket();
			this.editarTicket = Boolean.FALSE;
			
			this.listTickets = this.ticketService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		limpiarDatos();
		
	}
	
	public void limpiarDatos(){
		
		this.ticketSelected = new Ticket();
		this.paciente = new Paciente();
		this.pacienteParam = new Paciente();
		this.producto = new Producto();
		
	}

	public Boolean getEditarTicket() {
		return editarTicket;
	}

	public void setEditarTicket(Boolean editarTicket) {
		this.editarTicket = editarTicket;
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

	public List<TipoServicio> getListTipoServicios() {
		return listTipoServicios;
	}

	public void setListTipoServicios(List<TipoServicio> listTipoServicios) {
		this.listTipoServicios = listTipoServicios;
	}

	public List<Producto> getListProductos() {
		return listProductos;
	}

	public void setListProductos(List<Producto> listProductos) {
		this.listProductos = listProductos;
	}

	public List<Medico> getListMedicos() {
		return listMedicos;
	}

	public void setListMedicos(List<Medico> listMedicos) {
		this.listMedicos = listMedicos;
	}

	public Ticket getTicketSelected() {
		return ticketSelected;
	}

	public void setTicketSelected(Ticket ticketSelected) {
		this.ticketSelected = ticketSelected;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Paciente getPacienteParam() {
		return pacienteParam;
	}

	public void setPacienteParam(Paciente pacienteParam) {
		this.pacienteParam = pacienteParam;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Ticket> getListTickets() {
		return listTickets;
	}

	public void setListTickets(List<Ticket> listTickets) {
		this.listTickets = listTickets;
	}	

}
