<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product details</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
                <style >
            .myButton {
	box-shadow: 0px 10px 14px -7px #3e7327;
	background:linear-gradient(to bottom, #77b55a 5%, #72b352 100%);
	background-color:#77b55a;
	border-radius:4px;
	border:1px solid #4b8f29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:13px;
	font-weight:bold;
	padding:6px 12px;
	text-decoration:none;
	text-shadow:0px 1px 0px #5b8a3c;
                    margin-top: 20px;
}
.myButton:hover {
	background:linear-gradient(to bottom, #72b352 5%, #77b55a 100%);
	background-color:#72b352;
}
.myButton:active {
	position:relative;
	top:1px;
}

        </style>
    </head>
    <body>

        
 <%

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

         + "<input type='image' style='width:75%;height:75%' src='images/"+rs.getString("category")+"/"+rs.getInt("id")+"/1.jpg' onclick='this.form.m.value="+rs.getInt("id")+";'/>");
 out.println("</div>");
 out.println("<div class='w3-col s12 m6 l7'>");
 out.println("<h1>" + rs.getString("name")+"</h1>");
 out.println("<h3 style='color:red;'>HKD " +rs.getString("price") +"</h3>");
 out.println( "Stock of product: " +rs.getString("amount") );
 out.println( "<h1> Description</h1>");
 out.println( rs.getString("description"));
 
 out.println("</div>");
     out.println(" <br>");

     out.println("<section class='w3-col s4 m4 l3'><form action='shopping_cart.jsp' >");
 out.println("<input type='int' id='product_id' name='product_id' style='display:none'/>");

     

  out.println("<input type='submit' class='myButton' value='Add This To Shopping Cart !'  onclick='this.form.product_id.value="+rs.getInt("id")+";'/>");

 out.println("</form>");
 out.println("</section>");

 out.println("</div>");
 


 }
 }catch(Exception E){}

%>

<a href="homepage.html" class="myButton">Back Home Page</a>


    </body>
</html>
