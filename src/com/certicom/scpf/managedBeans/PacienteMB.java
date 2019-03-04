package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.ConsultaMedica;
import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.domain.Receta;
import com.certicom.scpf.domain.SignoVital;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.ConsultaMedicaService;
import com.certicom.scpf.services.ExamenAuxiliarService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.PacienteService;
import com.certicom.scpf.services.RecetaService;
import com.certicom.scpf.services.SignoVitalService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TicketService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="pacienteMB")
@ViewScoped
public class PacienteMB extends GenericBeans implements Serializable {
	
	private Paciente pacienteSelec;
	private Boolean editarPaciente;
	private Boolean bDetalleConsulta;
	private List<Paciente>listaPacientes;
	private List<Paciente> listaPacientesFilter;
	private LazyDataModel<Ticket> listTicketsPaciente;
	private List<Ticket> listFiltroTickets;
	private PacienteService pacienteService;
	private List<String> listaProblemasHC;
	private List<ExamenAuxiliar> listaExamenAuxiliaresHC;
	private List<Receta> listaRecetasHC;
	private SignoVital signoVitalHC;
	private Ticket ticketSelectedHC;
	private ConsultaMedica consultaMedicaHC;
    private Log log;
	private LogMB logmb;
	
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	private MenuServices menuServices;
	private ConsultaMedicaService consultaMedicaService;
	private TicketService ticketService;
	private SignoVitalService signoVitalService;
	private ExamenAuxiliarService examenAuxiliarService;
	private RecetaService recetaService;
	
	private List<Cliente> listaClientes;
	private ClienteService clienteService;
	
	@PostConstruct
	public void inicia(){
		this.pacienteSelec=new Paciente();
		this.editarPaciente = Boolean.FALSE;
		this.pacienteService= new PacienteService();
		this.clienteService= new ClienteService();
		this.ticketService = new TicketService();		
		this.pacienteService = new PacienteService();
		this.consultaMedicaService= new ConsultaMedicaService();
		this.examenAuxiliarService= new ExamenAuxiliarService();
		this.signoVitalService = new SignoVitalService();
		this.recetaService=new RecetaService();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		this.tablaTablasDetalleService=new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		try {
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:paciente");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.listaPacientes=this.pacienteService.findAll();
			this.listaClientes = this.clienteService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarDetalle(Ticket ticket){
		this.bDetalleConsulta = Boolean.TRUE;
		this.ticketSelectedHC = ticket;
		this.consultaMedicaHC = this.consultaMedicaService.findByTicket(ticket.getId_ticket());
		
		
		if(this.consultaMedicaHC==null){
			this.consultaMedicaHC= new ConsultaMedica();
			this.consultaMedicaHC.setId_ticket(ticket.getId_ticket());
			this.consultaMedicaHC.setFecha_consulta(new Date());
			this.consultaMedicaHC.setHora_consulta(new Date());
			this.consultaMedicaHC.setProducto(ticket.getProducto());
			this.consultaMedicaHC.setId_producto(ticket.getProducto().getId_producto());
			this.consultaMedicaHC.setId_tipo_servicio(ticket.getId_tipo_servicio());
			this.consultaMedicaHC.setId_medico(ticket.getId_medico());
			this.consultaMedicaHC.setId_paciente(ticket.getId_paciente());
			this.consultaMedicaHC.setId_cliente(ticket.getId_cliente());
			this.consultaMedicaHC.setId_especialidad(ticket.getId_especialidad());
			this.listaProblemasHC = new ArrayList<String>();
			this.listaExamenAuxiliaresHC = new ArrayList<ExamenAuxiliar>();
			this.listaRecetasHC = new ArrayList<Receta>();					 

			this.signoVitalHC= new SignoVital();
		}else{
			this.listaExamenAuxiliaresHC=this.examenAuxiliarService.findByConsulta(consultaMedicaHC.getId_consulta_medica());
			this.signoVitalHC=this.signoVitalService.findByConsulta(this.consultaMedicaHC.getId_consulta_medica());
			this.listaRecetasHC=this.recetaService.findByConsulta(consultaMedicaHC.getId_consulta_medica());
			this.listaProblemasHC=getListadoProblemas(consultaMedicaHC.getListado_problemas());
			
		}
	}
	
	public ArrayList<String> getListadoProblemas(String problemas){
		ArrayList<String> respuesta=new ArrayList<>();
		if(problemas!=null){
			String[] array = problemas.split("-");
			for(String cadena: array){
				if(!cadena.equals("-")){
					respuesta.add(cadena);
				}
			}			
		}
		return respuesta;
	}
	
	public void consultarHistoriaClinica(){
		this.bDetalleConsulta=Boolean.FALSE;
		listarTicketFiltroPaciente();
	}
	
	public void listarTicketFiltroPaciente(){
		//System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		
		this.listTicketsPaciente = new LazyDataModel<Ticket>() {
			private static final long serialVersionUID = 1L;
			private List<Ticket> datasource; 
			private Integer totalRow=0;
			
			
			@Override  
			public Ticket getRowData(String rowKey){
				 for(Ticket e : datasource) {  
                     if(e.getId_ticket().equals(rowKey))  
                         return e;  
                 }  

                 return null;  
			}
			
			 @Override
             public void setRowIndex(int rowIndex){
                 if (rowIndex == -1 || getPageSize() == 0) {
                     super.setRowIndex(-1);
                 }
                 else
                     super.setRowIndex(rowIndex % getPageSize());
             }
			 
			 @Override  
	            public Object getRowKey(Ticket e) {  
	                return e.getId_ticket();  
	            }  
			 @Override  
	            public List<Ticket> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {              
					try {
						totalRow = ticketService.countByPaciente(pacienteSelec.getId_paciente());
												
						  datasource = ticketService.findByPaciente(pacienteSelec.getId_paciente(), first, pageSize, filters, "cm.id_consulta_medica", "DESC");
						 return datasource;
					} catch (Exception e) {
						System.out.println("NULL ");
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
	            } 
			 
			 @Override  
				public int getRowCount() {     
					return totalRow;
	            }
			 
		};		
		
	}
	
	public void nuevoPaciente(){
		this.pacienteSelec = new Paciente();
		this.editarPaciente = Boolean.FALSE;
	}
	
	public void editarPaciente(Paciente paciente){
		
		this.pacienteSelec = paciente;
		this.editarPaciente = Boolean.TRUE;
	}
	
	public void eliminarPaciente(Paciente paciente){
		this.pacienteSelec = paciente;
	}
	
	public void guardarPaciente(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarPaciente) {
				this.pacienteService.actualizarPaciente(this.pacienteSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Vendedor : " + this.pacienteSelec.getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del paciente ha sido actualizado", 3);
			}else{
				this.pacienteService.crearPaciente(this.pacienteSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta Paciente: " + this.pacienteSelec.getNombre());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Paciente ha sido creado", 3);
			}
			
			this.pacienteSelec = new Paciente();
			this.editarPaciente = Boolean.FALSE;
			
			this.listaPacientes = this.pacienteService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarPaciente(){
		try {
		
			this.pacienteService.eliminarPaciente(this.pacienteSelec.getId_paciente());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al paciente: " + this.pacienteSelec.getNombre());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Paciente ha sido eliminado", 3);
			
			this.listaPacientes = this.pacienteService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Paciente getPacienteSelec() {
		return pacienteSelec;
	}

	public void setPacienteSelec(Paciente pacienteSelec) {
		this.pacienteSelec = pacienteSelec;
	}

	public Boolean getEditarPaciente() {
		return editarPaciente;
	}

	public void setEditarPaciente(Boolean editarPaciente) {
		this.editarPaciente = editarPaciente;
	}

	public List<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
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

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Paciente> getListaPacientesFilter() {
		return listaPacientesFilter;
	}

	public void setListaPacientesFilter(List<Paciente> listaPacientesFilter) {
		this.listaPacientesFilter = listaPacientesFilter;
	}

	public Boolean getbDetalleConsulta() {
		return bDetalleConsulta;
	}

	public void setbDetalleConsulta(Boolean bDetalleConsulta) {
		this.bDetalleConsulta = bDetalleConsulta;
	}

	public LazyDataModel<Ticket> getListTicketsPaciente() {
		return listTicketsPaciente;
	}

	public void setListTicketsPaciente(LazyDataModel<Ticket> listTicketsPaciente) {
		this.listTicketsPaciente = listTicketsPaciente;
	}

	public List<Ticket> getListFiltroTickets() {
		return listFiltroTickets;
	}

	public void setListFiltroTickets(List<Ticket> listFiltroTickets) {
		this.listFiltroTickets = listFiltroTickets;
	}

	public List<String> getListaProblemasHC() {
		return listaProblemasHC;
	}

	public void setListaProblemasHC(List<String> listaProblemasHC) {
		this.listaProblemasHC = listaProblemasHC;
	}

	public List<ExamenAuxiliar> getListaExamenAuxiliaresHC() {
		return listaExamenAuxiliaresHC;
	}

	public void setListaExamenAuxiliaresHC(
			List<ExamenAuxiliar> listaExamenAuxiliaresHC) {
		this.listaExamenAuxiliaresHC = listaExamenAuxiliaresHC;
	}

	public List<Receta> getListaRecetasHC() {
		return listaRecetasHC;
	}

	public void setListaRecetasHC(List<Receta> listaRecetasHC) {
		this.listaRecetasHC = listaRecetasHC;
	}

	public SignoVital getSignoVitalHC() {
		return signoVitalHC;
	}

	public void setSignoVitalHC(SignoVital signoVitalHC) {
		this.signoVitalHC = signoVitalHC;
	}

	public Ticket getTicketSelectedHC() {
		return ticketSelectedHC;
	}

	public void setTicketSelectedHC(Ticket ticketSelectedHC) {
		this.ticketSelectedHC = ticketSelectedHC;
	}

	public ConsultaMedica getConsultaMedicaHC() {
		return consultaMedicaHC;
	}

	public void setConsultaMedicaHC(ConsultaMedica consultaMedicaHC) {
		this.consultaMedicaHC = consultaMedicaHC;
	}	

}
