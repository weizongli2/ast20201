<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>User Info Contents</h1>
 <%
 Connection conn = null;
 Statement stmt = null;
 ResultSet rs = null;
 ResultSetMetaData meta = null;
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
 rs = stmt.executeQuery("select * from userinfo");
 meta = rs.getMetaData();
 }catch(SQLException E) {
 out.println(E.getMessage());
 }
 out.println("**** User Information ****");
 try{
 fieldCount = meta.getColumnCount();
 out.println("<table border=\"2\"><tr>");
 for(int i=1;i<=fieldCount;i++){
 out.println("<td>"+meta.getColumnName(i)+"</td>");
 }
// out.println("<td>select</td>");
 out.println("</tr>");
 while(rs.next()){
 out.println("<tr>");
 out.println("<td>" + rs.getInt("id")+"</td>");
 out.println("<td>" + rs.getString("username")+"</td>");
 out.println("<td>" + rs.getString("pwd")+"</td>");
 out.println("<td>" + rs.getString("usertype")+"</td>");
 out.println("<td>" + rs.getTimestamp("Lastlogin")+"</td>");
 out.println("<td>" + rs.getInt("Numlogin")+"</td>");
// out.println("<td><input type='checkbox' name='checkbox'></td>");
 out.println("</tr>");
 }
 out.println("</table>");
 }catch(Exception E){}
 %>
 <input type='Button' name='addUser' value='addUser' onclick='javascript:window.location.href="addUser.jsp"'>
 <input type='Button' name='deleteUser' value='deleteUser' onclick='javascript:window.location.href="deleteUser.jsp"'>
 <input type='Button' name='ModifyUser' value='ModifyUser' onclick='javascript:window.location.href="modifyUser.jsp"'>
 <input type='Button' name='exit' value='exit' onclick='javascript:window.location.href="login.html"'>
 </form>
 <div id='div_result'></div>

    </body>
</html>
