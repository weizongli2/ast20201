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
@WebServlet(urlPatterns = {"/UserFetch"})
public class UserFetch extends HttpServlet {

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
        
        //get input value from request
            String search_modify = request.getParameter("search_modify");
            String search_delete = request.getParameter("search_delete");
            String SingleSearchForModify = request.getParameter("SearchForModify");
            
            HttpSession session = request.getSession(true);
            Object obj=session.getAttribute("user");
            String user = (String)session.getAttribute("user");
            if(!user.equals("admin")){
                out.println("<script>alert('No authority!')</script>");
                out.print("<script>window.location.href=\"login.html\"</script>");
            }else{
                String search = null;
            if(search_modify != null){
                search = search_modify;
            }else if(search_delete != null){
                search = search_delete;
            }
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){
                e.getMessage();
                }

                try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                
                if(search != null){
                //print all records if input is blank
                if(search.length() > 0){
                pstmt=conn.prepareStatement("Select * from userinfo where username = ?");
                pstmt.setString(1,search);
                }else{
                    pstmt=conn.prepareStatement("Select * from userinfo");
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
                out.println("<th scope=\"col\">User ID</th>");
                out.println("<th scope=\"col\">User Name</th>");
                out.println("<th scope=\"col\">User Type</th>");
                out.println("<th scope=\"col\">Password</th>");
                
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
                    out.println("<td>"+rs.getInt("id")+"</td>");
                    out.println("<td>"+rs.getString("username")+"</td>");
                    out.println("<td>"+rs.getString("usertype")+"</td>");
                    out.println("<td>"+rs.getString("pwd")+"</td>");
                    
                    //print a checkbox for selecting records
                    if(search_delete != null){
                        out.println("<td><input id='" + i + "' value='" + rs.getString("id") + "' type='checkbox' class='select'></td>");
                    }
                    
                    out.println("</tr>");
                    i++;
                }
                
                out.println("</table>");
                out.println("</div>");
                }else if(SingleSearchForModify != null){
                    pstmt=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
                pstmt.setString(1,SingleSearchForModify);
                if(pstmt.execute()){
                    rs=pstmt.getResultSet();
                }
                
                if(rs.next()){
                    out.println("<div style='text-align:center'>");
                out.println("<form>");
                out.println("<table>");
                
                out.println("<tr>");
                out.println("<th>User ID</th>");
                out.println("<td>"+rs.getString("id")+"</td>");
                out.println("<td>Cannot be changed</td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<th>User Name</th>");
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td><input class=\"form-control\" type='text' id='u_name' placeholder='Change here'></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>User Type</th>");
                out.println("<td>"+rs.getString("usertype")+"</td>");
                out.println("<td><select class=\"form-control\" id='u_usertype'><option value='user'>User</option><option value='admin'>Admin</option></select></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Password</th>");
                out.println("<td>"+rs.getString("pwd")+"</td>");
                out.println("<td><input class=\"form-control\" type='text' id='pwd' placeholder='Change here'></td>");
                out.println("</tr>");

                out.println("</table>");
                out.println("</form>");
                out.println("<p></p >");
                out.println("<td><input class='change_submit' type='button' name='"+ rs.getString("id") +"' value='Submit Changes'></td>");
                out.println("</div>");
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