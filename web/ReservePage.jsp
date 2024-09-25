<%-- 
    Document   : ReservePage
    Created on : 13-Jun-2022, 12:39:36
    Author     : TRAIN 76
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <p> <%= request.getAttribute("msg") %> </p>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
