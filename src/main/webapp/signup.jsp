<%@page import="com.example.week_6.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
    <title>E-Commerce Cart</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Signup</div>
        <div class="card-body">
            <form action="user-signup" method="post">
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="signup-name" class="form-control" placeholder="Enter your name" required>
                </div>
                <div class="form-group">
                    <label>Email address:</label>
                    <input type="email" name="signup-email" class="form-control" placeholder="Enter your email" required>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="signup-password" class="form-control" placeholder="Enter your password" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Signup</button>
                </div>
            </form>
            <p>Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>