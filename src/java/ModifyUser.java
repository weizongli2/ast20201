/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;




/**
 *
 * @author lenovo
 */
public class ModifyUser extends HttpServlet {

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
        String user = (String)session.getAttribute("id");
        if(obj==null){
//            out.print("<script>alert('Invalid Login!');</script>");
//            response.sendRedirect("login.html");
                       out.print("<script type=\"text/javascript\"> alert('Invalid Login!');"
                       + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
        }else{
            String m_name=request.getParameter("u_name");
            String m_id=request.getParameter("u_id");
            String m_usertype=request.getParameter("u_usertype");
            String pwd=request.getParameter("pwd");

            Connection conn=null;
                PreparedStatement pstmt=null;
                PreparedStatement pstmt2=null;
                PreparedStatement pstmt3=null;
                ResultSet rs=null;
                ResultSet rs3=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){}

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                
                pstmt=conn.prepareStatement("Select * from products where id = ?");
                pstmt.setString(1,m_id);
                if(pstmt.execute()){
                    rs = pstmt.getResultSet();
                }
                if(rs.next()){
                    if(m_name.length() == 0){
                        m_name = rs.getString("name");
                    }
                    if(m_usertype.length() == 0){
                       m_usertype = rs.getString("usertype");
                    }
                    if(pwd.length() == 0){
                        pwd = rs.getString("pwd");
                    }
                }
                
                pstmt2=conn.prepareStatement("UPDATE userinfo SET username=?,usertype=?,pwd=? WHERE id=?");
                pstmt2.setString(1,m_name);
                pstmt2.setString(2,m_usertype);
                pstmt2.setString(3,pwd);
                pstmt2.setString(4,m_id);
                
//                if(pstmt2.executeUpdate()>=1){
//                    out.print("<script>alert('Updated Product information!');</script>");
//                }else{
//                    out.print("<script>alert('Modify Product failed!');</script>");
//                }
                pstmt3=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
                pstmt3.setInt(1,id);
                if(pstmt3.execute()){
                    rs3=pstmt3.getResultSet();
                }
                   if(rs3.next()){
                    if(!rs3.getString("usertype").equals("admin")){
//                        response.sendRedirect("login.html");
                            out.print("<script type=\"text/javascript\"> alert('No authority!');"
                            + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
                    }else{
                        if(pstmt2.executeUpdate()>=1){
                            out.print("<script>alert('Updated User information:\\nID:" +m_id+"\\nUser Name:" +m_name+"\\nPassword:" +pwd+"\\nUser Type:" +m_usertype+"');</script>");
                        }else{
                            out.print("<script>alert('Modify User failed!');</script>");
                        }
                    }
                   }
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
                }
//            if(m_name!=null||m_id!=null||m_usertype!=null||pwd!=null){
//                Connection conn=null;
//                PreparedStatement pstmt=null;
//                PreparedStatement pstmt2=null;
//                PreparedStatement pstmt3=null;
////                ResultSet rs=null;
//                ResultSet rs2=null;
//                ResultSet rs3=null;
//                try{
//                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//                }catch(Exception e){}
//
//                try{
//                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
//
//                pstmt3=conn.prepareStatement("Select * from userinfo where id = ?");
//                pstmt3.setString(1,m_id);
//                if(pstmt3.execute()){
//                    rs3 = pstmt3.getResultSet();
//                }
//                if(rs3.next()){
//                    if(m_name.length() == 0){
//                        m_name = rs3.getString("username");
//                    }
//                    if(m_usertype.length() == 0){
//                        m_usertype = rs3.getString("usertype");
//                    }
//                    if(pwd.length() == 0){
//                        pwd = rs3.getString("pwd");
//                    }
//                }
//                
//                pstmt=conn.prepareStatement("UPDATE userinfo SET username=?,pwd=?,usertype=? WHERE id=?");
//                pstmt.setString(1,m_name);
//                pstmt.setString(2,pwd);
//                pstmt.setString(3,m_usertype);
//                pstmt.setString(4,m_id);
//
//                pstmt2=conn.prepareStatement("SELECT * FROM userinfo WHERE username=?");
//                pstmt2.setString(1,user);
//                if(pstmt2.execute()){
//                    rs2=pstmt2.getResultSet();
//                }
//                if(rs3.next()){
//                    if(!rs3.getString("usertype").equals("admin")){
//                        response.sendRedirect("login.html");
//                    }else{
//                        if(pstmt.executeUpdate()>=1){
//                            out.print("<script>alert('Updated User information:\\nID:" +m_id+"\\nUser Name:" +m_name+"\\nPassword:" +pwd+"\\nUser Type:" +m_usertype+"');</script>");
//                        }else{
//                            out.print("<script>alert('Modify User failed!');</script>");
//                        }
//                    }
//                }else{
//                   response.sendRedirect("login.html");
//                }
//                conn.close();
//                }catch(SQLException e){
//                out.println(e.getMessage());
//                }
//            }
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
