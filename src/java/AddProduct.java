/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.InputStream;

/**
 *
 * @author Singh
 */
@WebServlet(urlPatterns = {"/AddProduct"})
@MultipartConfig 
public class AddProduct extends HttpServlet {

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
         PrintWriter out=response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        Object obj=session.getAttribute("id");
        int id = (int)session.getAttribute("id");
                if(obj==null){
//            out.print("<script>alert('Invalid Login!');</script>");
//            response.sendRedirect("login.html");
out.print("<script type=\"text/javascript\"> alert('Invalid Login!');"
                       + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
        }else{
            
            Part filePart = request.getPart("p_image");
            InputStream p_image = filePart.getInputStream();
            
            String p_name=request.getParameter("p_name");
            String p_desc=request.getParameter("p_desc");
            String p_category=request.getParameter("p_category");
            String p_subcategory=request.getParameter("p_subcategory");
            
            String price=(request.getParameter("p_price"));
            int p_price = 0;
            if(price.length() != 0){
                p_price = Integer.parseInt(price);
            }else{
                p_price = 0;
            }
            
            String amount= (request.getParameter("p_amount"));
            int p_amount = 0;
            if(amount.length() != 0){
                p_amount = Integer.parseInt(amount);
            }else{
                p_amount = 0;
            }


            if(p_name!=null){
                Connection conn=null;
                PreparedStatement pstmt=null;
                PreparedStatement pstmt2=null;
                ResultSet rs=null;
                ResultSet rs2=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){}

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                pstmt=conn.prepareStatement("INSERT INTO products (name,description,category,subcategory,price,amount,image,merchant) VALUES (?,?,?,?,?,?,?,?)");
                pstmt.setString(1,p_name);
                pstmt.setString(2,p_desc);
                pstmt.setString(3,p_category);
                pstmt.setString(4,p_subcategory);
                pstmt.setInt(5,p_price);
                pstmt.setInt(6,p_amount);
                pstmt.setBlob(7,p_image);
                pstmt.setInt(8,id);
                
                
                pstmt2=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
                pstmt2.setInt(1,id);
                
                if(pstmt2.execute()){
                    rs2=pstmt2.getResultSet();
                }
                if(rs2.next()){
                    if(!rs2.getString("usertype").equals("admin")&&!rs2.getString("usertype").equals("seller")){
//                        response.sendRedirect("login.html");
                            out.print("<script type=\"text/javascript\"> alert('No authority!');"
                            + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
                    }else{
                        if(pstmt.executeUpdate()>=1){
                            out.print("<script>alert('Product "+p_name+" is added');</script>");
                }else{
                    out.print("<script>alert('Add Product failed!');</script>");
                }
                    }
                }else{
                    response.sendRedirect("login.html");
                }
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
                }
                out.println("<script>window.location.href='addProduct.jsp'</script>");

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
