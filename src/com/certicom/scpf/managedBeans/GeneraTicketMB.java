package com.certicom.scpf.managedBeans;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.ConsultaMedica;
import com.certicom.scpf.domain.Control;
import com.certicom.scpf.domain.DetalleComprobanteRep;
import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.ExamenAuxiliar;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Medico;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.domain.Paciente;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Receta;
import com.certicom.scpf.domain.RecetaDetalleRep;
import com.certicom.scpf.domain.SignoVital;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Ticket;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.ComprobanteDetalleService;
import com.certicom.scpf.services.ComprobanteService;
import com.certicom.scpf.services.ConsultaMedicaService;
import com.certicom.scpf.services.ControlService;												 
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.ExamenAuxiliarService;
import com.certicom.scpf.services.MedicoService;
import com.certicom.scpf.services.ModoPagoService;
import com.certicom.scpf.services.PacienteService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.RecetaService;
import com.certicom.scpf.services.SignoVitalService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TicketService;
import com.certicom.scpf.services.TipoServicioService;
import com.certicom.scpf.services.VendedorService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.ExportarArchivo;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

import net.sf.jasperreports.engine.JRParameter;
import src.com.certicom.scpf.utils.Utils;

@ManagedBean(name="generaTicketMB")
@ViewScoped
public class GeneraTicketMB extends GenericBeans implements Serializable {

	@ManagedProperty(value="#{loginMB.perfilUsuario.cod_perfil}")
	private String cod_perfil;  
	private List<TipoServicio> listTipoServicios;
	private List<Producto> listProductos;
	private List<Medico> listMedicos;
	private DomicilioFiscal domicilioFiscalSelec;
	private TablaTablasDetalle tablaTablasDetalleTipoComprobante;
	//private List<Ticket> listTickets;
	private LazyDataModel<Ticket> listTickets;
	private LazyDataModel<Ticket> listTicketsPaciente;
	private List<Ticket> listFiltroTickets;
	private List<ConsultaMedica> listFiltroConsultasMedicas;
	private List<String> listaSignosVitales;
	private List<String> listaProblemas;
	private List<String> listaProblemasHC;
	private List<ExamenAuxiliar> listaExamenAuxiliares;
	private List<ExamenAuxiliar> listaExamenAuxiliaresHC;
	private List<Receta> listaRecetas;
	private List<Receta> listaRecetasHC;
	private SignoVital signoVital;
	private SignoVital signoVitalHC;
	private Ticket ticketSelected;
	private Ticket ticketSelectedHC;
	private Paciente paciente;
	private Paciente pacienteParam;
	private Producto producto;
	private String problema;
	private String cp;
	private String nombrePacienteCliente;
	private String numeroDocPacienteCliente;
	private ConsultaMedica consultaMedica;
	private ConsultaMedica consultaMedicaHC;
	private ExamenAuxiliar examenAuxiliar;
	private Receta receta;
	private PacienteService pacienteService;
	private TipoServicioService tipoServicioService;
	private DomicilioFiscalService domicilioFiscalService;
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
	private Date fechaActual;
	private List<Object> listCampos;
	private String anio;
	private String mes;
	private Integer id_medico;
	private Control control;
	private Boolean bControl;
	private Boolean bHospitalizacion;
	private Boolean bDetalleConsulta;
	private List<Control> listaControles;					 
	
	private Integer id_tipo_servicio_EX;
	private Integer id_producto_EX;
	private ExamenAuxiliarService examenAuxiliarService;
	private Boolean editarConsulta;
	private RecetaService recetaService;
	private ControlService controlService;								   
	
	
	private Comprobante comprobanteSelec;
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private boolean ingresarCliente;
	private List<ModoPago> listModoPagos;
	private ModoPagoService modoPagoService;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<Vendedor> listaVendedores;
	private VendedorService vendedorService;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<ComprobanteDetalle> listaComprobanteDetalle;
	private ComprobanteService comprobanteService;
	private ComprobanteDetalleService comprobanteDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private boolean generarComprobante;
	private Boolean disableBuscar;
	private Boolean disableRespuesta;
	private ClienteService clienteService;
	
	@PostConstruct
	public void inicia(){
		
		this.editarTicket = Boolean.FALSE;
		this.ticketSelected = new Ticket();
		this.paciente = new Paciente();
		this.pacienteParam = new Paciente();
		this.producto = new Producto();
		this.productoService = new ProductoService();
		this.tipoServicioService = new TipoServicioService();
		this.domicilioFiscalService = new DomicilioFiscalService();
		this.comprobanteDetalleService=new ComprobanteDetalleService();
		this.ticketService = new TicketService();		
		this.medicoService = new MedicoService();
		this.pacienteService = new PacienteService();
		this.signoVitalService = new SignoVitalService();
		this.listTipoServicios = new ArrayList<TipoServicio>();
		this.listMedicos = new ArrayList<Medico>();
		//this.listTickets = new ArrayList<Ticket>();
		this.listProductos = new ArrayList<Producto>();
		this.listMedicos = this.medicoService.findAll();
		this.listTipoServicios = this.tipoServicioService.findAllForTicket();	
		this.listCampos = new ArrayList<Object>();
		//this.listTickets = this.ticketService.findAll();
		this.bControl = Boolean.FALSE;
		this.bHospitalizacion = Boolean.FALSE;						
		
		this.listaProblemas= new ArrayList<>();
		
		this.consultaMedicaService= new ConsultaMedicaService();
		this.examenAuxiliarService= new ExamenAuxiliarService();
		this.recetaService=new RecetaService();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		this.editarConsulta=Boolean.FALSE;
		this.ingresarCliente = Boolean.TRUE;
		this.generarComprobante=  Boolean.TRUE;
		this.controlService = new ControlService();									 
		
		this.fechaActual = new Date();
		
		Format formatoAnio = new SimpleDateFormat("yyyy");
		Format formatoMes= new SimpleDateFormat("MM");
		
		try {
			this.tablaTablasDetalleService = new TablaTablasDetalleService();
			this.modoPagoService= new ModoPagoService();
			this.comprobanteService = new ComprobanteService();
			this.vendedorService = new VendedorService();
			this.emisorService = new EmisorService();
			this.clienteService=new ClienteService();
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listModoPagos = this.modoPagoService.findAll();
			this.listaVendedores = this.vendedorService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.listaComprobanteDetalle = new ArrayList<ComprobanteDetalle>();
			this.comprobanteSelec= new Comprobante();
			this.anio=formatoAnio.format(new Date());
			this.mes=formatoMes.format(new Date());
			this.id_medico = 0;		  
			listarTicketFiltros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consultarHistoriaClinica(){
		this.bDetalleConsulta=Boolean.FALSE;
		listarTicketFiltroPaciente();
	}
	
	public void onItemDocumento(SelectEvent event)  throws Exception{
		
		
		 this.disableBuscar = Boolean.FALSE; 
		
	}
	
	public void listarTicketFiltros(){
		//System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		 this.disableBuscar = Boolean.FALSE; 		
		
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
						totalRow = ticketService.countTicketPAGINATOR(Integer.parseInt(anio),Integer.parseInt(mes),id_medico,filters);
												
						  datasource = ticketService.findAllPAGINATOR(Integer.parseInt(anio),Integer.parseInt(mes),id_medico,first, pageSize, filters, "t.id_ticket", "DESC");
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
	
	/*public void imprimeReceta(){
			
		
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("p_nombre_medico", this.ticketSelected.getDes_medico());
		input.put("p_nombre_paciente", this.ticketSelected.getDes_paciente());		
		
			imprimirReceta(input,this.listaRecetas);			
		
	}*/
	
	public void imprimirHistoriaClinica(){
		System.out.println("IMPRIMIENDO HISTORIA CLINICA PDF");
		try {
		ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Map<String, Object> input =new  HashMap<String,Object>();
		
		List<Object> listaDetalleReporte= new ArrayList<>();
		input.put(JRParameter.REPORT_LOCALE, new Locale("es"));
//		input.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); // no parte la pagina todo lo mete en un A4
		input.put(JRParameter.IS_IGNORE_PAGINATION, false);
		
		
		String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptHistoriaClinica.jasper");
		System.out.println("PATH :"+path);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		byte[] bytes;
		
			bytes = ExportarArchivo.exportPdf(path, input, listaDetalleReporte);
			
			ExportarArchivo.executePdf(bytes, "HistoriaClinica.pdf");
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void imprimeReceta(){
		System.out.println("IMPRIMIENDO RECETA PDF");
	

		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			
			input.put("p_nombre_paciente", this.ticketSelected.getPaciente().getNombre());	
//			input.put("p_fecha_nacimiento_paciente", this.ticketSelected.getPaciente().getFecha_nacimiento()== null? "" : sdf.format(this.ticketSelected.getPaciente().getFecha_nacimiento()));	
//			input.put("p_tipo_sangre_paciente", this.ticketSelected.getPaciente().getTipo_sangre());
			
			input.put("p_nombre_medico",this.ticketSelected.getMedico().getNombre_medico() );
			input.put("p_especialidad_medico","MEDICO - CIRUJANO");
			input.put("p_emisor",this.emisorSelec.getRazon_social());
			input.put("p_direccion",this.emisorSelec.getDescripcion_domicilio_fiscal() );
			
			List<RecetaDetalleRep> listaDetalleReporte= new ArrayList<>();
			for(Receta receta: this.listaRecetas) {
				RecetaDetalleRep linea=new RecetaDetalleRep();
				linea.setCantidad(receta.getCantidad().toString());
				linea.setDosis(String.valueOf(receta.getId_receta()));
				linea.setDuracion(receta.getDuracion().toString());
				linea.setHoras(receta.getHoras().toString());
				linea.setMedicamento(receta.getMedicamento());
				listaDetalleReporte.add(linea);
			}
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));
//			input.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); // no parte la pagina todo lo mete en un A4
			input.put(JRParameter.IS_IGNORE_PAGINATION, false);
			
			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptHistoriaCli.jasper");
			System.out.println("PATH :"+path);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			byte[] bytes;
			bytes = ExportarArchivo.exportPdf(path, input, listaDetalleReporte);
			ExportarArchivo.executePdf(bytes, "Receta.pdf");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
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
						totalRow = ticketService.countByPaciente(ticketSelected.getPaciente().getId_paciente());
												
						  datasource = ticketService.findByPaciente(ticketSelected.getPaciente().getId_paciente(), first, pageSize, filters, "cm.id_consulta_medica", "DESC");
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
	
	public void obtenerAbreviatura() throws Exception{
		
		System.out.println("Estoy aqui");
		
		String sTexto = "";
		
		System.out.println(sTexto);
		
		String sValor = "";
		
		for (TablaTablasDetalle tablaTablasDetalle : listTablaTablasDetallesComprobante) {
			System.out.println("codigo_catalogo : "+tablaTablasDetalle.getCodigo_catalogo());
			System.out.println("tipo_comprobante "+this.comprobanteSelec.getTipo_comprobante());
			if(tablaTablasDetalle.getCodigo_catalogo().equals(this.comprobanteSelec.getTipo_comprobante())){
				sTexto = tablaTablasDetalle.getDescripcion_largo();
				sValor = tablaTablasDetalle.getCodigo_catalogo();
				this.tablaTablasDetalleTipoComprobante = tablaTablasDetalle;
			}
			
		}
		System.out.println("sTexto " + sTexto);
		System.out.println("sValor " + sValor);
		
		StringTokenizer stPalabras = new StringTokenizer(sTexto);
		
		
		
		
		String sPalabra = "";
		switch(sValor){
		case "01": sPalabra="F";
		break;
		case "03": sPalabra="B";
			break;
		case "07": sPalabra="F";
			break;
		case "08": sPalabra="F";
			break;
		
		}

//		while (stPalabras.hasMoreTokens()) {
//			  sPalabra = sPalabra + stPalabras.nextToken().substring(0,1);
//			  
//			  System.out.println(sPalabra);
//		}
//		
		this.domicilioFiscalSelec = this.domicilioFiscalService.findById(this.emisorSelec.getId_domicilio_fiscal_cab());
		
		int cantDom = 3;
		
		String dom="";
		
		for (int i = 0; i < (cantDom - this.domicilioFiscalSelec.getDomicilio().length()); i++) {
			
			dom = dom + "0";
			
		}
		
		dom = dom + this.domicilioFiscalSelec.getDomicilio();
		
		int cantComprobante = 8;
		
		String com = "";
		
		Integer valor = Integer.parseInt(sValor);
		valor = valor + 1;
		
//		String sValue = String.valueOf(valor);
		String sValue = String.valueOf(this.comprobanteService.getCorrelativoComprobante(this.comprobanteSelec.getTipo_comprobante()));
		System.out.println("sValue ===> "+sValue);
		for (int i = 0; i < (cantComprobante - sValue.length()); i++) {
			
			com = com + "0";
			
		}	
		
		com = com + sValue;
		
		this.comprobanteSelec.setNumero_serie_documento_cab(sPalabra+dom+"-"+com);
		
		//this.comprobanteSelec.setCliente(this.ticketSelected.getCliente());
		
		validarCampos(0, this.comprobanteSelec.getTipo_comprobante());
		
		validarCampos(1, this.comprobanteSelec.getNumero_serie_documento_cab());
		
	}
	
	public void prepararComprobante(Ticket ticket){
		this.comprobanteSelec= new Comprobante();
		this.ticketSelected=ticket;
		this.listaComprobanteDetalle= new ArrayList<>();
		this.comprobanteSelec.setFecha_emision_cab(new Date());
		this.comprobanteSelec.setHora_emision_cab(new Date());
		this.comprobanteSelec.setFecha_vencimiento_cab(new Date());
		ComprobanteDetalle comprobanteDetalle= new ComprobanteDetalle();
		try {
			Producto producto=this.ticketSelected.getProducto();
			
			if(this.ticketSelected.getPrecio_final_editado_cliente()!=null){
				producto.setPrecio_final_editado_cliente(this.ticketSelected.getPrecio_final_editado_cliente());
				producto.setValor_unitario_prod_det(this.ticketSelected.getPrecio_final_editado_cliente());
				this.comprobanteSelec.setTotal_precio_venta_cab(this.ticketSelected.getPrecio_final_editado_cliente());
				this.productoService.actualizarPrecioEditable(producto);
			}else{
				producto.setPrecio_final_editado_cliente(producto.getValor_unitario_prod_det());
				this.comprobanteSelec.setTotal_precio_venta_cab(producto.getValor_unitario_prod_det());
			}
			
			comprobanteDetalle.setId_producto(producto.getId_producto());
			comprobanteDetalle.setProducto(producto);
			comprobanteDetalle.setCant_unidades_item_det(new BigDecimal("1.00"));
			
			comprobanteDetalle.setPrecio_venta_unitario_det(producto.getValor_unitario_prod_det());
			comprobanteDetalle.setValor_venta_item_det(producto.getValor_unitario_prod_det().divide(new BigDecimal("1.18"), 2, RoundingMode.CEILING));
			comprobanteDetalle.setSuma_tributos_det(comprobanteDetalle.getPrecio_venta_unitario_det().subtract(comprobanteDetalle.getValor_venta_item_det()));
			
			comprobanteDetalle.setMontoIGV(comprobanteDetalle.getSuma_tributos_det());
			this.ingresarCliente = Boolean.FALSE;
			
			this.comprobanteSelec.setSuma_tributos_cab(comprobanteDetalle.getSuma_tributos_det());
			this.comprobanteSelec.setTotal_valor_venta_cab(comprobanteDetalle.getValor_venta_item_det());
			this.comprobanteSelec.setImporte_total_venta_cab(comprobanteDetalle.getPrecio_venta_unitario_det());
			
			this.comprobanteSelec.setTipo_operacion_cab("0101");
			this.comprobanteSelec.setId_modo_pago(4);
			this.comprobanteSelec.setTipo_moneda_cab("PEN");
			this.listaVendedores=this.vendedorService.findAll();
			if(!this.listaVendedores.isEmpty()){
				this.comprobanteSelec.setId_vendedor(this.listaVendedores.get(0).getId_vendedor());
			}
			
			
			this.listCampos = new ArrayList<Object>();
			
			
			this.listCampos.add(this.comprobanteSelec.getTipo_comprobante());
			this.listCampos.add(this.comprobanteSelec.getNumero_serie_documento_cab());
			this.listCampos.add(this.comprobanteSelec.getFecha_emision_cab());
			this.listCampos.add(this.comprobanteSelec.getId_modo_pago());
			this.listCampos.add(this.comprobanteSelec.getTipo_operacion_cab());
			this.listCampos.add(this.comprobanteSelec.getHora_emision_cab());
			this.listCampos.add(this.comprobanteSelec.getId_vendedor());
			this.listCampos.add(this.comprobanteSelec.getTipo_moneda_cab());
			this.listCampos.add(this.comprobanteSelec.getFecha_vencimiento_cab());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		this.listaComprobanteDetalle.add(comprobanteDetalle);
	}
	
	public void cambioPacienteCliente(){
		System.out.println("CP: "+this.cp);
		if(this.cp.equals("CLIENTE")){
			this.nombrePacienteCliente = this.ticketSelected.getCliente().getNombre_cab();
			//this.comprobanteSelec.setNumero_serie_documento_cab(this.ticketSelected.getCliente().getNumero_docu_iden_cab());
		}else{
			this.nombrePacienteCliente = this.ticketSelected.getPaciente().getNombre();
			//this.comprobanteSelec.setNumero_serie_documento_cab(this.ticketSelected.getPaciente().getNumero_documento());
		}
		
		//validarCampos(1, this.comprobanteSelec.getNumero_serie_documento_cab());
	}
	
	public void mostrarDatosCliente(){
		this.comprobanteSelec.setCliente(this.ticketSelected.getCliente());
		
		validarCampos(0, this.comprobanteSelec.getTipo_comprobante());
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
		this.consultaMedica=this.consultaMedicaService.findByTicket(this.ticketSelected.getId_ticket());
	}
	
	public void confirmaEliminarTicket(){
		try {
			if(!this.ticketSelected.isIntegrado_sunat()){
				if(this.ticketSelected.getId_examen()!=null){
					if(this.ticketSelected.getId_examen()>0){
						this.examenAuxiliarService.actualizarEstadoExamen(this.ticketSelected.getId_examen());
					}
					
				}
				
				this.ticketService.eliminarTicket(this.ticketSelected.getId_ticket());
				if(this.consultaMedica!=null){
					this.consultaMedicaService.eliminarConsultaMedica(this.consultaMedica.getId_consulta_medica());
					this.signoVitalService.eliminarSignosByConsulta(this.consultaMedica.getId_consulta_medica());
					this.recetaService.eliminarRecetasByConsulta(this.consultaMedica.getId_consulta_medica());
					this.examenAuxiliarService.eliminarExamenesByConsulta(this.consultaMedica.getId_consulta_medica());
					
				}
				
				log.setAccion("DELETE");
				log.setDescripcion("Se elimina al ticket: " + this.ticketSelected.getNro_ticket());
				logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Ticket ha sido eliminado", 3);
				
				//this.listTickets = this.ticketService.findAll();
			}else{
				FacesUtils.showFacesMessage("No es posible eliminar este ticket, ya se envio a sunat", 1);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void guardarConsultaMedica(){
		RequestContext context = RequestContext.getCurrentInstance();  
		this.consultaMedica.setListado_problemas(getProblemas(this.listaProblemas));		
		this.consultaMedicaService.crearConsultaMedica(this.consultaMedica);
		this.consultaMedica=this.consultaMedicaService.findByTicket(this.consultaMedica.getId_ticket());
		try {
			this.examenAuxiliarService.insertBatchExamenesAux(this.listaExamenAuxiliares,consultaMedica.getId_consulta_medica() );
			this.signoVital.setId_consulta_medica(this.consultaMedica.getId_consulta_medica());
			this.signoVitalService.crearSignoVital(this.signoVital);
			this.recetaService.insertBatchRecetas(this.listaRecetas, consultaMedica.getId_consulta_medica());
			this.control.setId_cliente(this.consultaMedica.getId_cliente());
			this.control.setId_especialidad(this.consultaMedica.getId_especialidad());
			this.control.setId_medico(this.consultaMedica.getId_medico());
			this.control.setId_paciente(this.consultaMedica.getId_paciente());
			this.control.setId_consulta_medica(this.consultaMedica.getId_consulta_medica());
			if(this.consultaMedica.getTipo_control().equalsIgnoreCase(Constante.TIPO_CONTROL)){
				Producto p=this.productoService.findByDescripcionControl(Constante.TIPO_CONTROL);
				this.control.setId_producto(p.getId_producto());
				this.control.setId_tipo_servicio(p.getId_tipo_servicio());
				this.controlService.crearControl(this.control);		
			}else{
				if(this.consultaMedica.getTipo_control().equalsIgnoreCase(Constante.TIPO_HOSPITALIZACION)){
					Producto p=this.productoService.findByDescripcionControl(Constante.TIPO_HOSPITALIZACION);
					this.control.setId_producto(p.getId_producto());
					this.control.setId_tipo_servicio(p.getId_tipo_servicio());
					this.controlService.crearControl(this.control);		
				}
			}
												   									  
			this.listaExamenAuxiliares=new ArrayList<>();
			this.listaRecetas= new ArrayList<>(); 
			this.listaProblemas= new ArrayList<>(); 
			
			FacesUtils.showFacesMessage("se ha generado la consulta m�dica", 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		context.update("msgGeneral");
	}
	
	public String getProblemas(List<String> lista){
		String problemas="";
		for(String cad:lista){
			problemas=problemas+cad+"-";
		}
		return problemas;
	}
	
	public void actualizarConsultaMedica(){
		
		this.consultaMedica.setListado_problemas(getProblemas(this.listaProblemas));	
		this.consultaMedicaService.actualizarConsultaMedica(this.consultaMedica);
		System.out.println(" listaExamenAuxiliares --->"+this.listaExamenAuxiliares.size());
		try {
			this.examenAuxiliarService.eliminarExamenesByConsulta(this.consultaMedica.getId_consulta_medica());
			this.examenAuxiliarService.insertBatchExamenesAux(this.listaExamenAuxiliares,consultaMedica.getId_consulta_medica() );	
			
			if(this.signoVitalService.findByConsulta(this.consultaMedica.getId_consulta_medica())==null){
				this.signoVital.setId_consulta_medica(this.consultaMedica.getId_consulta_medica());
				this.signoVitalService.crearSignoVital(this.signoVital);
			}else{
				this.signoVitalService.actualizarSignoVital(this.signoVital);
			}
			
			this.recetaService.eliminarRecetasByConsulta(this.consultaMedica.getId_consulta_medica());
			this.recetaService.insertBatchRecetas(this.listaRecetas,consultaMedica.getId_consulta_medica());
			
			this.listaExamenAuxiliares=new ArrayList<>();
			this.listaRecetas=new ArrayList<>();
			this.signoVital= new SignoVital();
			this.listaProblemas= new ArrayList<>(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void nuevoTicket(){
		
		Integer cifras=6;		
		Integer max = this.ticketService.obtenerMaxCF();
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
	
	public void cargarServiciosEX(){
		try {
			this.listProductos = this.productoService.findByIdTipoServicioEX(this.id_tipo_servicio_EX);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verControles(Integer id_ticket){
		try {
		this.listaControles = new ArrayList<Control>();
		
		ConsultaMedica cm = this.consultaMedicaService.findByTicket(id_ticket);
		
		
			this.listaControles = this.controlService.findByIdConsultaMedica(cm.getId_consulta_medica());
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
			this.ticketSelected.setEstado(Constante.TICKET_ESTADO_PENDIENTE);
			this.ticketSelected.setTipo_ticket("CF");
			Producto producto=this.productoService.findById(this.ticketSelected.getId_producto());
			if(producto!=null){					
				this.ticketSelected.setEncolado(producto.getGenera_cola());
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
			
			//this.listTickets = this.ticketService.findAll();
			context.update("msgGeneral");
			listarTicketFiltros();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		limpiarDatos();
		
	}
	
	public void cambiarTipoControl(){
		if(this.consultaMedica.getTipo_control().equals("CONTROL")){
			this.bControl = Boolean.TRUE;
			this.bHospitalizacion = Boolean.FALSE;
		}else if(this.consultaMedica.getTipo_control().equals("HOSPITALIZACION")){
			this.bHospitalizacion = Boolean.TRUE;
			this.bControl = Boolean.FALSE;
		}else{
			this.bHospitalizacion = Boolean.FALSE;
			this.bControl = Boolean.FALSE;
		}
	}
	
	/*public void imprimirReceta(Map<String, Object> input,List<Receta> listaRecetas){
		System.out.println(" imprimirCadenaBoleta ==>");
		PrintService service =null;
		try{
			service=PrintServiceLookup.lookupDefaultPrintService();
		}catch(Exception e ){
			System.out.println("Error ==========> no hay impresora "+e.toString());
		}
		System.out.println("Despues de configuracion impresa");
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		
		
		DocPrintJob pj = null;
		
		try{
			pj= service.createPrintJob();
		}catch(Exception e ){			
			System.out.println("  Error en service "+e.toString());
		}
		
		
		
		String ss=new String("Aqu� lo que vamos a imprimir.");
		
		String cadena="";
		cadena=cadena+"\t *** RECETA ***"+"\n";
		cadena=cadena+"\t\t MEDICO:"+ input.get("p_nombre_medico")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"\t\t PACIENTE: "+input.get("p_nombre_paciente")+"\n";
		
		cadena=cadena+"MEDICAMENTO	CANTIDAD	DOSIS	HORAS	DURACION"+"\n";
		cadena=cadena+"================================================"+"\n";
		
		for(Receta rec :this.listaRecetas){						
			cadena=cadena+rec.getMedicamento()+"	"+rec.getCantidad().toString()+"\n";					
			cadena=cadena+"   			";
			cadena=cadena+rec.getDosis() +"	"+rec.getHoras().toString()+"	"
			+rec.getDuracion().toString()+"\n";
		}

		cadena=cadena+"================================================"+"\n";
		
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		
		System.out.println(" cadena --------->"+cadena);
		byte[] bytes;
		bytes=cadena.getBytes();
		Doc doc=new SimpleDoc(bytes,flavor,null);
		try {
			pj.print(doc, null);
		}
		catch (PrintException e) {
		System.out.println("Error al imprimir: "+e.getMessage());
		}
		
		
	}*/
	
	public void nuevaConsultaMedica(Ticket ticket){
		
		this.ticketSelected = ticket;
		this.consultaMedica = this.consultaMedicaService.findByTicket(ticket.getId_ticket());
		this.editarExamenAuxiliar = Boolean.FALSE;
		this.editarProblemas = Boolean.FALSE;	
		this.editarReceta = Boolean.FALSE;
		this.editarSignoVital = Boolean.FALSE;
		
		if(this.consultaMedica==null){
			this.consultaMedica= new ConsultaMedica();
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
			this.listaProblemas = new ArrayList<String>();
			this.listaExamenAuxiliares = new ArrayList<ExamenAuxiliar>();
			this.listaRecetas = new ArrayList<Receta>();
			this.control = new Control();
			this.bControl = Boolean.FALSE;
			this.bHospitalizacion = Boolean.FALSE;						 

			this.editarConsulta=Boolean.FALSE;
			this.signoVital= new SignoVital();
		}else{
			this.listaExamenAuxiliares=this.examenAuxiliarService.findByConsulta(consultaMedica.getId_consulta_medica());
			this.editarConsulta=Boolean.TRUE;
			this.signoVital=this.signoVitalService.findByConsulta(this.consultaMedica.getId_consulta_medica());
			this.receta=new Receta();
			this.listaRecetas=this.recetaService.findByConsulta(consultaMedica.getId_consulta_medica());
			this.listaProblemas=getListadoProblemas(consultaMedica.getListado_problemas());
			
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
			this.listaExamenAuxiliaresHC=this.examenAuxiliarService.findByConsulta(consultaMedica.getId_consulta_medica());
			this.signoVitalHC=this.signoVitalService.findByConsulta(this.consultaMedica.getId_consulta_medica());
			this.listaRecetasHC=this.recetaService.findByConsulta(consultaMedica.getId_consulta_medica());
			this.listaProblemasHC=getListadoProblemas(consultaMedica.getListado_problemas());
			
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
	
	public void actualizarSignoVital(){
		
		if(this.signoVital==null){		
			this.signoVital = new SignoVital();		
		}
		
	}
	
	public void confirmarActualizarSignoVital(){

		this.editarSignoVital = Boolean.TRUE;		
		System.out.println("se almacenaron los signos vitales");
		this.signoVital.setId_cliente(this.ticketSelected.getId_cliente());
		this.signoVital.setId_especialidad(this.ticketSelected.getId_especialidad());
		this.signoVital.setId_medico(this.ticketSelected.getId_medico());
		this.signoVital.setId_paciente(this.ticketSelected.getId_paciente());
		this.signoVital.setId_producto(this.ticketSelected.getId_producto());
		this.signoVital.setId_tipo_servicio(this.ticketSelected.getId_tipo_servicio());
		
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
	
	public void cargaProductoEX(){
		this.examenAuxiliar.setId_producto(this.id_producto_EX);
		this.examenAuxiliar.setId_tipo_servicio(id_tipo_servicio_EX);
		this.examenAuxiliar.setId_paciente(this.consultaMedica.getId_paciente());
		this.examenAuxiliar.setId_cliente(this.consultaMedica.getId_cliente());
	}
	
	public void confirmarAgregarExamenAuxiliar(){
		
		try {
			System.out.println("Servicio "+this.examenAuxiliar.getId_producto());
			Producto p=this.productoService.findById(this.examenAuxiliar.getId_producto());
			this.examenAuxiliar.setProducto(p);
			this.examenAuxiliar.setDesServicio(p.getDescripcion_prod_det());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listaExamenAuxiliares.add(this.examenAuxiliar);
		
		this.editarExamenAuxiliar = Boolean.TRUE;
		
		this.examenAuxiliar = new ExamenAuxiliar();
		this.id_producto_EX=null;
		this.id_tipo_servicio_EX=null;
		
	}
	
	public void confirmarAgregarReceta(){
		
		this.receta.setId_cliente(this.ticketSelected.getId_cliente());
		this.receta.setId_especialidad(this.ticketSelected.getId_especialidad());
		this.receta.setId_medico(this.ticketSelected.getId_medico());
		this.receta.setId_paciente(this.ticketSelected.getId_paciente());
		this.receta.setId_producto(this.ticketSelected.getId_producto());
		this.receta.setId_tipo_servicio(this.ticketSelected.getId_tipo_servicio());
		
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
	
	public void validarCampos(int indice, Object obj){
		
		int resultado = 1;
		
		System.out.println("INDICE : "+indice);
		
		System.out.println("OBJETO : "+obj.toString());
		
		this.listCampos.set(indice, obj);
		
		int nro = 0;
		
		for (Object object : listCampos) {
			
			
			
			if(!(object == null)){
				System.out.println("Objeto " + nro + " : " + object.toString());
				resultado = resultado*1;
			}else{
				System.out.println("Objeto " + nro + " : " + " es nulo ");
				resultado = resultado*0;
			}
			
			nro++;
		}
		
		if(resultado==1){
			this.generarComprobante = Boolean.FALSE;
		}
		
		System.out.println("VALOR DEL RESULTADO : "+resultado);
		System.out.println("VALOR DEL BOOLEAN : "+this.generarComprobante);
		
	}
	
	public void generarComprobante(){
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			
			
			if(this.cp.equals("CLIENTE")){
				this.nombrePacienteCliente = this.ticketSelected.getCliente().getNombre_cab();					
					this.comprobanteSelec.setId_cliente(this.ticketSelected.getCliente().getId_cliente());
					this.comprobanteSelec.setTipo_docu_iden_cab(this.ticketSelected.getCliente().getTipo_docu_iden_cab());
			}else{
				this.nombrePacienteCliente = this.ticketSelected.getPaciente().getNombre();
				Cliente cl=this.clienteService.findByFiltroNroDni(this.ticketSelected.getPaciente().getNumero_documento());
				this.comprobanteSelec.setId_cliente(cl.getId_cliente());
				this.comprobanteSelec.setTipo_docu_iden_cab(cl.getTipo_docu_iden_cab());
			}
			
			System.out.println("  id_comprobante :"+this.comprobanteSelec.getId_comprobante());
			this.comprobanteSelec.setId_domicilio_fiscal_cab(this.domicilioFiscalSelec.getId_domicilio_fiscal_cab());
			this.comprobanteSelec.setId_emisor(this.emisorSelec.getId_emisor());
			this.comprobanteSelec.setVersion_ubl(Constante.VERSION_UBL_SUNAT);
			this.comprobanteSelec.setEstado_comunicacion_baja(Boolean.FALSE);
			this.comprobanteSelec.setCorrelativo(this.comprobanteService.getCorrelativoComprobante(this.comprobanteSelec.getTipo_comprobante()));
			this.comprobanteSelec.setEstado_sunat(Constante.ESTADO_PENDIENTE);
			this.comprobanteSelec.setId_ticket(this.ticketSelected.getId_ticket());
			this.comprobanteService.crearComprobanteSec(this.comprobanteSelec);	
			
			
			int id = this.comprobanteService.getSecIdComprobante();			
			System.out.println("ID: "+id);			
			this.comprobanteDetalleService.insertBatchComprobanteDetalle(this.listaComprobanteDetalle, id-1, this.comprobanteSelec);
			imprimirComprobante();
			
			context.update("msgGeneral");
			context.update("formAction");
			FacesUtils.showFacesMessage("Se registro el comprobante " + this.comprobanteSelec.getNumero_serie_documento_cab(), 3);
			
			this.ticketSelected.setEstado(Constante.TICKET_ESTADO_FACTURADO);
			this.ticketService.actualizarTicket(this.ticketSelected);
			
			
			this.comprobanteSelec= new Comprobante();
			this.listaComprobanteDetalle= new ArrayList<ComprobanteDetalle>();
			listarTicketFiltros();
			this.ticketSelected=new Ticket();
			this.nombrePacienteCliente="";
			this.cp="";
			this.paciente=new Paciente();
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listModoPagos = this.modoPagoService.findAll();
			this.listaVendedores = this.vendedorService.findAll(); /*Vega.com*/
			
			listarTicketFiltros();
			inicia();
			
			/*this.comprobanteSelec.setFecha_emision_cab(new Date()); 
			this.comprobanteSelec.setFecha_vencimiento_cab(new Date());
			this.comprobanteSelec.setHora_emision_cab(new Date());
			this.comprobanteSelec.setId_modo_pago(4); 
			this.comprobanteSelec.setTipo_operacion_cab("0101");
			this.comprobanteSelec.setTipo_moneda_cab("PEN"); */
			this.ingresarCliente = Boolean.TRUE;
			this.generarComprobante = Boolean.TRUE;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void imprimirComprobante(){
		try {
			String nombreCompleto="";
			String dni;
	
			String desProducto="";			
			if(this.comprobanteSelec!=null){
				nombreCompleto = this.ticketSelected.getCliente().getNombre_cab();
			}else{
				System.out.println("=======> NULO");
				
			}

			
			BigDecimal igv = new BigDecimal("0.00");
			BigDecimal dg = new BigDecimal("0.00");
			
			for (ComprobanteDetalle cd : this.listaComprobanteDetalle) {				
				igv = igv.add(cd.getMontoIGV());								
			}
			
			Map<String, Object> input = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Format formatoHora = new SimpleDateFormat("HH:mm:ss");
			input.put("p_fec_vencimiento", sdf.format(this.comprobanteSelec.getFecha_vencimiento_cab()));
			input.put("p_hora_emision", formatoHora.format(this.comprobanteSelec.getHora_emision_cab()));
			input.put("p_fec_emision", sdf.format(this.comprobanteSelec.getFecha_emision_cab()));
			input.put("p_cliente", nombreCompleto);
			input.put("p_ruc_cliente", this.ticketSelected.getCliente().getNumero_docu_iden_cab());
			input.put("p_ruc", this.emisorService.findAll().get(0).getRuc());
			//input.put("P_MONTO", Utils.customFormat("###,###.###",cuota.getMonto().doubleValue()));
			input.put("p_direccion", this.ticketSelected.getCliente().getDireccion());
			input.put("p_tipo_moneda", this.comprobanteSelec.getTipo_moneda_cab());
			input.put("p_tipo_documento", this.comprobanteSelec.getDescripcion_tipo_comprobante());
			input.put("p_numero_documento", this.comprobanteSelec.getNumero_serie_documento_cab());
			input.put("p_op_gravadas", this.comprobanteSelec.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
//			input.put("p_op_gravadas", "0");
			input.put("p_igv", igv.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());			
			input.put("p_descuento_global", dg.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_total_valor_venta", this.comprobanteSelec.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_descuentos_total", dg.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_importe_total", this.comprobanteSelec.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			input.put("des_unidad_medida", "PEN");	
			input.put("p_emisor_razonsocial", this.emisorSelec.getRazon_social());
			input.put("p_direccion_emisor", this.emisorSelec.getDescripcion_domicilio_fiscal());
			/*input.put("P_NOMBRE_EJECUTIVO", expediente.getNombre_ejecutivo());	
			input.put("P_TELEFONO", expediente.getUsu_telf());			
			input.put("P_EMAIL", expediente.getUsu_mail());	*/
						
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));
			
			List<DetalleComprobanteRep> listaDetalleReporte= new ArrayList<>();
			DetalleComprobanteRep linea;
			for(ComprobanteDetalle det: this.listaComprobanteDetalle){
				linea= new DetalleComprobanteRep();
				linea.setCant_unidades_item_det(det.getCant_unidades_item_det().toString());
				linea.setCodigo_producto(det.getProducto().getCod_prod_det());
				linea.setDes_producto(det.getProducto().getDescripcion_prod_det());
				linea.setDes_unidad_medida(det.getProducto().getDesUnidadMedida());
				linea.setDescuento("0");
				linea.setPrecio_unitario("0");
				linea.setPrecio_venta_unitario_det(det.getPrecio_venta_unitario_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());/*revisar campo*/
				linea.setValor_item(det.getValor_venta_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				linea.setValor_unitario(det.getProducto().getPrecio_final_editado_cliente().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				
				
				listaDetalleReporte.add(linea);
			}
		
			if(this.comprobanteSelec.getTipo_comprobante().equalsIgnoreCase("01")){
				//imprimirFactura(input,listaDetalleReporte);
				imprimirFacturaTexto(input,listaDetalleReporte);
			}else{
				if(this.comprobanteSelec.getTipo_comprobante().equalsIgnoreCase("03")){
					//imprimirCadenaBoleta(input,listaDetalleReporte);
					imprimirBoletaTexto(input,listaDetalleReporte);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void imprimirBoletaTexto(Map<String, Object> input,List<DetalleComprobanteRep> listaDetalleReporte){
		System.out.println(" imprimirCadenaBoleta ==>");
		PrintService service =null;
		try{
			service=PrintServiceLookup.lookupDefaultPrintService();
		}catch(Exception e ){
			System.out.println("Error ==========> no hay impresora "+e.toString());
		}
		System.out.println("Despues de configuracion impresa");
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		
		
		DocPrintJob pj = null;
		
		try{
			pj= service.createPrintJob();
		}catch(Exception e ){			
			System.out.println("  Error en service "+e.toString());
		}
		
		
		
		String ss=new String("Aqu� lo que vamos a imprimir.");
		
		String cadena="";
		cadena=cadena+"\t *** BOLETA DE VENTA ELECTRONICA ***"+"\n";
		cadena=cadena+"\t\t "+ input.get("p_numero_documento")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"* * * "+input.get("p_emisor_razonsocial")+"  * * * "+"\n";
		cadena=cadena+"      "+input.get("p_direccion_emisor")+"\n";
		cadena=cadena+"\t\t RUC: "+input.get("p_ruc")+"\n";
		cadena=cadena+"FECHA EMISION: "+input.get("p_fec_emision")+" HORA:"+input.get("p_hora_emision")+"\n";
		cadena=cadena+"\n";
		
		cadena=cadena+"CODIGO	PRODUCTO	P.U.	CANT	TOTAL"+"\n";
		cadena=cadena+"================================================"+"\n";
		
		for(DetalleComprobanteRep det :listaDetalleReporte){						
			cadena=cadena+det.getCodigo_producto()+"	"+det.getDes_producto()+"\n";					
			cadena=cadena+"   			";
			cadena=cadena+det.getValor_unitario() +"	"+det.getCant_unidades_item_det()+"	"
			+det.getPrecio_venta_unitario_det()+"\n";
		}

		cadena=cadena+"================================================"+"\n";
		cadena=cadena+"OP. GRAVADAS\t\t\t"+"S/.	"+input.get("p_op_gravadas")+"\n";
		cadena=cadena+"OP. GRATUITAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"OP. EXONERADAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"OP. INAFECTAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"TOTAL DEC. GLOBAL\t\t"+"S/.	"+"\n";
		cadena=cadena+"I.G.V.\t\t\t\t"+"S/.	"+input.get("p_igv")+"\n";	
		cadena=cadena+"* * * TOTAL VENTA :\t\t"+"S/.	"+input.get("p_importe_total")+"\n";
		cadena=cadena+"SON: "+Utils.cantidadConLetra(input.get("p_importe_total").toString())+"\n";	
		cadena=cadena+"CAJA : 01"+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"D.N.I. :"+input.get("p_ruc_cliente")+ "\n";
		cadena=cadena+"NOMBRE :"+input.get("p_cliente")+ "\n";
		cadena=cadena+"Autorizado mediante resolucion	"+"\n";
		cadena=cadena+"Anexo III - R.S. 155-2017	"+"\n";
		cadena=cadena+"Representacion impresa de la "+"\n";
		cadena=cadena+"Boleta Electronica"+"\n";
		cadena=cadena+"Consulte utilizando su clave SOL en "+"\n";
		cadena=cadena+"el portal SUNAT"+"\n";
		cadena=cadena+"Cualquier duda o consulta escribanos a:"+"\n";
		cadena=cadena+"soporte@quentinconsulting.com"+"\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		
		System.out.println(" cadena --------->"+cadena);
		byte[] bytes;
		bytes=cadena.getBytes();
		Doc doc=new SimpleDoc(bytes,flavor,null);
		try {
			pj.print(doc, null);
		}
		catch (PrintException e) {
		System.out.println("Error al imprimir: "+e.getMessage());
		}
		
		
	}
	
	public void imprimirFacturaTexto(Map<String, Object> input,List<DetalleComprobanteRep> listaDetalleReporte){
		
		System.out.println("imprimirFactura ========> ");
		PrintService service = null;
		
		try{
			service=PrintServiceLookup.lookupDefaultPrintService();
		}catch(Exception e ){
			System.out.println("Error ==========> no hay impresora "+e.toString());
		}
		
		System.out.println("Despues de configuracion impresa");
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		
		
		DocPrintJob pj = null;
		
		try{
			pj= service.createPrintJob();
		}catch(Exception e ){			
			System.out.println("  Error en service "+e.toString());
		}
		
		
		String ss=new String("Aqu� lo que vamos a imprimir.");
		
		String cadena="";
		cadena=cadena+"\t *** FACTURA ELECTRONICA ***"+"\n";
		cadena=cadena+"\t\t"+ input.get("p_numero_documento")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"\t * * * "+input.get("p_emisor_razonsocial")+" * * *"+"\n";
		cadena=cadena+"\t"+input.get("p_direccion_emisor")+"\n";
		cadena=cadena+"\t\t RUC: "+input.get("p_ruc")+"\n";
		cadena=cadena+"FECHA EMISION: "+input.get("p_fec_emision")+" HORA:"+input.get("p_hora_emision")+"\n";
		cadena=cadena+"\n";
		
		cadena=cadena+"CODIGO	PRODUCTO	P.U.	CANT	TOTAL"+"\n";
		cadena=cadena+"================================================"+"\n";
		
		for(DetalleComprobanteRep det :listaDetalleReporte){						
			cadena=cadena+det.getCodigo_producto()+"	"+det.getDes_producto()+"\n";					
			cadena=cadena+"   			";
			cadena=cadena+det.getValor_unitario() +"	"+det.getCant_unidades_item_det()+"	"
			+det.getPrecio_venta_unitario_det()+"\n";
		}

		cadena=cadena+"================================================"+"\n";
		cadena=cadena+"OP. GRAVADAS\t\t\t"+"S/.	"+input.get("p_op_gravadas")+"\n";
		cadena=cadena+"OP. GRATUITAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"OP. EXONERADAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"OP. INAFECTAS\t\t\t"+"S/. "+"\n";
		cadena=cadena+"TOTAL DEC. GLOBAL\t\t"+"S/.	"+"\n";
		cadena=cadena+"I.G.V.\t\t\t\t"+"S/.	"+input.get("p_igv")+"\n";	
		cadena=cadena+"* * * TOTAL VENTA :\t\t"+"S/.	"+input.get("p_importe_total")+"\n";
		cadena=cadena+"SON: "+Utils.cantidadConLetra(input.get("p_importe_total").toString())+"\n";		
		cadena=cadena+"CAJA : 01"+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"R.U.C. :"+input.get("p_ruc_cliente")+ "\n";
		cadena=cadena+"NOMBRE :"+input.get("p_cliente")+ "\n";
		cadena=cadena+"DIRECCION :"+input.get("p_direccion")+ "\n";
		
		cadena=cadena+"Autorizado mediante resolucion	"+"\n";
		cadena=cadena+"Anexo III - R.S. 155-2017	"+"\n";
		cadena=cadena+"Representacion impresa de la "+"\n";
		cadena=cadena+"Factura Electronica"+"\n";
		cadena=cadena+"Consulte utilizando su clave SOL en "+"\n";
		cadena=cadena+"el portal SUNAT"+"\n";
		cadena=cadena+"Cualquier duda o consulta escribanos a:"+"\n";
		cadena=cadena+"soporte@quentinconsulting.com"+"\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";
		cadena=cadena+"\n\n";

		
		System.out.println(" cadena --------->"+cadena);
		byte[] bytes;
		bytes=cadena.getBytes();
		Doc doc=new SimpleDoc(bytes,flavor,null);
		try {
			pj.print(doc, null);
		}
		catch (PrintException e) {
		System.out.println("Error al imprimir: "+e.getMessage());
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

	/*public List<Ticket> getListTickets() {
		return listTickets;
	}

	public void setListTickets(List<Ticket> listTickets) {
		this.listTickets = listTickets;
	}*/

	public String getCod_perfil() {
		return cod_perfil;
	}

	public LazyDataModel<Ticket> getListTickets() {
		return listTickets;
	}

	public void setListTickets(LazyDataModel<Ticket> listTickets) {
		this.listTickets = listTickets;
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

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public TipoServicioService getTipoServicioService() {
		return tipoServicioService;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
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

	public SignoVitalService getSignoVitalService() {
		return signoVitalService;
	}

	public void setSignoVitalService(SignoVitalService signoVitalService) {
		this.signoVitalService = signoVitalService;
	}

	public ConsultaMedicaService getConsultaMedicaService() {
		return consultaMedicaService;
	}

	public void setConsultaMedicaService(ConsultaMedicaService consultaMedicaService) {
		this.consultaMedicaService = consultaMedicaService;
	}

	public Integer getId_tipo_servicio_EX() {
		return id_tipo_servicio_EX;
	}

	public void setId_tipo_servicio_EX(Integer id_tipo_servicio_EX) {
		this.id_tipo_servicio_EX = id_tipo_servicio_EX;
	}

	public Integer getId_producto_EX() {
		return id_producto_EX;
	}

	public void setId_producto_EX(Integer id_producto_EX) {
		this.id_producto_EX = id_producto_EX;
	}

	public ExamenAuxiliarService getExamenAuxiliarService() {
		return examenAuxiliarService;
	}

	public void setExamenAuxiliarService(ExamenAuxiliarService examenAuxiliarService) {
		this.examenAuxiliarService = examenAuxiliarService;
	}

	public Boolean getEditarConsulta() {
		return editarConsulta;
	}

	public void setEditarConsulta(Boolean editarConsulta) {
		this.editarConsulta = editarConsulta;
	}

	public RecetaService getRecetaService() {
		return recetaService;
	}

	public void setRecetaService(RecetaService recetaService) {
		this.recetaService = recetaService;
	}

	public Comprobante getComprobanteSelec() {
		return comprobanteSelec;
	}

	public void setComprobanteSelec(Comprobante comprobanteSelec) {
		this.comprobanteSelec = comprobanteSelec;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesComprobante() {
		return listTablaTablasDetallesComprobante;
	}

	public void setListTablaTablasDetallesComprobante(List<TablaTablasDetalle> listTablaTablasDetallesComprobante) {
		this.listTablaTablasDetallesComprobante = listTablaTablasDetallesComprobante;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}

	public boolean isIngresarCliente() {
		return ingresarCliente;
	}

	public void setIngresarCliente(boolean ingresarCliente) {
		this.ingresarCliente = ingresarCliente;
	}

	public List<ModoPago> getListModoPagos() {
		return listModoPagos;
	}

	public void setListModoPagos(List<ModoPago> listModoPagos) {
		this.listModoPagos = listModoPagos;
	}

	public ModoPagoService getModoPagoService() {
		return modoPagoService;
	}

	public void setModoPagoService(ModoPagoService modoPagoService) {
		this.modoPagoService = modoPagoService;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesOperacion() {
		return listTablaTablasDetallesOperacion;
	}

	public void setListTablaTablasDetallesOperacion(List<TablaTablasDetalle> listTablaTablasDetallesOperacion) {
		this.listTablaTablasDetallesOperacion = listTablaTablasDetallesOperacion;
	}

	public List<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(List<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public VendedorService getVendedorService() {
		return vendedorService;
	}

	public void setVendedorService(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesMoneda() {
		return listTablaTablasDetallesMoneda;
	}

	public void setListTablaTablasDetallesMoneda(List<TablaTablasDetalle> listTablaTablasDetallesMoneda) {
		this.listTablaTablasDetallesMoneda = listTablaTablasDetallesMoneda;
	}

	public List<ComprobanteDetalle> getListaComprobanteDetalle() {
		return listaComprobanteDetalle;
	}

	public void setListaComprobanteDetalle(List<ComprobanteDetalle> listaComprobanteDetalle) {
		this.listaComprobanteDetalle = listaComprobanteDetalle;
	}

	public ComprobanteService getComprobanteService() {
		return comprobanteService;
	}

	public void setComprobanteService(ComprobanteService comprobanteService) {
		this.comprobanteService = comprobanteService;
	}

	public ComprobanteDetalleService getComprobanteDetalleService() {
		return comprobanteDetalleService;
	}

	public void setComprobanteDetalleService(ComprobanteDetalleService comprobanteDetalleService) {
		this.comprobanteDetalleService = comprobanteDetalleService;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesProducto() {
		return listTablaTablasDetallesProducto;
	}

	public void setListTablaTablasDetallesProducto(List<TablaTablasDetalle> listTablaTablasDetallesProducto) {
		this.listTablaTablasDetallesProducto = listTablaTablasDetallesProducto;
	}

	public Emisor getEmisorSelec() {
		return emisorSelec;
	}

	public void setEmisorSelec(Emisor emisorSelec) {
		this.emisorSelec = emisorSelec;
	}

	public EmisorService getEmisorService() {
		return emisorService;
	}

	public void setEmisorService(EmisorService emisorService) {
		this.emisorService = emisorService;
	}

	public boolean isGenerarComprobante() {
		return generarComprobante;
	}

	public void setGenerarComprobante(boolean generarComprobante) {
		this.generarComprobante = generarComprobante;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getNombrePacienteCliente() {
		return nombrePacienteCliente;
	}

	public void setNombrePacienteCliente(String nombrePacienteCliente) {
		this.nombrePacienteCliente = nombrePacienteCliente;
	}

	public Boolean getDisableBuscar() {
		return disableBuscar;
	}

	public void setDisableBuscar(Boolean disableBuscar) {
		this.disableBuscar = disableBuscar;
	}

	public Boolean getDisableRespuesta() {
		return disableRespuesta;
	}

	public void setDisableRespuesta(Boolean disableRespuesta) {
		this.disableRespuesta = disableRespuesta;
	}

	public List<Ticket> getListFiltroTickets() {
		return listFiltroTickets;
	}

	public void setListFiltroTickets(List<Ticket> listFiltroTickets) {
		this.listFiltroTickets = listFiltroTickets;
	}

	public String getNumeroDocPacienteCliente() {
		return numeroDocPacienteCliente;
	}

	public void setNumeroDocPacienteCliente(String numeroDocPacienteCliente) {
		this.numeroDocPacienteCliente = numeroDocPacienteCliente;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<Object> getListCampos() {
		return listCampos;
	}

	public void setListCampos(List<Object> listCampos) {
		this.listCampos = listCampos;
	}

	public DomicilioFiscal getDomicilioFiscalSelec() {
		return domicilioFiscalSelec;
	}

	public void setDomicilioFiscalSelec(DomicilioFiscal domicilioFiscalSelec) {
		this.domicilioFiscalSelec = domicilioFiscalSelec;
	}

	public TablaTablasDetalle getTablaTablasDetalleTipoComprobante() {
		return tablaTablasDetalleTipoComprobante;
	}

	public void setTablaTablasDetalleTipoComprobante(
			TablaTablasDetalle tablaTablasDetalleTipoComprobante) {
		this.tablaTablasDetalleTipoComprobante = tablaTablasDetalleTipoComprobante;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getId_medico() {
		return id_medico;
	}

	public void setId_medico(Integer id_medico) {
		this.id_medico = id_medico;
	}	
	
	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public Boolean getbControl() {
		return bControl;
	}

	public void setbControl(Boolean bControl) {
		this.bControl = bControl;
	}

	public Boolean getbHospitalizacion() {
		return bHospitalizacion;
	}

	public void setbHospitalizacion(Boolean bHospitalizacion) {
		this.bHospitalizacion = bHospitalizacion;
	}

	public List<Control> getListaControles() {
		return listaControles;
	}

	public void setListaControles(List<Control> listaControles) {
		this.listaControles = listaControles;
	}

	public List<ConsultaMedica> getListFiltroConsultasMedicas() {
		return listFiltroConsultasMedicas;
	}

	public void setListFiltroConsultasMedicas(
			List<ConsultaMedica> listFiltroConsultasMedicas) {
		this.listFiltroConsultasMedicas = listFiltroConsultasMedicas;
	}

	public LazyDataModel<Ticket> getListTicketsPaciente() {
		return listTicketsPaciente;
	}

	public void setListTicketsPaciente(LazyDataModel<Ticket> listTicketsPaciente) {
		this.listTicketsPaciente = listTicketsPaciente;
	}

	public Boolean getbDetalleConsulta() {
		return bDetalleConsulta;
	}

	public void setbDetalleConsulta(Boolean bDetalleConsulta) {
		this.bDetalleConsulta = bDetalleConsulta;
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
