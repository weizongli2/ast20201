/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Singh
 */
@WebServlet(urlPatterns = {"/ModifyProduct"})
@MultipartConfig 
public class ModifyProduct extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        Object obj=session.getAttribute("id");
        int id = (int)session.getAttribute("id");
        if(obj==null){

                       out.print("<script type=\"text/javascript\"> alert('Invalid Login!');"
                       + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
        }else{
                Connection conn=null;
                PreparedStatement pstmt=null;
                PreparedStatement pstmt2=null;
                PreparedStatement pstmt3=null;
                ResultSet rs=null;
                ResultSet rs3=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                    out.print(e.getMessage());
                }

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                
                Part filePart = request.getPart("p_image");
                InputStream p_image = filePart.getInputStream();
                
                String p_id = request.getParameter("p_id");
                String p_name = request.getParameter("p_name");
                String p_amount = request.getParameter("p_amount");
                String p_price = request.getParameter("p_price");
                String p_category = request.getParameter("p_category");
                String p_subcategory = request.getParameter("p_subcategory");
                String p_desc = request.getParameter("p_desc");
                pstmt=conn.prepareStatement("Select * from products where id = ?");
                pstmt.setString(1,p_id);
                if(pstmt.execute()){
                    rs = pstmt.getResultSet();
                }
                if(rs.next()){
                    if(p_name.length() == 0){
                        p_name = rs.getString("name");
                    }
                    if(p_amount.length() == 0){
                        p_amount = rs.getString("amount");
                    }
                    if(p_price.length() == 0){
                        p_price = rs.getString("price");
                    }
                    if(p_desc.length() == 0){
                        p_desc = rs.getString("description");
                    }
                }
                
                pstmt2=conn.prepareStatement("UPDATE products SET name=?,amount=?,price=?,category=?,subcategory=?,description=?,image=? WHERE id=?");
                pstmt2.setString(1,p_name);
                pstmt2.setString(2,p_amount);
                pstmt2.setString(3,p_price);
                pstmt2.setString(4,p_category);
                pstmt2.setString(5,p_subcategory);
                pstmt2.setString(6,p_desc);
                if(p_image.available() > 0){
                pstmt2.setBlob(7,p_image);
                }else{
                pstmt2.setBlob(7,rs.getBlob("image"));
                }
                pstmt2.setString(8,p_id);
                

                pstmt3=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
                pstmt3.setInt(1,id);
                if(pstmt3.execute()){
                    rs3=pstmt3.getResultSet();
                }
                   if(rs3.next()){
                    if(!rs3.getString("usertype").equals("admin")&&!rs3.getString("usertype").equals("seller")){
                            out.print("<script type=\"text/javascript\"> alert('No authority!');"
                            + "window.location.href = \"login.html\"; </script>");
                    }else{
                        if(pstmt2.executeUpdate()>=1){
                            out.print("<script>alert('Updated Product information!');"
                                    + "window.location.href = \"modifyProduct.jsp\"; </script>");
                            
                        }else{
                            out.print("<script>alert('Modify Product failed!');</script>");
                        }
                    }
                   }
                
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
                }

        
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
