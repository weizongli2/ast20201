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
public class SignUp extends HttpServlet {

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
        
//        HttpSession session = request.getSession(true);
//        Object obj=session.getAttribute("user");
//        String user = (String)session.getAttribute("user");
//        if(obj==null){
//            out.print("<script>alert('Invalid Login!');</script>");
//            response.sendRedirect("login.html");
//        }
        
        String new_name=request.getParameter("n_name");
        String new_usertype=request.getParameter("n_usertype");
        String pwd=request.getParameter("n_password");
        
        
        if(new_name!=null&&pwd!=null){
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;

            try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            }catch(Exception e){}

            try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
            pstmt=conn.prepareStatement("INSERT INTO userinfo (username,pwd,usertype) VALUES (?,?,?)");
            pstmt.setString(1,new_name);
            pstmt.setString(2,pwd);
            pstmt.setString(3,new_usertype);
            
            if(pstmt.executeUpdate()>=1){
                out.print("<script>alert('Account "+new_name+" is added');</script>");
                PreparedStatement getID=conn.prepareStatement("SELECT  MAX(id) FROM userinfo");
                        if(getID.execute()){
                            rs=getID.getResultSet();
                            if(rs.next()){
                                out.print("<script>alert('ID: "+rs.getInt("max(id)")+"');</script>");
                            }else{
                                out.print("<script>alert('Invalid ID!');</script>");
                            }
                        }
            }else{
                out.print("<script>alert('Add User failed!');</script>");
            }
            out.println("</ul>");
            conn.close();
            }catch(SQLException e){
            out.println(e.getMessage());
            }
            out.println("<script>window.location.href='login.html'</script>");
           
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
