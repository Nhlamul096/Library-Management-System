<%-- 
    Document   : books
    Created on : Jun 3, 2022, 9:48:58 AM
    Author     : CodeOps
--%>

<%@page import="za.co.material.Member"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books</title>
        <style>
            #table{
                border: 5px black;
                align-content: center;
                position: relative;
                left: 30px;
                border-collapse: collapse;
            }
            .box{
                width: 900px;
                height: 1100px;
                position: relative;
                left: 200px;
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
        </script>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
    <center> <h1 style="background-color: grey;color:black; opacity:0.85;">Books</h1></center>
    <a href="dashboard.jsp"> <i style="color:white; font-size: 30px; position: absolute; left: 40px;" class="fa fa-chevron-left" aria-hidden="true"></i></a>
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
    <span onclick="openNav()" style="color:white; font-size:36px;bottom:66px;left:90px;position: relative;
          background-color: grey;
          ">&#9779</span>
    <div class="box">


        <center><p style="position:relative; left: 20px;color: whitesmoke;"><%= new java.util.Date()%></p></center>
        <hr>

        <center> <div> <a href="addbook.jsp"> <div class="reserved">

                        <p> <i class="fa fa-plus-circle"></i>Add Book</p>
                    </div> </a>
                <a href="updatebook.jsp"> <div class="borrowed" >

                        <p><i class="fa fa-pencil-square-o"></i>Update Book</p>
                    </div></a>
                <a href="getbook.jsp"> <div class="returned" >

                        <p><i class="fa fa-arrow-circle-down"></i>Get Book</p>
                    </div></a>
                <a href="deletebook.jsp"> <div class="fines">

                        <p><i class="fa fa-times-circle"></i>Delete Book</p>
                    </div></a></div></center>

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

            <%
                Member m = (Member) session.getAttribute("member");
                String memberid = m.getIdNumber();
                String isbno = null;
                String title = null;

                String category = null;
                int bookid = 0;
                boolean isAvailable = true;
                int aid = 0;

                PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null;
                Connection con = null, con2 = null, con3 = null, con4 = null, con5 = null;
                ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
                Statement stm = null;
                BasicDataSource dbm = new BasicDataSource();
                dbm.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
                dbm.setUsername("root");
                dbm.setPassword("root");
                dbm.setMinIdle(50);
                dbm.setMaxIdle(100);
                try {
                    con = dbm.getConnection();
                    stm = con.createStatement();
                    rs = stm.executeQuery("SELECT * FROM books");
                    while (rs.next()) {
                        String author = "";
                        isbno = rs.getString("ISBNo");
                        title = rs.getString("Title");

                        try {
                            con2 = dbm.getConnection();
                            ps = con2.prepareStatement("SELECT description FROM category WHERE cid = ?");
                            ps.setInt(1, rs.getInt("cid"));
                            rs2 = ps.executeQuery();
                            if (rs2.next()) {
                                category = rs2.getString("description");
                            }
                        } catch (SQLException ex) {
                            out.print("<p> System Error, Please try again later.</p>");
                        } finally {
                            if (con2 != null) {
                                try {
                                    con2.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                            if (ps != null) {
                                try {
                                    ps.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                            if (rs2 != null) {
                                try {
                                    rs2.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                        }

                        try {
                            con3 = dbm.getConnection();
                            ps2 = con3.prepareStatement("SELECT bookid, IsAvailable FROM bookitem WHERE ISBNo = ?");
                            ps2.setString(1, rs.getString("ISBNo"));
                            rs3 = ps2.executeQuery();
                            while (rs3.next()) {
                                out.print("<tr>");
                                out.print("<td>" + isbno + "</td>");
                                out.print("<td>" + title + "</td>");
                                out.print("<td>" + category + "</td>");
                                bookid = rs3.getInt("bookid");
                                isAvailable = rs3.getBoolean("IsAvailable");
                                out.print("<td>" + bookid + "</td>");
                                out.print("<td>" + isAvailable + "</td>");

                                try {
                                    con4 = dbm.getConnection();
                                    ps3 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                    ps3.setString(1, rs.getString("ISBNo"));
                                    rs4 = ps3.executeQuery();
                                    while (rs4.next()) {
                                        aid = rs4.getInt("Aid");
                                        try {
                                            con5 = dbm.getConnection();
                                            ps4 = con5.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                                            ps4.setInt(1, aid);
                                            rs5 = ps4.executeQuery();
                                            while (rs5.next()) {
                                                author += rs5.getString("Author") + " , ";
                                            }
                                        } catch (SQLException ex) {
                                            out.print("<p> System Error, Please try again later.</p>");
                                        } finally {
                                            if (con5 != null) {
                                                try {
                                                    con5.close();
                                                } catch (SQLException ex) {
                                                    out.print("<p> System Error, Please try again later.</p>");
                                                }
                                            }
                                            if (ps4 != null) {
                                                try {
                                                    ps4.close();
                                                } catch (SQLException ex) {
                                                    out.print("<p> System Error, Please try again later.</p>");
                                                }
                                            }
                                            if (rs5 != null) {
                                                try {
                                                    rs5.close();
                                                } catch (SQLException ex) {
                                                    out.print("<p> System Error, Please try again later.</p>");
                                                }
                                            }
                                        }

                                    }
                                    out.print("<td>" + author + "</td>");
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

                                    out.print("</form>");
                                    out.print("</tr>");
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                } finally {
                                    if (con4 != null) {
                                        try {
                                            con4.close();
                                        } catch (SQLException ex) {
                                            out.print("<p> System Error, Please try again later.</p>");
                                        }
                                    }
                                    if (ps3 != null) {
                                        try {
                                            ps3.close();
                                        } catch (SQLException ex) {
                                            out.print("<p> System Error, Please try again later.</p>");
                                        }
                                    }
                                    if (rs4 != null) {
                                        try {
                                            rs4.close();
                                        } catch (SQLException ex) {
                                            out.print("<p> System Error, Please try again later.</p>");
                                        }
                                    }
                                }

                            }
                        } catch (SQLException ex) {
                            out.print("<p> System Error, Please try again later.</p>");
                        } finally {
                            if (con3 != null) {
                                try {
                                    con3.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                            if (ps2 != null) {
                                try {
                                    ps2.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                            if (rs3 != null) {
                                try {
                                    rs3.close();
                                } catch (SQLException ex) {
                                    out.print("<p> System Error, Please try again later.</p>");
                                }
                            }
                        }

                    }
                } catch (SQLException ex) {
                    out.print("<p> System Error, Please try again later.</p>");
                } finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            out.print("<p> System Error, Please try again later.</p>");
                        }
                    }
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (SQLException ex) {
                            out.print("<p> System Error, Please try again later.</p>");
                        }
                    }
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            out.print("<p> System Error, Please try again later.</p>");
                        }
                    }
                }

            %>
            <center><h1>ADMIN - <%= m.getName()%> , HERE'S A LIST OF ALL BOOKS</h1></center>
        </table>
        <br>
        <br>
        <center><p>Click below to go to the home page</p></center>
        <center><a href="dashboard.jsp"><button style="font-size: 25px" type="submit">DASHBOARD</button></a></center>

    </body>
</html>
