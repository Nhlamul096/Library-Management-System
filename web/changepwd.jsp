<%-- 
    Document   : changepwd
    Created on : 13 Jun 2022, 9:19:10 AM
    Author     : TRAIN 54
--%>

<%@page import="za.co.material.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <style>
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
                width: 400px;
                padding: 1em;
                border: 1px solid #CCC;
                border-radius: 1em;
                background-color: grey;
                opacity: 0.85;
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
    <center><h1 style="background-color: grey;color:white;opacity:0.9;">Change Password</h1></center>
<a href="dashboard.jsp"> <i style="color:white; font-size: 30px; position: absolute; left: 40px;" class="fa fa-chevron-left" aria-hidden="true"></i></a>
         <form name="login" METHOD = “POST” ACTION = "lib">
             <ul>
            <center><div class="cont">
               
                <li>
                    <% Member m = (Member)session.getAttribute("member");
                    String id = m.getIdNumber();  %>
                    <label for="id">Member ID.: <%= id %> </label>
                   <input type="text" id="id" name="id" value="<%= id %>">
            
               
                    </li>
                    <li>
                <input type="hidden" id="action" name="action" value="changepwd">
                <label for="pwd"><b>New password</b></label>
                <input type="password" placeholder="Enter new Password" name="pwd" required>
                    </li>
                    <li>
                <label for="pwd2"><b>Confirm password</b></label>
                 <input type="password" placeholder="Confirm new Password" name="pwd2" required>
                    </li>
                    <li>
                 <span style="color:red;">${errMsg}</span>
                <button type="submit" name="process" value="member">Change Password</button>
                    </li>
                </div></center>
             </ul>
        </form>
    </body>
</html>
