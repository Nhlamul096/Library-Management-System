<%-- 
    Document   : Category
    Created on : 09-Jun-2022, 15:05:19
    Author     : TRAIN 76
--%>

<%@page import="java.util.List"%>
<%@page import="za.co.material.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <h1>Hello World!</h1>
        <p> <%= request.getAttribute("msg") %> </p>
        <p> <%= request.getAttribute("category").toString() %></p>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
