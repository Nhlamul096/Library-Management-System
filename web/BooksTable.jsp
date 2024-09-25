<%-- 
    Document   : BooksTable
    Created on : 04 Jun 2022, 9:49:39 PM
    Author     : Lungh
--%>

<%@page import="java.util.List"%>
<%@page import="za.co.material.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <style>
            
        </style>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');" >
        
    <center><h1>Books Table</h1></center>
       
    <center><div>
        <p> <%= request.getAttribute("msg") %> </p>
        <% List<Book> b = (List)request.getAttribute("book"); %>
        <div> <% for(Book book : b){
          out.println("Book ID : "+book.getBookid()+"<br/>");
          out.println("ISBNumber : "+ book.getISBNo()+"<br/>");
          out.println("Title : "+book.getTitle()+"<br/>");
          out.println("Category : "+book.getCategory()+"<br/>");
          out.println("Authors : "+book.getAuthors()+"<br/>");
          out.println("Rack Number : "+book.getRackNo()+"<br/>");
          out.println("Is The Book Available : "+book.getIsIsAvailable()+"<br/>");
          out.println("Publication Date : "+book.getPubDate()+"<br/>");
        } ; %></div>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
        </div></center>
    </body>
</html>
