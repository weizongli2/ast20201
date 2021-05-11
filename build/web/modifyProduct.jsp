<%-- 
    Document   : modifyProduct
    Created on : 2021-4-27, 18:56:13
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify Product</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            img{
                max-height:250px;
                max-width:250px;
                height:auto;
                width:auto;
            }
       </style>
    </head>
    <body>
        
        <h1 style="text-align:center">Modify Product</h1>
        <p style="text-align:center"><strong>Only inventory of seller is available!</strong></p>
        <div class="container">
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
        <form class="card-body">
            <p>Press search without any input to get all records!</p>
        <input class="form-control" id="search" type="text" name="search" placeholder="Product Name">
        <p></p>
        <input class="form-control" type= "button" id="btn_srch" value = "Search">
        </form>
        </div>
        
            <div>&nbsp;</div>
        <div id="div_result"></div>
        
        <script type="text/javascript">
    $(document).ready(function() {
        
        $('#btn_srch').click(function (){
                var search = $("#search").val();
               $.post(
                    "fetch",
			{
                         search_modify: search
			},
                        function(data){
                            $("#div_result").html(data);
                            //clear deleted results
                            $("#delete_result").html("");
			}
                    );
                });
        
    //for dynamically generated content need .on
    $(document).on("click",".modify_button",function() {
        
        //redirect to modify page and send Product id with url
        var p_id = $(this).attr('id');
            $.post(
                    "ModifyProductForm.jsp",
			{
                         p_id: p_id
			},
                        function(data){
                            $("#div_result").html(data);
			}
                );
           
        });
    });

 </script>

    </body>
</html>
