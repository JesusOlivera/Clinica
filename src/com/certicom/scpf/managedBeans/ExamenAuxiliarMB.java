package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.services.ExamenAuxiliarService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TicketService;
import com.certicom.scpf.services.TipoServicioService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="examenAuxiliarMB")
@ViewScoped
public class ExamenAuxiliarMB extends GenericBeans implements Serializable{

	private List<ExamenAuxiliar> listaExamenAuxiliares;
	private ExamenAuxiliar examenAuxiliar;
	private ExamenAuxiliarService examenAuxiliarService;
	
	private List<Medico>listaMedicos;
	private MedicoService medicoService;
	
	private TicketService ticketService;
	private Ticket ticketSelected;
	private List<TipoServicio> listTipoServicios;
	private TipoServicioService tipoServicioService;
	private Integer id_medico_EX;
	private ProductoService productoService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	@PostConstruct
	public void inicia(){
		this.examenAuxiliarService=new ExamenAuxiliarService();
		this.examenAuxiliar=new ExamenAuxiliar();
		this.listaExamenAuxiliares=this.examenAuxiliarService.findAll();
		this.medicoService= new MedicoService();
		this.listaMedicos=this.medicoService.findAll();
		
		this.ticketService= new TicketService();
		this.tipoServicioService= new TipoServicioService();
		this.listTipoServicios = this.tipoServicioService.findAllForTicket();	
		
		this.productoService= new ProductoService();
	}
	
	public void cargaEspecialidad(){
		System.out.println("cargaEspecialidad ----------> "+this.id_medico_EX);
		this.ticketSelected.setId_medico(this.id_medico_EX);
		Medico medico= new Medico();
		medico=this.medicoService.findById(this.ticketSelected.getId_medico());		
		this.ticketSelected.setId_especialidad(medico.getId_especialidad());
		this.examenAuxiliar.setId_medico(id_medico_EX);
		this.examenAuxiliar.setId_especialidad(this.ticketSelected.getId_especialidad());
		
	}
	
	public void editarExamen(ExamenAuxiliar examenAuxiliar){
		this.examenAuxiliar=examenAuxiliar;
	}
	
	public void actualizarExamen(){
		this.examenAuxiliarService.actualizarExamenAuxiliar(this.examenAuxiliar);
		this.listaExamenAuxiliares=this.examenAuxiliarService.findAll();
		this.examenAuxiliar= new ExamenAuxiliar();
	}
	
	public void nuevoTicket(ExamenAuxiliar examenAuxiliar){
		
		if(!examenAuxiliar.getTicket_generado()){
			Integer cifras=6;		
			Integer max = this.ticketService.obtenerMax();	
			this.examenAuxiliar=examenAuxiliar;
			this.ticketSelected = new Ticket();
			this.ticketSelected.setNro_ticket(generarNroTicket(max, cifras));
			this.ticketSelected.setFecha_ticket(new Date());
			this.ticketSelected.setHora_ticket(new Date());
			this.ticketSelected.setId_cliente(this.examenAuxiliar.getId_cliente());
			this.ticketSelected.setId_paciente(this.examenAuxiliar.getId_paciente());	
			this.ticketSelected.setId_producto(this.examenAuxiliar.getId_producto());
			this.ticketSelected.setId_tipo_servicio(this.examenAuxiliar.getId_tipo_servicio());
			this.ticketSelected.setEstado(Constante.TICKET_ESTADO_PENDIENTE);
			this.ticketSelected.setId_examen(this.examenAuxiliar.getId_examen_auxiliar());
			try {
				Producto producto=this.productoService.findById(this.examenAuxiliar.getId_producto());
				if(producto!=null){					
					this.ticketSelected.setEncolado(producto.getGenera_cola());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.listaMedicos=this.medicoService.findAll();
			
		}else{
			FacesUtils.showFacesMessage("Ya se genero un ticket desde este examen", 3);
		}
	
		
	}
	
	
	
	public void guardarTicket(){
		
		
		this.ticketService.crearTicket(this.ticketSelected);
		this.examenAuxiliar.setTicket_generado(Boolean.TRUE);
		this.examenAuxiliarService.actualizarExamenAuxiliar(this.examenAuxiliar);
		this.listaExamenAuxiliares=this.examenAuxiliarService.findAll();
	}
		
	public String generarNroTicket(Integer max, Integer cifras){
		
		String smax = "", nroTicket="EX";			
			max = max + 1;			
			smax = Integer.toString(max);			
			for (int i = 0; i < (cifras - smax.length()) ; i++) {
				nroTicket = nroTicket + "0";
			}			
			nroTicket = nroTicket + smax;		
		return nroTicket;
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

	public ExamenAuxiliarService getExamenAuxiliarService() {
		return examenAuxiliarService;
	}

	public void setExamenAuxiliarService(ExamenAuxiliarService examenAuxiliarService) {
		this.examenAuxiliarService = examenAuxiliarService;
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

	public List<Medico> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<Medico> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public MedicoService getMedicoService() {
		return medicoService;
	}

	public void setMedicoService(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public Ticket getTicketSelected() {
		return ticketSelected;
	}

	public void setTicketSelected(Ticket ticketSelected) {
		this.ticketSelected = ticketSelected;
	}

	public List<TipoServicio> getListTipoServicios() {
		return listTipoServicios;
	}

	public void setListTipoServicios(List<TipoServicio> listTipoServicios) {
		this.listTipoServicios = listTipoServicios;
	}

	public TipoServicioService getTipoServicioService() {
		return tipoServicioService;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
	}

	public Integer getId_medico_EX() {
		return id_medico_EX;
	}

	public void setId_medico_EX(Integer id_medico_EX) {
		this.id_medico_EX = id_medico_EX;
	}
	
	
}
