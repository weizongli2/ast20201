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
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
public class login extends HttpServlet {

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
        String user_name=request.getParameter("user_name");
        String pwd=request.getParameter("user_password");
//        String user=user_name;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
       // ResultSetMetaData meta = null;
       // int fieldCount = 0;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(Exception E) {}
        try{
        conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/ast20201db?useSSL=false",
        "root",
        "root");
        pstmt = conn.prepareStatement("select * from userinfo where username = ?");
        pstmt.setString(1, user_name);

        pstmt2=conn.prepareStatement("Update userinfo SET lastlogin=? WHERE username=?");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        pstmt2.setString(1,date.toString());
        pstmt2.setString(2,user_name);
        if(pstmt.execute()){
           rs=pstmt.getResultSet();
       }
        }catch(SQLException E) {
        out.println(E.getMessage());
        }
        
        try{
        if(rs.next()){
            if(rs.getString("pwd").equals(pwd)){
                int id=rs.getInt("id");
                session.setAttribute("id",id);
                session.setAttribute("user",user_name);
                String usertype=rs.getString("usertype");
                session.setAttribute("usertype",usertype);
//                out.println("<p>"+user+"<p>");
                pstmt2.execute();
                out.println("<div class='alert alert-info'><strong>Welcome," + user_name + "\nLast login :" + rs.getTimestamp("Lastlogin") + "</strong></div>");
                if(rs.getString("usertype").equals("user")){
                    out.println("<script> alert('Welcome,"+user_name+"! Last login:"+rs.getTimestamp("Lastlogin")+"')</script>");
                    out.print("<script> window.location.href = \"homepage.html\"</script>");
//                    response.sendRedirect("homepage.html");
                }
                if(rs.getString("usertype").equals("admin")){
                    response.sendRedirect("DBMShome.html");
                }else if(rs.getString("usertype").equals("seller")){
                    response.sendRedirect("DBMShome.html");
                }
            }else{
                out.println("<script> alert('Password Incorrect!')</script>");
                out.print("<script> window.location.href = \"login.html\"</script>");
            }

        }else{
            out.println("<script> alert('No such User!')</script>");
            out.print("<script> window.location.href = \"SignUp.jsp\"</script>");
        }
        }catch(Exception E){}
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
