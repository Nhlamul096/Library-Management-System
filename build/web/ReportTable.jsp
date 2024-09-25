<%-- 
    Document   : ReportTable
    Created on : 08-Jun-2022, 12:00:39
    Author     : TRAIN 76
--%>

<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <h1>Hello World!</h1>
        <h1>Report Table</h1>
        <p> <%= request.getAttribute("msg")%> </p>
        <a href="<%= request.getAttribute("report")%>" target="blank"> Click here to open the report. </a>
<!--        <a href="<%= request.getAttribute("report")%>"> Click here to open the report. </a>-->
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
