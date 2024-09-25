<%-- 
    Document   : addbook
    Created on : May 27, 2022, 1:56:07 PM
    Author     : CodeOps
--%>
<%@page import="za.co.material.service.impl.BooksServiceImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js">
            
            function checkISBN(){
                $('input[type=text]').on('change', function(){
                   $siblings = $(this).siblings();
                   $.each($siblings, function(i, key){
                       arr.push($(key).val());
                   });
                   if($.inArray($(this).val(), arr) !== -1)
                   {
                       alert("ISBN already exists!")
                   }
                });

            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
        <style>
            .duplicate{
                border: 1px solid red;
                color: red;
            }
              body{
                background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');
            }
            .sidenav{
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: grey;
                overflow-x: hidden;
                padding-top: 60px;
                transition: 0.6s;
                color: white;
            }
             .sidenav a{
                padding: 6px 6px 6px 36px;
                text-decoration: none;
                font-size: 26px;
                color: black;
                display: block;
                transition: 0.3s;
            }
            .sidenav a:hover{
                color: #f1f1f1;
            }
            .sidenav .closebtn{
                position: absolute;
                top: 0;
                right: 26px;
                font-size: 36px;
                margin-left:60px;
            }
            @media screen and (max-height:450px){
                .sidenav{padding-top:15px;}
                .sidenav a{font-size: 18px;}
            }
            form{
                margin: 0 auto;
                width: 500px;
                padding: 1em;
                border: 1px solid #CCC;
                border-radius: 1em;
                background-color: grey;
            }
            ul{
                list-style: none;
                padding: 0;
                margin: 0;
            }
            form li + li {
                margin-top: 1em;
            }
            label{
                display: inline-block;
                width: 90px;
                text-align: right;
            }
            input {
                font: 1em sans-serif;
                width: 300px;
                box-sizing: border-box;
                border: 1px solid #999;
            }
            input:focus {
                border-color: #000;
            }
            .button{
                padding-left: 90px;
            }
            button{
                margin-left: .5em;
            }
        </style>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
    <center><h1 style="background-image: url('library.jpg');color:grey;">Update Book</h1></center>
    <a href="books.jsp"> <i style="color:white;" class="fa fa-chevron-left" aria-hidden="true"></i></a>
        <form name="addbook" METHOD = “POST” ACTION = "lib">

            <ul>
                <li>
                    <label for="process"> Process </label>
                    
                    <select id="process" name="process">
                        <option value="book">Book</option>
                    </select>
                </li>
                
                <li>
                    <label for="action">Action.: </label> 
                    
                    <select id="action" name="action">
                        <option value="update">Update</option>
                    </select>
                </li>
                
                <li>
                    <label for="bookid">BookId.: </label>
                    <input type="text" id="bookid" name="bookid" placeholder="0000">
                </li>
                
                <li>
                    <label for="isbno">ISBNo.: </label>
                    <input type="text" id="isbno" name="isbno" placeholder="0000">
                </li>
                <li>
                    <label for="author">Author and Aid: </label>
                    <input type="text" id="author" name="author" placeholder="e.g. Collins Mahlangu">
                    <input type="text" id="aid" name="aid" placeholder="10 -!only numeric values!-">
                    
                </li>
                <li>
                    <label for="title">Title: </label>
                    <input type="text" id="title" name="title" placeholder="e.g. The Gearsh">
                </li>
                <li>
                    <label for="category"> Category: </label>
                    
                    <select id="category" name="category">
                        <option value="1">African</option>
                        <option value="2">Educational</option>
                        <option value="3">Adventure</option>
                        <option value="4">Comic</option>
                        <option value="5">Fantasy</option>
                        <option value="6">Horror</option>
                        <option value="7">Mystery</option>
                    </select>
                </li>
                <li>
                    <label for="rackno">Rack Number: </label>
                    <input type="text" id="rackNo" name="rackNo" placeholder="53879">
                </li>
                <li>
                    <label for="pubdate">Publication Date: </label>
                    <input type="date" id="pubdate" name="pubDate" placeholder="e.g. 2022-04-21">
                </li>
                <li>
                    <label for="isAvailable">IsAvailable: </label>
                     <select id="isavailable" name="isAvailable">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select>
                </li>
                <li class="button">
                    <input type="submit" value="Update Book">
                </li>
            </ul>
        </form>
    </body>
</html>