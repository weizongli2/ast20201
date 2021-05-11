<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product details</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
 <%
 String category=request.getParameter("category");
 String subcategory=request.getParameter("subcategory");
 String id=request.getParameter("product_id");




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
 "root","root");

 stmt = conn.createStatement();
 rs = stmt.executeQuery("select * from products where id='"+id+"'");

 }catch(SQLException E) {
 out.println(E.getMessage());
 }
 try{
 while(rs.next()){
 out.println("<div class='w3-row' style='margin-left: 20px;margin-top: 40px;margin-right: 20px;'>");
 out.println("<div class='w3-col s12 m6 l5'>");
 out.println("<input type='text' id='product_id' name='product_id' style='display:none'/>"

         + "<input type='image' style='width:100%;height:500px' src='../"+rs.getString("category")+"/"+rs.getString("subcategory")+"/"+rs.getInt("id")+"/1.jpg'>");
 out.println("</div>");
 out.println("<div class='w3-col s12 m6 l7'>");
 out.println("<h1>" + rs.getString("name")+"</h1>");
 out.println("<h3 style='color:red;'>HKD " +rs.getString("price") +"</h3>");
 out.println( "Stock of product: " +rs.getString("amount") );
 out.println( "<h1> Description </h1>");
 out.println( rs.getString("description"));
 out.println("</div>");
 out.println("</div>");
 }
 }catch(Exception E){}
 %>
    </body>
</html>
