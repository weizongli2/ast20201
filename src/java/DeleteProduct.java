/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/DeleteProduct"})
public class DeleteProduct extends HttpServlet {

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
        //get array
        String[] ToDelete = request.getParameterValues("ToDelete[]");
        
        HttpSession session = request.getSession(true);
        Object obj=session.getAttribute("id");
        int id = (int)session.getAttribute("id");
        if(obj==null){
//            out.print("<script>alert('Invalid Login!');</script>");
//            response.sendRedirect("login.html");
                        out.print("<script type=\"text/javascript\"> alert('Invalid Login!');"
                       + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
        }else{
                Connection conn=null;
                PreparedStatement pstmt=null;
                PreparedStatement pstmt3=null;
                ResultSet rs3=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                    out.print(e.getMessage());
                }

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                
                out.print("<table id='Deleted_products' >");
                for(int i = 0; i < ToDelete.length;i++){
                    out.print("<tr>");
                    pstmt=conn.prepareStatement("DELETE FROM products WHERE id=?");
                    pstmt.setString(1,ToDelete[i]);
                    pstmt3=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
                    pstmt3.setInt(1,id);
                    if(pstmt3.execute()){
                        rs3=pstmt3.getResultSet();
                    }
                       if(rs3.next()){
                        if(!rs3.getString("usertype").equals("admin")&&!rs3.getString("usertype").equals("seller")){
//                            response.sendRedirect("login.html");
                             out.print("<script type=\"text/javascript\"> alert('No authority!');"
                            + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
                        }else{
                            if(pstmt.executeUpdate() >= 1){
                                out.print("<td>ProductID: "+ToDelete[i] + " has been deleted.</td>");
                            }else{
                                out.print("<td>ProductID: "+ToDelete[i] + " failed to delete.\n</td>");
                            }
                        }
                   }
//                    if(pstmt.executeUpdate() >= 1){
//                        out.print("<td>ProductID: "+ToDelete[i] + " has been deleted.</td>");
//                    }else{
//                        out.print("<td>ProductID: "+ToDelete[i] + " failed to delete.\n</td>");
//                    }
                    out.print("</tr>");
                }
                out.print("</table>");
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
                }
            
        
        }
        
        /*for(int i =0;i<ToDelete.length;i++){
            out.print(i+": "+ToDelete[i]+"\n");
        }
        */
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
