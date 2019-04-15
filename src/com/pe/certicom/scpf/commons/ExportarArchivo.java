package com.pe.certicom.scpf.commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class ExportarArchivo {

	 public static String getPath(String ruta){
	        ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
	        String path = servletContext.getRealPath(ruta);
	        return path;
	    }

	    /**
	     * Exportar archivos a pdf.
	     * @param [jasperFile] [Nombre del archivo compilado .jaspe], tipo [String].
	     * @param [parameters] [Ingreso de parametros que pueden ser utilizados en el jasper], tipo [Map<>].
	     * @return [dataList] [Arreglo que contiene los registros que deber� pintar el jasperreport], tipo [List<?>].
	     * @throws [nombre de excepci�n] [explicaci�n].
	     */
	    public static byte[] exportPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList) throws Exception {
	        System.out.println("exportPdf ==>");
	        JRDataSource vacio = new JREmptyDataSource(1);
	        
	        URL url=null;
	        File file=null;
	        JasperReport reporte=null;
	        try{
	        	url = new URL(jasperFile);	        	
	        }catch(MalformedURLException e){
//	        	System.out.println("ERROR : "+e.toString());
	        }
	         file=new File(jasperFile);
	        if(url!=null){
	        	  reporte = (JasperReport) JRLoader.loadObject(url);
	        }else{
	        	  reporte = (JasperReport) JRLoader.loadObject(file);
	        }
	       
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, new JRBeanCollectionDataSource(dataList));
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        JRPdfExporter jRPdfExporter = new JRPdfExporter();
	        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	        jRPdfExporter.exportReport();
	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        jRPdfExporter = null;
	        /*
	        String path = getServletContext().getRealPath("/jrxml/employeesList.jrxml");
	        InputStream input = new FileInputStream(new File(path));
	        JasperDesign jasperDesign = JRXmlLoader.load(input);
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        */
	        return bytes;
	    }
	    
	    
	    public static byte[] exportPdf2(String jasperFile, Map<String, Object> parameters) throws Exception {
	        System.out.println("exportPdf ==>");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters);
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        JRPdfExporter jRPdfExporter = new JRPdfExporter();
	        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	        jRPdfExporter.exportReport();
	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        jRPdfExporter = null;
	        return bytes;
	    }
	    
	    
	    /**
	     * Exportar archivos a pdf.
	     * @param [jasperFile] [Nombre del archivo compilado .jaspe], tipo [String].
	     * @param [parameters] [Ingreso de parametros que pueden ser utilizados en el jasper], tipo [Map<>].
	     * @return [dataList] [Arreglo que contiene los registros que deber� pintar el jasperreport], tipo [List<?>].
	     * @throws [nombre de excepci�n] [explicaci�n].
	     */
	    public static byte[] exportXls(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
	    	 parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        JRXlsExporter exporter = new JRXlsExporter();
		        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
		        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		        exporter.setParameter(JExcelApiExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
		        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
		        exporter.exportReport();
		        byte[] bytes = byteArrayOutputStream.toByteArray();
		        byteArrayOutputStream.flush();
		        byteArrayOutputStream.close();
		        exporter = null;
		        return bytes;
	    }

	    public static void executePdf(byte[] bytes, String name){
	        try {

	            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	            response.reset();
	            response.setContentType("application/octet-stream");
	            response.setContentLength(bytes.length);
	            response.setHeader("Content-disposition","attachment; filename=\""+name+"\"");
	            response.setHeader("Pragma", "no-cache");
	            response.setDateHeader("Expires", 0);

	            ServletOutputStream ouputStream = response.getOutputStream();
	            ouputStream.write(bytes, 0, bytes.length);
	            ouputStream.flush();
	            ouputStream.close();

	        } catch (Exception e) {
	            System.out.println("ERROR JASPER ==>"+e);
	        }
	    }

	    public static void executeExccel(byte[] bytes, String name){
	        try {

	        	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	            response.reset();
	            response.setContentType("application/octet-stream");
	            response.setContentLength(bytes.length);
	            response.setHeader("Content-disposition","attachment; filename=\"'"+name+"\"");
	            response.setHeader("Pragma", "no-cache");
	            response.setDateHeader("Expires", 0);
	            
	            ServletOutputStream ouputStream = response.getOutputStream();
	            ouputStream.write(bytes, 0, bytes.length);
	            ouputStream.flush();
	            ouputStream.close();
	            
	        } catch (Exception e) {
	            System.out.println("ERROR JASPER ==>"+e);
	        }
	    }
	    
	    
	   /*Reporte Masivo para Contadores*/ 
	    public static byte[] exportarExcel(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet, Connection conn) throws Exception {
	    	 parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	    	
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, conn);
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        JRXlsExporter exporter = new JRXlsExporter();
		        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
		        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		        exporter.setParameter(JExcelApiExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
		        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
		        exporter.exportReport();
		        byte[] bytes = byteArrayOutputStream.toByteArray();
		        byteArrayOutputStream.flush();
		        byteArrayOutputStream.close();
		        exporter = null;
		        return bytes;
	    }

	
}
