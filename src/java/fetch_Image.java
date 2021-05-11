/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.awt.Image;

/**
 *
 * @author Singh
 */
public class fetch_Image extends HttpServlet {

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
        String id=request.getParameter("id");
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            }catch(Exception e){
                //out.print(e.getMessage());
            }
        
        
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
            pstmt = conn.prepareStatement("SELECT * FROM products WHERE id=?");
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                    PrintWriter out = response.getWriter();
                    Blob blob = rs.getBlob("image");
                    byte byteArray[] = blob.getBytes(1,(int)blob.length());
                    String encodedImage=Base64.getEncoder().encodeToString(byteArray);
                    out.print("<img src='data:image/jpg;base64,"+encodedImage+"' >");
                }else{
                System.out.println("No image found with this id");
            }
            conn.close();
            }catch(SQLException e){
                //out.println(e.getMessage());
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
