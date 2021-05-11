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
 * @author lenovo
 */
public class CheckOut extends HttpServlet {

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
        int order_id=0;
        HttpSession session = request.getSession(true);
        Object obj=session.getAttribute("id");
        int id = (int)session.getAttribute("id");
        String user = (String)session.getAttribute("user");
        if(obj==null){
                        out.print("<script type=\"text/javascript\"> alert('Invalid Login!');"
                       + "window.location.href = \"http://localhost:8080/ast20201groupproject/login.html\"; </script>");
        }else{
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
//                PreparedStatement pstmt2=null;
                ResultSet rs2=null;
                PreparedStatement pstmt3=null;
                ResultSet Pinfo=null;
                PreparedStatement update=null;
                PreparedStatement remove=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                    out.print(e.getMessage());
                }
                
                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                pstmt=conn.prepareStatement("SELECT * FROM cart");
                PreparedStatement getID=conn.prepareStatement("SELECT * FROM orders");
                if(getID.execute()){
                    rs2=getID.getResultSet();
                    if(rs2.next()){
                        
                        order_id=rs2.getInt("order_id")+1;
//                        out.print("<script>alert('"+order_id+"');</script>");
                    }
                }
                if(pstmt.execute()){
                    rs=pstmt.getResultSet();
                }
                while(rs.next()){
                    pstmt3=conn.prepareStatement("INSERT INTO orders (order_id,user,price,products,merchant) VALUES (?,?,?,?,?)");
                    pstmt3.setInt(1,order_id);
                    pstmt3.setString(2,user);
                    pstmt3.setString(3,rs.getString("price"));
                    PreparedStatement getPinfo=conn.prepareStatement("SELECT * FROM products where name=?");
                    getPinfo.setString(1,rs.getString("name"));
                    if(getPinfo.execute()){
                        Pinfo=getPinfo.getResultSet();
                    }
                    if(Pinfo.next()){
                        pstmt3.setString(4,Pinfo.getString("id"));
                        pstmt3.setString(5,Pinfo.getString("merchant"));
                        update=conn.prepareStatement("UPDATE products SET amount=? WHERE id=?");
                        if((Pinfo.getInt("amount")-1)==-1){
                            remove=conn.prepareStatement("Delete From cart where name=?");
                            remove.setString(1,Pinfo.getString("name"));
                            remove.execute();
                            out.print("<script>alert('One of the products in your cart is out of stock!!!');</script>");
                            out.println("<script>window.location.href = \"homepage.html\";</script>");
                        }else{
                            update.setInt(1,Pinfo.getInt("amount")-1);
                            update.setString(2,Pinfo.getString("id"));
                            update.execute();
                        }
                    }
                    pstmt3.execute();
                }
                out.println("<script>window.location.href = \"Goodbye.jsp\";</script>");
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
