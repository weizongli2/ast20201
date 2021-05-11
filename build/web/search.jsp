<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
 <%
 String name=request.getParameter("name");
 Connection conn = null;
 Statement stmt = null;
 ResultSet rs = null;
// ResultSetMetaData meta = null;

 int fieldCount = 0;
 try{
 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 }catch(Exception E) {}
 try{
 conn = DriverManager.getConnection(
 "jdbc:mysql://localhost:3306/ast20201db?useSSL=false",
 "root",
 "root");
 stmt = conn.createStatement();
 rs = stmt.executeQuery("select * from products where name like '%"+name+"%'");
 }catch(SQLException E) {
 out.println(E.getMessage());
 }
 try{
 while(rs.next()){
   int product_idpass = rs.getInt("id");
   if(!rs.getString("category").equals("homepage")){
     out.println("<section class='w3-col s4 m4 l3'><form action='detailpage.jsp'>");
     out.println("<input type='text' value= "+product_idpass+" id='product_id' name='product_id' style='display:none' />"
             + "<input type='image' class='thumbnail' style='width:100%;height:250px' src='"+rs.getString("category")+"/"+rs.getString("subcategory")+"/"+rs.getInt("id")+"/1.jpg' onclick='this.form.m.value="+rs.getInt("id")+";'/>"
                     + "</form>");
     out.println("<h3 style='color:red;'>HKD " +rs.getString("price") +"</h3>");
     out.println("<a href='#' class='link'>" + rs.getString("name")+"</a>");
     out.println("</section>");
   }
}
 }catch(Exception E){}
 %>
    </body>
</html>
