<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String id=request.getParameter("m_id");
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(Exception e){}
        
        try{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
        pstmt=conn.prepareStatement("SELECT * FROM userinfo WHERE id=?");
        pstmt.setString(1,id);
        
        pstmt.execute();
        rs=pstmt.getResultSet();
        }catch(SQLException e){
        out.println(e.getMessage());
        }
        out.println("<ul>");
        out.println("User information:");
        if(rs.next()){
            out.println("<li>ID:" + rs.getInt("id")+"</li>");
            out.println("<li>User Name:" + rs.getString("username")+"</li>");
            out.println("<li>Password:" + rs.getString("pwd")+"</li>");
            out.println("<li>User Type:" + rs.getString("usertype")+"</li>");
        }
        out.println("</ul>");
        %>
    </body>
</html>
