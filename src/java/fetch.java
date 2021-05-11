/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Singh
 */
@WebServlet(urlPatterns = {"/fetch"})
public class fetch extends HttpServlet {

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
        
        //get id of user from session to seperate sellers
        HttpSession session = request.getSession(true);
        int id = (int)session.getAttribute("id");
        String usertype = (String)session.getAttribute("usertype");
        //get input value from request
            String search_modify = request.getParameter("search_modify");
            String search_delete = request.getParameter("search_delete");
            
            String search = null;
            if(search_modify != null){
                search = search_modify;
            }else if(search_delete != null){
                search = search_delete;
            }
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
//                PreparedStatement pstmt2=null;
//                ResultSet rs2=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                e.getMessage();
                }

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
//                pstmt2=conn.prepareStatement("SELECT merchant FROM products");
//                if(pstmt2.execute()){
//                    rs2=pstmt2.getResultSet();
//                }
                if(search != null){
                //print all records if input is blank
                if(search.length() > 0){
                    if("admin".equals(usertype)){
                    pstmt=conn.prepareStatement("Select * from products where name = ?");
                    pstmt.setString(1,search);
                    }else{
                    pstmt=conn.prepareStatement("Select * from products where name = ? and merchant=?");
                    pstmt.setString(1,search);
                    pstmt.setInt(2,id);
                    }
                }else{
                    if("admin".equals(usertype)){
                    pstmt=conn.prepareStatement("Select * from products");
                    }else{
                    pstmt=conn.prepareStatement("Select * from products where merchant = ?");
                    pstmt.setInt(1,id);
                    }
                }
                if(pstmt.execute()){
                    rs=pstmt.getResultSet();
                }
                //print table of results
                out.println("<div class=\"table-responsive\">");
                out.println("<table class=\"table table-hover\">");
                out.println("<thead class=\"thead-dark\">");
                out.println("<tr>");
                if(search_modify != null){
                out.println("<th>MODIFY</th>");
                }
                out.println("<th scope=\"col\">Product ID</th>");
                out.println("<th scope=\"col\">Product Name</th>");
                out.println("<th scope=\"col\">Amount</th>");
                out.println("<th scope=\"col\">Price</th>");
                out.println("<th scope=\"col\">Category</th>");
                out.println("<th scope=\"col\">SubCategory</th>");
                
                if(search_delete != null){
                        out.println("<th>Select</th>");
                    }
                out.println("</tr>");
                out.println("</thead>");
                
                int i = 0;
                while(rs.next()){
                    out.println("<tr>");
                    if(search_modify != null){
                        out.println("<td><input type='button' value='MODIFY' class='modify_button' id='" + rs.getString("id") + "' ></td>");
                    }
                    out.println("<td>"+rs.getString("id")+"</td>");
                    out.println("<td>"+rs.getString("name")+"</td>");
                    out.println("<td>"+rs.getString("amount")+"</td>");
                    out.println("<td>"+rs.getString("price")+"</td>");
                    out.println("<td>"+rs.getString("category")+"</td>");
                    out.println("<td>"+rs.getString("subcategory")+"</td>");
                    
                    //print a checkbox for selecting records
                    if(search_delete != null){
                        out.println("<td><input id='" + i + "' value='" + rs.getString("id") + "' type='checkbox' class='select'></td>");
                    }
                    
                    out.println("</tr>");
                    i++;
                }
                
                out.println("</table>");
                out.println("</div>");
                
                }
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
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
