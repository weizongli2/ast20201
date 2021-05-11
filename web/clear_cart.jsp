<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cleaning Your Cart!</title>
        

        
    </head>
    <body>
        
     
        
      

        
        
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/ast20201db?useSSL=false"
     user="root"  password="root"/>
        
        <sql:update dataSource="${snapshot}" var="result">
INSERT INTO cart (id, name, price) SELECT id, name, price FROM products WHERE id= ?;
<sql:param value="${product_id}" />
</sql:update>




        
 
    </body>
</html>
