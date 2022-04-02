<%@ page import="com.attech.estore.dao.AdminDao" %>
<%@ page import="com.attech.estore.dto.ProductDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
    <link rel="stylesheet" href="./css/style.css">

</head>

<body>
<!-- partial:index.partial.html -->
<%
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
                <form action="index.jsp" method="get">
                    <input type="hidden" id="all" name="cat" value="all">
                    <button type="submit" class="dropdown-item">all</button>
                </form>
                <form action="index.jsp">
                    <input type="hidden" id="cat1" name="cat" value="apples and pears">
                    <button type="submit" class="dropdown-item">apples and pears</button>
                </form>
                <form action="index.jsp" method="get">
                    <input type="hidden" id="cat2" name="cat" value="melons">
                    <button type="submit" class="dropdown-item">melons</button>
                </form>
                <form action="index.jsp">
                    <input type="hidden" id="cat3" name="cat" value="citrus">
                    <button type="submit" class="dropdown-item">citrus</button>
                </form>

                <form action="index.jsp">
                    <input type="hidden" id="cat4" name="cat" value="stone fruit">
                    <button type="submit" class="dropdown-item">stone fruit</button>
                </form>
                <form action="index.jsp">
                    <input type="hidden" id="cat5" name="cat" value="tropical and exotic">
                    <button type="submit" class="dropdown-item">tropical and exotic</button>
                </form>
                <form action="index.jsp">
                    <input type="hidden" id="cat6" name="cat" value="berries">
                    <button type="submit" class="dropdown-item">berries</button>
                </form>
            </div>
        </div>
        <a href="#" target="_blank">contact</a>
        <a href="#" target="_blank">about</a>
        <a href="./pages/login.jsp">login</a>
        <a href="./pages/register_user.jsp">sign up</a>
    </div>
</div>
<!-- Main -->
<div class="container">

    <!-- Nav -->
    <div class="cart-menu">
        <div class="row">
            <div class="col">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cart">Cart (<span
                        class="total-count"></span>)</button><button class="clear-cart btn btn-danger">Clear Cart</button>
            </div>
        </div>
    </div>
    <div class="row cart-items">
        <%for (ProductDto product: products){%>
        <div class="col">
            <div class="card">
                <img class="card-img-top" src="<%=product.getUrl()%>" alt="<%=product.getName()%>">
                <div class="card-block">
                    <h4 class="card-title"><%= product.getName()%></h4>
                    <p class="card-text">NGN <%= product.getPrice()%></p>
                    <a href="#" data-name="<%= product.getName()%>" data-price="<%= product.getPrice()%>" class="add-to-cart btn btn-primary">Add to cart</a>
                    <a href="${pageContext.request.contextPath}/pages/view_product.jsp?id=<%=product.getId()%>" class="btn btn-dark"><i class="fa fa-eye"></i> </a>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="show-cart table">

                </table>
                <div>Total price: NGN<span class="total-cart"></span>
                    <input type="text" class="total-cart-input" value="0">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Order now</button>
            </div>
        </div>
    </div>
</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src="./js/script.js"></script>

</body>
</html>