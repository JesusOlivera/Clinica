package com.certicom.scpf.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pe.certicom.scpf.commons.Conexion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Servlet implementation class ServletReporte
 */
//@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection cnx = null;
        Conexion con   = null;
        String strFile = null;
        String fpath   = null;
        String par     = null;
        String numfac  = null;
        File ffile     = null;
        HashMap hm     = null;
        byte [] bytes = null;
        JasperDesign jasperDesign = null;
        JasperReport jasperReport = null;
        ServletOutputStream out = null;
        try {
            strFile = "D:/conexion.properties";
            con     = new Conexion();
            cnx     = con.getConexion(strFile);
            if(cnx != null){
                fpath  = "D:/rptUnidadRecepcion.jrxml";
                ffile  = new File(fpath);
                if(ffile.exists()){
                    if(ffile.isFile()){
                        /*par          = request.getParameter("txtparam").trim();
                        numfac       = request.getParameter("txtfac").trim();
                        hm           = new HashMap();
                        hm.put("P_LOGO", par);
                        hm.put("P_NUMFAC", numfac);*/
                        jasperDesign = JRXmlLoader.load(fpath);
                        jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        bytes        = JasperRunManager.runReportToPdf(jasperReport, hm, cnx);
                        response.setContentType("application/pdf");
                        response.setContentLength(bytes.length);
                        try {
                            out = response.getOutputStream();
                            out.write(bytes, 0, bytes.length);
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        }
                    }
            }
        }catch(JRException e){
            e.printStackTrace();
        } finally { 
           //out.close();
            try {
                if(cnx != null){
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
