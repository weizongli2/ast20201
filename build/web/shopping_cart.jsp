<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        
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
        
        <h1 align="center"> Shopping list </h1>
        
      
        
        <c:set var="product_id" value=  "<%= request.getParameter("product_id")%>" />
        
        
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/ast20201db?useSSL=false"
     user="root"  password="root"/>
        
        <sql:update dataSource="${snapshot}" var="result">
INSERT INTO cart (id, name, price) SELECT id, name, price FROM products WHERE id= ?;
<sql:param value="${product_id}" />
</sql:update>




        <sql:query dataSource="${snapshot}" var="result">
SELECT * from cart;
</sql:query>


<table border="1" width="100%">
<tr>
   <th>Product ID</th>
   <th>Name</th>
   <th>Price</th>
</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td><c:out value="${row.id}"/></td>
   <td><c:out value="${row.name}"/></td>
   <td><c:out value="${row.price}"/></td>
</tr>

</c:forEach>




</table>

<sql:query dataSource="${snapshot}" var="result">
SELECT SUM(price) AS TOTAL FROM cart;
</sql:query>

<table border="1" width="100%">
<tr>
   <th><h1> Total Price:</h1></th>

</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td align=" center"><h1>$ <c:out value="${row.TOTAL}"/> </h1> </td>

</tr>
</c:forEach>
</table>

    <a href="homepage.html" class="myButton">Back Home Page</a>
    
    <a href="Goodbye.jsp" class="myButton" >Check out</a>
 



    </body>
</html>
