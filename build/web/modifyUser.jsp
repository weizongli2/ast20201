<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify User</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $('#btn_srch').click(function (){
                var search = $("#search").val();
               $.post(
                    "UserFetch",
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
        });
 </script>
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
        
        <h1 style="text-align:center">Modify User</h1>
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
        <input class="form-control" id="search" type="text" name="search" placeholder="User Name">
        <p></p>
        <input class="form-control" type= "button" id="btn_srch" value = "Search">
        </form>
        </div>
        
            <div>&nbsp;</div>
        <div id="div_result"></div>
        
        <script type="text/javascript">
    $(document).ready(function() {
    //for dynamically generated content need .on
    $(document).on("click",".modify_button",function() {
        
        //redirect to modify page and send Product id with url
        var p_id = $(this).attr('id');
            $.post(
                    "UserFetch",
			{
                         SearchForModify: p_id
			},
                        function(data){
                            $("#div_result").html(data);
			}
                    );
        
        });
        
    $(document).on("click",".change_submit",function() {
        
        var u_id = $(this).attr('name');
        var u_name = $("#u_name").val();
        var u_usertype = $("#u_usertype").val();
        var pwd = $("#pwd").val();
        
            $.post(
                    "ModifyUser",
			{
                         u_id: u_id,
                         u_name: u_name,
                         u_usertype: u_usertype,
                         pwd: pwd
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
