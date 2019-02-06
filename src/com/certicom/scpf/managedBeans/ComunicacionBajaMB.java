package com.certicom.scpf.managedBeans;

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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.ArchivoPlano;
import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.ComunicacionBaja;
import com.certicom.scpf.domain.DetalleComprobanteRep;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.RespuestaSunat;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.TributoProducto;
import com.certicom.scpf.jdbc.dao.Conexion;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.ComprobanteDetalleService;
import com.certicom.scpf.services.ComprobanteService;
import com.certicom.scpf.services.ComunicacionBajaService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TributoProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.ExportarArchivo;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

import net.sf.jasperreports.engine.JRParameter;
import src.com.certicom.scpf.utils.GeneraArchivoPlano;
import src.com.certicom.scpf.utils.LeerFileXML;
import src.com.certicom.scpf.utils.LeerZip;
import src.com.certicom.scpf.utils.Utils;

@ManagedBean(name="comunicacionBajaMB")
@ViewScoped
public class ComunicacionBajaMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comprobante comprobanteSelec;
	private ComprobanteDetalle comprobanteDetalleSelec;
	private List<Comprobante> listaClientes;
	private Cliente clienteEncontrado;
	private Producto productoEncontrado;
	private Producto productoSelec;
	private TributoProducto tributoProducto;
	private List<TributoProducto> listTributoProductos;
	private String campo_busqueda;
	private Boolean editarCliente;
	private MenuServices menuServices;
	private ClienteService clienteService;
	private ProductoService productoService;
	private ComprobanteService comprobanteService;
	private TributoProductoService tributoProductoService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private Emisor emisorSelec; /*Jesus*/
	private EmisorService emisorService;  /*Jesus*/
	
	
	
	private LazyDataModel<Comprobante> listaComprobante;
	private List<Comprobante> listaFiltroComprobante;
	private ComunicacionBaja comunicacionBajaSelec;
	private ComunicacionBajaService comunicacionBajaService;
	//datos Log
    private Log log;
	private LogMB logmb;
	
	Integer id_modo_pago=4;
	private String anio;
	private String mes;
	private Integer id_cliente;
	private String tipo_comprobante;
	
	private Boolean disableRespuesta; /*Jesus*/
	private Boolean disableBuscar;  /*Jesus*/
	
	
	private List<ComprobanteDetalle> listaComprobanteDetalle;
	private ComprobanteDetalleService comprobanteDetalleService;
	
	
	public ComunicacionBajaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		this.editarCliente = Boolean.FALSE;
		this.comprobanteSelec = new Comprobante();
		this.clienteEncontrado = new Cliente();
		this.clienteService = new ClienteService();
		this.productoService = new ProductoService();
		this.tributoProductoService = new TributoProductoService();
		this.productoEncontrado = new Producto();
		this.comprobanteDetalleSelec = new ComprobanteDetalle();
		this.tributoProducto = new TributoProducto();
		this.emisorService = new EmisorService(); /*Jesus*/
		
		this.comprobanteService= new ComprobanteService();
		this.comunicacionBajaService= new ComunicacionBajaService();
		
		this.comprobanteDetalleService= new ComprobanteDetalleService();

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		Format formatoAnio = new SimpleDateFormat("yyyy");
		Format formatoMes= new SimpleDateFormat("MM");
		
		
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:comunicacionBaja");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
			//this.listaComprobante=(LazyDataModel<Comprobante>) this.comprobanteService.listaComprobantes();			
			this.anio=formatoAnio.format(new Date());
			this.mes=formatoMes.format(new Date());
			this.emisorSelec = this.emisorService.findAll().get(0); /*Jesus*/
			this.disableBuscar = Boolean.TRUE; 
			this.disableRespuesta = Boolean.TRUE; 
//			this.tipo_comprobante=listTablaTablasDetallesComprobante.get(0).getCodigo_catalogo();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*Jesus*/
	public void onItemDocumento(SelectEvent event)  throws Exception{
					
					
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.TRUE; 
		
	}
	
	public void listarComprobantesFiltros(){
		System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.FALSE; 		
		
		this.listaComprobante = new LazyDataModel<Comprobante>() {
			private static final long serialVersionUID = 1L;
			private List<Comprobante> datasource; 
			private Integer totalRow=0;
			
			
			@Override  
			public Comprobante getRowData(String rowKey){
				 for(Comprobante e : datasource) {  
                     if(e.getId_comprobante().equals(rowKey))  
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
	            public Object getRowKey(Comprobante e) {  
	                return e.getId_comprobante();  
	            }  
			 @Override  
	            public List<Comprobante> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {              
					try {
						totalRow = comprobanteService.countCompByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, filters);
												
						  datasource = comprobanteService.listComprobantesByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, first, pageSize, filters, "e.numero_serie_documento_cab", "DESC");
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

	public void prepararComunicacionBaja(Comprobante comprobante){		
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
		if(!comprobante.getEstado_comunicacion_baja()){
			System.out.println("Cliente : "+comprobante.getCliente().getNombre_cab());
			System.out.println("Cliente : "+comprobante.getDescripcion_tipo_comprobante());
			this.comprobanteSelec=comprobante;		
			this.comunicacionBajaSelec= new ComunicacionBaja();
			this.comunicacionBajaSelec.setFecha_emision_cab(this.comprobanteSelec.getFecha_emision_cab());
			this.comunicacionBajaSelec.setNumero_serie_documento_cab(this.comprobanteSelec.getNumero_serie_documento_cab());
			this.comunicacionBajaSelec.setId_comprobante(this.comprobanteSelec.getId_comprobante());
			this.comunicacionBajaSelec.setId_cliente(this.comprobanteSelec.getId_cliente());
			this.comunicacionBajaSelec.setTipo_comprobante(this.comprobanteSelec.getTipo_comprobante());
			this.comunicacionBajaSelec.setId_emisor(this.comprobanteSelec.getId_emisor());
			this.comunicacionBajaSelec.setId_domicilio_fiscal_cab(this.comprobanteSelec.getId_domicilio_fiscal_cab());
			this.comunicacionBajaSelec.setId_modo_pago(this.id_modo_pago);
		}else{
			valido=Boolean.FALSE;
			context.update("msgGeneral");
			context.addCallbackParam("esValido", valido);
			context.execute("PF('dlgNuevo').hide();");
			FacesUtils.showFacesMessage("Este comprobante ya fue dado de baja", 2);
			try {
				listarComprobantesFiltros();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.comprobanteSelec= new Comprobante();
			context.update("msgGeneral");	
		}
		
	}
	
	public void guardarComunicacionBaja(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		try { 
			System.out.println("id_comprobante:"+comunicacionBajaSelec.getId_comprobante());
			System.out.println("modo pago:"+this.comunicacionBajaSelec.getId_modo_pago());
			this.comunicacionBajaService.crearComunicacionBaja(comunicacionBajaSelec);
			this.comprobanteSelec.setEstado_comunicacion_baja(Boolean.TRUE);
			this.comprobanteService.actualizarEstadoBaja(comprobanteSelec);
			
			FacesUtils.showFacesMessage("Se dio de alta al comprobante", 3);			
//			this.listaComprobante=this.comprobanteService.listaComprobantes();
			listarComprobantesFiltros();
			this.comprobanteSelec= new Comprobante();
			context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Cliente> consultarCliente(String query) throws Exception {
		System.out.println("entrando autocomplete");
        List<Cliente> allClients = this.clienteService.findAll();
        List<Cliente> filteredClients = new ArrayList<Cliente>();
         
        for (int i = 0; i < allClients.size(); i++) {
        	Cliente skin = allClients.get(i);
            if(skin.getNombre_cab().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredClients.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredClients.size());
         
        return filteredClients;
    }
	
	public List<Producto> consultarProducto(String query) throws Exception{
		
		System.out.println("entrando autocomplete");
        List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
         
        for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getDescripcion_prod_det().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProducts.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredProducts.size());
		
		return filteredProducts;
		
	}
	
	public void onItemSelect(SelectEvent event)  throws Exception{
		
		String s = event.getObject().toString();
		
		
		
		List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
		
		for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getDescripcion_prod_det().equals(s)) {
            	//filteredProducts.add(skin);
            	this.productoSelec = skin;
            	
            	break;
            	
            }
        }
		
		this.comprobanteDetalleSelec.setId_producto(this.productoSelec.getId_producto());
		this.comprobanteDetalleSelec.setProducto(this.productoSelec);
		
		
		this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
		
		for (TributoProducto tp : this.listTributoProductos) {
			
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteDetalleSelec.setTpOtros(tp);
			}
		}
		
		
		this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add(this.comprobanteDetalleSelec.getMontoIGV().add(this.comprobanteDetalleSelec.getMontoISC())));
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
	
	public void enviarSunat(Comprobante comprobante){
		
		System.out.println(" enviarSunat ---->");
		
		Format formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Format formatoHora = new SimpleDateFormat("HH:mm:ss");
		
		ComprobanteDetalleService comprobanteDetalleService= new ComprobanteDetalleService();
		
		 System.out.println("Everd chupa pija");
		 ArrayList<ArchivoPlano> listaArchivoCabecera = new ArrayList<ArchivoPlano>(); 
		 ArrayList<ArchivoPlano> listaArchivoNota = new ArrayList<ArchivoPlano>(); 
		 ArrayList<ArchivoPlano> listaArchivoDetalle = new ArrayList<ArchivoPlano>(); 
		 ArrayList<ArchivoPlano> listaArchivoTributos = new ArrayList<ArchivoPlano>(); 
		 ArrayList<ArchivoPlano> listaArchivoLeyenda = new ArrayList<ArchivoPlano>();  
		 ArrayList<ArchivoPlano> listaArchivoBaja = new ArrayList<ArchivoPlano>(); 
		 
		 
		 String rutaDescarga =Constante.RUTA_DESCARGA;
		 String serie ="F001"; 
		 String correlativo ="00000002"; 
		 String tipo_comprobante ="01"; 
		 String ruc ="12345678901"; 
		 String fecha_envio_Baja =""; 
		 String correlativo_baja =""; 
		 
		 serie=comprobante.getNumero_serie_documento_cab();
		 String sValue = String.valueOf(comprobante.getCorrelativo());
		 int cantComprobante = 8;
		 String com = "";
		 
			for (int i = 0; i < (cantComprobante - sValue.length()); i++) {				
				com = com + "0";				
			}	
			
			correlativo=com + sValue;
			tipo_comprobante=comprobante.getTipo_comprobante();
			ruc=comprobante.getEmisor().getRuc();
		 
		 
		 
		 ArchivoPlano archivoPlanoCabecera = new ArchivoPlano();	 
		 		 
		 archivoPlanoCabecera.setTipo_operacion_cab(comprobante.getTipo_operacion_cab());
		 archivoPlanoCabecera.setFecha_emision_cab(formatoFecha.format(comprobante.getFecha_emision_cab()));
		 archivoPlanoCabecera.setHora_emision_cab(formatoHora.format(comprobante.getHora_emision_cab()));
		 archivoPlanoCabecera.setFecha_vencimiento_cab(formatoFecha.format(comprobante.getFecha_vencimiento_cab()));
		 archivoPlanoCabecera.setDomicilio_fiscal_cab(comprobante.getDomicilioFiscal().getDomicilio());
		 archivoPlanoCabecera.setTipo_docu_iden_cab(comprobante.getTipo_docu_iden_cab().toString());
		 archivoPlanoCabecera.setNumero_docu_iden_cab(comprobante.getCliente().getNumero_docu_iden_cab());
		 archivoPlanoCabecera.setNombre_cab(comprobante.getCliente().getNombre_cab());
		 archivoPlanoCabecera.setTipo_moneda_cab(comprobante.getTipo_moneda_cab());
		 archivoPlanoCabecera.setSuma_tributos_cab(comprobante.getSuma_tributos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		 archivoPlanoCabecera.setTotal_valor_venta_cab(comprobante.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		 archivoPlanoCabecera.setTotal_precio_venta_cab(comprobante.getTotal_precio_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		
		 if(comprobante.getTotal_descuentos_cab()!=null){
			 archivoPlanoCabecera.setTotal_descuentos_cab(comprobante.getTotal_descuentos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 }else{
				 archivoPlanoCabecera.setTotal_descuentos_cab("0.00");
		}
		 if(comprobante.getSuma_otros_cargos_cab()!=null){
			 archivoPlanoCabecera.setSuma_otros_cargos_cab(comprobante.getSuma_otros_cargos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 }else{
				 archivoPlanoCabecera.setSuma_otros_cargos_cab("0.00");
		}
		 
		 if(comprobante.getTotal_anticipos_cab()!=null){
			 archivoPlanoCabecera.setTotal_anticipos_cab(comprobante.getTotal_descuentos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 }else{
				 archivoPlanoCabecera.setTotal_anticipos_cab("0.00");
		}
		 
		 
		 archivoPlanoCabecera.setImporte_total_venta_cab(comprobante.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		 archivoPlanoCabecera.setVersion_ubl_cab(comprobante.getVersion_ubl());
		 archivoPlanoCabecera.setCustomizacion_documento_cab(Constante.CUSTOMIZACION_DOCUMENTO);
		 
		 listaArchivoCabecera.add(archivoPlanoCabecera); 
		 
		 try {
			List<ComprobanteDetalle> listaDetalle=comprobanteDetalleService.findByIdComprobante(comprobante.getId_comprobante());
			for(ComprobanteDetalle detalle : listaDetalle){
				ArchivoPlano archivoPlanodetalle= new ArchivoPlano();	
				
					archivoPlanodetalle.setUnidad_medida_det(detalle.getProducto().getUnidad_medida_det());
				 	archivoPlanodetalle.setCant_unidades_item_det(detalle.getCant_unidades_item_det().toString());
				 	archivoPlanodetalle.setCod_prod_det(detalle.getProducto().getCod_prod_det());
				 	
//				 	if(detalle.getProducto().getTipo_prod_sunat_det()!=null){
//				 		archivoPlanodetalle.setTipo_prod_sunat_det(detalle.getProducto().getTipo_prod_sunat_det().toString());
//				 	}else{
//				 		archivoPlanodetalle.setTipo_prod_sunat_det("");
//				 	}
				 	archivoPlanodetalle.setTipo_prod_sunat_det("");
				 	
				    archivoPlanodetalle.setDescripcion_prod_det(detalle.getProducto().getDescripcion_prod_det());
				    archivoPlanodetalle.setValor_unitario_prod_det(detalle.getProducto().getValor_unitario_prod_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				    archivoPlanodetalle.setSuma_tributos_det(detalle.getSuma_tributos_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				   	
				   /** PROVISIONALMENTE **/
			    	archivoPlanodetalle.setTipo_tributo_igv_det("1000");
				    archivoPlanodetalle.setMonto_tributo_igv_det(detalle.getSuma_tributos_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				    archivoPlanodetalle.setBase_imponible_igv_det(detalle.getValor_venta_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				    archivoPlanodetalle.setTipo_tributo_nombre_igv_det("IGV");
				    archivoPlanodetalle.setTipo_tributo_inter_igv_det("VAT");
				    archivoPlanodetalle.setTipo_afectacion_igv_det("10");
				    archivoPlanodetalle.setPorcentaje_igv_det("18");
				    
//				    if(detalle.getMontoIGV()!=null){
//				    	
//				    }else{
//				    	archivoPlanodetalle.setTipo_tributo_igv_det("");
//					    archivoPlanodetalle.setMonto_tributo_igv_det("0.00");
//					    archivoPlanodetalle.setBase_imponible_igv_det("0.00");
//					    archivoPlanodetalle.setTipo_tributo_nombre_igv_det("");
//					    archivoPlanodetalle.setTipo_tributo_inter_igv_det("");
//					    archivoPlanodetalle.setTipo_afectacion_igv_det("");
//					    archivoPlanodetalle.setPorcentaje_igv_det("");
//				    }
				    
//				    if(detalle.getMontoISC()!=null){
//				    	
//				    }else{
					    archivoPlanodetalle.setTipo_tributo_isc_det("-");
					    archivoPlanodetalle.setMonto_tributo_isc_det("0.00");
					    archivoPlanodetalle.setBase_imponible_isc_det("0.00");
					    archivoPlanodetalle.setTipo_tributo_nombre_isc_det("");
					    archivoPlanodetalle.setTipo_tributo_inter_isc_det("");
					    archivoPlanodetalle.setTipo_sistema_isc_det("");
					    archivoPlanodetalle.setPorcentaje_isc_det("0");
//				    }		    	    
//
//				    if(detalle.getMontoOtros()!=null){
//				    	
//				    }else{
					    archivoPlanodetalle.setTipo_tributo_otros_det("-");
					    archivoPlanodetalle.setMonto_tributo_otros_det("0.00");
					    archivoPlanodetalle.setBase_imponible_otros_det("0.00");
					    archivoPlanodetalle.setTipo_tributo_nombre_otros_det("");
					    archivoPlanodetalle.setTipo_tributo_inter_otros_det("");
					    archivoPlanodetalle.setPorcentaje_otros_det("0");
//				    }	
				    
				    archivoPlanodetalle.setPrecio_venta_unitario_det(detalle.getPrecio_venta_unitario_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				    archivoPlanodetalle.setValor_venta_item_det(detalle.getValor_venta_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				    
//				    if(detalle.getValor_referencial_unit_det()!=null){
//				    	archivoPlanodetalle.setValor_referencial_unit_det(detalle.getValor_referencial_unit_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
//				    }else{
//				    	archivoPlanodetalle.setValor_referencial_unit_det("0");
//				    }
				    archivoPlanodetalle.setValor_referencial_unit_det("0");
				    
				
				listaArchivoDetalle.add(archivoPlanodetalle); 
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 	 ArchivoPlano archivoPlanoTributos= new ArchivoPlano();	
			 archivoPlanoTributos.setCodigo_catalogo_tri("1000");
			 archivoPlanoTributos.setDescripcion_corto_tri("IGV");
			 archivoPlanoTributos.setAtributo_1_tri("VAT");
			 archivoPlanoTributos.setTotal_valor_venta_tri(comprobante.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 archivoPlanoTributos.setSuma_tributos_tri(comprobante.getSuma_tributos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			 listaArchivoTributos.add(archivoPlanoTributos); 
		 
			 ArchivoPlano archivoPlanoLeyenda= new ArchivoPlano();	
			 archivoPlanoLeyenda.setCodigo_catalogo_ley("1000");
			 archivoPlanoLeyenda.setDescripcion_leyenda_ley(Utils.cantidadConLetra(comprobante.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()));
			 listaArchivoLeyenda.add(archivoPlanoLeyenda);
			 
			 
			 
			 if(comprobante.getTipo_comprobante().equals("07") || comprobante.getTipo_comprobante() .equals("08") ){
				 ArchivoPlano archivoNota= new ArchivoPlano();
				 
				 archivoNota.setTipo_operacion_not(comprobante.getTipo_operacion_cab());
				 archivoNota.setFecha_emision_not(formatoFecha.format(comprobante.getFecha_emision_cab()));
				 archivoNota.setHora_emision_not(formatoHora.format(comprobante.getHora_emision_cab()));
				 archivoNota.setFecha_vencimiento_cab(formatoFecha.format(comprobante.getFecha_vencimiento_cab()));
				 archivoNota.setDomicilio_fiscal_not(comprobante.getDomicilioFiscal().getDomicilio());
				 archivoNota.setTipo_docu_iden_not(comprobante.getTipo_docu_iden_cab().toString());
				 archivoNota.setNumero_docu_iden_not(comprobante.getCliente().getNumero_docu_iden_cab());
				 archivoNota.setNombre_not(comprobante.getCliente().getNombre_cab());
				 archivoNota.setTipo_moneda_not(comprobante.getTipo_moneda_cab());
				 archivoNota.setSuma_tributos_not(comprobante.getSuma_tributos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				 archivoNota.setTotal_valor_venta_not(comprobante.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				 archivoNota.setTotal_precio_venta_not(comprobante.getTotal_precio_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				 
				 archivoNota.setVersion_ubl_not(comprobante.getVersion_ubl());
				 archivoNota.setCustomizacion_documento_not(Constante.CUSTOMIZACION_DOCUMENTO);
 	  			
				 archivoNota.setNombre_not(comprobante.getCliente().getNombre_cab());
				 archivoNota.setTipo_tipo_nota_not(comprobante.getTipo_tipo_nota_not().toString());
				 archivoNota.setDescripcion_motivo_not(comprobante.getDescripcion_motivo_not());
				 archivoNota.setTipo_comprobante_afecta_not(comprobante.getTipo_comprobante_afecta_not());
				 archivoNota.setNumero_serie_documento_not(comprobante.getNumero_serie_documento_cab());

				 
				 archivoNota.setTotal_valor_venta_not(comprobante.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				 archivoNota.setTotal_precio_venta_not(comprobante.getTotal_precio_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				
				 if(comprobante.getTotal_descuentos_cab()!=null){
					 archivoNota.setTotal_descuentos_not(comprobante.getTotal_descuentos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
					 }else{
						 archivoNota.setTotal_descuentos_not("0");
				}
				 if(comprobante.getSuma_otros_cargos_cab()!=null){
					 archivoNota.setSuma_otros_cargos_not(comprobante.getSuma_otros_cargos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
					 }else{
						 archivoNota.setSuma_otros_cargos_not("0");
				}
				 
				 if(comprobante.getTotal_anticipos_cab()!=null){
					 archivoNota.setTotal_anticipos_not(comprobante.getTotal_descuentos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
					 }else{
						 archivoNota.setTotal_anticipos_not("0");
				}
 	  			archivoNota.setImporte_total_venta_not(comprobante.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
 	  							 
				 listaArchivoNota.add(archivoNota);
			 }
		 
		GeneraArchivoPlano.enviarFacturador(listaArchivoCabecera, 
											listaArchivoNota, 
											listaArchivoDetalle, 
											listaArchivoTributos, 
											listaArchivoLeyenda, 
											listaArchivoBaja, 
											rutaDescarga, 
											serie, 
											correlativo, 
											tipo_comprobante, 
											ruc, 
											fecha_envio_Baja, 
											correlativo_baja);
		
		try {
			
			comprobante.setEstado_sunat(Constante.ESTADO_ENVIADO);
			this.comprobanteService.actualizarEstadoEnvioSunatComprobante(comprobante);
			
			
			RequestContext context = RequestContext.getCurrentInstance(); 
			context.update("msgGeneral");
			FacesUtils.showFacesMessage("Envio de comprobante realizado ", 3);
//			this.listaComprobante=this.comprobanteService.listaComprobantes();	
			listarComprobantesFiltros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void imprimirComprobanteTicket(Comprobante comprobanteImprimir){
		
		List<DetalleComprobanteRep> listaDetalleReporte= new ArrayList<>();
		DetalleComprobanteRep linea;
		
		
		
		Map<String, Object> input = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Format formatoHora = new SimpleDateFormat("HH:mm:ss");
		input.put("p_fec_vencimiento", sdf.format(comprobanteImprimir.getFecha_vencimiento_cab()));
		input.put("p_hora_emision", formatoHora.format(comprobanteImprimir.getHora_emision_cab()));
		input.put("p_fec_emision", sdf.format(comprobanteImprimir.getFecha_emision_cab()));
		input.put("p_cliente", comprobanteImprimir.getCliente().getNombre_cab());
		input.put("p_ruc_cliente", comprobanteImprimir.getCliente().getNumero_docu_iden_cab());
		try {
			input.put("p_ruc", this.emisorService.findAll().get(0).getRuc());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//input.put("P_MONTO", Utils.customFormat("###,###.###",cuota.getMonto().doubleValue()));
		input.put("p_direccion", comprobanteImprimir.getCliente().getDireccion());
		input.put("p_tipo_moneda", comprobanteImprimir.getTipo_moneda_cab());
		input.put("p_tipo_documento", comprobanteImprimir.getDescripcion_tipo_comprobante());
		input.put("p_numero_documento", comprobanteImprimir.getNumero_serie_documento_cab());
		input.put("p_op_gravadas", comprobanteImprimir.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
//		input.put("p_op_gravadas", "0");
//		input.put("p_igv", igv.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());		
		input.put("p_igv", comprobanteImprimir.getSuma_tributos_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
//		input.put("p_descuento_global", dg.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
		input.put("p_total_valor_venta", comprobanteImprimir.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
//		input.put("p_descuentos_total", dg.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
		input.put("p_importe_total", comprobanteImprimir.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
		input.put("des_unidad_medida", "PEN");	
		input.put("p_emisor_razonsocial", this.emisorSelec.getRazon_social());
		input.put("p_direccion_emisor", this.emisorSelec.getDescripcion_domicilio_fiscal());
		
		
		try {
			this.listaComprobanteDetalle=this.comprobanteDetalleService.findByIdComprobante(comprobanteImprimir.getId_comprobante());
			
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
				//linea.setValor_unitario(det.getProducto().getPrecio_final_editado_cliente().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				linea.setValor_unitario((((det.getPrecio_venta_unitario_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide((det.getCant_unidades_item_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN))).setScale(2, BigDecimal.ROUND_HALF_EVEN)).toString());
//				(det.getPrecio_venta_unitario_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide(det.getCant_unidades_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN)).toString()				
//				((det.getPrecio_venta_unitario_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide((det.getCant_unidades_item_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN))).setScale(2, BigDecimal.ROUND_HALF_EVEN)).toString()
				listaDetalleReporte.add(linea);
				
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(comprobanteImprimir.getTipo_comprobante().equalsIgnoreCase("01")){
			imprimirFacturaTexto(input,listaDetalleReporte);
		}else{
			if(comprobanteImprimir.getTipo_comprobante().equalsIgnoreCase("03")){
				imprimirBoletaTexto(input,listaDetalleReporte);
			}
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
		
		
		
		String ss=new String("Aquí lo que vamos a imprimir.");
		
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
		
		
		String ss=new String("Aquí lo que vamos a imprimir.");
		
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
	
	public void imprimirComprobante(Comprobante comprobanteImprimir){	
		
		try {
			
			System.out.println("Ingreso metodo");
			ComprobanteDetalleService comprobanteDetalleService= new ComprobanteDetalleService();
			String nombreCompleto="";
			String nombreComprobante="";
			String dni;
			//this.listDetalleCuotas = this.detalleCronogramaService.findByIdCronograma(this.cuota.getId_cronograma());		
			String desProducto="";	
			
			String p_logo = ExportarArchivo.getPath("/resources/img/vetvictoria.jpg");
			
			if(comprobanteImprimir!=null){
				nombreCompleto = comprobanteImprimir.getCliente().getNombre_cab();
			}else{
				System.out.println("=======> NULO");
				
			}
			
			switch(comprobanteImprimir.getTipo_comprobante()){
			case "01": nombreComprobante="FACTURA ELECTRONICA";
			break;
			case "03": nombreComprobante="BOLETA DE VENTA ELECTRONICA";
				break;
			case "07": nombreComprobante="NOTA DE CREDITO";
				break;
			case "08": nombreComprobante="NOTA DE DEBITO";
				break;
			
			}
			
			
			
			BigDecimal igv = new BigDecimal("0.00");
			BigDecimal dg = new BigDecimal("0.00");
			
			List<ComprobanteDetalle> listaDetalle=comprobanteDetalleService.findByIdComprobante(comprobanteImprimir.getId_comprobante());
			
			for (ComprobanteDetalle cd : listaDetalle) {
				
				System.out.println("nombre " + cd.getProducto().getDescripcion_prod_det());
				igv = igv.add(cd.getSuma_tributos_det());

			}
			
			Map<String, Object> input = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			input.put("p_fec_vencimiento", sdf.format(comprobanteImprimir.getFecha_vencimiento_cab()));
			input.put("p_fec_emision", sdf.format(comprobanteImprimir.getFecha_emision_cab()));
			input.put("p_cliente", nombreCompleto);
			input.put("p_ruc", comprobanteImprimir.getCliente().getNumero_docu_iden_cab());		
			input.put("p_direccion", comprobanteImprimir.getCliente().getDireccion());
			input.put("p_tipo_moneda", comprobanteImprimir.getTipo_moneda_cab());		
			input.put("p_tipo_documento", nombreComprobante);
			input.put("p_numero_documento", comprobanteImprimir.getNumero_serie_documento_cab());
			input.put("p_op_gravadas", comprobanteImprimir.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_igv", igv.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());			
			input.put("p_descuento_global", dg.toString());	
			input.put("p_total_valor_venta", comprobanteImprimir.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_descuentos_total", dg.toString());	
			input.put("p_importe_total", comprobanteImprimir.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			input.put("p_monto_texto", Utils.cantidadConLetra(comprobanteImprimir.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()));
			input.put("des_unidad_medida", this.comprobanteSelec.getTipo_moneda_cab());	
			input.put("p_nombre_comercial", this.emisorSelec.getNombre_comercial()); 
			input.put("p_razon_social", this.emisorSelec.getRazon_social()); 
			input.put("p_direccion_emisor", this.emisorSelec.getDescripcion_domicilio_fiscal());
			input.put("p_ruc_emisor", this.emisorSelec.getRuc());
			//input.put("p_slogan", this.emisorSelec.getSlogan());  

			input.put("p_logo" , p_logo); 
			/*input.put("P_NOMBRE_EJECUTIVO", expediente.getNombre_ejecutivo());	
			input.put("P_TELEFONO", expediente.getUsu_telf());			
			input.put("P_EMAIL", expediente.getUsu_mail());	*/
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));
			
			List<DetalleComprobanteRep> listaDetalleReporte= new ArrayList<>();
			DetalleComprobanteRep linea;
			for(ComprobanteDetalle det: listaDetalle){
				linea= new DetalleComprobanteRep();
				linea.setCant_unidades_item_det(det.getCant_unidades_item_det().toString());
				linea.setCodigo_producto(det.getProducto().getCod_prod_det());
				linea.setDes_producto(det.getProducto().getDescripcion_prod_det());			
				linea.setDes_unidad_medida(det.getProducto().getDesUnidadMedida());
				linea.setDescuento("0");
				linea.setPrecio_unitario("0");
//				linea.setPrecio_venta_unitario_det("0");/*revisar campo*/
				linea.setPrecio_venta_unitario_det(det.getPrecio_venta_unitario_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				linea.setValor_item(det.getValor_venta_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				//linea.setValor_unitario(det.getProducto().getValor_unitario_prod_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				//linea.setValor_unitario(det.getValor_venta_item_det().divide(new BigDecimal(det.getCant_unidades_item_det())).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
//				linea.setValor_unitario(det.getValor_venta_item_det().divide(det.getCant_unidades_item_det(),2 , RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				linea.setValor_unitario((((det.getPrecio_venta_unitario_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide((det.getCant_unidades_item_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN))).setScale(2, BigDecimal.ROUND_HALF_EVEN)).toString());
				listaDetalleReporte.add(linea);
				
				
			}
			
			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/comprobante.jasper");
			
			System.out.println(" path ===================> "+path);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

			byte[] bytes;
			
			/* bytes = ExportarArchivo.exportXlsX(path, input, lstCroBean, false);
			ExportarArchivo.executeExcel(bytes, dni+"-"+nombreCompleto+".xlsx");*/
			bytes = ExportarArchivo.exportPdf(path, input,listaDetalleReporte); 
			ExportarArchivo.executePdf(bytes, comprobanteImprimir.getTipo_comprobante()+ "-" + comprobanteImprimir.getNumero_serie_documento_cab()+ ".pdf");
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	public void leerRespuesta(){
		System.out.println("leerRespuesta ====>");
		LeerZip zip = new LeerZip();
		LeerFileXML filexml= new LeerFileXML();
		
		String carpetaZip = Constante.RUTA_RESPUESTA;
		String carpetaRpta=Constante.RUTA_SALIDA_LECTURA_ZIP;
		String rutaFileZip="";
		String rutaFileXml="";
		List<Comprobante> listaPendienteLectura;
		
		try {
			listaPendienteLectura = this.comprobanteService.listaComprobantesLecturaRespuesta();
			
			for(Comprobante comprobante:listaPendienteLectura){
				rutaFileZip="R"+comprobante.getEmisor().getRuc()+"-"+comprobante.getTipo_comprobante()+"-"+comprobante.getNumero_serie_documento_cab()+".zip";
				rutaFileXml="R-"+comprobante.getEmisor().getRuc()+"-"+comprobante.getTipo_comprobante()+"-"+comprobante.getNumero_serie_documento_cab()+".xml";
				
				String rutaZip=carpetaZip+rutaFileZip;
				String rutaXml=carpetaRpta+rutaFileXml;
				
				System.out.println(" rutaZip :"+rutaZip);
				System.out.println(" rutaXml :"+rutaXml);
				
				try{
					zip.unZip(rutaZip, carpetaRpta);
					RespuestaSunat respuestaSunat=filexml.leerXml(rutaXml, comprobante.getTipo_comprobante());
					if(respuestaSunat!=null){
						comprobante.setEstado_sunat(Constante.ESTADO_ENVIADO);
						if(respuestaSunat.getResponseCode()==0){
							comprobante.setEstado_respuesta_sunat(Constante.RESPUESTA_ENVIO_ACEPTADO);
						}else{
							comprobante.setEstado_respuesta_sunat(Constante.RESPUESTA_ENVIO_RECHZADO);
						}
						comprobante.setDescripcion_respuesta_sunat(respuestaSunat.getDescription());
					}
					this.comprobanteService.actualizarEstadoRespuestaSunatComprobante(comprobante);
					RequestContext context = RequestContext.getCurrentInstance(); 
					context.update("msgGeneral");
					FacesUtils.showFacesMessage("Se recibieron respuestas de SUNAT ", 3);
				}catch(Exception e){
					
					comprobante.setEstado_respuesta_sunat(Constante.ESTADO_PROBLEMAS_SUNAT);
					comprobante.setDescripcion_respuesta_sunat(Constante.RESPUESTA_PROBLEMAS_SUNAT);					
					this.comprobanteService.actualizarEstadoRespuestaSunatComprobante(comprobante);
					RequestContext context = RequestContext.getCurrentInstance(); 
					context.update("msgGeneral");
					FacesUtils.showFacesMessage("Problemas con el servicio de SUNAT ", 2);
				}

				listarComprobantesFiltros();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*Reporte Masivo para Contadores*/ 
public void imprimirLista(){
		
		System.out.println("=======> imprimirLista <=========");
		try {
			String nombreCompleto="";
			String nombreComprobante=""; 
			String descripcionMes=""; 
			String dni;
			//this.listDetalleCuotas = this.detalleCronogramaService.findByIdCronograma(this.cuota.getId_cronograma());		
			String desProducto="";			
			
		
			BigDecimal igv = new BigDecimal("0.00");
			BigDecimal dg = new BigDecimal("0.00");

			
			Map<String, Object> input = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Format formatoHora = new SimpleDateFormat("HH:mm:ss");

			String pathContratoSUBDIR=ExportarArchivo.getPath("/resources/reports/jxrml/");
			
			switch(mes){
			case "01": descripcionMes="ENERO";
			break;
			case "02": descripcionMes="FEBRERO";
			break;
			case "03": descripcionMes="MARZO";
			break;
			case "04": descripcionMes="ABRIL";
			break;
			case "05": descripcionMes="MAYO";
			break;
			case "06": descripcionMes="JUNIO";
			break;
			case "07": descripcionMes="JULIO";
			break;
			case "08": descripcionMes="AGOSTO";
			break;
			case "09": descripcionMes="SEPTIEMBRE";
			break;
			case "10": descripcionMes="OCTUBRE";
			break;
			case "11": descripcionMes="NOVIEMBRE";
			break;
			case "12": descripcionMes="DICIEMBRE";
			break;
			}
			
			switch(tipo_comprobante){
			case "01": nombreComprobante="FACTURA ELECTRONICA";
			break;
			case "03": nombreComprobante="BOLETA DE VENTA ELECTRONICA";
				break;
			case "07": nombreComprobante="NOTA DE CREDITO";
				break;
			case "08": nombreComprobante="NOTA DE DEBITO";
				break;
			
			}
			
			

					
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));
			input.put("SUBREPORT_DIR", pathContratoSUBDIR);
			input.put("tipo_comprobante", tipo_comprobante);
			input.put("nombreComprobante", nombreComprobante);
			input.put("ruc", this.emisorSelec.getRuc());
			input.put("razonSocial", this.emisorSelec.getRazon_social());
			input.put("sucursal", this.emisorSelec.getDescripcion_domicilio_fiscal());
			input.put("descripcionMes", descripcionMes);
			input.put("mes", mes);
			input.put("año", anio);

			String path = ExportarArchivo.getPath("/resources/reports/jxrml/Principal.jasper");
			
			System.out.println(" path ===================> "+path);
			

			byte[] bytes;
			
			/* bytes = ExportarArchivo.exportXlsX(path, input, lstCroBean, false);
			ExportarArchivo.executeExcel(bytes, dni+"-"+nombreCompleto+".xlsx");*/
//			bytes = ExportarArchivo.exportXls(path, input,null, false);
			
			bytes = ExportarArchivo.exportarExcel(path, input, null, false, Conexion.getConnection());
//			bytes = ExportarArchivo.exportPdf(path, input,null);
			ExportarArchivo.executeExccel(bytes, "listaExcel.xls");

//			ExportarArchivo.executePdf(bytes, "comprobante.pdf");
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(
			TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}	

	public List<TablaTablasDetalle> getListTablaTablasDetallesComprobante() {
		return listTablaTablasDetallesComprobante;
	}

	public void setListTablaTablasDetallesComprobante(
			List<TablaTablasDetalle> listTablaTablasDetallesComprobante) {
		this.listTablaTablasDetallesComprobante = listTablaTablasDetallesComprobante;
	}

	public Comprobante getComprobanteSelec() {
		return comprobanteSelec;
	}

	public void setComprobanteSelec(Comprobante comprobanteSelec) {
		this.comprobanteSelec = comprobanteSelec;
	}

	public String getCampo_busqueda() {
		return campo_busqueda;
	}

	public void setCampo_busqueda(String campo_busqueda) {
		this.campo_busqueda = campo_busqueda;
	}

	public Cliente getClienteEncontrado() {
		return clienteEncontrado;
	}

	public void setClienteEncontrado(Cliente clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesOperacion() {
		return listTablaTablasDetallesOperacion;
	}

	public void setListTablaTablasDetallesOperacion(
			List<TablaTablasDetalle> listTablaTablasDetallesOperacion) {
		this.listTablaTablasDetallesOperacion = listTablaTablasDetallesOperacion;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesMoneda() {
		return listTablaTablasDetallesMoneda;
	}

	public void setListTablaTablasDetallesMoneda(
			List<TablaTablasDetalle> listTablaTablasDetallesMoneda) {
		this.listTablaTablasDetallesMoneda = listTablaTablasDetallesMoneda;
	}

	public ComprobanteDetalle getComprobanteDetalleSelec() {
		return comprobanteDetalleSelec;
	}

	public void setComprobanteDetalleSelec(
			ComprobanteDetalle comprobanteDetalleSelec) {
		this.comprobanteDetalleSelec = comprobanteDetalleSelec;
	}

	public Producto getProductoEncontrado() {
		return productoEncontrado;
	}

	public void setProductoEncontrado(Producto productoEncontrado) {
		this.productoEncontrado = productoEncontrado;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesProducto() {
		return listTablaTablasDetallesProducto;
	}

	public void setListTablaTablasDetallesProducto(
			List<TablaTablasDetalle> listTablaTablasDetallesProducto) {
		this.listTablaTablasDetallesProducto = listTablaTablasDetallesProducto;
	}

	public Producto getProductoSelec() {
		return productoSelec;
	}

	public void setProductoSelec(Producto productoSelec) {
		this.productoSelec = productoSelec;
	}

	public TributoProducto getTributoProducto() {
		return tributoProducto;
	}

	public void setTributoProducto(TributoProducto tributoProducto) {
		this.tributoProducto = tributoProducto;
	}

	public List<TributoProducto> getListTributoProductos() {
		return listTributoProductos;
	}

	public void setListTributoProductos(List<TributoProducto> listTributoProductos) {
		this.listTributoProductos = listTributoProductos;
	}

	public List<Comprobante> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Comprobante> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Boolean getEditarCliente() {
		return editarCliente;
	}

	public void setEditarCliente(Boolean editarCliente) {
		this.editarCliente = editarCliente;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public ComprobanteService getComprobanteService() {
		return comprobanteService;
	}

	public void setComprobanteService(ComprobanteService comprobanteService) {
		this.comprobanteService = comprobanteService;
	}

	public TributoProductoService getTributoProductoService() {
		return tributoProductoService;
	}

	public void setTributoProductoService(TributoProductoService tributoProductoService) {
		this.tributoProductoService = tributoProductoService;
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

	public List<Comprobante> getListaFiltroComprobante() {
		return listaFiltroComprobante;
	}

	public void setListaFiltroComprobante(List<Comprobante> listaFiltroComprobante) {
		this.listaFiltroComprobante = listaFiltroComprobante;
	}

	public ComunicacionBaja getComunicacionBajaSelec() {
		return comunicacionBajaSelec;
	}

	public void setComunicacionBajaSelec(ComunicacionBaja comunicacionBajaSelec) {
		this.comunicacionBajaSelec = comunicacionBajaSelec;
	}

	public LazyDataModel<Comprobante> getListaComprobante() {
		return listaComprobante;
	}

	public void setListaComprobante(LazyDataModel<Comprobante> listaComprobante) {
		this.listaComprobante = listaComprobante;
	}

	public ComunicacionBajaService getComunicacionBajaService() {
		return comunicacionBajaService;
	}

	public void setComunicacionBajaService(ComunicacionBajaService comunicacionBajaService) {
		this.comunicacionBajaService = comunicacionBajaService;
	}

	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
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

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Boolean getDisableRespuesta() {
		return disableRespuesta;
	}

	public void setDisableRespuesta(Boolean disableRespuesta) {
		this.disableRespuesta = disableRespuesta;
	}

	public Boolean getDisableBuscar() {
		return disableBuscar;
	}

	public void setDisableBuscar(Boolean disableBuscar) {
		this.disableBuscar = disableBuscar;
	}	

	
	  
}
