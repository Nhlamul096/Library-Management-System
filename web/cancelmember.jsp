<%-- 
    Document   : cancelmember
    Created on : Jun 2, 2022, 9:34:17 AM
    Author     : TRAIN 94
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
            input:invalid {
                border: 1px dashed red;
            }
            input:invalid:focus {
                background-image: linear-gradient(red, white);
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
         <script>
            function openNav(){
                document.getElementById("sidenav").style.width = "250px";
            }
            function closeNav(){
                document.getElementById("sidenav").style.width = "0";
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Member</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
       <center> <h1 style="background-color: grey;color:white;opacity:0.9;">Cancel Member</h1></center>
       <a href="dashboard.jsp"> <i style="color:white; font-size: 30px; position: absolute; left: 40px;" class="fa fa-chevron-left" aria-hidden="true"></i></a>
      <div id="sidenav" class="sidenav" >
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times</a>
            <a href="dashboard.jsp"><i class="fa fa-home"></i>Dashboard</a>
            <a href="books.jsp"><i class="fa fa-book"></i>Books</a>
            <a href="authors.jsp"><i class="fa fa-book"></i>Authors</a>
            <a href="categories.jsp"><i class="fa fa-book"></i>Categories</a>
            <a href="reports.jsp"><i class="fa fa-file-pdf-o"></i>Reports</a>
            <a href="register.jsp"><i class="fa fa-user-plus"></i>Register</a>
            <a href="cancelmember.jsp"><i class="fa fa-user-times"></i>Cancel Member</a>
            <a href="index.jsp"><i class="fa fa-toggle-left"></i>Logout</a>
        </div>
           <span onclick="openNav()" style="color:white; font-size:36px;bottom:66px;left:90px;position: relative;
              background-color: grey;
              ">&#9779</span>
         <form name="addmember" METHOD = “POST” ACTION = "lib">

            <ul>
                <li>
                    <label for="process"> Process </label>
                    
                    <select id="process" name="process">
                        <option value="member">Member</option>
                        
                    </select>
                </li>
                
                <li>
                    <label for="action">Action.: </label> 
                    
                    <select id="action" name="action">
                        
                        <option value="delete">Delete</option>
                    </select>
                </li>
                <li>
                    <label for="memberId">ID Number: </label>
                    <input type="text" id="idno" name="idno" placeholder="2201014800082" minlength="13" maxlength="13">
                </li>
                </li>
                <li class="button">
                    <input type="submit" value="Delete Member">
                </li>
            </ul>
        </form>
    </body>
</html>
