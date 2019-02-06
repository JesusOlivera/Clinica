package src.com.certicom.scpf.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.certicom.scpf.domain.ArchivoPlano;

public class GeneraArchivoPlano {
	

    public static void crearArchivoPlano(ArrayList<String> lista, String rutaDescarga, String nombre, String extension){
    	       
        try {      	
		        	System.out.println("crear");
		        	
		          //   	"C:/SFS_v1.2/sunat_archivos/sfs/DATA/"
		          //     C:/Nueva carpeta/	
		        	
		            String ruta = rutaDescarga + nombre + extension;
		            File file = new File(ruta);
		            if (!file.exists()) {
		                file.createNewFile();
		            }
		            FileWriter fw = new FileWriter(file);
		            BufferedWriter bw = new BufferedWriter(fw);
		            
		            for (int i=0 ; i< lista.size(); i++ ){
		            	bw.write(lista.get(i));
		                bw.newLine();        	
		            }     
		            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
     public static void enviarFacturador( ArrayList<ArchivoPlano> listaArchivoCabecera,
    		                        ArrayList<ArchivoPlano> listaArchivoNota,
    		                        ArrayList<ArchivoPlano> listaArchivoDetalle,
    		                        ArrayList<ArchivoPlano> listaArchivoTributos,
    		                        ArrayList<ArchivoPlano> listaArchivoLeyenda, 
    		                        ArrayList<ArchivoPlano> listaArchivoBaja, 
    		                        String  rutaDescarga,
    		                        String  serie,
    		                        String  correlativo, 
    		                        String  tipo_comprobante, 
    		                        String  ruc, 
    		                        String  fecha_envio_Baja,
    		                        String  correlativo_baja){ 
    	 
         try {  
        	 
		        	 String nombre = ""; 
		        	 
		        	 /*Generamos para Facturas 01  Boletas 03*/
		         	 
		         	 if(tipo_comprobante.equals("01") || tipo_comprobante.equals("03")){
		         		 
//		            	 nombre = ruc + "-" + tipo_comprobante + "-" + serie + "-" + correlativo; 
		         		nombre = ruc + "-" + tipo_comprobante + "-" + serie; 
		            	        	 
		         		 ArrayList<String> listaCabecera = new ArrayList<String>();
		             	 ArrayList<String> listaDetalle = new ArrayList<String>();
		             	 ArrayList<String> listaTributos = new ArrayList<String>();
		             	 ArrayList<String> listaLeyenda = new ArrayList<String>();
		             	 
		            	 Parsear (listaCabecera, listaArchivoCabecera, ".CAB"); 
		            	 Parsear (listaDetalle,  listaArchivoDetalle,  ".DET"); 
		            	 Parsear (listaTributos, listaArchivoTributos, ".TRI"); 
		            	 Parsear (listaLeyenda,  listaArchivoLeyenda,  ".LEY"); 
		             	 
		                 crearArchivoPlano(listaCabecera, rutaDescarga,  nombre, ".CAB");
		                 crearArchivoPlano(listaDetalle,  rutaDescarga,  nombre, ".DET");
		                 crearArchivoPlano(listaTributos, rutaDescarga,  nombre, ".TRI");
		                 crearArchivoPlano(listaLeyenda,  rutaDescarga,  nombre, ".LEY");	 
		         	 }
		         	         
		             
		         	 /*Generamos para Nota de Credito 07 Debito 08*/
		         	 
		         	if(tipo_comprobante.equals("07") || tipo_comprobante.equals("08")){ 
		              	 
//		         		 nombre = ruc + "-" + tipo_comprobante + "-" + serie + "-" + correlativo; 
		         		 nombre = ruc + "-" + tipo_comprobante + "-" + serie; 
		         		
		         		 ArrayList<String> listaNota     = new ArrayList<String>();
		             	 ArrayList<String> listaDetalle  = new ArrayList<String>();
		             	 ArrayList<String> listaTributos = new ArrayList<String>();
		             	 ArrayList<String> listaLeyenda  = new ArrayList<String>();
		             	          	 
		            	 Parsear (listaNota,     listaArchivoNota,     ".NOT"); 
		            	 Parsear (listaDetalle,  listaArchivoDetalle,  ".DET"); 
		            	 Parsear (listaTributos, listaArchivoTributos, ".TRI"); 
		            	 Parsear (listaLeyenda,  listaArchivoLeyenda,  ".LEY"); 
		             	 
		    	         crearArchivoPlano(listaNota,     rutaDescarga, nombre, ".NOT");
		    	         crearArchivoPlano(listaDetalle,  rutaDescarga, nombre, ".DET");
		    	         crearArchivoPlano(listaTributos, rutaDescarga, nombre, ".TRI");
		    	         crearArchivoPlano(listaLeyenda,  rutaDescarga, nombre, ".LEY");
		         	}
		             
		             /*Comunicacion de Baja  RA */
		         	
		         	 if(tipo_comprobante.equals("RA")){
		         		 
		         		 nombre = ruc + "-" + tipo_comprobante + "-" + fecha_envio_Baja + "-" + correlativo_baja;  
		         		 
		         		 ArrayList<String> listaBaja = new ArrayList<String>();
		         		 
		         		 Parsear (listaBaja, listaArchivoBaja, ".CBA"); 
		         		 
		         		 crearArchivoPlano(listaBaja, rutaDescarga, nombre, ".CBA");
		         	 }
        	 
         } catch (Exception e) {
             e.printStackTrace();
         }
    	  


     }
     
     
     public static void Parsear(ArrayList<String> lista ,ArrayList<ArchivoPlano> listaArchivoPlano, String extesion){ 
    	 
    	 
    	    try {  
    	 
			            System.out.println("Parsear");
			    	  	
			           /* 
			    	  	for (int j=0; j<30; j++){
			    	  		String concatenado = ""; 
			    	  		concatenado = concatenado + extesion + j + "|";
			    	  		concatenado = concatenado + extesion + j + "|";
			    	  		concatenado = concatenado + extesion + j + "|";
			    	  		lista.add(concatenado); 
			    	  	}
			    	  	*/
			    	  	
			    	  	if (extesion.equals(".CAB")){
			    	  		
			    	  		for(ArchivoPlano a : listaArchivoPlano){
			    	  			String concatenado = ""; 
			    	  			
			    	  			concatenado= concatenado + a.getTipo_operacion_cab() + "|";
			    	  			concatenado= concatenado + a.getFecha_emision_cab() + "|"; 
			    	  			concatenado= concatenado + a.getHora_emision_cab() + "|"; 
			    	  			concatenado= concatenado + a.getFecha_vencimiento_cab() + "|"; 
			    	  			concatenado= concatenado + a.getDomicilio_fiscal_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTipo_docu_iden_cab() + "|"; 
			    	  			concatenado= concatenado + a.getNumero_docu_iden_cab() + "|"; 
			    	  			concatenado= concatenado + a.getNombre_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTipo_moneda_cab() + "|"; 
			    	  			concatenado= concatenado + a.getSuma_tributos_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTotal_valor_venta_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTotal_precio_venta_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTotal_descuentos_cab() + "|"; 
			    	  			concatenado= concatenado + a.getSuma_otros_cargos_cab() + "|"; 
			    	  			concatenado= concatenado + a.getTotal_anticipos_cab() + "|"; 
			    	  			concatenado= concatenado + a.getImporte_total_venta_cab() + "|"; 
			    	  			concatenado= concatenado + a.getVersion_ubl_cab() + "|"; 
			    	  			concatenado= concatenado + a.getCustomizacion_documento_cab() + "|"; 
			    	  								
			    	  			lista.add(concatenado); 
			    	  		} 	  	
			    	  	}
			    	  	
			           if (extesion.equals(".DET")){
			    	  		
			    	  		for(ArchivoPlano a : listaArchivoPlano){
			    	  			String concatenado = ""; 
			    	  			
			    	  			concatenado= concatenado + a.getUnidad_medida_det() + "|";
			    	  			concatenado= concatenado + a.getCant_unidades_item_det() + "|";
			    	  			concatenado= concatenado + a.getCod_prod_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_prod_sunat_det() + "|";
			    	  			concatenado= concatenado + a.getDescripcion_prod_det() + "|";
			    	  			concatenado= concatenado + a.getValor_unitario_prod_det() + "|";
			    	  			concatenado= concatenado + a.getSuma_tributos_det() + "|";
			    	  			
			    	  			concatenado= concatenado + a.getTipo_tributo_igv_det() + "|";
			    	  			concatenado= concatenado + a.getMonto_tributo_igv_det() + "|";
			    	  			concatenado= concatenado + a.getBase_imponible_igv_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_nombre_igv_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_inter_igv_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_afectacion_igv_det() + "|";
			    	  			concatenado= concatenado + a.getPorcentaje_igv_det() + "|";
			    	  			    	  			
			    	  			concatenado= concatenado + a.getTipo_tributo_isc_det() + "|";
			    	  			concatenado= concatenado + a.getMonto_tributo_isc_det() + "|";
			    	  			concatenado= concatenado + a.getBase_imponible_isc_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_nombre_isc_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_inter_isc_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_sistema_isc_det() + "|";
			    	  			concatenado= concatenado + a.getPorcentaje_isc_det() + "|";
			    	  			
			    	  			concatenado= concatenado + a.getTipo_tributo_otros_det() + "|";
			    	  			concatenado= concatenado + a.getMonto_tributo_otros_det() + "|";
			    	  			concatenado= concatenado + a.getBase_imponible_otros_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_nombre_otros_det() + "|";
			    	  			concatenado= concatenado + a.getTipo_tributo_inter_otros_det() + "|";
			    	  			concatenado= concatenado + a.getPorcentaje_otros_det() + "|";
			    	  			
			    	  			
			    	  			concatenado= concatenado + a.getPrecio_venta_unitario_det() + "|";
			    	  			concatenado= concatenado + a.getValor_venta_item_det() + "|";
			    	  			concatenado= concatenado + a.getValor_referencial_unit_det() + "|";   	  			
			    	  			
			    	  			
			    	  			lista.add(concatenado); 
			    	  		   } 
			              } 
			    	  		
			    	  		
			    	  		if (extesion.equals(".CBA")){
			        	  		
			        	  		for(ArchivoPlano a : listaArchivoPlano){
			        	  			String concatenado = ""; 
			        	  			
			        	  			concatenado= concatenado + a.getFecha_emision_cba() + "|";
			        	  			concatenado= concatenado + a.getFecha_comunicacion_cba() + "|"; 
			        	  			concatenado= concatenado + a.getTipo_comprobante_cba() + "|"; 
			        	  			concatenado= concatenado + a.getNumero_serie_documento_cba() + "|"; 
			        	  			concatenado= concatenado + a.getDescripcion_motivo_cba() + "|"; 
			        	  							
			        	  			lista.add(concatenado); 
			        	  		} 	  	
			        	  	}
			    	  		
			    	  		
			    	  		
					  		if (extesion.equals(".NOT")){
				        	  		
				        	  		for(ArchivoPlano a : listaArchivoPlano){
				        	  			String concatenado = ""; 
				        	  			
				        	  			concatenado= concatenado + a.getTipo_operacion_not() + "|";
				        	  			concatenado= concatenado + a.getFecha_emision_not() + "|"; 
				        	  			concatenado= concatenado + a.getHora_emision_not() + "|"; 
				        	  			concatenado= concatenado + a.getDomicilio_fiscal_not() + "|"; 
				        	  			concatenado= concatenado + a.getTipo_docu_iden_not() + "|"; 
				        	  			concatenado= concatenado + a.getNumero_docu_iden_not() + "|"; 
				        	  			concatenado= concatenado + a.getNombre_not() + "|"; 
				        	  			concatenado= concatenado + a.getTipo_moneda_not() + "|"; 
				        	  			concatenado= concatenado + a.getTipo_tipo_nota_not() + "|"; 
				        	  			concatenado= concatenado + a.getDescripcion_motivo_not() + "|"; 
				        	  			concatenado= concatenado + a.getTipo_comprobante_afecta_not() + "|"; 
				        	  			concatenado= concatenado + a.getNumero_serie_documento_not() + "|"; 
				        	  			concatenado= concatenado + a.getSuma_tributos_not() + "|"; 
				        	  			concatenado= concatenado + a.getTotal_valor_venta_not() + "|"; 
				        	  			concatenado= concatenado + a.getTotal_precio_venta_not() + "|"; 
				        	  			concatenado= concatenado + a.getTotal_descuentos_not() + "|"; 
				        	  			concatenado= concatenado + a.getSuma_otros_cargos_not() + "|"; 
				        	  			concatenado= concatenado + a.getTotal_anticipos_not() + "|"; 
				        	  			concatenado= concatenado + a.getImporte_total_venta_not() + "|"; 
				        	  			concatenado= concatenado + a.getVersion_ubl_not() + "|"; 
				        	  			concatenado= concatenado + a.getCustomizacion_documento_not() + "|"; 
				        	  										
				        	  			lista.add(concatenado); 
				        	  		} 	  	
				        	  	}
					  		
					  		
			    	  		if (extesion.equals(".TRI")){
			        	  		
			        	  		for(ArchivoPlano a : listaArchivoPlano){
			        	  			String concatenado = ""; 
			        	  			
			        	  			concatenado= concatenado + a.getCodigo_catalogo_tri() + "|";
			        	  			concatenado= concatenado + a.getDescripcion_corto_tri() + "|"; 
			        	  			concatenado= concatenado + a.getAtributo_1_tri() + "|"; 
			        	  			concatenado= concatenado + a.getTotal_valor_venta_tri() + "|"; 
			        	  			concatenado= concatenado + a.getSuma_tributos_tri() + "|"; 
			        	  							
			        	  			lista.add(concatenado); 
			        	  		} 	  	
			        	  	}
			    	  		
			    	  		
			    	  		if (extesion.equals(".LEY")){
			        	  		
			        	  		for(ArchivoPlano a : listaArchivoPlano){
			        	  			String concatenado = ""; 
			        	  			
			        	  			concatenado= concatenado + a.getCodigo_catalogo_ley() + "|";
			        	  			concatenado= concatenado + a.getDescripcion_leyenda_ley() + "|"; 
			        	  							
			        	  			lista.add(concatenado); 
			        	  		} 	  	
			        	  	}
			    	  		
			    	  	
           } catch (Exception e) {
               e.printStackTrace();
           }
    	  		
    }
    	  	
    	 
}
   
     
