<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %> 
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
 <%
        String user_name=request.getParameter("user_name");
        String pwd=request.getParameter("pwd");
        String user=request.getParameter("user");
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
                session.setAttribute("user",user);
                pstmt2.execute();
                out.println("<div class='alert alert-info'><strong>Welcome," + user_name + "\nLast login :" + rs.getTimestamp("Lastlogin") + "</strong></div>");
//                if(rs.getString("usertype").equals("user")){
//                    out.println("<script>window.location.href = \"SignUp.html\"<script>");
//                }
                if(rs.getString("usertype").equals("admin")){
                    out.println("<ul><li><a href=\"addUser.jsp\">Add User</a>&nbsp<a href=\"deleteUser.jsp\">Delete User</a>&nbsp<a href=\"modifyUser.jsp\">Modify User</a></li>");
                    out.println("<li><a href=\"addProduct.jsp\">Add Product</a>&nbsp<a href=\"deleteProduct.jsp\">Delete Product</a>&nbsp<a href=\"modifyProduct.jsp\">Modify Product</a></li></ul>");
                }else if(rs.getString("usertype").equals("seller")){
                    out.println("<ul><li><a href=\"addProduct.jsp\">Add Product</a>&nbsp<a href=\"deleteProduct.jsp\">Delete Product</a>&nbsp<a href=\"modifyProduct.jsp\">Modify Product</a></li></ul>");
                }
            }else{
                out.println("<div class='alert alert-danger'><strong>Password Incorrect!</strong></div>");
            }

        }else{
            out.println("<div class='alert alert-danger'><strong>No such user!</strong></div>");
        }
        }catch(Exception E){}
        
%>
    </body>
</html>