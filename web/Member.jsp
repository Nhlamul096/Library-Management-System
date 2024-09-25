<%-- 
    Document   : Member
    Created on : 30-May-2022, 10:43:16
    Author     : TRAIN 76
--%>

<%@page import="za.co.material.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <h1>Members!!! Table</h1>
        <% Member m = (Member)request.getAttribute("member"); %>
        <p><%=  request.getAttribute("msg") %></p>
        <% if(m!=null){ %>
        <p>Id Number : <%= m.getIdNumber() %> </p>
        <p>Name : <%= m.getName() %> </p>
        <p>Surname: <%=  m.getSurname() %></p>
        <p>Title : <%= m.getTitle() %> </p>
        <p>Address : <%= m.getAddress() %> </p>
        <p>TelNumber : <%=  m.getTelNumber() %></p>
        <p>CellNumber : <%= m.getCellNumber() %> </p>
        <p>Email : <%= m.getEmail() %> </p>
        <p>Role : <%= m.getRole() %> </p>
        <%}%>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
