<%-- 
    Document   : categories
    Created on : Jun 15, 2022, 8:37:26 AM
    Author     : TRAIN 94
--%>

<%@page import="za.co.material.Member"%>
<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories Page</title>
        <style>
            .borrowed:hover,.reserved:hover,.fines:hover,.returned:hover{
                width: 200px;
                height: 50px;
                border-radius: 36px;
                border-outline: 1px deepskyblue outset;
                background-color: whitesmoke;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                color: deepskyblue;
            }
            .borrowed,.reserved,.fines,.returned{
                width: 200px;
                height: 40px;
                border-radius: 36px;
                border-outline: 1px deepskyblue outset;
                background-color: deepskyblue;
                color: whitesmoke;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                position: relative;
                left: 36px;
            }
            *{box-sizing: border-box}

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
            table{
                border: 5px black;
                align-content: center;
                position: relative;
                left: 10px;
                border-collapse: collapse;
            }
            .box{
                width: 1000px;
                max-height: 1100px;
                position: relative;
                left: 0px;
                border: 1px solid white;
                background-color: gray;
                opacity: 0.85;
            }
            td{
                padding: 10px;
            }
            th{
                padding: 10px;
            }
            tr:nth-child(even) {
                background-color: burlywood;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current "tab button" class */
            .tab button.active {
                background-color: #ccc;
            }
            .borrowed:hover,.reserved:hover,.fines:hover,.returned:hover{
                width: 200px;
                height: 40px;
                border-radius: 36px;
                border-outline: 1px burlywood outset;
                background-color: burlywood;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                color: black ;
                opacity: 0.85;
                transform: scale(1.1);
            }
            .borrowed,.reserved,.fines,.returned{
                width: 200px;
                height: 40px;
                border-radius: 36px;
                border-outline: 1px grey outset;
                background-color: grey;
                color: whitesmoke;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
                position: relative;

            }

            /* Style the tab content */
            .tabcontent {
                float: center;
                padding: 0px 12px;
                border: 1px solid #ccc;
                width: 100%;
                border-left: none;
                height: 300px;
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
            function openNav() {
                document.getElementById("sidenav").style.width = "250px";
            }
            function closeNav() {
                document.getElementById("sidenav").style.width = "0";
            }
        </script>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
    <center> <h1 style="background-color: grey;color:white;opacity:0.9;"><a href="dashboard1.jsp"></i></a>Categories</h1></center>
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

    <center> <div> <a href="addbook.jsp"> <div class="reserved">

                    <p><i class="fa fa-plus-circle"></i>Add Category</p>
                </div> </a>
            <a href="updatebook.jsp"> <div class="borrowed" >

                    <p> <i class="fa fa-pencil-square-o"></i>Update Category</p>
                </div></a>
            <a href="getbook.jsp"> <div class="returned" >

                    <p><i class="fa fa-arrow-circle-down"></i>Get Category</p>
                </div></a>
            <a href="deletebook.jsp"> <div class="fines">

                    <p><i class="fa fa-times-circle"></i>Delete Category</p>
                </div></a></div></center>

    <center> <div class="tab">
            <button class="tablinks" onclick="openCat(event, 'African')">African</button>
            <button class="tablinks" onclick="openCat(event, 'Educational')">Educational</button>
            <button class="tablinks" onclick="openCat(event, 'Adventure')">Adventure</button>
            <button class="tablinks" onclick="openCat(event, 'Comics')">Comics</button>
            <button class="tablinks" onclick="openCat(event, 'Fantasy')">Fantasy</button>
            <button class="tablinks" onclick="openCat(event, 'Horror')">Horror</button>
            <button class="tablinks" onclick="openCat(event, 'Mystery')">Mystery</button>
            <button class="tablinks" onclick="openCat(event, 'Historical')">Historical</button>

            <% String isbno = null;
                String title = null;

                Member m = (Member) session.getAttribute("member");
                String memberid = m.getIdNumber();

                String category = null;
                int bookid = 0;
                boolean isAvailable = false;
                String authors = null;

                PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null;
                Connection con = null, con2 = null, con3 = null, con4 = null, con5 = null;
                ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
                Statement stm = null, stm2 = null;
                BasicDataSource dbm = new BasicDataSource();
                dbm.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
                dbm.setUsername("root");
                dbm.setPassword("root");
                dbm.setMinIdle(50);
                dbm.setMaxIdle(100);%>

            <div id="African" class="tabcontent">
                <h3>African Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'African' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 1");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "African";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (ps4 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
                <br>
                <br>

            </div>


            <div id="Educational" class="tabcontent">
                <h3>Educational Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Educational' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 2");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Educational";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>

            <div id="Adventure" class="tabcontent">
                <h3>Adventure Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Adventure' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 3");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Adventure";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>
            <div id="Comics" class="tabcontent">
                <h3>Comics Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Comics' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 4");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Comics";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>

            <div id="Fantasy" class="tabcontent">
                <h3>Fantasy Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Fantasy' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 5");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Fantasy";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>

            <div id="Horror" class="tabcontent">
                <h3>Horror Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Horror' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 6");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Horror";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>

            <div id="Mystery" class="tabcontent">
                <h3>Mystery Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Mystery' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 7");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Mystery";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>

            <div id="Historical" class="tabcontent">
                <h3>Historical Books</h3>
                <div class="box">
                    <table id="table">
                        <tr>
                            <th><h3>ISBNumber</h3></th>
                            <th><h3>Title</h3></th>
                            <th><h3>Category</h3></th>
                            <th><h3>BookId</h3></th>
                            <th><h3>Availability</h3></th>
                            <th><h3>Author(s)</h3></th>
                            <th><h3>Loan Book</h3></th>
                            <th><h3>Reserve Book</h3></th>
                        <hr>
                        </tr>
                        <% try {
                                con = dbm.getConnection();
                                stm = con.createStatement();
                                rs = stm.executeQuery("SELECT cid FROM category WHERE description = 'Historical' ");
                                if (rs.next()) {
                                    try {
                                        con2 = dbm.getConnection();
                                        stm2 = con2.createStatement();
                                        rs2 = stm2.executeQuery("SELECT ISBNo, Title FROM books WHERE cid = 9");
                                        while (rs2.next()) {
                                            isbno = rs2.getString("ISBNo");
                                            title = rs2.getString("Title");
                                            category = "Historical";
                                            try {
                                                con3 = dbm.getConnection();
                                                ps = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                                                ps.setString(1, rs2.getString("ISBNo"));
                                                rs3 = ps.executeQuery();
                                                while (rs3.next()) {
                                                    bookid = rs3.getInt("bookid");
                                                    isAvailable = rs3.getBoolean("IsAvailable");
                                                    try {
                                                        con4 = dbm.getConnection();
                                                        ps2 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                                        ps2.setString(1, rs2.getString("ISBNo"));
                                                        rs4 = ps2.executeQuery();
                                                        while (rs4.next()) {
                                                            try {
                                                                con5 = dbm.getConnection();
                                                                ps3 = con5.prepareStatement("SELECT author FROM authors WHERE Aid = ?");
                                                                ps3.setInt(1, rs4.getInt("Aid"));
                                                                rs5 = ps3.executeQuery();
                                                                if (rs5.next()) {
                                                                    authors = rs5.getString("author") + " ,";
                                                                }
                                                            } catch (SQLException ex) {
                                                                out.print("System Error (authors section)");
                                                            } finally {
                                                                if (con5 != null) {
                                                                    try {
                                                                        con5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (ps3 != null) {
                                                                    try {
                                                                        ps3.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                                if (rs5 != null) {
                                                                    try {
                                                                        rs5.close();
                                                                    } catch (SQLException ex) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.print("<tr>");
                                                        out.print("<td>" + isbno + "</td>");
                                                        out.print("<td>" + title + "</td>");
                                                        out.print("<td>" + category + "</td>");
                                                        out.print("<td>" + bookid + "</td>");
                                                        out.print("<td>" + isAvailable + "</td>");
                                                        out.print("<td>" + authors + "</td>");
                                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                                        out.print("<td> "
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value = \"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"loanbook\">"
                                                                + "<input type=\"hidden\" value=\"loans\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                                + "</form></td>");

                                                        out.print("<td> "
                                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                                + "<button style=\"\" type=\"submit\">RESERVE BOOK</button>"
                                                                + "</form></td>");
                                                        out.print("<tr>");
                                                    } catch (SQLException ex) {
                                                        out.print("System Error ");
                                                    } finally {
                                                        if (con4 != null) {
                                                            try {
                                                                con4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (ps2 != null) {
                                                            try {
                                                                ps2.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                        if (rs4 != null) {
                                                            try {
                                                                rs4.close();
                                                            } catch (SQLException ex) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                out.print("System Error ");
                                            } finally {
                                                if (con3 != null) {
                                                    try {
                                                        con3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                                if (rs3 != null) {
                                                    try {
                                                        rs3.close();
                                                    } catch (SQLException ex) {
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : statement no2");
                                    } finally {
                                        if (con2 != null) {
                                            try {
                                                con2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (stm2 != null) {
                                            try {
                                                stm2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                        if (rs2 != null) {
                                            try {
                                                rs2.close();
                                            } catch (SQLException ex) {
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                out.print("System Error : statement no1");
                            } finally {
                                if (con != null) {
                                    try {
                                        con.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (stm != null) {
                                    try {
                                        stm.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (SQLException ex) {
                                    }
                                }
                            }

                        %>
                    </table>
                </div>
            </div>
            <center style="background-color: burlywood;">
                <p>Click below to go to the home page</p><br>
                <a href="dashboard.jsp" style=" text-align: center;"><button style="font-size: 25px" type="submit">DASHBOARD</button></a>
            </center>
        </div>
        
    </center>
    
</body>
</html>
