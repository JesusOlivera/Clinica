package src.com.certicom.scpf.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Utils {

	public static boolean isNumeric(String string){
		if(string.trim().isEmpty() || string==null)
			return false;
		
		int i=0;
		if(string.charAt(0)=='-'){
			if(string.length()>0)
				i++;
			else
				return false;
		}
		int point=0;
		for(; i<string.length(); i++){
			if(string.charAt(i)=='.'){
				point++;
				if(point>1)
					return false;
			}else{
				if(!Character.isDigit(string.charAt(i)))
					return false;
			}
		}
		return true;
	}
	
	
	public static BigDecimal isNumericExcel(String string){
		BigDecimal result = BigDecimal.ZERO;
		String cadena="";
		if(string.trim().isEmpty() || string==null)
			return BigDecimal.ZERO;
		
		int i=0;
		if(string.charAt(0)=='-'){
			if(string.length()>0){
				i++;
				cadena="-";
			}else
				return BigDecimal.ZERO;
		}
		
		//Verificando cuantos puntos trae la celda del excel, por ejm si es: 150000 excel lo trae 1.500.00
		/*int numberPoint=0;
		for(int k=0; k<string.length(); k++){
			if(string.charAt(k)=='.')
				numberPoint++;
		}*/
		
		int point=0;
		//if(numberPoint>1){
		for(; i<string.length(); i++){
			if(string.charAt(i)=='.'){
			}else{
				if(!Character.isDigit(string.charAt(i)))
					return BigDecimal.ZERO;
				else
					cadena=cadena+string.charAt(i);
			}
		}
		result = BigDecimal.valueOf(Double.parseDouble(cadena));
		/*}else{
			for(; i<string.length(); i++){
				if(string.charAt(i)=='.'){
				}else{
					if(!Character.isDigit(string.charAt(i)))
						return BigDecimal.ZERO;
				}
			}
			result = BigDecimal.valueOf(Double.parseDouble(string));
		}*/
		
		return result;
	}
	
	
	public static BigDecimal isNumericExcelPuntoDecimal(String stringDec){
		BigDecimal result = BigDecimal.ZERO;
		String cadena="";
		String string="";
		//Quitando el punto decimal
		for(int k=0; k<stringDec.length(); k++){
			if(stringDec.charAt(k)=='.'){
				break;
			}else{
				string=string+stringDec.charAt(k);
			}
		}
		
		string = string.replaceAll(",", "").trim();
		if(string.trim().isEmpty() || string==null)
			return BigDecimal.ZERO;
		int i=0;
		if(string.charAt(0)=='-'){
			if(string.length()>0){
				i++;
				cadena="-";
			}else
				return BigDecimal.ZERO;
		}
		
		//if(numberPoint>1){
		for(; i<string.length(); i++){
			if(!Character.isDigit(string.charAt(i))){
				return BigDecimal.ZERO;
			}else
				cadena=cadena+string.charAt(i);
		}
		result = BigDecimal.valueOf(Double.parseDouble(cadena));
		
		return result;
	}
	
	public static String getMonthByValue(String val){
		String month ="";
		switch (val) {
			case "01":
				month = "ENERO";
			break;
			case "02":
				month = "FEBRERO";
				break;
			case "03":
				month = "MARZO";
				break;
			case "04":
				month = "ABRIL";
				break;
			case "05":
				month = "MAYO";
				break;
			case "06":
				month = "JUNIO";
				break;
			case "07":
				month = "JULIO";
				break;
			case "08":
				month = "AGOSTO";
				break;
			case "09":
				month = "SETIEMBRE";
				break;
			case "10":
				month = "OCTUBRE";
				break;
			case "11":
				month = "NOVIEMBRE";
				break;
			case "12":
				month = "DICIEMBRE";
				break;
		}
		
		return month;
	}
	
	public static String addZeoLeftMonth(int month){
		String mes = ""+month;
		if(mes.length()==1) mes = "0"+mes;
		return mes;
	}
	
	public static Date setDateMontAndYear(Integer date, Integer month, Integer year){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, date);	  
		calendar.set(Calendar.MONTH, month);	  
		calendar.set(Calendar.YEAR, year);	  
	  
	  return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	}
	
	public static Date setDateToActualMonthYear(Integer date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Calendar newDate = Calendar.getInstance();
		
		newDate.set(Calendar.DATE, date);	  
		newDate.set(Calendar.MONTH, calendar.get(Calendar.MONTH));	  
		newDate.set(Calendar.YEAR, calendar.get(Calendar.YEAR));	
		
		return newDate.getTime();
	  
	}
	

public static String dateFormatToString(Date fecha,int opcion){
	String fechaString = "";
	String FORMATO = "";
	switch (opcion) {
	case 1:
		FORMATO = "dd-MM-yyyy";
		break;
	case 2:
		FORMATO = "HH:mm:ss";
		break;
		//agregar
	}
	DateFormat dformat  = new SimpleDateFormat(FORMATO);
	fechaString = dformat.format(fecha);
	return fechaString;
}

public  static Date stringFormatToDate(String fecha, int opcion) throws ParseException{
	Date fechaDate = null;
	String FORMATO = "";
	switch (opcion) {
	case 1:
		FORMATO = "dd-MM-yyyy HH:mm:ss";
		break;
	case 2:
		FORMATO = "dd-MM-yyyy";
		break;
		//agregar
	}
	SimpleDateFormat sdf = new SimpleDateFormat(FORMATO);
	
	fechaDate = sdf.parse(fecha);
	return fechaDate;
}

/*Jesús Crea un archivo temporal de imagen*/
public static String guardaBlobEnFicheroTemporal(byte[] bytes, String nombreArchivo){
	String ubicacionImagen = null; 
	
	ServletContext servletContex = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	String path = servletContex.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "img" + File.separatorChar + "tmp" + File.separatorChar + File.separatorChar + nombreArchivo;
	
	File f= null; 
	InputStream in = null; 
	
	
	try{ 
		f= new File(path);
		in = new ByteArrayInputStream (bytes); 
		FileOutputStream out = new FileOutputStream (f.getAbsolutePath());
		
		int c=0;
		while ((c=in.read()) >=0){
			out.write(c);	
		}
		out.flush();
		out.close();
		ubicacionImagen = "../../resources/img/tmp/" + nombreArchivo;
		
	}catch (Exception e){
		System.err.println("No se pudo cargar la imagen");
	}
	
	return ubicacionImagen;
}
public static String cantidadConLetra(String s) {
    StringBuilder result = new StringBuilder();
    BigDecimal totalBigDecimal = new BigDecimal(s).setScale(2, BigDecimal.ROUND_DOWN);
    
    
    long parteEntera = totalBigDecimal.toBigInteger().longValue();
    int triUnidades      = (int)((parteEntera % 1000));
    int triMiles         = (int)((parteEntera / 1000) % 1000);
    int triMillones      = (int)((parteEntera / 1000000) % 1000);
    int triMilMillones   = (int)((parteEntera / 1000000000) % 1000);

    if (parteEntera == 0) {
        result.append("Cero ");
        return result.toString();
    }

    if (triMilMillones > 0) result.append(triTexto(triMilMillones).toString() + "Mil ");
    if (triMillones > 0)    result.append(triTexto(triMillones).toString());

    if (triMilMillones == 0 && triMillones == 1) result.append("Millón ");
    else if (triMilMillones > 0 || triMillones > 0) result.append("Millones ");

    if (triMiles > 0)       result.append(triTexto(triMiles).toString() + "Mil ");
    if (triUnidades > 0)    result.append(triTexto(triUnidades).toString());
    
    /* Inicio Jesus Agregamos la parte decimal*/
    BigDecimal bd = new BigDecimal(s);
    BigDecimal decimal = bd.subtract(bd.setScale(0, RoundingMode.FLOOR)).movePointRight(bd.scale());
    long parteDecimal = decimal.toBigInteger().longValue();
    String nombreDecimal ="";
    nombreDecimal = " con " + parteDecimal +"/100";
    
    if (parteDecimal != 0) {
        result.append(nombreDecimal);
        return result.toString();
    }
    /* Fin*/

    return result.toString();
}

private static StringBuilder triTexto(int n) {
    StringBuilder result = new StringBuilder();
    int centenas = n / 100;
    int decenas  = (n % 100) / 10;
    int unidades = (n % 10);

    switch (centenas) {
        case 0: break;
        case 1:
            if (decenas == 0 && unidades == 0) {
                result.append("Cien ");
                return result;
            }
            else result.append("Ciento ");
            break;
        case 2: result.append("Doscientos "); break;
        case 3: result.append("Trescientos "); break;
        case 4: result.append("Cuatrocientos "); break;
        case 5: result.append("Quinientos "); break;
        case 6: result.append("Seiscientos "); break;
        case 7: result.append("Setecientos "); break;
        case 8: result.append("Ochocientos "); break;
        case 9: result.append("Novecientos "); break;
    }

    switch (decenas) {
        case 0: break;
        case 1:
            if (unidades == 0) { result.append("Diez "); return result; }
            else if (unidades == 1) { result.append("Once "); return result; }
            else if (unidades == 2) { result.append("Doce "); return result; }
            else if (unidades == 3) { result.append("Trece "); return result; }
            else if (unidades == 4) { result.append("Catorce "); return result; }
            else if (unidades == 5) { result.append("Quince "); return result; }
            else result.append("Dieci");
            break;
        case 2:
            if (unidades == 0) { result.append("Veinte "); return result; }
            else result.append("Veinti");
            break;
        case 3: result.append("Treinta "); break;
        case 4: result.append("Cuarenta "); break;
        case 5: result.append("Cincuenta "); break;
        case 6: result.append("Sesenta "); break;
        case 7: result.append("Setenta "); break;
        case 8: result.append("Ochenta "); break;
        case 9: result.append("Noventa "); break;
    }

    if (decenas > 2 && unidades > 0)
        result.append("y ");

    switch (unidades) {
        case 0: break;
        case 1: result.append("Un "); break;
        case 2: result.append("Dos "); break;
        case 3: result.append("Tres "); break;
        case 4: result.append("Cuatro "); break;
        case 5: result.append("Cinco "); break;
        case 6: result.append("Seis "); break;
        case 7: result.append("Siete "); break;
        case 8: result.append("Ocho "); break;
        case 9: result.append("Nueve "); break;
    }

    return result;
}
	
}
