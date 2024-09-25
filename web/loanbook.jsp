<%-- 
    Document   : loanbook
    Created on : Jun 7, 2022, 9:29:48 AM
    Author     : TRAIN 94
--%>

<%@page import="za.co.material.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
         <a href="dashboard.jsp"> <i style="color:white;" class="fa fa-chevron-left" aria-hidden="true"></i></a>
        <form name="loanbook" METHOD = “POST” ACTION = "lib">
            <ul>
                 <li>
                    <% Member m = (Member)session.getAttribute("member");
                    String id = m.getIdNumber();  %>
                    <label for="id">Member ID.: <%= id %> </label>
                   <input type="text" id="id" name="id" value="<%= id %>">
            
               
                    </li>
                <li>
                    <label for="title">Title: </label>
                    <input type="text" id="title" name="title" placeholder="e.g. The Gearsh">
                </li>
                <li>
                    <label for="process"> Process </label>
                    <select id="process" name="process">
                        <option value="Loans">Loans</option>
                    </select>
                </li>
                <li class="button">
                    <input type="submit" id="action" name="action" value="LoanBook">
                </li>

            </ul>
        </form>
        <div style="background-color: grey; text-align: center;">
        <h3 style="position: relative; color: white ">Notice!</h3>
        <p style="position:relative;color: white;"> Please note that books must be returned within 10 DAYS to avoid fines. Book loans can be extended by 10 days.</p>
        <p><%= new java.util.Date()%></p>
        </div>
    </body>
</html>
