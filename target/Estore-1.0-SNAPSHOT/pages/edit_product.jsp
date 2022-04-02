<%@ page import="java.util.Objects" %>
<%@ page import="com.attech.estore.dao.AdminDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.attech.estore.dto.ProductDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Golden Garden</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
        integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
          integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
          crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
          integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
          crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
          integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="../css/style.css">

</head>

<body>
<!-- partial:index.partial.html -->
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Expires","0");


  HttpSession session1 = request.getSession();
  String email = (String) session1.getAttribute("email");
  String role = (String) session1.getAttribute("role");

  if (!Objects.equals(role, "admin") || email ==  null){
    session1.invalidate();
    response.sendRedirect("../index.jsp");
  }
      AdminDao adminDao = new AdminDao();
      ProductDto products = adminDao.getProductById(Integer.parseInt(request.getParameter("id")));


%>


<div class="nav">
  <input type="checkbox" id="nav-check">
  <div class="nav-header">
    <div class="nav-title">
      Golden Garden
    </div>
  </div>
  <div class="nav-btn">
    <label for="nav-check">
      <span></span>
      <span></span>
      <span></span>
    </label>
  </div>

  <div class="nav-links">
    <a href="./home.jsp" target="_blank">product</a>
    <a href="#" target="_blank">contact</a>
    <a href="#" target="_blank">about</a>
    <a><form action="logout"><button style="border: none; background: none;color: inherit;">logout</button></form></a>
  </div>
</div>

<!-- Main -->
<div class="dash-container">
  <section class="add-product">
    <div class="card product-form">
      <h4 class="form-title">Edit Fruit</h4>
      <form method="post" action="updateProductServlet" class="add-item">
        <input type="hidden" name="id" value="<%= products.getId()%>">
        <input type="text" name="name" value="<%= products.getName()%>">
        <input type="text" name="price" value="<%= products.getPrice()%>">
        <input type="text" name="quantity" value="<%= products.getQuantity()%>">
        <input type="text" name="url" value="<%= products.getUrl()%>">
        <button>update product</button>
      </form>
    </div>
  </section>
</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src="../js/script.js"></script>
</body>

</html>
