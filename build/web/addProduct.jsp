<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            body{
                background-color: #EAEAEF;
            }
       </style>
</head>
    <body>
<section>
<h3 style="text-align:center">Add Product</h3>
<p style="text-align:center"><strong>Only inventory of seller is available!</strong></p>
</section>
<article>
<div>
	<div>&nbsp;</div>
	<div>
    	<form class="container" id="form_addProduct" name="form_addProduct" action="AddProduct" method="post" enctype="multipart/form-data">
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
            <label class="form-label" for="p_name">Product Name</label>
            <div><p><input type="text" class="form-control" id="p_name" name="p_name" required/></p></div>
        </div>
          
        <div class="card-body">
            <label class="form-label" for="p_price">Price</label>
            <div><p><input type="number" class="form-control" id="p_price" name="p_price" max="10000" min="1" required/></p></div>
        </div>
        <div class="card-body">
            <label class="form-label" for="p_amount">Amount</label>
            <div><p><input type="number" class="form-control" id="p_amount" name="p_amount" max="300" min="1" required/></p></div>
        </div>
        <div class="card-body">
            <label class="form-label" for="p_desc">Description</label>
            <div><p><input type="text" class="form-control" id="p_desc" name="p_desc" /></p></div>
        </div>
        <div class="card-body">
            <label class="form-label" for="p_category">Category</label>
            <div><p><select class="form-control" id="p_category" name="p_category" required>
                     <option value="" selected data-default>Select Category</option>
                     <option value="clothes">Clothes</option>
                     <option value="clothing">Clothing</option>
                     <option value="products">Products</option>
                     <option value="eproducts">E-products</option>
                    </select>
                </p></div>
        </div>
        <div class="card-body">
            <label class="form-label" for="p_subcategory">Sub Category</label>
            <div><p><select class="form-control" id="p_subcategory" name="p_subcategory" required>
                        <option value="" selected data-default>Select Sub Category</option>
                        <option value="Mensclothing">Men's clothing</option>
                        <option value="Womensclothing">Women's clothing</option>
                        <option value="Childrensclothing">Children's clothing</option>
                        <option value="Mensshoes">Men's shoes</option>
                        <option value="Womensshoes">Women's shoes</option>
                        <option value="Toys">Toys</option>
                        <option value="Furniture">Furniture</option>
                        <option value="eproducts">E-products</option>
                    </select>
                </p>
            </div>
        </div>
       
       
        <div class="card-body">
            <label class="form-label" for="p_image">Image Upload</label>
            <div><p><input type="file" class="form-control" id="p_image" name="p_image" required/></p></div>
        </div>
            
        <div class="card-body">
			<div>&nbsp;</div>
            <div><p>
            <input type="submit" class="btn btn-primary" id="n_submit" name="n_submit" value="add" /></p></div>
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
