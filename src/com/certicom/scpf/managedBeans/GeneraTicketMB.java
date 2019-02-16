package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.ConsultaMedica;
import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Receta;
import com.certicom.scpf.domain.SignoVital;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.services.ConsultaMedicaService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.PacienteService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.SignoVitalService;
import com.certicom.scpf.services.TicketService;
import com.certicom.scpf.services.TipoServicioService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="generaTicketMB")
@ViewScoped
public class GeneraTicketMB extends GenericBeans implements Serializable {

	@ManagedProperty(value="#{loginMB.perfilUsuario.cod_perfil}")
	private String cod_perfil;  
	private List<TipoServicio> listTipoServicios;
	private List<Producto> listProductos;
	private List<Medico> listMedicos;
	private List<Ticket> listTickets;
	private List<String> listaSignosVitales;
	private List<String> listaProblemas;
	private List<ExamenAuxiliar> listaExamenAuxiliares;
	private List<Receta> listaRecetas;
	private SignoVital signoVital;
	private Ticket ticketSelected;
	private Paciente paciente;
	private Paciente pacienteParam;
	private Producto producto;
	private String problema;
	private ConsultaMedica consultaMedica;
	private ExamenAuxiliar examenAuxiliar;
	private Receta receta;
	private PacienteService pacienteService;
	private TipoServicioService tipoServicioService;
	private ProductoService productoService;
	private MedicoService medicoService;
	private TicketService ticketService;
	private SignoVitalService signoVitalService;
	private ConsultaMedicaService consultaMedicaService;
	private Boolean editarTicket;
	private Boolean editarProblemas;
	private Boolean editarExamenAuxiliar;
	private Boolean editarSignoVital;
	private Boolean editarReceta;
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
		this.signoVitalService = new SignoVitalService();
		this.listTipoServicios = new ArrayList<TipoServicio>();
		this.listMedicos = new ArrayList<Medico>();
		this.listTickets = new ArrayList<Ticket>();
		this.listProductos = new ArrayList<Producto>();
		this.listMedicos = this.medicoService.findAll();
		this.listTipoServicios = this.tipoServicioService.findAllForTicket();	
		this.listTickets = this.ticketService.findAll();
		
		this.consultaMedicaService= new ConsultaMedicaService();
		
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
	
	public void guardarConsultaMedica(){
		
		this.consultaMedicaService.crearConsultaMedica(this.consultaMedica);
	}
	
	public void nuevoTicket(){
		
		Integer cifras=6;		
		Integer max = this.ticketService.obtenerMax();
		limpiarDatos();
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
			
			if(!this.ticketSelected.getId_tipo_servicio().equals(Constante.COD_TIPO_SERVICIO_LABORATORIO)){
				this.ticketSelected.setEncolado(Boolean.TRUE);
			}
			
			if(this.ticketSelected.getId_tipo_servicio().equals(Constante.COD_TIPO_SERVICIO_LABORATORIO)){
				this.ticketSelected.setEstado("TERMINADO");
			}else{
				this.ticketSelected.setEstado("PENDIENTE");
			}
			
			
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
	
	public void nuevaConsultaMedica(Ticket ticket){
		
		this.consultaMedica = new ConsultaMedica();
		this.consultaMedica.setId_ticket(ticket.getId_ticket());
		this.consultaMedica.setFecha_consulta(new Date());
		this.consultaMedica.setHora_consulta(new Date());
		this.consultaMedica.setProducto(ticket.getProducto());
		this.consultaMedica.setId_producto(ticket.getProducto().getId_producto());
		this.consultaMedica.setId_tipo_servicio(ticket.getId_tipo_servicio());
		this.consultaMedica.setId_medico(ticket.getId_medico());
		this.consultaMedica.setId_paciente(ticket.getId_paciente());
		this.consultaMedica.setId_cliente(ticket.getId_cliente());
		this.consultaMedica.setId_especialidad(ticket.getId_especialidad());
		this.ticketSelected = ticket;
		this.editarProblemas = Boolean.FALSE;	
		this.editarExamenAuxiliar = Boolean.FALSE;
		this.editarReceta = Boolean.FALSE;
		this.listaProblemas = new ArrayList<String>();
		this.listaExamenAuxiliares = new ArrayList<ExamenAuxiliar>();
		this.listaRecetas = new ArrayList<Receta>();
		this.editarSignoVital = Boolean.FALSE;
		
	}
	
	public void actualizarSignoVital(){
		
		if(this.signoVital==null){
		
			this.signoVital = new SignoVital();
		
		}
		
	}
	
	public void confirmarActualizarSignoVital(){
		
		this.listaSignosVitales = new ArrayList<String>();
		
		this.listaSignosVitales.add("Talla: " + this.signoVital.getTalla().toString());
		this.listaSignosVitales.add("Ocupación: " + this.signoVital.getOcupacion());
		this.listaSignosVitales.add("Peso: " + this.signoVital.getPeso().toString());
		this.listaSignosVitales.add("Temperatura: " + this.signoVital.getTemperatura().toString());
		this.listaSignosVitales.add("Alergia: " + this.signoVital.getAlergia());
		this.listaSignosVitales.add("Frecuencia Cardiaca: " + this.signoVital.getFrecuencia_cardiaca());
		this.listaSignosVitales.add("Frecuencia Respiratoria: " + this.signoVital.getFrecuencia_respiratoria());
		this.listaSignosVitales.add("Presión Arterial: " + this.signoVital.getPresion_arterial());
		this.listaSignosVitales.add("Embarazada: " + (this.signoVital.getEmbarazo()? "SI":"NO"));
		this.listaSignosVitales.add("Otros: " + this.signoVital.getOtros1() + " " + this.signoVital.getOtros2() + " " + this.signoVital.getOtros3());
		this.listaSignosVitales.add("Sat 02: " + this.signoVital.getSat_oxigeno());
		
		this.editarSignoVital = Boolean.TRUE;
		
		System.out.println("se almacenaron los signos vitales");
		
	}
	
	public void limpiarDatos(){
		
		this.ticketSelected = new Ticket();
		this.paciente = new Paciente();
		this.pacienteParam = new Paciente();
		this.producto = new Producto();
		
	}
	
	public void agregarProblema(){
		
		if(!this.problema.isEmpty()){
			
			if(!(this.listaProblemas.contains(this.problema))){
		
			this.listaProblemas.add(this.problema);
			
			this.editarProblemas = Boolean.TRUE;
			
			}
		
		}
		
		System.out.println("cantidad de problemas: " + this.listaProblemas.size());
		this.problema = "";		
		
	}
	
	public void agregarExamenAuxiliar(){
		
		this.examenAuxiliar = new ExamenAuxiliar();
		
	}
	
	public void agregarReceta(){
		
		this.receta = new Receta();
		
	}
	
	public void confirmarAgregarExamenAuxiliar(){
		
		this.listaExamenAuxiliares.add(this.examenAuxiliar);
		
		this.editarExamenAuxiliar = Boolean.TRUE;
		
		this.examenAuxiliar = new ExamenAuxiliar();
		
	}
	
	public void confirmarAgregarReceta(){
		
		this.listaRecetas.add(this.receta);
		
		this.editarReceta = Boolean.TRUE;
		
		this.receta = new Receta();
		
	}
	
	public void eliminarProblema(String problema){
		
		this.listaProblemas.remove(problema);
		
		if(this.listaProblemas.size() == 0){
			this.editarProblemas = Boolean.FALSE;
		}
		
	}
	
	public void eliminarExamenAuxiliar(ExamenAuxiliar exaux){
		
		this.listaExamenAuxiliares.remove(exaux);
		
		if(this.listaExamenAuxiliares.size() == 0){
			this.editarExamenAuxiliar = Boolean.FALSE;
		}
		
	}
	
	public void eliminarReceta(Receta recet){
		
		this.listaRecetas.remove(recet);
		
		if(this.listaRecetas.size() == 0){
			this.editarReceta = Boolean.FALSE;
		}
		
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

	public String getCod_perfil() {
		return cod_perfil;
	}

	public void setCod_perfil(String cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public ConsultaMedica getConsultaMedica() {
		return consultaMedica;
	}

	public void setConsultaMedica(ConsultaMedica consultaMedica) {
		this.consultaMedica = consultaMedica;
	}

	public SignoVital getSignoVital() {
		return signoVital;
	}

	public void setSignoVital(SignoVital signoVital) {
		this.signoVital = signoVital;
	}

	public List<String> getListaSignosVitales() {
		return listaSignosVitales;
	}

	public void setListaSignosVitales(List<String> listaSignosVitales) {
		this.listaSignosVitales = listaSignosVitales;
	}

	public List<String> getListaProblemas() {
		return listaProblemas;
	}

	public void setListaProblemas(List<String> listaProblemas) {
		this.listaProblemas = listaProblemas;
	}

	public Boolean getEditarProblemas() {
		return editarProblemas;
	}

	public void setEditarProblemas(Boolean editarProblemas) {
		this.editarProblemas = editarProblemas;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public List<ExamenAuxiliar> getListaExamenAuxiliares() {
		return listaExamenAuxiliares;
	}

	public void setListaExamenAuxiliares(List<ExamenAuxiliar> listaExamenAuxiliares) {
		this.listaExamenAuxiliares = listaExamenAuxiliares;
	}

	public ExamenAuxiliar getExamenAuxiliar() {
		return examenAuxiliar;
	}

	public void setExamenAuxiliar(ExamenAuxiliar examenAuxiliar) {
		this.examenAuxiliar = examenAuxiliar;
	}

	public Boolean getEditarExamenAuxiliar() {
		return editarExamenAuxiliar;
	}

	public void setEditarExamenAuxiliar(Boolean editarExamenAuxiliar) {
		this.editarExamenAuxiliar = editarExamenAuxiliar;
	}

	public Boolean getEditarSignoVital() {
		return editarSignoVital;
	}

	public void setEditarSignoVital(Boolean editarSignoVital) {
		this.editarSignoVital = editarSignoVital;
	}

	public List<Receta> getListaRecetas() {
		return listaRecetas;
	}

	public void setListaRecetas(List<Receta> listaRecetas) {
		this.listaRecetas = listaRecetas;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Boolean getEditarReceta() {
		return editarReceta;
	}

	public void setEditarReceta(Boolean editarReceta) {
		this.editarReceta = editarReceta;
	}	

}
