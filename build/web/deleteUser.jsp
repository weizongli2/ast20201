<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 

<!DOCTYPE html>
<html>
    <head>
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
                         search_delete: search
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page Delete User</title>
    </head>
    <body>
        <h1 style="text-align:center">Delete User</h1>
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
        <input class="form-control" id="search" type="text" name="search" placeholder="User name">
        <p></p>
        <input class="form-control" type= "button" id="btn_srch" value = "Search">
        <p></p>
        <input class="form-control" type= "button" id="btn_dlt" value = "Delete">
        </form>
        </div>
            </div>
        
        <div>&nbsp;</div>
        <div id="delete_result"></div>
        <div id="div_result"></div>
        
        
<script type="text/javascript">
    $(document).ready(function() {
        $("#btn_dlt").click(function (){
        var selectboxes = $(".select");
        
        //Create array to store id's of selected products
        var ToDelete = [];
        for(i=0;i<selectboxes.length;i++){
            if(selectboxes[i].checked){
                ToDelete.push($("#"+i).val());
            }
        }
        if(ToDelete.length > 0){
        $.post(
                    "DeleteUser",
			{
                         ToDelete: ToDelete
			},
                        function(data){
                            $("#delete_result").html(data);
                            //clear search results
                            $("#div_result").html("");
			}
                    );
        }
       
    });
    
});

 </script>
    </body>
</html>
