<%-- 
    Document   : LoansTable
    Created on : 15-Jun-2022, 09:49:51
    Author     : TRAIN 76
--%>

<%@page import="za.co.material.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <h1>Hello World!</h1>
        <h1>Books Table</h1>

        <% Book b = (Book) request.getAttribute("book");
            if (b.getIsIsAvailable() == false) {
                out.print("Sorry, The book is currently unavailable");

            } else {
                out.print("<p>" + request.getAttribute("msg") + "</p>");
                out.print("<p>Book ID : " + b.getBookid() + "</p>");
                out.print("<p>ISBNumber:" + b.getISBNo() + "</p>");
                out.print("<p>Title : " + b.getTitle() + "</p>");
                out.print("<p>Category : " + b.getCategory() + "</p>");
                out.print("<p>Authors : " + b.getAuthors() + "</p>");
                out.print("<p>Rack Number : " + b.getRackNo() + "</p>");
                out.print("<p>Publication Date : " + b.getPubDate() + "</p>");
            }
        %>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
