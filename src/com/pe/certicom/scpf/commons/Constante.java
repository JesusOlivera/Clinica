package com.pe.certicom.scpf.commons;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Constante {
	
	public static final String SESSION_USER = "usuarioEnSesion";
	public static final String SESSION_CONF = "configuracionEnSesion";
	public static final String SESSION_TIPODOC = "tipoDocumentoEnSesion";
	public static final String SESSION_IMAGEN = "imagenEnSesion";
	public static final String SESSION_LOTECLIENTEBD = "loteClienteBDEnSesion";
	public static final String SESSION_PERIODO = "periodoEnSesion";
	public static final String SESSION_INDICE = "indiceEnSesion";
	public static final String SESSION_UR = "urEnSesion";
	public static final String SESSION_LOG = "logEnSesion";
	
	
	/******************Rutas*******************/
	public static final String RUTA_SERVER_IMG_BUSQ = "/imagenesSDD/";
	public static final String RUTA_SERVER_IMG_TD = "/tipoDocumental/";
	public static final String RUTA_SERVER_IMG = "/var/www/html/imagenesSDD/";
	public static final String RUTA_LOCAL_IMG = "/Digitalizacion/";
	
	/******************Conexiones Serv. Imagenes*******************/
	public static final String HOST_SERVER_IMG = "10.162.170.103";//"172.17.100.108";//"172.17.100.74";//
	public static final String USER_SERVER_IMG = "admin";//"root";
	public static final String PASS_SERVER_IMG = "fedora";//"123456";//"s0p0rt32013";
	
	/******************Conexiones Serv. App.*******************/
	public static final String HOST_SERVER_APP = "10.162.170.103";//"172.17.100.108";//"172.17.100.74";//
	public static final String USER_SERVER_APP = "admin";
	public static final String PASS_SERVER_APP = "fedora";//"123456";//"s0p0rt32013";
	
	/******************Conexion BD y HTTP*******************/
	public static final String RUTAURL = "http://10.162.170.103/html/tipoDocumental/";//"http://172.17.100.108/html/tipoDocumental/";//"http://172.17.100.74/html/tipoDocumental/";//
	public static final String URLBD = "jdbc:postgresql://10.162.170.103:5432/Digitalizacion_ONP";//"jdbc:postgresql://172.17.100.108:5432/Digitalizacion";//"jdbc:postgresql://172.17.100.74:5432/Digitalizacion";//
	public static final String USSERBD = "postgres";//
	public static final String PASSBD = "postgres";//"135abc246";
	
	

	
	/******************Para Configuracion Scanner*******************/
	public static final String PREFIJO_IMG ="Page-";
	
	public static final String EXTENSION_IMG =".jpg";
	public static final String EXTENSION_ZIPEADO =".zip";
	public static final int NRO_DIG_NUMERACION_SCANNER =3;

	/************Para Tipos de Imagenes********/
	public static final int TIPO_IMG_ULTIMA_SUBLOTE =5;
	public static final int TIPO_IMG_DOCUMENTO =4;
	
	
	/*************para tpos de mensaje: will*******/
	public static final Integer ERROR = 1;
	public static final Integer FATAL = 2;
	public static final Integer INFORMACION = 3;
	public static final Integer ADVERTENCIA = 4;
	
	/*CODIGOS DE TIPO DE SERVICIO*/
	public static final Integer  COD_TIPO_SERVICIO_CONSULTA_MEDICA = 1;
	public static final Integer  COD_TIPO_SERVICIO_LABORATORIO = 2;
	public static final Integer  COD_TIPO_SERVICIO_ECOGRAFIA = 3;
	public static final Integer  COD_TIPO_SERVICIO_CONTROL = 6;
	public static final Integer  COD_TIPO_SERVICIO_HOSPITALIZACION = 7;
	
	public static final String  TICKET_ESTADO_PENDIENTE = "PENDIENTE";
	public static final String  TICKET_ESTADO_TERMINADO = "TERMINADO";
	public static final String  TICKET_ESTADO_FACTURADO = "FACTURADO";
	
	
	/*CODIGOS DE PERFIL*/
	public static final Integer  PERFIL_COD_GERENTE_PROYECTO = 1;
	public static final Integer  PERFIL_COD_SUPERVISOR = 44 ;
	public static final Integer  PERFIL_COD_COORDINADOR = 42;
	public static final Integer  PERFIL_COD_EJECUTIVO = 46;
	public static final Integer  PERFIL_COD_BACKOFFICE = 45;
	
	/* TABLA DE TABLAS */
	
	public static final Integer COD_TIPOS_DOCUMENTOS = 1;
	public static final Integer COD_TIPO_DE_MONEDA = 2;
	public static final Integer COD_UNIDADES_DE_MEDIDA = 3;
	public static final Integer COD_TIPOS_DE_TRIBUTO = 5;
	public static final Integer COD_TIPOS_DOCUMENTOS_IDENTIDAD = 6;
	public static final Integer COD_TIPOS_DE_AFECTACION_DEL_IGV = 7;
	public static final Integer COD_TIPOS_DE_SISTEMA_DE_CALCULO_DEL_ISC = 8;
	public static final Integer COD_TIPO_OPERACION = 51;	
	public static final Integer COD_TIPOS_PRODUCTO = 25;
	
	public static final Integer COD_TIPOS_NOTA_CRED = 9;
	public static final Integer COD_TIPOS_NOTA_DEB = 10;
	
	
	public static final String VERSION_UBL_SUNAT = "2.1";
	public static final String CUSTOMIZACION_DOCUMENTO = "2.0";
	
	/* TABLA DE TABLAS DETALLE*/
	
	public static final Integer COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS = 12;
	public static final Integer COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO = 14;
	
	/* RUTAS */
	public static final String RUTA_DESCARGA = "C:/SFS_v1.2/sunat_archivos/sfs/DATA/"; 
	public static final String RUTA_RESPUESTA ="C:\\SFS_v1.2\\sunat_archivos\\sfs\\RPTA\\";
	public static final String RUTA_SALIDA_LECTURA_ZIP="C:\\SFS_v1.2\\SALIDA\\";
	
	public static final String ESTADO_ENVIADO="ENVIADO";
	public static final String ESTADO_PENDIENTE="PENDIENTE";
	public static final String RESPUESTA_ENVIO_ACEPTADO="ACEPTADO";
	public static final String RESPUESTA_ENVIO_RECHZADO="RECHZADO";
	public static final String ESTADO_PROBLEMAS_SUNAT="CON ERRORES";
	public static final String RESPUESTA_PROBLEMAS_SUNAT="Error al invocar el servicio de SUNAT";
}

