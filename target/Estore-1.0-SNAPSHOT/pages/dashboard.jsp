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
  String category = request.getParameter("cat");
  List<ProductDto> products;
  if (category == null || category.equals("all")){
    products = adminDao.fetchProducts();
  }else {
    products = adminDao.filterByCategory(category);
  }

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
    <div class="btn-group">
      <button type="button" class="dropdown-toggle" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false"
              style=" background-color: inherit;
                    color: #fff;border:none;cursor: pointer;">
        <i class="fa-solid fa-filter"></i>
      </button>
      <div class="dropdown-menu">
        <form action="dashboard.jsp" method="get">
          <input type="hidden" id="all" name="cat" value="all">
          <button type="submit" class="dropdown-item">all</button>
        </form>
        <form action="dashboard.jsp" method="get">
          <input type="hidden" id="cat1" name="cat" value="melons">
          <button type="submit" class="dropdown-item">melons</button>
        </form>
        <form action="dashboard.jsp">
          <input type="hidden" id="cat2" name="cat" value="citrus">
          <button type="submit" class="dropdown-item">citrus</button>
        </form>
      </div>
    </div>
    <a href="./find_product.jsp" target="_blank"><i class="fa fa-search"></i></a>
    <a href="#" target="_blank">contact</a>
    <a href="#" target="_blank">about</a>
    <a><form action="logout" method="post"><button style="border: none; background: none;color: inherit;">logout</button></form></a>
  </div>
</div>

<!-- Main -->
<div class="dash-container">
  <section class="add-product">
    <div class="card product-form">
      <h4 class="form-title">Add Fruit</h4>
      <form method="post" action="addProduct" class="add-item">
        <input type="text" name="name" placeholder="item name">
        <input type="text" name="price" placeholder="item price">
        <select class="category" name="category">
          <option value="stone">Stone fruit</option>
          <option value="tropical and exotic">Tropical and exotic</option>
          <option value="berries">Berries</option>
          <option value="citrus">Citrus</option>
          <option value="melons">Melons</option>
          <option value="tropical and exotic">Tropical and exotic</option>
        </select>
        <input type="number" name="quantity" value="0">
        <input type="text" name="url" placeholder="image url">
        <button>add product</button>
      </form>
    </div>
    <div class="card product-table">
      <table>
        <thead>
        <th class="th">SN</th>
        <th class="th">name</th>
        <th class="th">price</th>
        <th class="th">quantity</th>
        <th class="th">update</th>
        <th class="th">delete</th>
        </thead>
        <tbody>
        <%
          int count = 1;
          for (ProductDto product: products){%>
        <tr>
          <td class="td"><%= count%></td>
          <td class="td"><%=product.getName()%></td>
          <td class="td"><%=product.getPrice()%></td>
          <td class="td"><%=product.getQuantity()%></td>
          <td class="td">
            <a class="" href="./edit_product.jsp?id=<%=product.getId()%>" aria-label="edit">
              <i class="fa fa-pencil edit" aria-hidden="true"></i>
            </a>
          </td>
          <td class="td">
            <a class="" href="./deleteProductServlet?id=<%=product.getId()%>" aria-label="Delete">
              <i class="fa fa-trash delete" aria-hidden="true"></i>
            </a>
          </td>
        </tr>
        <%count++;}%>

        </tbody>
      </table>
    </div>
  </section>

  <section class="add-product" style="margin-top: 20px;">
    <div class="card product-form">
      <h4 class="form-title">Users</h4>
      <form method="" action="" class="add-item">
        <input type="text" name="" placeholder="item name">
        <input type="text" name="" placeholder="item price">
        <input type="number" name="" value="0">
        <input type="text" name="" placeholder="image url">
        <button>add user</button>
      </form>
    </div>
    <div class="card product-table">
      <table>
        <thead>
        <th class="th">SN</th>
        <th class="th">name</th>
        <th class="th">price</th>
        <th class="th">quantity</th>
        <th class="th">update</th>
        <th class="th">delete</th>
        </thead>
        <tbody>
        <tr>
          <td class="td">1</td>
          <td class="td">banana</td>
          <td class="td">N 500.00</td>
          <td class="td">10</td>
          <td class="td">u</td>
          <td class="td"> d</td>
        </tr>
        </tbody>
      </table>
    </div>
  </section>

</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src="../js/script.js"></script>

</body>

</html>
