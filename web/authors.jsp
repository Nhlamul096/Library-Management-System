<%-- 
    Document   : authors
    Created on : Jun 15, 2022, 8:37:57 AM
    Author     : TRAIN 94
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current "tab button" class */
            .tab button.active {
                background-color: #ccc;
            }
            /* Style the tab */
            .tab {
                float: right;
                border: 1px solid #ccc;
                background-color: #f1f1f1;
                width: 80%;
                height: 300px;
                opacity: 0.9;
            }

            /* Style the buttons that are used to open the tab content */
            .tab button {
                display: block;
                background-color: inherit;
                color: black;
                padding: 22px 16px;
                width: 100%;
                border: none;
                outline: none;
                text-align: left;
                cursor: pointer;
                transition: 0.3s;
            }
            .borrowed:hover,.reserved:hover,.fines:hover,.returned:hover{
                width: 200px;
                height: 50px;
                border-radius: 36px;
                border-outline: 1px deepskyblue outset;
                background-color: burlywood;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                color: black;
            }
            .borrowed,.reserved,.fines,.returned{
                width: 200px;
                height: 40px;
                border-radius: 36px;
                border-outline: 1px deepskyblue outset;
                background-color: grey;
                color: whitesmoke;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                position: relative;
                left: 36px;
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
                opacity: 0.9;
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
                position: relative;
                top: 0px;
                right: 26px;
                font-size: 36px;
                margin-left:60px;
            }
            @media screen and (max-height:450px){
                .sidenav{padding-top:15px;}
                .sidenav a{font-size: 18px;}
            }
        </style>
        <script>
            function openNav() {
            document.getElementById("sidenav").style.width = "250px";
            }
            function closeNav() {
            document.getElementById("sidenav").style.width = "0";
            }
    
            function openCat(evt, catName) {
                    // Declare all variables
                    var i, tabcontent, tablinks;
            // Get all elements with class="tabcontent" and hide them
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
            }

            // Get all elements with class="tablinks" and remove the class "active"
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
            }

            // Show the current tab, and add an "active" class to the link that opened the tab
            document.getElementById(catName).style.display = "block";
            evt.currentTarget.className += " active";
                }
        </script>
    </script>
</head>
<body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
<center> <h1 style="background-color: grey;color:white;opacity:0.9;">Authors</h1></center>
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
<center> <div> <a href="addauthor.jsp"> <div class="reserved">

                <p><i class="fa fa-plus-circle"></i>Add Author</p>
            </div> </a>
        <a href="updatebook.jsp"> <div class="borrowed" >

                <p><i class="fa fa-pencil-square-o"></i>Update Author</p>
            </div></a>
        <a href="getbook.jsp"> <div class="returned" >

                <p><i class="fa fa-arrow-circle-down"></i>Get Author</p>
            </div></a>
        <a href="deletebook.jsp"> <div class="fines">

                <p><i class="fa fa-times-circle"></i>Delete Author</p>
            </div></a></div></center>

<center> 
    <div class="tab">
        <%
            List<String> list = new ArrayList();
            Connection con = null, con2 = null;
            ResultSet rs = null, rs2 = null;
            Statement stm = null, stm2 = null;
            BasicDataSource dbm = new BasicDataSource();
            dbm.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
            dbm.setUsername("root");
            dbm.setPassword("root");
            dbm.setMinIdle(50);
            dbm.setMaxIdle(100);
            try {
                con = dbm.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT Author FROM authors");
                while (rs.next()) {
                    list.add(rs.getString("Author"));
                }
            } catch (SQLException ex) {

            }
            for (String s : list) {
                out.print("<button class=\"tablinks\" onclick=\"openCat(event, \'African\')\">"+s+"</button>");
            }
        %>
    </div>
</center>
</body>
</html>
