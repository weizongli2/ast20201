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
@WebServlet(urlPatterns = {"/OrderFetch"})
public class OrderFetch extends HttpServlet {

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

            HttpSession session = request.getSession(true);
            Object obj=session.getAttribute("user");
            String user = (String)session.getAttribute("user");
            String usertype = (String)session.getAttribute("usertype");
//            if(!user.equals("admin")){
//                if(user.equals("seller")){
//                    response.sendRedirect("CheckOrder");
//                }else{
//                    out.println("<script>alert('No authority!')</script>");
//                    out.print("<script>window.location.href=\"login.html\"</script>");
//                }
//            }else{

                String search=request.getParameter("search");
                
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                PreparedStatement pstmt2=null;
                ResultSet rs2=null;
//                PreparedStatement pstmt3=null;
//                ResultSet rs3=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                e.getMessage();
                }
//                try{
//                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
//                pstmt3=conn.prepareStatement("select * from userinfo where username=?");
//                pstmt3.setString(1,user);
//                if(pstmt3.execute()){
//                    rs3=pstmt3.getResultSet();
//                }
//                if(rs3.next()){
//                    usertype=rs3.getString("usertype");
//                }
//                conn.close();
//                }catch(SQLException e){
//                out.println(e.getMessage());
//                }
           
            if(!usertype.equals("admin")&&!usertype.equals("seller")){
                    out.println("<script>alert('No authority!')</script>");
                    out.print("<script>window.location.href=\"login.html\"</script>");
}else if(usertype.equals("admin")){
try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
if(search.equals("")){
    
                    pstmt=conn.prepareStatement("select * from orders");

                    if(pstmt.execute()){
                        rs=pstmt.getResultSet();
                    }
                out.println("<div class=\"table-responsive\">");
                out.println("<table class=\"table table-hover\">");
                out.println("<thead class=\"thead-dark\">");
                out.println("<tr>");
                out.println("<th scope=\"col\">ID</th>");
                out.println("<th scope=\"col\">Order ID</th>");
                out.println("<th scope=\"col\">Buyer</th>");
                out.println("<th scope=\"col\">Price</th>");
                out.println("<th scope=\"col\">Products</th>");
                out.println("<th scope=\"col\">Amount</th>");
                out.println("<th scope=\"col\">Seller</th>");
                out.println("</tr>");
                out.println("</thead>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("id")+"</td>");
                    out.println("<td>"+rs.getString("order_id")+"</td>");
                    out.println("<td>"+rs.getString("user")+"</td>");
                    out.println("<td>"+rs.getString("price")+"</td>");
                    out.println("<td>"+rs.getString("products")+"</td>");
                    out.println("<td>"+rs.getString("amout")+"</td>");
                    out.println("<td>"+rs.getString("merchant")+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</div>");
}else{
        double totalprice=0;
        String buyer=null;
        pstmt=conn.prepareStatement("select * from orders where order_id=?");
        pstmt.setString(1,search);
                    if(pstmt.execute()){
                        rs=pstmt.getResultSet();
                    }
                    out.println("<div class='container card'>");
                    out.println("<div class='card-body'><strong>Order ID</strong>:"+search+"</div>");
                    
                while(rs.next()){
                    out.println("<div class='card-body'>"+rs.getString("products")+"X"+rs.getString("amout")+" Price:"+rs.getString("price")+" Seller:"+rs.getString("merchant")+"</div>");
                    out.println();
                    totalprice=rs.getInt("price")+totalprice;
                    buyer=rs.getString("user");
                }
                    out.println("<div class='card-body'><strong>Total price</strong>:"+totalprice+"</div>");
                    out.println("<div class='card-body'><strong>Buyer</strong>:"+buyer+"</div>");
                    out.println("</div>");
}
                conn.close();
                }catch(SQLException e){
                out.println(e.getMessage());
                }
}else if(usertype.equals("seller")){
try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                pstmt=conn.prepareStatement("Select * from orders where merchant=?");
                pstmt2=conn.prepareStatement("select * from userinfo where username=?");
                pstmt2.setString(1,user);
                if(pstmt2.execute()){
                    rs2=pstmt2.getResultSet();
                }
                if(rs2.next()){
                        pstmt.setString(1,rs2.getString("id"));
                    }else{
                    pstmt=conn.prepareStatement("Select * from orders");
                }
                if(pstmt.execute()){
                    rs=pstmt.getResultSet();
                }
                if(search.equals("")){
                out.println("<div class=\"table-responsive\">");
                out.println("<table class=\"table table-hover\">");
                out.println("<thead class=\"thead-dark\">");
                out.println("<tr>");
                out.println("<th scope=\"col\">Order ID</th>");
                out.println("<th scope=\"col\">Buyer</th>");
                out.println("<th scope=\"col\">Price</th>");
                out.println("<th scope=\"col\">Comments</th>");
                out.println("<th scope=\"col\">Products</th>");
                out.println("<th scope=\"col\">Amount</th>");
                out.println("</tr>");
                out.println("</thead>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("order_id")+"</td>");
                    out.println("<td>" + rs.getString("user")+"</td>");
                    out.println("<td>" + rs.getString("price")+"</td>");
                    out.println("<td>" + rs.getString("comments")+"</td>");
                    out.println("<td>" + rs.getString("products")+"</td>");
                    out.println("<td>" + rs.getString("amout")+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</div>");
                }else{
                    double totalprice=0;
                    String buyer=null;
                    pstmt=conn.prepareStatement("select * from orders where order_id=? AND merchant=?");
                    pstmt.setString(1,search);
                    pstmt.setString(2,rs2.getString("id"));
                    if(pstmt.execute()){
                        rs=pstmt.getResultSet();
                    }
                    out.println("<div class='container card'>");
                    out.println("<div class='card-body'><strong>Order ID</strong>:"+search+"</div>");
                    
                while(rs.next()){
                    out.println("<div class='card-body'>"+rs.getString("products")+"X"+rs.getString("amout")+" Price:"+rs.getString("price")+" </div>");
                    out.println();
                    totalprice=rs.getInt("price")+totalprice;
                    buyer=rs.getString("user");
                }
                    out.println("<div class='card-body'><strong>Total price</strong>:"+totalprice+"</div>");
                    out.println("<div class='card-body'><strong>Buyer</strong>:"+buyer+"</div>");
                    out.println("</div>");
                }
                }catch(SQLException e){
                out.println(e.getMessage());
                }
}else{
    out.println("<script>alert('Invalid Login!')</script>");
                    out.print("<script>window.location.href=\"login.html\"</script>");
}
                
//            }
    
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
