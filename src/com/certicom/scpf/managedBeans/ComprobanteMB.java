package com.certicom.scpf.managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import src.com.certicom.scpf.utils.Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.DetalleComprobanteRep;
import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.TributoProducto;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.ComprobanteDetalleService;
import com.certicom.scpf.services.ComprobanteService;
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ModoPagoService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TributoProductoService;
import com.certicom.scpf.services.VendedorService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.ExportarArchivo;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="comprobanteMB")
@ViewScoped
public class ComprobanteMB extends GenericBeans implements Serializable{

	private Comprobante comprobanteSelec;
	private ComprobanteDetalle comprobanteDetalleSelec;
	private List<ComprobanteDetalle> listaComprobanteDetalle;
	private Cliente clienteEncontrado;
	private Producto productoEncontrado;
	private Producto productoSelec;
	private DomicilioFiscal domicilioFiscalSelec;
	private TributoProducto tributoProducto;
	private TablaTablasDetalle tablaTablasDetalleTipoComprobante;
	private List<TributoProducto> listTributoProductos;
	private String campo_busqueda;
	private String numero_documento;
	private Emisor emisorSelec;
	private Boolean editarCliente;
	private MenuServices menuServices;
	private ClienteService clienteService;
	private ProductoService productoService;
	private ComprobanteService comprobanteService;
	private ComprobanteDetalleService comprobanteDetalleService;
	private DomicilioFiscalService domicilioFiscalService;
	private TributoProductoService tributoProductoService;
	private EmisorService emisorService;
	private ModoPagoService modoPagoService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private List<ModoPago> listModoPagos;
    private boolean adicionar; /*Jesï¿½s*/
    private boolean generarComprobante; /*Jesï¿½s*/
    private boolean ingresarCliente; /*Jesï¿½s*/
    private List<Vendedor> listaVendedores; /*Vega.com*/
    private VendedorService vendedorService;
    
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ComprobanteMB(){}
	
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
		this.comprobanteService = new ComprobanteService();
		this.comprobanteDetalleService = new ComprobanteDetalleService();
		this.domicilioFiscalService = new DomicilioFiscalService();
		this.modoPagoService = new ModoPagoService();
		this.emisorService = new EmisorService();
		this.productoEncontrado = new Producto();
		this.comprobanteDetalleSelec = new ComprobanteDetalle();
		this.tributoProducto = new TributoProducto();
		this.listaComprobanteDetalle = new ArrayList<ComprobanteDetalle>();
		this.comprobanteSelec.setFecha_emision_cab(new Date()); /*Jesï¿½s*/
		this.comprobanteSelec.setFecha_vencimiento_cab(new Date()); /*Jesï¿½s*/
		this.comprobanteSelec.setHora_emision_cab(new Date());/*Jesï¿½s*/
		this.comprobanteSelec.setId_modo_pago(4); /* Jesï¿½s contado*/
		this.comprobanteSelec.setTipo_operacion_cab("0101"); /* Jesï¿½s Tipo Operacion*/
		this.comprobanteSelec.setTipo_moneda_cab("PEN"); /* Jesï¿½s Tipo Moneda*/
		this.adicionar = Boolean.TRUE; /*Jesï¿½s*/
		this.generarComprobante=  Boolean.TRUE; /*Jesï¿½s*/
		this.ingresarCliente = Boolean.TRUE; /*Jesus*/
		this.comprobanteSelec.setSuma_tributos_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_precio_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_valor_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setImporte_total_venta_cab(new BigDecimal("0.00"));
		this.vendedorService= new VendedorService();
		
		

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listaVendedores = this.vendedorService.findAll(); /*Vega.com*/
			this.listModoPagos = this.modoPagoService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.comprobanteSelec.setId_emisor(this.emisorSelec.getId_emisor());
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:comprobante");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
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
	
	public void onItemCliente(SelectEvent event)  throws Exception{
		
		String s = event.getObject().toString();
				
				
				
				List<Cliente> allProducts = this.clienteService.findAll();
		        //List<Cliente> filteredProducts = new ArrayList<Cliente>();
				
				for (int i = 0; i < allProducts.size(); i++) {
		        	Cliente skin = allProducts.get(i);
		            if(skin.getNombre_cab().equals(s)) {
		            	//filteredProducts.add(skin);
		            	//this.comprobanteSelec.setTipo_comprobante(skin.getDescripcion_prod_det());
		            	this.clienteEncontrado = skin;
		            	break;
		            	
		            }
		        }		
				
				this.comprobanteSelec.setId_cliente(this.clienteEncontrado.getId_cliente());
				this.comprobanteSelec.setTipo_docu_iden_cab(this.clienteEncontrado.getTipo_docu_iden_cab());
				this.adicionar = Boolean.FALSE; /*Jesus*/
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
		
		this.comprobanteSelec.setId_domicilio_fiscal_cab(this.domicilioFiscalSelec.getId_domicilio_fiscal_cab());
		
		this.clienteEncontrado = new Cliente();
		this.comprobanteSelec.setFecha_emision_cab(new Date()); /*Jesï¿½s*/
		this.comprobanteSelec.setFecha_vencimiento_cab(new Date()); /*Jesï¿½s*/
		this.comprobanteSelec.setHora_emision_cab(new Date());/*Jesï¿½s*/
		this.comprobanteSelec.setId_modo_pago(4); /* Jesï¿½s contado*/
		this.comprobanteSelec.setTipo_operacion_cab("0101"); /* Jesï¿½s Tipo Operacion*/
		this.comprobanteSelec.setTipo_moneda_cab("PEN"); /* Jesï¿½s Tipo Moneda*/
		this.comprobanteSelec.setSuma_tributos_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_precio_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_valor_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setImporte_total_venta_cab(new BigDecimal("0.00"));
		this.adicionar = Boolean.TRUE; /*Jesï¿½s*/
		this.generarComprobante=  Boolean.TRUE; /*Jesï¿½s*/
		this.ingresarCliente = Boolean.FALSE; /*Jesï¿½s*/
		this.listaComprobanteDetalle = new ArrayList<ComprobanteDetalle>();/*Jesus*/
		
		System.out.println("Cï¿½digo de Documento: "+this.comprobanteSelec.getNumero_serie_documento_cab());
		
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
		
		
		if(this.productoSelec.isValor_unit_incluye_impuestos()== Boolean.FALSE){
			
			        System.out.println("Everd es  false : " + this.productoSelec.isValor_unit_incluye_impuestos());
			        
			        this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); /*Jesï¿½s*/
			
		this.comprobanteDetalleSelec.setId_producto(this.productoSelec.getId_producto());
		this.comprobanteDetalleSelec.setProducto(this.productoSelec);
		
		
		this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
		
		for (TributoProducto tp : this.listTributoProductos) {
			
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
				this.comprobanteDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
				this.comprobanteDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteDetalleSelec.setTpOtros(tp);
			}
		}
		
		
					this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoIGV()).add((this.comprobanteDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()))));					
			
		}else{
			
			        System.out.println("Everd es  true : " + this.productoSelec.isValor_unit_incluye_impuestos());
			        this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); /*Jesï¿½s*/
			
					this.comprobanteDetalleSelec.setId_producto(this.productoSelec.getId_producto());
					this.comprobanteDetalleSelec.setProducto(this.productoSelec);
					
					
					this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
					
					for (TributoProducto tp : this.listTributoProductos) {
						
						if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
							this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
							this.comprobanteDetalleSelec.setTpISC(tp);
						}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente());
							this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal("18.00")));
							this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().divide(new BigDecimal("118.00"), RoundingMode.HALF_UP));
							this.productoSelec.setValor_unitario_prod_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteDetalleSelec.getMontoIGV()));
							this.comprobanteDetalleSelec.setTpIGV(tp);
						}else{
							this.comprobanteDetalleSelec.setTpOtros(tp);
						}
					}
					
					
					//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoIGV()).add((this.comprobanteDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()))));					
					
		}
		
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
	
	public void adicionarProducto(){
		this.productoEncontrado = new Producto();
		this.comprobanteDetalleSelec = new ComprobanteDetalle();
		
	}
	
	
	
	public void calcularMonto(){
		for (TributoProducto tp : this.listTributoProductos) {
			
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
				this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
				this.comprobanteDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				
				this.comprobanteDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
				this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
				this.comprobanteDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteDetalleSelec.setTpOtros(tp);
			}
		}
		
		//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().multiply(new BigDecimal(this.comprobanteDetalleSelec.getCant_unidades_item_det())));
		//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoIGV()));
		//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoISC()));
		this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
		this.comprobanteDetalleSelec.setSuma_tributos_det((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoIGV()).add(this.comprobanteDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()));
		this.comprobanteDetalleSelec.setValor_venta_item_det(this.productoSelec.getValor_unitario_prod_det().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
	}
	
	/*Inicio Jesus*/
	public void calcularMontoPrecio(){
		
		
		try{   
			       if(this.productoSelec.isValor_unit_incluye_impuestos()== Boolean.FALSE){   
				
									for (TributoProducto tp : this.listTributoProductos) {
										
										if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
											this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
											this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
											this.comprobanteDetalleSelec.setTpISC(tp);
										}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
											
											this.comprobanteDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getPrecio_final_editado_cliente()));
											this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
											this.comprobanteDetalleSelec.setTpIGV(tp);
										}else{
											this.comprobanteDetalleSelec.setTpOtros(tp);
										}
									}
									
									this.productoSelec.setValor_unitario_prod_det(this.productoSelec.getPrecio_final_editado_cliente());
									this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
									this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoIGV()));
									this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoISC()));
									this.comprobanteDetalleSelec.setSuma_tributos_det((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoIGV()).add(this.comprobanteDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()));
									this.comprobanteDetalleSelec.setValor_venta_item_det(this.productoSelec.getValor_unitario_prod_det().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
									
					}else{
									for (TributoProducto tp : this.listTributoProductos) {
										
										if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
											this.comprobanteDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
											this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteDetalleSelec.setMontoISC(this.comprobanteDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
											this.comprobanteDetalleSelec.setTpISC(tp);
										}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
											this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente());
											this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal("18.00")));
											this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().divide(new BigDecimal("118.00"), RoundingMode.HALF_UP));	
											this.productoSelec.setValor_unitario_prod_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteDetalleSelec.getMontoIGV()));
											this.comprobanteDetalleSelec.setMontoIGV(this.comprobanteDetalleSelec.getMontoIGV().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteDetalleSelec.setTpIGV(tp);
										}else{
											this.comprobanteDetalleSelec.setTpOtros(tp);
										}
									}
									
									this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().multiply(this.comprobanteDetalleSelec.getCant_unidades_item_det()));
								    this.comprobanteDetalleSelec.setSuma_tributos_det((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteDetalleSelec.getMontoIGV()).add(this.comprobanteDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()));
									this.comprobanteDetalleSelec.setValor_venta_item_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteDetalleSelec.getSuma_tributos_det()));
									
					}
								
		}catch (Exception e) { 
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		
		
		
		
	}
	/*Fin Jesus*/
	
	
	public void adicionarCompra(){
		
		System.out.println("Entra a adicionarCompra");
		this.comprobanteDetalleSelec.setTipo_comprobante(this.comprobanteSelec.getTipo_comprobante());
		this.comprobanteDetalleSelec.setId_cliente(this.comprobanteSelec.getId_cliente());
		this.comprobanteDetalleSelec.setId_emisor(this.comprobanteSelec.getId_emisor());
		this.comprobanteDetalleSelec.setId_domicilio_fiscal_cab(this.comprobanteSelec.getId_domicilio_fiscal_cab());
		this.comprobanteDetalleSelec.setId_modo_pago(this.comprobanteSelec.getId_modo_pago());
		System.out.println("MODO PAGO :"+this.comprobanteSelec.getId_modo_pago());
		this.listaComprobanteDetalle.add(this.comprobanteDetalleSelec);
		
		this.comprobanteSelec.setSuma_tributos_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_precio_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setTotal_valor_venta_cab(new BigDecimal("0.00"));
		this.comprobanteSelec.setImporte_total_venta_cab(new BigDecimal("0.00"));
		
		for (ComprobanteDetalle comprobanteDetalle : this.listaComprobanteDetalle) {
			this.comprobanteSelec.setSuma_tributos_cab(this.comprobanteSelec.getSuma_tributos_cab().add(comprobanteDetalle.getSuma_tributos_det()));
			this.comprobanteSelec.setTotal_precio_venta_cab(this.comprobanteSelec.getTotal_precio_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
			this.comprobanteSelec.setTotal_valor_venta_cab(this.comprobanteSelec.getTotal_valor_venta_cab().add(comprobanteDetalle.getValor_venta_item_det()));
			this.comprobanteSelec.setImporte_total_venta_cab(this.comprobanteSelec.getImporte_total_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
		}
		
		this.generarComprobante = Boolean.FALSE; /*Jesï¿½s*/
	}
	
	
	public void generarComprobante(){
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			
			
			
			System.out.println("  id_comprobante :"+this.comprobanteSelec.getId_comprobante());
			this.comprobanteSelec.setVersion_ubl(Constante.VERSION_UBL_SUNAT);
			this.comprobanteSelec.setEstado_comunicacion_baja(Boolean.FALSE);
			this.comprobanteSelec.setCorrelativo(this.comprobanteService.getCorrelativoComprobante(this.comprobanteSelec.getTipo_comprobante()));
			this.comprobanteSelec.setEstado_sunat(Constante.ESTADO_PENDIENTE);
			this.comprobanteService.crearComprobanteSec(this.comprobanteSelec);
			int id = this.comprobanteService.getSecIdComprobante();
			System.out.println("ID: "+id);
			
			this.comprobanteDetalleService.insertBatchComprobanteDetalle(this.listaComprobanteDetalle, id-1);
			imprimirPDFSim2();
			
			context.update("msgGeneral");
			context.update("formAction");
			FacesUtils.showFacesMessage("Se registro el comprobante " + this.comprobanteSelec.getNumero_serie_documento_cab(), 3);
			
			
			this.comprobanteSelec= new Comprobante();
			this.listaComprobanteDetalle= new ArrayList<ComprobanteDetalle>();
			
			
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listModoPagos = this.modoPagoService.findAll();
			this.listaVendedores = this.vendedorService.findAll(); /*Vega.com*/
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.comprobanteSelec.setId_emisor(this.emisorSelec.getId_emisor());
			this.clienteEncontrado= new Cliente();
			
			this.comprobanteSelec.setFecha_emision_cab(new Date()); /*Jesï¿½s*/
			this.comprobanteSelec.setFecha_vencimiento_cab(new Date()); /*Jesï¿½s*/
			this.comprobanteSelec.setHora_emision_cab(new Date());/*Jesï¿½s*/
			this.comprobanteSelec.setId_modo_pago(4); /* Jesï¿½s contado*/
			this.comprobanteSelec.setTipo_operacion_cab("0101"); /* Jesï¿½s Tipo Operacion*/
			this.comprobanteSelec.setTipo_moneda_cab("PEN"); /* Jesï¿½s Tipo Moneda*/
			this.adicionar = Boolean.TRUE; /*Jesï¿½s*/
			this.generarComprobante = Boolean.TRUE; /*Jesï¿½s*/
			this.ingresarCliente = Boolean.TRUE; /*Jesï¿½s*/
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void imprimirPDFSim() {		
		try {
			String nombreCompleto="";
			String nombreComprobante="";
			String dni;
			//this.listDetalleCuotas = this.detalleCronogramaService.findByIdCronograma(this.cuota.getId_cronograma());		
			String desProducto="";		
			String p_logo = ExportarArchivo.getPath("/resources/img/vetvictoria.jpg");
			
			if(this.comprobanteSelec!=null){
				nombreCompleto = this.clienteEncontrado.getNombre_cab();
			}else{
				System.out.println("=======> NULO");
				
			}
			
			switch(this.comprobanteSelec.getTipo_comprobante()){
			case "01": nombreComprobante="FACTURA ELECTRONICA";
			break;
			case "03": nombreComprobante="BOLETA DE VENTA ELECTRONICA";
				break;
			case "07": nombreComprobante="NOTA DE CREDITO";
				break;
			case "08": nombreComprobante="NOTA DE DEBITO";
				break;
			
			}
			
			
			/*expediente = new ExpedienteService().findById(expediente.getExpediente_id());
			 nombreCompleto = expediente.getNombre_completo() + " " +
             expediente.getApellido_paterno() + " " +
		     expediente.getApellido_materno();
			
			dni = expediente.getNum_doc();*/
			
			BigDecimal igv = new BigDecimal("0.00");
			BigDecimal dg = new BigDecimal("0.00");
			
			for (ComprobanteDetalle cd : this.listaComprobanteDetalle) {
				
				igv = igv.add(cd.getMontoIGV());
				
				
			}
			
			Map<String, Object> input = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			input.put("p_fec_vencimiento", sdf.format(this.comprobanteSelec.getFecha_vencimiento_cab()));
			input.put("p_fec_emision", sdf.format(this.comprobanteSelec.getFecha_emision_cab()));
			input.put("p_cliente", nombreCompleto);
			input.put("p_ruc", this.clienteEncontrado.getNumero_docu_iden_cab());		
			input.put("p_direccion", this.clienteEncontrado.getDireccion());
			input.put("p_tipo_moneda", this.comprobanteSelec.getTipo_moneda_cab());			
			input.put("p_tipo_documento", nombreComprobante);
			input.put("p_numero_documento", this.comprobanteSelec.getNumero_serie_documento_cab());
			input.put("p_op_gravadas", this.comprobanteSelec.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_igv", igv.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());			
			input.put("p_descuento_global", dg.toString());	
			input.put("p_total_valor_venta", this.comprobanteSelec.getTotal_valor_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());	
			input.put("p_descuentos_total", dg.toString());	
			input.put("p_importe_total", this.comprobanteSelec.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
			input.put("p_monto_texto", Utils.cantidadConLetra(comprobanteSelec.getImporte_total_venta_cab().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()));
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
			for(ComprobanteDetalle det: this.listaComprobanteDetalle){
				linea= new DetalleComprobanteRep();
				linea.setCant_unidades_item_det(det.getCant_unidades_item_det().toString());
				linea.setCodigo_producto(det.getProducto().getCod_prod_det());
				linea.setDes_producto(det.getProducto().getDescripcion_prod_det());
				linea.setDes_unidad_medida(det.getProducto().getDesUnidadMedida());
				linea.setDescuento("0");
				linea.setPrecio_unitario("0");
				linea.setPrecio_venta_unitario_det("0");/*revisar campo*/
				linea.setValor_item(det.getValor_venta_item_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				linea.setValor_unitario(det.getProducto().getValor_unitario_prod_det().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				listaDetalleReporte.add(linea);
			}
			
			
			
			/*List<CronogramaBean> lstCroBean = new ArrayList<CronogramaBean>();		*/	
			
			/*for(DetalleCronograma dc: this.listDetalleCuotas){
				CronogramaBean cb = new CronogramaBean();
				cb.setNro_cuota(""+(dc.getNro_cuota() == null ? "" : dc.getNro_cuota()));
				cb.setFec_ini(""+(dc.getFecha_inicio() == null ? "" : Utils.dateToString(dc.getFecha_inicio())));
				cb.setFec_cap(""+(dc.getFecha_vencimiento() == null ? "" : Utils.dateToString(dc.getFecha_vencimiento())));
				cb.setCapital(""+(dc.getCapital() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getCapital().doubleValue())));
				cb.setDias_cuota(""+(dc.getDias() == null ? "" : dc.getDias()));
				cb.setAmortizacion(""+(dc.getAmortizacion() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getAmortizacion().doubleValue())));
				cb.setCuota(""+(dc.getCuota() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getCuota().doubleValue())));
				cb.setInteres(""+(dc.getInteres() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getInteres().doubleValue())));
				cb.setInteres_cap(""+(dc.getInteres_capitalizable() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getInteres_capitalizable().doubleValue())));
				cb.setCapital_final(""+(dc.getCapital_final() == null ? "" :"S/."+Utils.customFormat("###.###",dc.getCapital_final().doubleValue())));
				cb.setGracia(""+(dc.getGracia() == null ? "" : "S/."+Utils.customFormat("###.###", dc.getGracia().doubleValue())));
				cb.setCuota_total(""+(dc.getCuota_total() == null ? "" : "S/."+Utils.customFormat("###.###", dc.getCuota_total().doubleValue())));
				cb.setSaldo_credito_inicial(""+(dc.getSaldo_credito_inicial() == null ? "" : "S/."+Utils.customFormat("###.###", dc.getSaldo_credito_inicial().doubleValue())));
				cb.setSaldo_comision_inicial(""+(dc.getSaldo_comision_inicial() == null ? "" : "S/."+Utils.customFormat("###.###", dc.getSaldo_comision_inicial().doubleValue())));
				cb.setDescripcion_estado_cuota(""+dc.getDescripcion_estado_cuota());
				System.out.println("=====dc.getCuota_total(): " + dc.getCuota_total().doubleValue());
				lstCroBean.add(cb);
			}*/
			
			/*String path = ExportarArchivo
					.getPath("/resources/reports/jxrml/rptPrueba1.jasper");
			
			String path = ExportarArchivo
					.getPath("/resources/reports/jxrml/rptCronogramaPDF.jasper");*/
		//	String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptComprobante.jasper");
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/comprobante.jasper");
			
			System.out.println(" path ===================> "+path);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

			byte[] bytes;
			
			/* bytes = ExportarArchivo.exportXlsX(path, input, lstCroBean, false);
			ExportarArchivo.executeExcel(bytes, dni+"-"+nombreCompleto+".xlsx");*/
			bytes = ExportarArchivo.exportPdf(path, input,listaDetalleReporte); 
			ExportarArchivo.executePdf(bytes, this.comprobanteSelec.getTipo_comprobante()+ "-" + this.comprobanteSelec.getNumero_serie_documento_cab()+ ".pdf");
			
			FacesContext.getCurrentInstance().responseComplete();
			
		 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void imprimirPDFSim2() {		
		try {
			String nombreCompleto="";
			String dni;
			//this.listDetalleCuotas = this.detalleCronogramaService.findByIdCronograma(this.cuota.getId_cronograma());		
			String desProducto="";			
			if(this.comprobanteSelec!=null){
				nombreCompleto = this.clienteEncontrado.getNombre_cab();
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
			input.put("p_ruc_cliente", this.clienteEncontrado.getNumero_docu_iden_cab());
			input.put("p_ruc", this.emisorService.findAll().get(0).getRuc());
			//input.put("P_MONTO", Utils.customFormat("###,###.###",cuota.getMonto().doubleValue()));
			input.put("p_direccion", this.clienteEncontrado.getDireccion());
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
			
			
			

			
			/*String path = ExportarArchivo
					.getPath("/resources/reports/jxrml/rptPrueba1.jasper");
			
			String path = ExportarArchivo
					.getPath("/resources/reports/jxrml/rptCronogramaPDF.jasper");*/
//			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptComprobante.jasper");
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/comprobanteBol.jasper");
			
//			System.out.println(" path ===================> "+path);
//			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

			byte[] bytes;
			
			/* bytes = ExportarArchivo.exportXlsX(path, input, lstCroBean, false);
			ExportarArchivo.executeExcel(bytes, dni+"-"+nombreCompleto+".xlsx");*/
//			System.out.println("p_numero_documento =============>"+input.get("p_numero_documento"));
			bytes = ExportarArchivo.exportPdf(path, input,listaDetalleReporte); 
//			try (OutputStream out = new FileOutputStream("C:\\PDF\\boleta.pdf")) {
//				   out.write(bytes);
//				}
			
//			imprimir();
			
			if(this.comprobanteSelec.getTipo_comprobante().equalsIgnoreCase("01")){
				//imprimirFactura(input,listaDetalleReporte);
				imprimirFacturaTexto(input,listaDetalleReporte);
			}else{
				if(this.comprobanteSelec.getTipo_comprobante().equalsIgnoreCase("03")){
					//imprimirCadenaBoleta(input,listaDetalleReporte);
					imprimirBoletaTexto(input,listaDetalleReporte);
				}
			}

//			ExportarArchivo.executePdf(bytes, "comprobante.pdf");
//			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


public void imprimir(){
	try {
		PDDocument document=PDDocument.load(new File("C:\\PDF\\boleta.pdf"));
	} catch (InvalidPasswordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



/*
public void imprimirFactura(Map<String, Object> input,List<DetalleComprobanteRep> listaDetalleReporte){
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
		cadena=cadena+"*** FACTURA ELECTRONICA ***"+"\n";
		cadena=cadena+"            "+ input.get("p_numero_documento")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"* * * "+input.get("p_emisor_razonsocial")+"  * * * "+"\n";
		cadena=cadena+"      "+input.get("p_direccion_emisor")+"\n";
//		cadena=cadena+"		CHORRILLOS - LIMA - LIMA"+"\n";
//		cadena=cadena+" C.C. PLZ SAN MIGUEL AV. LA MARINA 2000 T99"+"\n";
//		cadena=cadena+"		SAN MIGUEL - LIMA - LIMA"+"\n";
		cadena=cadena+"RUC: "+input.get("p_ruc")+"\n";
		cadena=cadena+"FECHA EMISION: "+input.get("p_fec_emision")+" HORA:"+input.get("p_hora_emision")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"=================================="+"\n";
		
		for(DetalleComprobanteRep det :listaDetalleReporte){
			
			String descripcionPorducto= ""; 
			String espacio= "";
			
			if(det.getDes_producto().length()>10){
				descripcionPorducto= det.getDes_producto().substring(0, 10);
			}else{
				int tamaño = det.getDes_producto().length(); 
				for (int i =1; i<= (10 -tamaño); i++){
					espacio= espacio + " "; 
				}
				descripcionPorducto= det.getDes_producto()+ espacio;
				
			}
			
			cadena=cadena+ descripcionPorducto+"    "
						 +det.getCant_unidades_item_det()+"  "		
						//+ (new BigDecimal(det.getValor_item()).divide(new BigDecimal(det.getCant_unidades_item_det()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()+"  "
//								+ (new BigDecimal(det.getValor_item()).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal(det.getCant_unidades_item_det()).setScale(2, BigDecimal.ROUND_HALF_EVEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN)).toString()+"  "
						 +det.getValor_item()+"  "
						 +det.getPrecio_venta_unitario_det()+"\n";
		}

		cadena=cadena+"=================================="+"\n";
		cadena=cadena+"OP. GRAVADAS		S/. :"+input.get("p_op_gravadas")+"\n";
		cadena=cadena+"OP. GRATUITAS	S/. "+"\n";
		cadena=cadena+"OP. EXONERADAS	S/. "+"\n";
		cadena=cadena+"OP. INAFECTAS	S/. "+"\n";
		cadena=cadena+"TOTAL DEC. GLOBAL	S/. "+"\n";
		cadena=cadena+"I.G.V. 			S/. :"+input.get("p_igv")+"\n";		
		cadena=cadena+"* * * TOTAL VENTA : S/."+input.get("p_importe_total")+"\n";
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
		cadena=cadena+"facturacionelectronica@suempresa.com"+"\n";
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
	
	public void imprimirCadenaBoleta(Map<String, Object> input,List<DetalleComprobanteRep> listaDetalleReporte ){
		
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
		cadena=cadena+"*** BOLETA DE VENTA ELECTRONICA ***"+"\n";
		cadena=cadena+"            "+ input.get("p_numero_documento")+"\n";
		cadena=cadena+"\n";
		cadena=cadena+"* * * "+input.get("p_emisor_razonsocial")+"  * * * "+"\n";
		cadena=cadena+"      "+input.get("p_direccion_emisor")+"\n";
//		cadena=cadena+"		CHORRILLOS - LIMA - LIMA"+"\n";
//		cadena=cadena+" C.C. PLZ SAN MIGUEL AV. LA MARINA 2000 T99"+"\n";
//		cadena=cadena+"		SAN MIGUEL - LIMA - LIMA"+"\n";
		cadena=cadena+"RUC: "+input.get("p_ruc")+"\n";
		cadena=cadena+input.get("p_fec_emision")+"\n";
		cadena=cadena+"\n";
		
		

		
		for(DetalleComprobanteRep det :listaDetalleReporte){
			
			String descripcionPorducto= ""; 
			String espacio= "";
			
			if(det.getDes_producto().length()>10){
				descripcionPorducto= det.getDes_producto().substring(0, 10);
			}else{
				int tamaño = det.getDes_producto().length(); 
				for (int i =1; i<= (10 -tamaño); i++){
					espacio= espacio + " "; 
				}
				descripcionPorducto= det.getDes_producto()+ espacio;
				
			}
			
			
			cadena=cadena+ descripcionPorducto +"    "
						 +det.getCant_unidades_item_det()+"  "
//								 + (new BigDecimal(det.getValor_item()).divide(new BigDecimal(det.getCant_unidades_item_det()))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()+"  "						 
						 +det.getValor_item()+"  " 
						+det.getPrecio_venta_unitario_det()+"\n";
		}

		cadena=cadena+"\n";
		cadena=cadena+"OP. GRAVADAS		S/. :"+input.get("p_op_gravadas")+"\n";
		cadena=cadena+"OP. GRATUITAS	S/. "+"\n";
		cadena=cadena+"OP. EXONERADAS	S/. "+"\n";
		cadena=cadena+"OP. INAFECTAS	S/. "+"\n";
		cadena=cadena+"TOTAL DEC. GLOBAL	S/. "+"\n";
		cadena=cadena+"I.G.V. 			S/. :"+input.get("p_igv")+"\n";	
		cadena=cadena+"* * * TOTAL VENTA : S/."+input.get("p_importe_total")+"\n";
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
		cadena=cadena+"facturacionelectronica@suempresa.com"+"\n";
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
	
	*/

	
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
	
	

	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

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

	public List<ComprobanteDetalle> getListaComprobanteDetalle() {
		return listaComprobanteDetalle;
	}

	public void setListaComprobanteDetalle(
			List<ComprobanteDetalle> listaComprobanteDetalle) {
		this.listaComprobanteDetalle = listaComprobanteDetalle;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
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

	public List<ModoPago> getListModoPagos() {
		return listModoPagos;
	}

	public void setListModoPagos(List<ModoPago> listModoPagos) {
		this.listModoPagos = listModoPagos;
	}

	public Emisor getEmisorSelec() {
		return emisorSelec;
	}

	public void setEmisorSelec(Emisor emisorSelec) {
		this.emisorSelec = emisorSelec;
	}

	public boolean isAdicionar() {
		return adicionar;
	}

	public void setAdicionar(boolean adicionar) {
		this.adicionar = adicionar;
	}

	public boolean isGenerarComprobante() {
		return generarComprobante;
	}

	public void setGenerarComprobante(boolean generarComprobante) {
		this.generarComprobante = generarComprobante;
	}

	public boolean isIngresarCliente() {
		return ingresarCliente;
	}

	public void setIngresarCliente(boolean ingresarCliente) {
		this.ingresarCliente = ingresarCliente;
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

	public ComprobanteDetalleService getComprobanteDetalleService() {
		return comprobanteDetalleService;
	}

	public void setComprobanteDetalleService(ComprobanteDetalleService comprobanteDetalleService) {
		this.comprobanteDetalleService = comprobanteDetalleService;
	}

	public DomicilioFiscalService getDomicilioFiscalService() {
		return domicilioFiscalService;
	}

	public void setDomicilioFiscalService(DomicilioFiscalService domicilioFiscalService) {
		this.domicilioFiscalService = domicilioFiscalService;
	}

	public TributoProductoService getTributoProductoService() {
		return tributoProductoService;
	}

	public void setTributoProductoService(TributoProductoService tributoProductoService) {
		this.tributoProductoService = tributoProductoService;
	}

	public EmisorService getEmisorService() {
		return emisorService;
	}

	public void setEmisorService(EmisorService emisorService) {
		this.emisorService = emisorService;
	}

	public ModoPagoService getModoPagoService() {
		return modoPagoService;
	}

	public void setModoPagoService(ModoPagoService modoPagoService) {
		this.modoPagoService = modoPagoService;
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

	
	  
}
