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
public class StatusOfCampaigns extends HttpServlet {
            
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
            out.println("<title>Servlet StatusOfCampaigns</title>");            
            out.println("</head>");
            out.println("<body>");
            
                         Connection connection = null;
             
             Class.forName(jdbcDriver);

                     // Get a Connection to the database
                     connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);    
                     //Select the data from the database
                     
                    //query for all the approved campaigns
                     String sql = "SELECT * FROM `promotioncampaign`";  
                    //binfing the query
                     Statement s = connection.createStatement();
                     
                    //executing the query
                     s.executeQuery(sql);
                     
                     //results of query
                     ResultSet  rs = s.getResultSet();
                     
//                     out.println("<h1>Stats</h1>");
                     String area_name;
                     String date;
                     String time;
                     String status;
                     int isStatus;
                     int count;
                     //fetch the results
                     //unappproved campaigns
                     out.println("<table>");
                     out.println("<thead>UNAPPROVED CAMPAIGNS</thead>");
                     count = 0;
                     while (rs.next()){
                         area_name = rs.getString("area");
                         date = rs.getString("date");
                         time = rs.getString("time");
                         status = rs.getString("status");
                         
                         isStatus = Integer.parseInt(status);
                         
                         if( isStatus == 0){
                             count++;
                             out.println("<tr>");
                             out.println("<td>"+area_name+"</td>");
                             out.println("<td>"+date+"</td>");
                             out.println("<td>"+time+"</td>");
                             out.println("<td>NOT APPROVED</td>");
                             out.println("</tr>");
                         }
                     }
                     out.println("<h3>There are "+count+" number of unappoved campaigns</h3>");
                     out.println("</table>");
                     
                     //approved campaigns 
                     out.println("<table>");
                     out.println("<thead>APPROVED CAMPAIGNS</thead>");
                     count = 0;
                     while (rs.next()){
                         area_name = rs.getString("area");
                         date = rs.getString("date");
                         time = rs.getString("time");
                         status = rs.getString("status");
                         
                         isStatus = Integer.parseInt(status);
                         
                         if( isStatus == 1){
                             count++;
                             out.println("<tr>");
                             out.println("<td>"+area_name+"</td>");
                             out.println("<td>"+date+"</td>");
                             out.println("<td>"+time+"</td>");
                             out.println("<td>APPROVED</td>");
                             out.println("</tr>");
                         }
                     }
                     out.println("<h3>There are "+count+" number of unappoved campaigns</h3>");
                     out.println("</table>");
                     
                     
            out.println("<h1>Servlet StatusOfCampaigns at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }finally {
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
