<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 

<!DOCTYPE html>
<html>
    <head>
        <title>Modify product Form</title>
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

<article>
<div>
	<div>&nbsp;</div>
	<div>
    	<form class="container" id="form_ModifyProduct" name="form_ModifyProduct" action="ModifyProduct" method="post" enctype="multipart/form-data">
    	<div class="card" style="box-shadow:5px 5px 10px">
            <%
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                }catch(Exception e){}
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ast20201db?useSSL=false","root","root");
                String p_id = request.getParameter("p_id");
                pstmt=conn.prepareStatement("Select * from products where id = ?");
                pstmt.setString(1,p_id);
                if(pstmt.execute()){
                    rs = pstmt.getResultSet();
                }
                if(rs.next()){
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_id'><strong>Product ID: </strong>   "+rs.getInt("id") +"</label>");
                out.print("<div><p><input type='text' class='form-control' name='p_id' id='p_id' value='"+rs.getInt("id") +"' readonly/></p></div>");
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_name'><strong>Product Name: </strong> "+rs.getString("name") +"</label>");
                out.print("<div><p><input type='text' class='form-control' id='p_name' name='p_name' /></p></div>");
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_price'><strong>Price: </strong>   "+rs.getInt("amount") +"</label>");
                out.print("<div><p><input type='number' class='form-control' id='p_price' name='p_price' max='10000' min='1' /></p></div>");
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print(" <label class='form-label' for='p_amount'><strong>Amount : </strong>  "+rs.getInt("price") +" </label>");
                out.print("<div><p><input type='number' class='form-control' id='p_amount' name='p_amount' max='300' min='1' /></p></div>");               
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_desc'><strong>Description: </strong>   "+rs.getString("description") +"</label>");
                out.print("<div><p><input type='text' class='form-control' id='p_desc' name='p_desc' /></p></div>");               
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_category'><strong>Category</strong> :   "+rs.getString("category") +"</label>");
                out.print(" <div><p><select class='form-control' id='p_category' name='p_category' >"); 
                out.print("<option value='' selected data-default>Select Category</option>");  
                out.print("<option value='clothes'>Clothes</option>");               
                out.print("<option value='clothing'>Clothing</option>");               
                out.print("<option value='products'>Products</option>");               
                out.print("<option value='eproducts'>E-products</option>");               
                out.print("</select>");               
                out.print("</p></div>");               
                out.print("</div>");
                
                out.print("<div class='card-body'>");
                out.print("<label class='form-label' for='p_subcategory'><strong>Sub Category : </strong>   "+rs.getString("subcategory") +"</label>");
                out.print("<div><p><select class='form-control' id='p_subcategory' name='p_subcategory' >");               
                out.print("<option value='' selected data-default>Select Sub Category</option>");               
                out.print("<option value='Mensclothing'>Men's clothing</option>");               
                out.print(" <option value='Womensclothing'>Women's clothing</option>");               
                out.print("<option value='Childrensclothing'>Children's clothing</option>");               
                out.print("<option value='Mensshoes'>Men's shoes</option>");               
                out.print("<option value='Womensshoes'>Women's shoes</option>");               
                out.print("<option value='Toys'>Toys</option>");               
                out.print("<option value='Furniture'>Furniture</option>");               
                out.print("<option value='eproducts'>E-products</option>");               
                out.print("</select></p></div>");               
                out.print("</div>");
                
                
                }
            }catch(Exception e){out.print(e.getMessage());}
            %>
              

                       
       
        <div class="card-body">
            <label class="form-label" for="p_image"><strong>Change Image</strong></label>
            <div id="image"></div>
            <div><p><input type="file" class="form-control" id="p_image" name="p_image"/></p></div>
        </div>
            
        <div class="card-body">
			<div>&nbsp;</div>
            <div><p>
            <input type="submit" class="btn btn-primary" id="n_submit" name="n_submit" value="Submit Changes" /></p></div>
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
<script>
    $(document).ready(function() {
        var p_id = $("#p_id").val();
            console.log(p_id);
            $.post(
                    "fetch_Image",
			{
                         id: p_id
			},
                        function(data){
                            $("#image").html(data);
			}
                );
        
        });
</script>
    </body>
</html>
