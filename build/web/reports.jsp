

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .borrowed,.reserved,.fines,.returned{
                width: 200px;
                height: 200px;
                background-color: grey;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                opacity: 0.85;
            }
            p{
                color: black;
            }
            .sidenav{
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: grey;
                opacity: 0.85;
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
            .reserved:hover, .borrowed:hover, .returned:hover, .fines:hover {

                transform: scale(1.155);
                transition: 1.5;
               
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
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
    <center><h1 style="background-color: grey;color:black; opacity:0.85;">Reports</h1></center>
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
    
    <center><div>
         <a href="membersReport.jsp"> <div class="reserved">
                <p style="padding-top:20px">
                <i class="fa fa-file-pdf-o"></i><br>
                Click Here To Create <br>Members List Report</p>
                </div> </a>
            <a href="loansreport.jsp"> <div class="borrowed" >
                 <p style="padding-top:20px">
                 <i class="fa fa-file-pdf-o"></i><br>
                 Click Here To Create <br>Loaned Books Report</p>
                </div></a>
            <a href="reservedReport.jsp"> <div class="returned" >
                 <p style="padding-top:20px">
                 <i class="fa fa-file-pdf-o"></i><br>
                 Click Here To Create <br>Reserved Books Report</p>
                </div></a>
             <a href="overdueloans.jsp"> <div class="fines">
                 <p style="padding-top:20px">
                 <i class="fa fa-file-pdf-o"></i><br>
                 Click Here To Create <br>Overdue Books Report</p>
                 </div></a>
        </div></center>
    </body>
</html>
