<%-- 
    Document   : dashboard
    Created on : 15-Jun-2022, 08:50:23
    Author     : TRAIN 76
--%>

<%@page import="za.co.material.Member"%>
<%@page import="za.co.material.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            h1{color: whitesmoke;

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
            .box{
                width: 900px;
                min-height: 1100px;
                position: relative;
                left: 240px;
                border: 1px solid white;
                background-color: grey;
            }
            .borrowed,.reserved,.fines,.returned{
                width: 200px;
                height: 100px;
                background-color: burlywood;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                color: white;
            }
            .borrowed:hover,.reserved:hover,.fines:hover,.returned:hover{
                transition: 0.5s;
                transform: scale(1.2);
            }
            #available{
                display: inline-block;
                text-align: center;
                position: relative;
                transition: 1.5s;
                transform: scale(1.0);
                margin-right: 1px;
                /*                padding-right: 5px;*/
                /*                width: 200px;
                                height: 500px;*/

            }
            #available:hover .image{

                transform: scale(1.255);
                transition: 1.5s;
            }
            #submit{
                position: relative;
                left:290px;
                bottom: 6px;
            }
            footer{
                position: relative;
                bottom: 0;
                left: 0;
                right: 0;
                background: grey;
                height: auto;
                width: 100%;
                padding-top: 40px;
                margin-top: 40px;
                color: white;
                opacity: 0.9;
            }
            body {
                opacity: 0.9;
                background-image: url('library.jpg');
            }
            #search{
                position: relative;
                left: 420px;
                width: 300px;
                height: 50px;
                border-radius: 6px;
                display: flex;
                flex-direction: row;
                align-items: center;
                bottom: 60px;
            }
            /*            .form{
                            position: absolute;
                            transition: .5s ease;
                            opacity : 1;
                            transform: translate(-50%,-50%);
                            -ms-transform: translate(-50%,-50%);
                            font-size: 37px;
                        }*/
        </style>
        <script type="">
            function openNav() {
            document.getElementById("sidenav").style.width = "250px";
            }
            function closeNav() {
            document.getElementById("sidenav").style.width = "0";
            }


        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Techno Library</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <div id="topbar">
            <center> 
                <h1 style="background-color: grey;position: relative; left: 80px;">
                    Techno Library

                </h1>
                <form name="search" method="POST" id="search" action="lib" role="search">
                    <input type="search" id="title" name="title" placeholder="Search catalogue...">
                    <select id="process" name="process">
                        <option value="book">Book</option>
                    </select>
                    <button namw="action" value="get"><i class="fa fa-search"></i></button>
                </form>
            </center> 
            <%
                Member mem = (Member) session.getAttribute("member");
               
                if (mem.getRole() == 1) {
                    out.print("<div id=\"sidenav\" class=\"sidenav\" >");
                    out.print("<a href=\"#\"><i class=\"fa fa-home\"></i>Dashboard</a>");
                    out.print("<a href=\"javascript:void(0)\" class=\"closebtn\" onclick= \"closeNav()\">&times</a>");
                    out.print("<a href=\"quickloans.jsp\"><i class=\"fa fa-book\"></i>Loan Book</a>");
                    out.print("<a href=\"getbook.jsp\"><i class=\"fa fa-search\"></i>Search Book</a>");
                    out.print("<a href=\"cancelmember.jsp\"><i class=\"fa fa-user-times\"></i>Delete Profile</a>");
                    out.print("<a href=\"changepwd.jsp\"><i class=\"fa fa-user-secret\"></i>Change Password</a>");
                    out.print("<a href=\"index.jsp\"><i class=\"fa fa-toggle-left\"></i>Logout</a>");
                    out.print("</div>");

                } else if (mem.getRole() == 2) {
                    out.print("<div id=\"sidenav\" class=\"sidenav\" >");
                    out.print("<a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times</a>");
                    out.print("<a href=\"#\"><i class=\"fa fa-home\"></i>Dashboard</a>");
                    out.print("<a href=\"books.jsp\"><i class=\"fa fa-book\"></i>Books</a>");
                    out.print("<a href=\"authors.jsp\"><i class=\"fa fa-book\"></i>Authors</a>");
                    out.print("<a href=\"categories.jsp\"><i class=\"fa fa-book\"></i>Categories</a>");
                    out.print("<a href=\"reports.jsp\"><i class=\"fa fa-file-pdf-o\"></i>Reports</a>");
                    out.print("<a href=\"register.jsp\"><i class=\"fa fa-user-plus\"></i>Register</a>");
                    out.print("<a href=\"cancelmember.jsp\"><i class=\"fa fa-user-times\"></i>Cancel Member</a>");
                    out.print("<a href=\"index.jsp\"><i class=\"fa fa-toggle-left\"></i>Logout</a>");
                    out.print("</div>");

                }

            %>
        </div>
        <span onclick="openNav()" style="color:white; font-size:36px;bottom:110px;left:90px;position: relative;
              background-color: grey;
              ">&#9779</span>

        <div class="box">
            <h1 style="position: relative; left: 20px">Dashboard</h1>
            <p style="position:relative; left: 20px;color: white;"><%= new java.util.Date()%></p>
            <hr>
            <h4 style="position:relative; left: 20px;color: white;">WELCOME TO TECHNOLIBRARY, <%= mem.getName() %></h4>
            <hr>
            <a href="books.jsp"> <div class="reserved">
                    <h3>08</h3>
                    <p>Loan Books</p>
                </div> </a>
            <a href="renewloan.jsp"> <div class="borrowed" >
                    <h3>16</h3>
                    <p>Renew Loan</p>
                </div></a>
            <a href="returnbook.jsp"> <div class="returned" >
                    <h3>26</h3>
                    <p>Return books</p>
                </div></a>
            <a href="#"> <div class="fines">
                    <h3>06</h3>
                    <p>Overdue Books</p>
                </div></a>

            <% Member m = (Member) session.getAttribute("member");
                String id = m.getIdNumber();%>

            <h2 style="position: relative; left: 20px;color:white;">Popular Books</h2>
            <div name="book1" id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Memory">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 0px;" src="data:image/jpeg;base64,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" alt="Memory">
                    </button>

                </form>

            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Lunar Storm">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;" src="data:image/jpeg;base64,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" alt="Lunar Storm">
                    </button>

                </form>


            </div>

            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="My Book Cover">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;" src="data:image/jpeg;base64,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" alt="MyBook">
                    </button>

                </form>
            </div>

            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Six Of Crows">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;" src="data:image/jpeg;base64,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" alt="Six of Crows">
                    </button>

                </form>
            </div>

            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="The Gods Hate Midnight">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSi4AIDuYgpk8AqGHOKld9bAU6CbqczlmLIKg&usqp=CAU">
                    </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Son Of The Strom">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQyxjtSaGpu9ejEoYhVImRFBgClTVkyCbgtA&usqp=CAU">
                    </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Five Feet Apart">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ32-tcI6rws1QsToccUo0Miyb1kfB49Xrddg&usqp=CAU">
                    </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="The Good Guy">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXbQPuFEtQ2e9E1wBuB3zLCM8QzeA4IjNjgg&usqp=CAU">
                    </button>

                </form>
            </div>

            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="The Invisible Cloud">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="data:image/jpeg;base64,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0AlFBooBRThTaUGgHA0U2aJoB1E0k0jNQD5ozVyvcNc73TQEkXHWm+tHWol7xrwfENQE764daPXL1qunFNQMU1AWL1o60ucdagFxLV7JeNATWaiajEumvdLhoDrmivNHNPmgFoJps0TQAaQ0GkoANFBooAops0TQDqKbNE0A6aJps0TQClRTDaBoW4DIBBI3gjTz6UpagGHDCvM4QV6+uXbMu07jbr5U4uOu+3jQHMcEKPoQrqzUs0BzDBinrhRTy46jlz67Uq3AdiDG+tAItkCnBBTWuKIJIExBJGs7RTi0akx50A6iaZmG00s0A6imzRNAOops0UApopKKAbNE0yaWaAdNE02aJoB00TTZomgK7d4ViAbjW8iMxfKwZgzBrhcEkAQQIG55173cJflBnYl3fPqxUJllfBTIUeTNU3NE1p1GZ9JFbxXBbzMjDICtlU1bZlS4undk6uNZHOQa9W4biC0l/ZcshztIBjTaIjSKn5omp6jHSRXUwmLDoC5Izs5OdiuUNbmdOYDwvKa9b+BxbM5FzKpcsgDtsFcDy1NuRtoffOzRNR1H6Q6S9sgP+EXXW4HYS9y24bMSYRywO2kCIXwpuH4XiFCgspEQwVyonIgn2e8JD90/aqwzRNOox0kQJ4XfNm2jZHdGMMT3YiB3CpBAGkabSCKG4biZY+smS2hdiILORoRppk+BqemiadRjpIhOGcLu2nVi3dAysM7HMJuEaEbyy+WtTs02aJqtU6e2WmVK0h00TTZomqlh00TTZpJoB80UyaKASiiigCiiigCiiigCiiigCiiigCiiigCiiigCiiigCiiigCiiigCiiigCiiigP//Z">
                    </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Tech Trends In Practice">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                        <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ__sHSIzSog_rVo9m24XRrYkGp_xTfQfW-uA&usqp=CAU">
                    </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Tech World In Winter">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpLYf4Czj95MrclJWZb80Sb_ed3TyEqbpHgQ&usqp=CAU">
                </button>

                </form>
            </div>
            <div id="available" class="container">
                <form class="form" name="loanbook" METHOD = “POST” ACTION = "lib">

                    <input type="hidden" id="memberid" name="memberid" value="<%= id%>">
                    <input type="hidden" id="title" name="title" value="Tech World In Winter">
                    <input type="hidden" id="process" name="process" value="Loans">
                    <button type="submit" id="action" name="action" value="LoanBook" style="background-color: grey; border-color: grey">
                <img class="image" style="position:relative; margin-right: 20px;opacity: 1.0;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRi7eXxDBiL5qjlKAUIFFA4B0F4InK9yTKEuQ&usqp=CAU">
                </button>

                </form>
            </div>
            <footer>
                <center> <p> Created by: Code<i class="fa fa-gear"></i>ps <br>
                        <a href="mailto:thegearsh@gmail.com">codeops@thegearsh.com</a></p></center>
            </footer>
    </body>
</html>
