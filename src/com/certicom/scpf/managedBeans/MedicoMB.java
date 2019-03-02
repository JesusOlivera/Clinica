package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Especialidad;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.services.EspecialidadService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TicketService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="medicoMB")
@ViewScoped
public class MedicoMB extends GenericBeans implements Serializable{

	private Medico medicoSelec;
	private Integer id_producto;
	private Integer id_productoSel;
	private Boolean editarmedico;
	private Boolean bBusqueda;
	private Date fecIni;
	private Date fecFin;
	private Date fecInicio;
	private Date fecFinal;
	private List<Medico>listamedicosFilter;
	

	private List<Medico>listamedicos;
	private LazyDataModel<Ticket> listTickets;
	private List<Ticket> listFiltroTickets;
	private List<Ticket> datasource;
	private List<Producto> listProductos;
	private MedicoService medicoService;
	private ProductoService productoService;
	private TicketService ticketService;
    private Log log;
	private LogMB logmb;
	
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	private MenuServices menuServices;
	
	private EspecialidadService especialidadService;
	private List<Especialidad> listaEspecialidades;
	
	@PostConstruct
	public void inicia(){
		this.medicoSelec=new Medico();
		this.editarmedico = Boolean.FALSE;
		this.medicoService= new MedicoService();
		this.productoService = new ProductoService();
		this.ticketService = new TicketService();
		this.fecIni = new Date();
		this.fecFin = new Date();
		
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		this.tablaTablasDetalleService=new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		this.especialidadService=new EspecialidadService();
		
		try {
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:medico");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
			this.listaEspecialidades=this.especialidadService.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.listamedicos=this.medicoService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarTickets(Medico medico){
		try {
			this.listProductos = this.productoService.findAll();
			this.id_productoSel = 0;
			this.fecInicio = null;
			this.fecFinal = null;
			this.medicoSelec = medico;
			listarTicketFiltros();
			
			this.bBusqueda = Boolean.TRUE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void habilitarBoton(){
		this.bBusqueda = Boolean.FALSE;
	}
	
	public void busquedaConsultas(){
		System.out.println("ID PRODUCTO: "+this.id_producto);
		System.out.println("FEC INI: "+this.fecIni);
		System.out.println("FEC FIN: "+this.fecFin);
		
		listarTicketFiltros();
	}
	
	public void listarTicketFiltros(){
		//System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
			
		
		this.listTickets = new LazyDataModel<Ticket>() {
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
						totalRow = ticketService.countByMedicoPAGINATOR(fecInicio,fecFinal,medicoSelec.getId_medico(),id_productoSel,filters);					
						  datasource = ticketService.findByMedicoPAGINATOR(fecInicio,fecFinal,medicoSelec.getId_medico(),id_productoSel, first, pageSize, filters, "t.id_ticket", "DESC");
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
	
	
	public void nuevomedico(){
		this.medicoSelec = new Medico();
		this.editarmedico = Boolean.FALSE;
	}
	
	public void editarmedico(Medico medico){
		
		this.medicoSelec = medico;
		this.editarmedico = Boolean.TRUE;
	}
	
	public void eliminarmedico(Medico medico){
		this.medicoSelec = medico;
	}
	
	public void guardarmedico(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarmedico) {
				this.medicoService.actualizarmedico(this.medicoSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el Vendedor : " + this.medicoSelec.getNombre_medico());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion del medico ha sido actualizado", 3);
			}else{
				this.medicoService.crearmedico(this.medicoSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta medico: " + this.medicoSelec.getNombre_medico());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("medico ha sido creado", 3);
			}
			
			this.medicoSelec = new Medico();
			this.editarmedico = Boolean.FALSE;
			
			this.listamedicos = this.medicoService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarmedico(){
		try {
		
			this.medicoService.eliminarMedico(this.medicoSelec.getId_medico());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina al medico: " + this.medicoSelec.getNombre_medico());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("medico ha sido eliminado", 3);
			
			this.listamedicos = this.medicoService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Medico getMedicoSelec() {
		return medicoSelec;
	}

	public void setMedicoSelec(Medico medicoSelec) {
		this.medicoSelec = medicoSelec;
	}

	public Boolean getEditarmedico() {
		return editarmedico;
	}

	public void setEditarmedico(Boolean editarmedico) {
		this.editarmedico = editarmedico;
	}

	public List<Medico> getListamedicos() {
		return listamedicos;
	}

	public void setListamedicos(List<Medico> listamedicos) {
		this.listamedicos = listamedicos;
	}

	public MedicoService getMedicoService() {
		return medicoService;
	}

	public void setMedicoService(MedicoService medicoService) {
		this.medicoService = medicoService;
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

	public EspecialidadService getEspecialidadService() {
		return especialidadService;
	}

	public void setEspecialidadService(EspecialidadService especialidadService) {
		this.especialidadService = especialidadService;
	}

	public List<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}
	
	public List<Medico> getListamedicosFilter() {
		return listamedicosFilter;
	}

	public void setListamedicosFilter(List<Medico> listamedicosFilter) {
		this.listamedicosFilter = listamedicosFilter;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public List<Producto> getListProductos() {
		return listProductos;
	}

	public void setListProductos(List<Producto> listProductos) {
		this.listProductos = listProductos;
	}

	public Date getFecIni() {
		return fecIni;
	}

	public void setFecIni(Date fecIni) {
		this.fecIni = fecIni;
	}

	public Date getFecFin() {
		return fecFin;
	}

	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
	}

	public LazyDataModel<Ticket> getListTickets() {
		return listTickets;
	}

	public void setListTickets(LazyDataModel<Ticket> listTickets) {
		this.listTickets = listTickets;
	}

	public List<Ticket> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<Ticket> datasource) {
		this.datasource = datasource;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFinal() {
		return fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}

	public Integer getId_productoSel() {
		return id_productoSel;
	}

	public void setId_productoSel(Integer id_productoSel) {
		this.id_productoSel = id_productoSel;
	}

	public List<Ticket> getListFiltroTickets() {
		return listFiltroTickets;
	}

	public void setListFiltroTickets(List<Ticket> listFiltroTickets) {
		this.listFiltroTickets = listFiltroTickets;
	}

	public Boolean getbBusqueda() {
		return bBusqueda;
	}

	public void setbBusqueda(Boolean bBusqueda) {
		this.bBusqueda = bBusqueda;
	}
	
}
