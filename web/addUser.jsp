<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            body{
            background-color: #EAEAEF;}
            table, th, td {
              border: 1px solid black;
              margin:0 auto
            }
       </style>
</head>
    <body>
<h1 style="text-align:center">AddUser</h1>
</section>
<article>
<div>
	<div>&nbsp;</div>
	<div>
    	<form class="container" id="form_addUser" name="form_addUser" action="AddUser" method="post">
    	<div class="card" style="box-shadow:5px 5px 10px">
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="homepage.html"><img src="images/logo.png" alt="Logo"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="addUser.jsp">Add User</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="deleteUser.jsp">Delete User</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="modifyUser.jsp">Modify User</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addProduct.jsp">Add Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="deleteProduct.jsp">Delete Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="modifyProduct.jsp">Modify Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="logout">Log Out</a>
      </li>
    </ul>
  </div>  
</nav>
        <div class="card-body">
			<div><p>User Name:</p></div>
            <div><p><input type="text" class="form-control" id="n_name" name="n_name" /></p></div>
        </div>
          <div class="card-body">
			<div ><p>User Type:</p></div>
                        <div><p>
                                <select class="form-control" id="n_usertype" name="n_usertype" >
                                    <option value="user">user</option>
                                    <option value="seller">seller</option>
                                    <option value="admin">admin</option>
                                </select></p></div>
        </div>
        <div class="card-body">
			<div><p>Password:</p></div>
            <div><p><input type="password" class="form-control" id="n_password" name="n_password" /></p></div>
        </div>
        <div>
			<div>&nbsp;</div>
            <div class="card-body"><p>
            <input type="submit" class="btn btn-primary" id="n_submit" name="n_submit" value="addUser" /></p></div>
        </div>
        <div>
			<div><p id="div_result">&nbsp;</p></div>
        </div>

        </div>
        </form>
	</div>
    <div>&nbsp;</div>
</div>
</article>

    </body>
</html>
