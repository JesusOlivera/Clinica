package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Sistema;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.TipoServicio;
import com.certicom.scpf.domain.TributoProducto;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TipoServicioService;
import com.certicom.scpf.services.TributoProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="productoMB")
@ViewScoped
public class ProductoMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Producto productoSelec;
	private List<Producto> listaProductos;
	private Boolean editarProducto;
	private MenuServices menuServices;
	private ProductoService productoService;
	private TributoProducto tributoProductoIGV;
	private TributoProducto tributoProductoISC;
	private TributoProducto tributoProductoOtros;
	private List<TributoProducto> listTributoProductos;
	private TributoProductoService tributoProductoService;
	private Integer tipoTributoDet;
	private boolean bIGV;
	private boolean bISC;
	private boolean bOTROS;
	
	private boolean categoriaArticulo;
	
	private List<Producto> listaFiltroProductos;
	
	private TipoServicioService tipoServicioService;
	private List<TipoServicio> listaTipoServicio;
	
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetallesTipoTributo;
	private List<TablaTablasDetalle> listTablaTablasDetallesTipoProductoSunat;
	private List<TablaTablasDetalle> listTablaTablasDetallesUnidadMedida;
	private List<TablaTablasDetalle> listTablaTablasDetallesAfectacionIGV;
	private List<TablaTablasDetalle> listTablaTablasDetallesSistemaCalculoISC;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ProductoMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.productoSelec = new Producto();
		this.productoService = new ProductoService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.tributoProductoService = new TributoProductoService();
		this.menuServices=new MenuServices();
		this.tributoProductoIGV = new TributoProducto();
		this.tributoProductoISC = new TributoProducto();
		this.tributoProductoOtros = new TributoProducto();
		this.listaProductos = new ArrayList<Producto>();
		
		this.editarProducto = Boolean.FALSE;
		this.categoriaArticulo=Boolean.FALSE;////SERVICIO
		
		this.tipoServicioService= new TipoServicioService();

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaProductos = this.productoService.findAll();
			this.listaTipoServicio=this.tipoServicioService.findAll();
			this.listTablaTablasDetallesTipoProductoSunat = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listTablaTablasDetallesUnidadMedida = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_UNIDADES_DE_MEDIDA);
			this.listTablaTablasDetallesAfectacionIGV = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DE_AFECTACION_DEL_IGV);
			this.listTablaTablasDetallesSistemaCalculoISC = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DE_SISTEMA_DE_CALCULO_DEL_ISC);
			this.listTablaTablasDetallesTipoTributo = this.tablaTablasDetalleService.findBySinIGVSinISC();
			
			this.tributoProductoIGV = new TributoProducto();
			/*TablaTablasDetalle ttdIGV = new TablaTablasDetalle();
			ttdIGV = this.tablaTablasDetalleService.findById(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
			this.tributoProductoIGV.setTipo_tributo_det(ttdIGV.getId_codigo());
			this.tributoProductoIGV.setTipo_tributo_codigo_det(ttdIGV.getValor());
			this.tributoProductoIGV.setTipo_tributo_inter_det(ttdIGV.getDescripcion_corto());
			this.tributoProductoIGV.setTipo_tributo_nombre_det(ttdIGV.getDescripcion_largo());*/
			
			this.tributoProductoISC = new TributoProducto();
			/*TablaTablasDetalle ttdISC = new TablaTablasDetalle();
			ttdISC = this.tablaTablasDetalleService.findById(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
			this.tributoProductoISC.setTipo_tributo_det(ttdISC.getId_codigo());
			this.tributoProductoISC.setTipo_tributo_codigo_det(ttdISC.getValor());
			this.tributoProductoISC.setTipo_tributo_inter_det(ttdISC.getDescripcion_corto());
			this.tributoProductoISC.setTipo_tributo_nombre_det(ttdISC.getDescripcion_largo());*/
			
			this.tributoProductoOtros = new TributoProducto();
			
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:producto");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public void cambiarEstadoArticulo(){
		
		if(this.productoSelec.getEs_servicio()){
			this.categoriaArticulo=Boolean.FALSE;
		}else{
			this.categoriaArticulo=Boolean.TRUE;
		}
		
	}
	
	/* para tabla maestra */
	
	public void guardarProducto(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarProducto) {
				this.productoService.actualizarProducto(this.productoSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el producto : " + this.productoSelec.getDescripcion_prod_det());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El producto ha sido actualizado", 3);
			}else{
				this.productoService.crearProducto(this.productoSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el producto : " + this.productoSelec.getDescripcion_prod_det());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Producto ha sido creado", 3);
			}
			
			this.productoSelec = new Producto();
			this.editarProducto = Boolean.FALSE;
			
			this.listaProductos = this.productoService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void cambiarEstadoGeneraCola(Producto producto){
		
		   if(producto.getGenera_cola()){
			   //System.out.println("es igual a uno se pone a cero");
			   producto.setGenera_cola(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   producto.setGenera_cola(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.productoService.actualizarProducto(producto);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de Genera Cola",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + producto.getGenera_cola());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void cambiarEstadoHistoriaClinica(Producto producto){
		
		   if(producto.getGenera_historia_clinica()){
			   //System.out.println("es igual a uno se pone a cero");
			   producto.setGenera_historia_clinica(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   producto.setGenera_historia_clinica(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.productoService.actualizarProducto(producto);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de Historia Clinica",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + producto.getGenera_historia_clinica());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void cambiarEstadoGeneraTicket(Producto producto){
		
		   if(producto.getGenera_ticket()){
			   //System.out.println("es igual a uno se pone a cero");
			   producto.setGenera_ticket(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   producto.setGenera_ticket(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.productoService.actualizarProducto(producto);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de Genera Ticket",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + producto.getGenera_ticket());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void cambiarEstadoGeneraExamenAuxiliar(Producto producto){
		
		   if(producto.getExamen_auxiliar()){
			   //System.out.println("es igual a uno se pone a cero");
			   producto.setExamen_auxiliar(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
		   }else{
			   //System.out.println("es igual a cero");
			   producto.setExamen_auxiliar(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
		   }
		   
		   try {
			   this.productoService.actualizarProducto(producto);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de Genera Examen Auxiliar",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("Se actualizó el estado a  : " + producto.getExamen_auxiliar());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	
	
	public void nuevoProducto(){
		this.productoSelec = new Producto();
		this.editarProducto = Boolean.FALSE;
		this.categoriaArticulo=Boolean.FALSE;
	}

	public void editarProducto(Producto producto){
		this.productoSelec = producto;
		this.editarProducto = Boolean.TRUE;
		this.categoriaArticulo=!producto.getEs_servicio();
	}
	
	public void asignarTributoProducto(Producto producto){			
		try {
			this.productoSelec = producto;
			this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
			
			if(this.listTributoProductos.size()>0){
				boolean flagIGV=true, flagISC=true, flagOtros=true;
				for (TributoProducto tributoProducto : listTributoProductos) {
					if(tributoProducto.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
						this.tributoProductoIGV = new TributoProducto();
						this.tributoProductoIGV.setTipo_tributo_det(tributoProducto.getTipo_tributo_det());
						this.tributoProductoIGV.setTipo_tributo_codigo_det(tributoProducto.getTipo_tributo_codigo_det());
						this.tributoProductoIGV.setTipo_tributo_inter_det(tributoProducto.getTipo_tributo_inter_det());
						this.tributoProductoIGV.setTipo_tributo_nombre_det(tributoProducto.getTipo_tributo_nombre_det());
						this.tributoProductoIGV.setTipo_afectacion_igv_det(tributoProducto.getTipo_afectacion_igv_det());
						this.tributoProductoIGV.setPorcentaje_det(tributoProducto.getPorcentaje_det());
//						System.out.println("tributoProducto.getAsignado() "+tributoProducto.getAsignado());
						this.tributoProductoIGV.setAsignado(tributoProducto.getAsignado());
						this.bIGV = this.tributoProductoIGV.getAsignado();
						flagIGV=false;
					}else if(tributoProducto.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
						this.tributoProductoISC = new TributoProducto();
						this.tributoProductoISC.setTipo_tributo_det(tributoProducto.getTipo_tributo_det());
						this.tributoProductoISC.setTipo_tributo_codigo_det(tributoProducto.getTipo_tributo_codigo_det());
						this.tributoProductoISC.setTipo_tributo_inter_det(tributoProducto.getTipo_tributo_inter_det());
						this.tributoProductoISC.setTipo_tributo_nombre_det(tributoProducto.getTipo_tributo_nombre_det());
						this.tributoProductoISC.setTipo_sistema_isc_det(tributoProducto.getTipo_sistema_isc_det());
						this.tributoProductoISC.setPorcentaje_det(tributoProducto.getPorcentaje_det());
						this.tributoProductoISC.setAsignado(tributoProducto.getAsignado());
						this.bISC = this.tributoProductoISC.getAsignado();
						flagISC=false;
					}else{
						this.tributoProductoOtros = new TributoProducto();
						this.tributoProductoOtros.setTipo_tributo_det(tributoProducto.getTipo_tributo_det());
						this.tributoProductoOtros.setTipo_tributo_codigo_det(tributoProducto.getTipo_tributo_codigo_det());
						this.tributoProductoOtros.setTipo_tributo_inter_det(tributoProducto.getTipo_tributo_inter_det());
						this.tributoProductoOtros.setTipo_tributo_nombre_det(tributoProducto.getTipo_tributo_nombre_det());
						this.tributoProductoOtros.setPorcentaje_det(tributoProducto.getPorcentaje_det());
						this.tributoProductoOtros.setAsignado(tributoProducto.getAsignado());
						this.tipoTributoDet = tributoProducto.getTipo_tributo_det();
						this.bOTROS = this.tributoProductoOtros.getAsignado();
						flagOtros=false;
					}
				}
				
				if(flagIGV){
					this.tributoProductoIGV = new TributoProducto();
					TablaTablasDetalle ttdIGV = new TablaTablasDetalle();
					ttdIGV = this.tablaTablasDetalleService.findById(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
					this.tributoProductoIGV.setTipo_tributo_det(ttdIGV.getId_codigo());
					this.tributoProductoIGV.setTipo_tributo_codigo_det(ttdIGV.getValor());
					this.tributoProductoIGV.setTipo_tributo_inter_det(ttdIGV.getDescripcion_corto());
					this.tributoProductoIGV.setTipo_tributo_nombre_det(ttdIGV.getDescripcion_largo());
				}
				
				if(flagISC){
					this.tributoProductoISC = new TributoProducto();
					TablaTablasDetalle ttdISC = new TablaTablasDetalle();
					ttdISC = this.tablaTablasDetalleService.findById(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
					this.tributoProductoISC.setTipo_tributo_det(ttdISC.getId_codigo());
					this.tributoProductoISC.setTipo_tributo_codigo_det(ttdISC.getValor());
					this.tributoProductoISC.setTipo_tributo_inter_det(ttdISC.getDescripcion_corto());
					this.tributoProductoISC.setTipo_tributo_nombre_det(ttdISC.getDescripcion_largo());
				}
				
				if(flagOtros){
					this.tributoProductoOtros = new TributoProducto();
				}
			}else{
				this.tributoProductoIGV = new TributoProducto();
				TablaTablasDetalle ttdIGV = new TablaTablasDetalle();
				ttdIGV = this.tablaTablasDetalleService.findById(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
				this.tributoProductoIGV.setTipo_tributo_det(ttdIGV.getId_codigo());
				this.tributoProductoIGV.setTipo_tributo_codigo_det(ttdIGV.getValor());
				this.tributoProductoIGV.setTipo_tributo_inter_det(ttdIGV.getDescripcion_corto());
				this.tributoProductoIGV.setTipo_tributo_nombre_det(ttdIGV.getDescripcion_largo());
				
				this.tributoProductoISC = new TributoProducto();
				TablaTablasDetalle ttdISC = new TablaTablasDetalle();
				ttdISC = this.tablaTablasDetalleService.findById(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
				this.tributoProductoISC.setTipo_tributo_det(ttdISC.getId_codigo());
				this.tributoProductoISC.setTipo_tributo_codigo_det(ttdISC.getValor());
				this.tributoProductoISC.setTipo_tributo_inter_det(ttdISC.getDescripcion_corto());
				this.tributoProductoISC.setTipo_tributo_nombre_det(ttdISC.getDescripcion_largo());
				
				this.tributoProductoOtros = new TributoProducto();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarProducto(Producto producto){
//		System.out.println("eliminarProducto");  
		this.productoSelec = producto;
	}
	
	
	public void confirmaEliminarProducto(){
		try {
		
			this.productoService.eliminarProducto(this.productoSelec.getId_producto());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina el producto: " + this.productoSelec.getDescripcion_prod_det());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Producto ha sido eliminado", 3);
			
			this.listaProductos = this.productoService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarDatosOtros(){
		
		
		try {
			TablaTablasDetalle ttdISC = new TablaTablasDetalle();
			ttdISC = this.tablaTablasDetalleService.findById(this.tipoTributoDet);
			this.tipoTributoDet = ttdISC.getId_codigo();
			//this.tributoProductoOtros.setTipo_tributo_det(ttdISC.getId_codigo());
			this.tributoProductoOtros.setTipo_tributo_codigo_det(ttdISC.getValor());
			this.tributoProductoOtros.setTipo_tributo_inter_det(ttdISC.getDescripcion_corto());
			this.tributoProductoOtros.setTipo_tributo_nombre_det(ttdISC.getDescripcion_largo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void guardarTributosProducto(){
		
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			
			if(this.listTributoProductos.size()>0){
				boolean flagIGV=true, flagISC=true, flagOtros=true;
				for (TributoProducto tributoProducto : listTributoProductos) {

					if(tributoProducto.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
//						System.out.println("actualiza igv");
						if(this.bIGV){
							this.tributoProductoIGV.setId_producto(this.productoSelec.getId_producto());
							this.tributoProductoIGV.setTipo_tributo_det(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
	//						this.tributoProductoService.crearTributoProducto(this.tributoProductoIGV);
	//						System.out.println(" tributoProductoIGV : "+this.tributoProductoIGV.getPorcentaje_det());
							this.tributoProductoService.actualizarTributoProducto(this.tributoProductoIGV);
							
							//this.tributoProductoService.actualizarTributoProducto(this.tributoProductoIGV);
							flagIGV = false;
						}
					}else if(tributoProducto.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
//						System.out.println("actualiza isc");
						if(this.bISC){
							this.tributoProductoISC.setId_producto(this.productoSelec.getId_producto());
							this.tributoProductoISC.setTipo_tributo_det(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
	//						this.tributoProductoService.crearTributoProducto(this.tributoProductoISC);
	//						System.out.println(" tributoProductoISC : "+this.tributoProductoISC.getPorcentaje_det());
							this.tributoProductoService.actualizarTributoProducto(this.tributoProductoISC);
							
							//this.tributoProductoService.actualizarTributoProducto(this.tributoProductoISC);
							flagISC = false;
						}
					}else{
						if(this.bOTROS){
							this.tributoProductoOtros.setId_producto(this.productoSelec.getId_producto());
	//						this.tributoProductoService.crearTributoProducto(this.tributoProductoOtros);
	//						System.out.println(" tributoProductoISC : "+this.tributoProductoOtros.getPorcentaje_det());
							//this.tributoProductoService.actualizarTributoProducto(this.tributoProductoOtros);
							
							this.tributoProductoService.actualizarTributoProductoOtros(this.tributoProductoOtros, this.tipoTributoDet);
							flagOtros = false;
						}
//						flagIGV = false;
					}/*else{
//						System.out.println("actualiza otros");
						this.tributoProductoService.actualizarTributoProducto(this.tributoProductoOtros);
//						flagIGV = false;

					}*/
					
				}
//				System.out.println("flagIGV "+flagIGV);
				if(flagIGV){
					System.out.println("flagIGV");
					if(this.bIGV){
						this.tributoProductoIGV.setAsignado(this.bIGV);
						this.tributoProductoIGV.setId_producto(this.productoSelec.getId_producto());
						this.tributoProductoIGV.setTipo_tributo_det(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
						this.tributoProductoService.crearTributoProducto(this.tributoProductoIGV);
					}
				}
				
				if(flagISC){
//					System.out.println("bISC");
					if(this.bISC){
						this.tributoProductoISC.setAsignado(this.bISC);
						this.tributoProductoISC.setId_producto(this.productoSelec.getId_producto());
						this.tributoProductoISC.setTipo_tributo_det(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
						this.tributoProductoService.crearTributoProducto(this.tributoProductoISC);
					}
				}
				
				if(flagOtros){
//					System.out.println("bOTROS");
					if(this.bOTROS){
						this.tributoProductoOtros.setAsignado(this.bOTROS);
						this.tributoProductoOtros.setId_producto(this.productoSelec.getId_producto());
						this.tributoProductoService.crearTributoProducto(this.tributoProductoOtros);
					}
				}
				
			}else{
				if(this.bIGV){
					this.tributoProductoIGV.setAsignado(this.bIGV);
					this.tributoProductoIGV.setId_producto(this.productoSelec.getId_producto());
					this.tributoProductoIGV.setTipo_tributo_det(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS);
					this.tributoProductoService.crearTributoProducto(this.tributoProductoIGV);
				}
				
				if(this.bISC){
					this.tributoProductoISC.setAsignado(this.bISC);
					this.tributoProductoISC.setId_producto(this.productoSelec.getId_producto());
					this.tributoProductoISC.setTipo_tributo_det(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO);
					this.tributoProductoService.crearTributoProducto(this.tributoProductoISC);
				}
				
				if(this.bOTROS){
					this.tributoProductoOtros.setAsignado(this.bOTROS);
					this.tributoProductoOtros.setId_producto(this.productoSelec.getId_producto());
					this.tributoProductoService.crearTributoProducto(this.tributoProductoOtros);
				}
			}
			context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	   /**
     * cambia de estado
     * @param sistem
     */
   public void cambiarEstado(Producto producto){
	   
	  // if(sistem.getInd_activo().compareTo(new Integer(1)) == 0 ){
		   
	   if( producto.isValor_unit_incluye_impuestos() == true ){	   
		   //System.out.println("es igual a uno se pone a cero");
		   producto.setValor_unit_incluye_impuestos(false);
	   }else{
		   producto.setValor_unit_incluye_impuestos(true);
	   }
	   
	   System.out.println("entra a metodo " + producto.isValor_unit_incluye_impuestos());
	   try {
		   this.productoService.actualizarProducto(producto);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
	

	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
		
	public Producto getProductoSelec() {
		return productoSelec;
	}

	public void setProductoSelec(Producto productoSelec) {
		this.productoSelec = productoSelec;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Boolean getEditarProducto() {
		return editarProducto;
	}

	public void setEditarProducto(Boolean editarProducto) {
		this.editarProducto = editarProducto;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesTipoProductoSunat() {
		return listTablaTablasDetallesTipoProductoSunat;
	}

	public void setListTablaTablasDetallesTipoProductoSunat(
			List<TablaTablasDetalle> listTablaTablasDetallesTipoProductoSunat) {
		this.listTablaTablasDetallesTipoProductoSunat = listTablaTablasDetallesTipoProductoSunat;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesUnidadMedida() {
		return listTablaTablasDetallesUnidadMedida;
	}

	public void setListTablaTablasDetallesUnidadMedida(
			List<TablaTablasDetalle> listTablaTablasDetallesUnidadMedida) {
		this.listTablaTablasDetallesUnidadMedida = listTablaTablasDetallesUnidadMedida;
	}

	public TributoProducto getTributoProductoIGV() {
		return tributoProductoIGV;
	}

	public void setTributoProductoIGV(TributoProducto tributoProductoIGV) {
		this.tributoProductoIGV = tributoProductoIGV;
	}

	public TributoProducto getTributoProductoISC() {
		return tributoProductoISC;
	}

	public void setTributoProductoISC(TributoProducto tributoProductoISC) {
		this.tributoProductoISC = tributoProductoISC;
	}

	public TributoProducto getTributoProductoOtros() {
		return tributoProductoOtros;
	}

	public void setTributoProductoOtros(TributoProducto tributoProductoOtros) {
		this.tributoProductoOtros = tributoProductoOtros;
	}

	public boolean isbIGV() {
		return bIGV;
	}

	public void setbIGV(boolean bIGV) {
		this.bIGV = bIGV;
	}

	public boolean isbISC() {
		return bISC;
	}

	public void setbISC(boolean bISC) {
		this.bISC = bISC;
	}

	public boolean isbOTROS() {
		return bOTROS;
	}

	public void setbOTROS(boolean bOTROS) {
		this.bOTROS = bOTROS;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesAfectacionIGV() {
		return listTablaTablasDetallesAfectacionIGV;
	}

	public void setListTablaTablasDetallesAfectacionIGV(
			List<TablaTablasDetalle> listTablaTablasDetallesAfectacionIGV) {
		this.listTablaTablasDetallesAfectacionIGV = listTablaTablasDetallesAfectacionIGV;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesSistemaCalculoISC() {
		return listTablaTablasDetallesSistemaCalculoISC;
	}

	public void setListTablaTablasDetallesSistemaCalculoISC(
			List<TablaTablasDetalle> listTablaTablasDetallesSistemaCalculoISC) {
		this.listTablaTablasDetallesSistemaCalculoISC = listTablaTablasDetallesSistemaCalculoISC;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesTipoTributo() {
		return listTablaTablasDetallesTipoTributo;
	}

	public void setListTablaTablasDetallesTipoTributo(
			List<TablaTablasDetalle> listTablaTablasDetallesTipoTributo) {
		this.listTablaTablasDetallesTipoTributo = listTablaTablasDetallesTipoTributo;
	}

	public List<TributoProducto> getListTributoProductos() {
		return listTributoProductos;
	}

	public void setListTributoProductos(List<TributoProducto> listTributoProductos) {
		this.listTributoProductos = listTributoProductos;
	}

	public Integer getTipoTributoDet() {
		return tipoTributoDet;
	}

	public void setTipoTributoDet(Integer tipoTributoDet) {
		this.tipoTributoDet = tipoTributoDet;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public TributoProductoService getTributoProductoService() {
		return tributoProductoService;
	}

	public void setTributoProductoService(TributoProductoService tributoProductoService) {
		this.tributoProductoService = tributoProductoService;
	}

	public TipoServicioService getTipoServicioService() {
		return tipoServicioService;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
	}

	public List<TipoServicio> getListaTipoServicio() {
		return listaTipoServicio;
	}

	public void setListaTipoServicio(List<TipoServicio> listaTipoServicio) {
		this.listaTipoServicio = listaTipoServicio;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isCategoriaArticulo() {
		return categoriaArticulo;
	}

	public void setCategoriaArticulo(boolean categoriaArticulo) {
		this.categoriaArticulo = categoriaArticulo;
	}

	public List<Producto> getListaFiltroProductos() {
		return listaFiltroProductos;
	}

	public void setListaFiltroProductos(List<Producto> listaFiltroProductos) {
		this.listaFiltroProductos = listaFiltroProductos;
	}
	
}
