/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodMarket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
//@WebServlet(name = "MaterialsServlet", urlPatterns = {"/MaterialsServlet"})
public class MaterialsServlet extends HttpServlet {
    
        private String jdbcURL = "jdbc:mysql://localhost:3306/promotion?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MaterialsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>PROMOTION MATERIAL CONTENT</h1>");
            
             String matName = "banner5";
             String matDir = "/jsj/dkjf";
             String table = "promotionmaterial";
             Connection connection = null;
             
             Class.forName(jdbcDriver);

                     // Get a Connection to the database
                     connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);    
                     //Select the data from the database
                     
                     //String sql = "SELECT `name`, `password` FROM `"+ table+"`";
//                     String sql = "SELECT name, image, FROM `"+table+"` WHERE name = \""+username+"\" AND password = \""+password+"\"";
                     String sql = "SELECT name, image, approved FROM "+ table;  
                    //binfing the query
                     Statement s = connection.createStatement();
                     
                    //executing the query
                     s.executeQuery(sql);
                     
                     //results of query
                     ResultSet  rs = s.getResultSet();
                     out.println("<h1>hello there</h1>");
                     String matname;
                     String imgdir;
                     String isApproved;
                     int status;
                     
                     out.println("<table border=\"1\">");
                           out.println("<tr>");
                                out.println("<td>MATERIAL NAME</td>");
                                out.println("<td>IMAGE</td>");
                                out.println("<td>APPROVAL STATUS</td>");
//                                 out.println("<td></td>");
                           out.println("</tr");
                     while (rs.next()) {  
                        out.println("<form method=\"POST\" action=\"ApproveServlet\">");
                           matname = rs.getString("name");
                           imgdir = rs.getString("image");
                           isApproved = rs.getString("approved");
                           
                           status =Integer.parseInt(isApproved);
                           
                           out.println("<tr>");
                                out.println("<td>"+matname+"</td>");
                                out.println("<td>"+imgdir+"</td>");
                                if(status==0){
                                    out.println("<td>Not Approved</td>");
                                }else{ 
                                    out.println("<td>Approved</td>");
                                }
                                 out.println("<td><input type='submit' value='APPROVE' /></td>"); //approve button
                           out.println("</tr>");
                        out.println("</form>");
                     }  
                     out.println("</table>");
            out.println("</body>");
            out.println("</html>");
               rs.close ();	
                    s.close ();
        } catch(Exception e){

		System.out.println("Exception is ;"+e);
		}
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
