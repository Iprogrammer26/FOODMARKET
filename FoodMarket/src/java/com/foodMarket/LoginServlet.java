/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodMarket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
//@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

      
        private String jdbcURL = "jdbc:mysql://localhost:3306/promotion?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        
        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * 
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        
        
        
//    protected void service(ServletRequest request, ServletResponse response)
//     throws ServletException, IOException {
//    
//    }
        
        
        
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                //String username = request.getParameter("username");
		//String password = request.getParameter("password");
                //testing
                
                String username = "paul";
                String password = "paul";
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login</title>");            
                out.println("</head>");
                out.println("<body>");
    
             //   out.println("<h3>username from form is:"+username+"</h3>");
             //   out.println("<h3>password from form is:"+password+"</h3>");
                Connection connection = null;
                String table = "agent";
                out.println("<h1>hello there outside try</h1>");
                try {
                      out.println("<h1>hello there inside</h1>");
                    // Load the database driver

                     Class.forName(jdbcDriver);

                     // Get a Connection to the database
                     connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);    
                     //Select the data from the database
                     
                     //String sql = "SELECT `name`, `password` FROM `"+ table+"`";
                     String sql = "SELECT name, password FROM `"+table+"` WHERE name = \""+username+"\" AND password = \""+password+"\"";
                       
                    //binfing the query
                     Statement s = connection.createStatement();
                     
                    //executing the query
                     s.executeQuery(sql);
                     
                     //results of query
                     ResultSet  rs = s.getResultSet();
                     out.println("<h1>hello there</h1>");
                     String name;
                     String passcode;
                     while (rs.next()) {  
                           name = rs.getString("name");
                           passcode = rs.getString("password");
                           out.print("<h1>name is "+ name+" and password is "+ passcode +"</h1>");
                           //validating
                           if((password == null)&&(username == null)){
                               out.println("<p>password or username is invalid</p>");
                           }else{
                               if((password.equals(passcode)) && (username.equals(name))){
                               //redirect them to the AddMaterails Page
                               out.println("<h1>validated</h1>");
                               
                               
                               }else{
                               out.println("the username or password you have entered is invalid");
                               }
                           }
//                            out.println("<option value = "+ rs.getInt("vaccine_id") +">" + name + "</option>");
	         	  }  
            out.println("</body>");
            out.println("</html>");
                    rs.close ();	
                    s.close ();

			}catch(Exception e){

				System.out.println("Exception is ;"+e);
		}
//        processRequest(request, response);
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
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                String username = request.getParameter("username");
		String password = request.getParameter("password");
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet HelloServlet</title>");            
                out.println("</head>");
                out.println("<body>");
    
                Connection connection = null;
                String table = "agent";
       
                try {

                    // Load the database driver

                     Class.forName(jdbcDriver);

                     // Get a Connection to the database

                     connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); 

                     //Select the data from the database

                     //String sql = "SELECT * FROM " + table+ " 'name'";
                     
                     String sql = "SELECT name, password FROM "+table;

                     Statement s = connection.createStatement();

                     s.executeQuery (sql);

                     ResultSet  rs = s.getResultSet();

                     while (rs.next()) {  
                            String name = rs.getString("name");
                            
//                          out.println("<option value = "+ rs.getInt("vaccine_id") +">" + name + "</option>");
                            out.println("name is "+ name);
	         	  }  
            out.println("</body>");
            out.println("</html>");
            
            rs.close ();	
            s.close ();

	}catch(Exception e){
            System.out.println("Exception is ;"+e);
	}
                
		
//		request.setAttribute("date", date);
//		request.setAttribute("time", time);
//		request.setAttribute("center", health_center);
//		request.setAttribute("vaccine", vaccine);
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsps/booking/make_booking.jsp");
//		dispatcher.forward(request, response);
        
//        processRequest(request, response);
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
        
        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * 
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        
        
        
//    protected void service(ServletRequest request, ServletResponse response)
//     throws ServletException, IOException {
//    
//    }
        
        
       