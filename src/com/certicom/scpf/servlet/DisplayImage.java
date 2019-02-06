package com.certicom.scpf.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.certicom.scpf.jdbc.dao.Conexion;

/**
 * Servlet implementation class DisplayImage
 */
//@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
	
	private static final long serialVersionUID = 4593558495041379082L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement pstmt = null;
		ResultSet rs;
		Connection con;
		
		byte[] data = null;
		
		
		InputStream sImage;
		 //prueba
		try {
			  String PATH ="/src/com/certicom/scpf/propiedades/database.properties"; 
	         Properties properties = new Properties();  
	         properties.load(Conexion.class.getResourceAsStream(PATH)); 
			   //obteniendo el archivo de imagen.			
			
//			   String urldb ="jdbc:postgresql://10.162.170.103:5432/SCPF";
//			   String userbd="postgres";
//			   String passbd = "postgres11";
			   
			   String urldb =properties.getProperty("jdbc.url");
			   String userbd=properties.getProperty("jdbc.username");
			   String passbd = properties.getProperty("jdbc.password");
			   con = DriverManager.getConnection(urldb,userbd,passbd);
			  
			   String codigo = request.getParameter("codigo").toString();			
				   
				   String idusuario = codigo;//request.getParameter("idPiloto").toString();	
					   //para meter a bd parametros
					   //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

					   String strSql = "select imagen from t_usuario where idusuario = ?";
					   pstmt = con.prepareStatement(strSql);
			           pstmt.setInt(1,Integer.parseInt(idusuario));
					   
			           rs = pstmt.executeQuery();
			          
			           if (rs.next()) {
			        	   data = rs.getBytes("imagen");
			            }else{
			            	System.out.println("No hay registros");
			            }
			           response.reset();
		               response.setContentType("image/jpeg");
		               if(data!=null)
		               {
		            	   response.getOutputStream().write(data);
		               }
				   con.close();			   			
			  
			} catch (IOException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	}

}

