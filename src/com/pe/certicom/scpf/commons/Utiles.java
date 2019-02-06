package com.pe.certicom.scpf.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utiles {
	
	

	public static Date stringToDate(String dia, String mes, String anio)
	{
        Date fecha = null;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = anio+"-"+mes+"-"+dia;
        try {

            fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }
        
        return fecha;
		
	}

}
