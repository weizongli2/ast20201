<!--This is a simple session checking jsp, you can simply include it in the head of jsp documents and it will check whether you have logged in or not-->
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
        Object obj=session.getAttribute("user");
        String user=(String)session.getAttribute("user");
        if(obj==null){%>
<script type="text/javascript" language="javascript">
        alert("Invalid Login!");
 </script>
        <%out.print("window.location.href='login.html'");
        }
        %>
    </body>
</html>
