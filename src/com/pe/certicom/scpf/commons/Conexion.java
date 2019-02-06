package com.pe.certicom.scpf.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * calse de prueba por modificar
 * @author Julio Carranza
 *
 */
public class Conexion {

	public Connection getConexion(String strFile){
        Connection con   = null;
        File ffile       = null;
        FileInputStream fis = null;
        Properties pro   = null;
        String strDriver = null;
        String strUrl    = null;
        String strUser   = null;
        String strPassw  = null;
        try {
            ffile = new File(strFile);
            if(ffile.exists()){
                if(ffile.isFile()){
                    fis  = new FileInputStream(ffile); 
                    pro  = new Properties();
                    pro.load(fis);
                    strDriver = pro.getProperty("driver");
                    strUrl    = pro.getProperty("url");
                    strUser   = pro.getProperty("username");
                    strPassw  = pro.getProperty("password");
                    System.out.println("Driver : " + strDriver);
                    System.out.println("Url    : " + strUrl);
                    System.out.println("User   : " + strUser);
                    System.out.println("Passw  : " + strPassw);
                    Class.forName(strDriver);
                    con = DriverManager.getConnection(strUrl, strUser, strPassw);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
	
	
	
}
