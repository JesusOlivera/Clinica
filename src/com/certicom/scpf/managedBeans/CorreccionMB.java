package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.ComprobanteDetalleService;
import com.certicom.scpf.services.ComprobanteService;
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ModoPagoService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.VendedorService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="correccionMB")
@ViewScoped
public class CorreccionMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private Comprobante comprobanteSelec;
	private String numeroComprobante;
	private ComprobanteService comprobanteService;
	private Boolean ingresarCliente;
	private Boolean adicionar;
	private Boolean generarComprobanteBoolean;
	private List<ModoPago> listModoPagos;
	private ModoPagoService modoPagoService;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<Vendedor> listaVendedores;
	private VendedorService vendedorService;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private List<ComprobanteDetalle> listaComprobanteDetalle;
	private ComprobanteDetalleService comprobanteDetalleService;
	private Comprobante comprobanteCorreccion;
	private TablaTablasDetalle tablaTablasDetalleTipoComprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesTipoNC;
	private DomicilioFiscal domicilioFiscalSelec;
	private DomicilioFiscalService domicilioFiscalService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private ProductoService productoService;
	private Integer id_modo_pago;
	private String tipo_tipo_nota_not;
	private String descripcion_motivo_not;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	
	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}

	@PostConstruct
	public void inicia(){
		
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.comprobanteService= new ComprobanteService();
		this.modoPagoService = new ModoPagoService();
		this.vendedorService = new VendedorService();
		this.comprobanteDetalleService=new ComprobanteDetalleService();
		this.domicilioFiscalService = new DomicilioFiscalService();
		this.productoService = new ProductoService();
		this.emisorService = new EmisorService();
		this.numeroComprobante="";
		this.ingresarCliente=Boolean.FALSE;
		this.adicionar=Boolean.TRUE;
		this.generarComprobanteBoolean=Boolean.TRUE;
		this.listaComprobanteDetalle = new ArrayList<ComprobanteDetalle>();
		this.comprobanteSelec=new Comprobante();
		this.comprobanteCorreccion=new Comprobante();
		this.menuServices=new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		try {
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listTablaTablasDetallesTipoNC=this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_NOTA_CRED);
			this.listModoPagos = this.modoPagoService.findAll();
			this.listaVendedores = this.vendedorService.findAll();
			
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:correccion");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buscarComprobante(){
		String numero_serie_documento_cab=this.numeroComprobante;
		this.generarComprobanteBoolean=Boolean.FALSE;
		this.ingresarCliente=Boolean.TRUE;
		System.out.println("numero_serie_documento_cab =======> "+numero_serie_documento_cab);
		this.comprobanteSelec=this.comprobanteService.getByNumeroSerieComprobante(numero_serie_documento_cab);
		try {
			if(comprobanteSelec!=null){
				this.listaComprobanteDetalle=this.comprobanteDetalleService.findByIdComprobante(this.comprobanteSelec.getId_comprobante());
			}
			
			System.out.println("detalle----------->"+listaComprobanteDetalle.size());
			this.listTablaTablasDetallesTipoNC=this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_NOTA_CRED);
			this.listModoPagos = this.modoPagoService.findAll();
			
			this.comprobanteCorreccion.setId_emisor(this.emisorSelec.getId_emisor());
			this.comprobanteCorreccion.setFecha_emision_cab(new Date()); /*Jesus*/
			this.comprobanteCorreccion.setFecha_vencimiento_cab(new Date()); /*Jesus*/
			this.comprobanteCorreccion.setHora_emision_cab(new Date());/*Jesus*/
			this.comprobanteCorreccion.setId_modo_pago(4); /* Jesus contado*/
			this.comprobanteCorreccion.setTipo_operacion_cab("0101"); /* Jesus Tipo Operacion*/
			this.comprobanteCorreccion.setTipo_moneda_cab("PEN"); /* Jesus Tipo Moneda*/
			this.comprobanteCorreccion.setId_vendedor(2);/*Vega.com*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setModoPago(){
		System.out.println("id_modo_pago=========>"+this.id_modo_pago);
		this.comprobanteCorreccion.setId_modo_pago(this.id_modo_pago);
	}
	public void setTipoNotCred(){
		this.comprobanteCorreccion.setTipo_tipo_nota_not(this.tipo_tipo_nota_not);		
	}
	
	public void setDescipcionMotivo(){
		this.comprobanteCorreccion.setDescripcion_motivo_not(this.descripcion_motivo_not);
	}
	
	
	public void generarComprobante(){
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			
			if(this.tipo_tipo_nota_not==null || this.descripcion_motivo_not==null){
				context.update("msgGeneral");
				context.update("formAction");
				FacesUtils.showFacesMessage("Se necesita ingresar el Tipo de Nota de Credito y el motivo ", 1);
			}else{
				
			
			System.out.println("  id_comprobante :"+this.comprobanteSelec.getId_comprobante());
			System.out.println("id_modo_pago=========>"+this.comprobanteCorreccion.getId_modo_pago());
			System.out.println("tipo_comprobante=========>"+this.comprobanteCorreccion.getTipo_comprobante());
			
			this.comprobanteCorreccion.setTipo_operacion_cab(this.comprobanteSelec.getTipo_operacion_cab());
			this.comprobanteCorreccion.setId_cliente(this.comprobanteSelec.getId_cliente());
			this.comprobanteCorreccion.setTipo_docu_iden_cab(this.comprobanteSelec.getTipo_docu_iden_cab());
			this.comprobanteCorreccion.setCliente(this.comprobanteSelec.getCliente());
			this.comprobanteCorreccion.setId_emisor(this.emisorSelec.getId_emisor());
			this.comprobanteCorreccion.setVersion_ubl(Constante.VERSION_UBL_SUNAT);
			this.comprobanteCorreccion.setEstado_comunicacion_baja(Boolean.FALSE);
			this.comprobanteCorreccion.setCorrelativo(this.comprobanteService.getCorrelativoComprobante(this.comprobanteCorreccion.getTipo_comprobante()));
			this.comprobanteCorreccion.setEstado_sunat(Constante.ESTADO_PENDIENTE);
			
			this.comprobanteCorreccion.setSuma_tributos_cab(this.comprobanteSelec.getSuma_tributos_cab());
			this.comprobanteCorreccion.setTotal_valor_venta_cab(this.comprobanteSelec.getTotal_valor_venta_cab());
			this.comprobanteCorreccion.setTotal_precio_venta_cab(this.comprobanteSelec.getTotal_precio_venta_cab());
			this.comprobanteCorreccion.setTotal_descuentos_cab(this.comprobanteSelec.getTotal_descuentos_cab());
			this.comprobanteCorreccion.setSuma_otros_cargos_cab(this.comprobanteSelec.getSuma_otros_cargos_cab());
			this.comprobanteCorreccion.setTotal_anticipos_cab(this.comprobanteSelec.getTotal_anticipos_cab());
			this.comprobanteCorreccion.setImporte_total_venta_cab(this.comprobanteSelec.getImporte_total_venta_cab());
			this.comprobanteCorreccion.setTipo_comprobante_afecta_not(this.comprobanteSelec.getTipo_comprobante());
			this.comprobanteCorreccion.setNumero_serie_documento_cab(this.comprobanteSelec.getNumero_serie_documento_cab());
			
			
			
			this.comprobanteService.crearComprobanteSec(this.comprobanteCorreccion);
			int id = this.comprobanteService.getSecIdComprobante();
			System.out.println("ID: "+id);
									
			this.comprobanteDetalleService.insertBatchComprobanteDetalle(this.listaComprobanteDetalle, id-1);
//			this.productoService.actualizarBatchStockProductoDevolucion(this.listaComprobanteDetalle); /*Actualizar stock*/
			
			context.update("msgGeneral");
			context.update("formAction");
			FacesUtils.showFacesMessage("Se registro el comprobante " + this.comprobanteSelec.getNumero_serie_documento_cab(), 3);
			
			
			
			
			
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listaVendedores = this.vendedorService.findAll(); /*Vega.com*/
			this.listModoPagos = this.modoPagoService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);

			this.comprobanteCorreccion= new Comprobante();
			this.comprobanteSelec= new Comprobante();
			this.comprobanteCorreccion.setId_emisor(this.emisorSelec.getId_emisor());
			this.comprobanteCorreccion.setFecha_emision_cab(new Date()); /*Jesus*/
			this.comprobanteCorreccion.setFecha_vencimiento_cab(new Date()); /*Jesus*/
			this.comprobanteCorreccion.setHora_emision_cab(new Date());/*Jesus*/
			this.comprobanteCorreccion.setId_modo_pago(4); /* Jesus contado*/
			this.comprobanteCorreccion.setTipo_operacion_cab("0101"); /* Jesus Tipo Operacion*/
			this.comprobanteCorreccion.setTipo_moneda_cab("PEN"); /* Jesus Tipo Moneda*/
			this.comprobanteCorreccion.setId_vendedor(2);/*Vega.com*/
			this.adicionar = Boolean.TRUE; /*Jesus*/
			this.generarComprobanteBoolean = Boolean.TRUE; /*Jesus*/
			this.ingresarCliente = Boolean.TRUE; /*Jesus*/
			this.id_modo_pago=0;
			this.descripcion_motivo_not="";
			this.tipo_tipo_nota_not="";
			this.numeroComprobante="";
			
			this.listaComprobanteDetalle= new ArrayList<ComprobanteDetalle>();
			}		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obtenerAbreviatura() throws Exception{
		
		System.out.println("------------->obtenerAbreviatura");
		
		String sTexto = "";		
		System.out.println(sTexto);		
		String sValor = "";
		
		
		for (TablaTablasDetalle tablaTablasDetalle : listTablaTablasDetallesComprobante) {
			System.out.println("codigo_catalogo : "+tablaTablasDetalle.getCodigo_catalogo());
			System.out.println("tipo_comprobante "+this.comprobanteCorreccion.getTipo_comprobante());
			if(tablaTablasDetalle.getCodigo_catalogo().equals(this.comprobanteCorreccion.getTipo_comprobante())){
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
		String sValue = String.valueOf(this.comprobanteService.getCorrelativoComprobante(this.comprobanteCorreccion.getTipo_comprobante()));
		System.out.println("sValue ===> "+sValue);
		for (int i = 0; i < (cantComprobante - sValue.length()); i++) {			
			com = com + "0";			
		}			
		com = com + sValue;		
		
//		this.comprobanteCorreccion.setNumero_serie_documento_cab(sPalabra+dom+"-"+com);		
		this.comprobanteCorreccion.setId_domicilio_fiscal_cab(this.domicilioFiscalSelec.getId_domicilio_fiscal_cab());				
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


	public Comprobante getComprobanteSelec() {
		return comprobanteSelec;
	}


	public void setComprobanteSelec(Comprobante comprobanteSelec) {
		this.comprobanteSelec = comprobanteSelec;
	}


	public String getNumeroComprobante() {
		return numeroComprobante;
	}


	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ComprobanteService getComprobanteService() {
		return comprobanteService;
	}

	public void setComprobanteService(ComprobanteService comprobanteService) {
		this.comprobanteService = comprobanteService;
	}

	public Boolean getIngresarCliente() {
		return ingresarCliente;
	}

	public void setIngresarCliente(Boolean ingresarCliente) {
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

	public List<TablaTablasDetalle> getListTablaTablasDetallesProducto() {
		return listTablaTablasDetallesProducto;
	}

	public void setListTablaTablasDetallesProducto(List<TablaTablasDetalle> listTablaTablasDetallesProducto) {
		this.listTablaTablasDetallesProducto = listTablaTablasDetallesProducto;
	}

	public Boolean getAdicionar() {
		return adicionar;
	}

	public void setAdicionar(Boolean adicionar) {
		this.adicionar = adicionar;
	}

	public List<ComprobanteDetalle> getListaComprobanteDetalle() {
		return listaComprobanteDetalle;
	}

	public void setListaComprobanteDetalle(List<ComprobanteDetalle> listaComprobanteDetalle) {
		this.listaComprobanteDetalle = listaComprobanteDetalle;
	}

	public ComprobanteDetalleService getComprobanteDetalleService() {
		return comprobanteDetalleService;
	}

	public void setComprobanteDetalleService(ComprobanteDetalleService comprobanteDetalleService) {
		this.comprobanteDetalleService = comprobanteDetalleService;
	}

	public Boolean getGenerarComprobanteBoolean() {
		return generarComprobanteBoolean;
	}

	public void setGenerarComprobanteBoolean(Boolean generarComprobanteBoolean) {
		this.generarComprobanteBoolean = generarComprobanteBoolean;
	}

	public Comprobante getComprobanteCorreccion() {
		return comprobanteCorreccion;
	}

	public void setComprobanteCorreccion(Comprobante comprobanteCorreccion) {
		this.comprobanteCorreccion = comprobanteCorreccion;
	}

	public TablaTablasDetalle getTablaTablasDetalleTipoComprobante() {
		return tablaTablasDetalleTipoComprobante;
	}

	public void setTablaTablasDetalleTipoComprobante(TablaTablasDetalle tablaTablasDetalleTipoComprobante) {
		this.tablaTablasDetalleTipoComprobante = tablaTablasDetalleTipoComprobante;
	}

	public DomicilioFiscal getDomicilioFiscalSelec() {
		return domicilioFiscalSelec;
	}

	public void setDomicilioFiscalSelec(DomicilioFiscal domicilioFiscalSelec) {
		this.domicilioFiscalSelec = domicilioFiscalSelec;
	}

	public DomicilioFiscalService getDomicilioFiscalService() {
		return domicilioFiscalService;
	}

	public void setDomicilioFiscalService(DomicilioFiscalService domicilioFiscalService) {
		this.domicilioFiscalService = domicilioFiscalService;
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

	public List<TablaTablasDetalle> getListTablaTablasDetallesTipoNC() {
		return listTablaTablasDetallesTipoNC;
	}

	public void setListTablaTablasDetallesTipoNC(List<TablaTablasDetalle> listTablaTablasDetallesTipoNC) {
		this.listTablaTablasDetallesTipoNC = listTablaTablasDetallesTipoNC;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public String getTipo_tipo_nota_not() {
		return tipo_tipo_nota_not;
	}

	public void setTipo_tipo_nota_not(String tipo_tipo_nota_not) {
		this.tipo_tipo_nota_not = tipo_tipo_nota_not;
	}

	public String getDescripcion_motivo_not() {
		return descripcion_motivo_not;
	}

	public void setDescripcion_motivo_not(String descripcion_motivo_not) {
		this.descripcion_motivo_not = descripcion_motivo_not;
	}
	
	
	

}
