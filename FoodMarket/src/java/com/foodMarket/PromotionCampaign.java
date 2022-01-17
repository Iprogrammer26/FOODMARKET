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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class PromotionCampaign extends HttpServlet {
    
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
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agree Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agree with agent</h1>");
            
             String matName = "banner5";
             String matDir = "/jsj/dkjf";
             String table = "promotioncampaign";
             Connection connection = null;
             int acID = 1; //GOTTEN FROM A SESSION
             
             Class.forName(jdbcDriver);

                     // Get a Connection to the database
                     connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);    
                     //Select the data from the database
                     
                     //String sql = "SELECT `name`, `password` FROM `"+ table+"`";
//                     String sql = "SELECT name, image, FROM `"+table+"` WHERE name = \""+username+"\" AND password = \""+password+"\"";
                     String sql = "SELECT name, PCid FROM `areacoordinator` WHERE ACID = "+ acID;
                     
                    //binfing the query
                     Statement s = connection.createStatement();
                     
                    //executing the query
                     s.executeQuery(sql);
                     
                     //results of query
                     ResultSet  rs = s.getResultSet();
                     
                     out.println("<h1></h1>");
                    
                     int PCid;
                     int hasAgreed;
                     String pcid;
                     String area;
                     String status;
                     String date;
                     String time;
                     String agName;
                     
//                     out.println("<h1>testing 1</h1>");
                     out.println("<div>");
                     while (rs.next()) {
//                          out.println("<h1>testing 1</h1>");
                           pcid = rs.getString("PCid");
                           agName = rs.getString("name");
                           PCid = Integer.parseInt(pcid);      
//                            out.println("<h1>"+PCid+"and name is "+agName+"</h1>");
                     
                     String sql1 = "SELECT * FROM `promotioncampaign` WHERE `PCid` = "+ PCid;
                     Statement s1 = connection.createStatement();
                     s1.executeQuery(sql1);
                     ResultSet  rs1 = s1.getResultSet();
                     
                     out.println("<h1>PROMOTION CAMPAIGN</h1>");
                     out.println("<table border = `1`>");
                     while (rs1.next()) {
                          out.println("<thead><h2>AREA CORDINATOR NAME: "+agName+"</h2></thead>");
                           area = rs1.getString("area");
                           date = rs1.getString("date");
                           time = rs1.getString("time");
                           status = rs1.getString("status");
                           hasAgreed = Integer.parseInt(status);
                         
                           
                           out.println("<tr><td><h2>THE CAMPAIGN AREA:</td><td>"+area+"</h2></td></tr>");
                           out.println("<tr><td><h2>DATE:            </td><td>"+date+"</h2></td></tr>");
                           out.println("<tr><td><h2>TIME:           </td><td>"+time+"</h2></td></tr>");
                           if( hasAgreed == 0){
                               out.println("<tr><td><h2 style=\"color = \"red\";\">Agreement status</h2></td><td>Pending<br><form><input type=\"submit\" value=\"Agree\" /></form></td></tr>");
                           }else{
                               out.println("<tr><td><h2 style=\"color = \"red\";\">Agreement status</h2></td><td>Agreed</td></tr>");
                           }
                           
                     }//inner while
                     out.println("</table>");
                    } //outer while
                     out.println("</div>");
            out.println("</body>");
            out.println("</html>");
               rs.close ();	
                    s.close ();
        }catch(Exception e){

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
