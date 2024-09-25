<%-- 
    Document   : loanbook
    Created on : Jun 7, 2022, 9:29:48 AM
    Author     : TRAIN 94
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="za.co.material.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
                opacity: 0.85;
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
            table{
                border: 5px black;
                align-content: center;
                position: relative;
                left: 30px;
                border-collapse: collapse;
                padding-bottom: 40px;
            }
            .box{
                width: 900px;
                max-height: 1100px;
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
        <script type="">
           function openNav() {
           document.getElementById("sidenav").style.width = "250px";
           }
           function closeNav() {
           document.getElementById("sidenav").style.width = "0";
           }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">

    <center> <h1 style="background-color: grey;color:black; opacity:0.85;">Renew Loans</h1></center>
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
      background-color: grey; opacity: 0.85;
      ">&#9779</span>

<%  Member m = (Member) session.getAttribute("member");
    String memberid = m.getIdNumber();

    String isbno = null;
    String title = null;
    String category = null;
    String author = null;
    LocalDate date = null;
    int bookid = 0;
    boolean returned = false;
    int aid = 0;

    PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6 = null;
    Connection con = null, con2 = null, con3 = null, con4 = null, con5 = null, con6 = null;
    ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null;
    Statement stm = null;
    BasicDataSource dbm = new BasicDataSource();
    dbm.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
    dbm.setUsername("root");
    dbm.setPassword("root");
    dbm.setMinIdle(50);
    dbm.setMaxIdle(100);


%>
<div class="box">
    <table id="table">
        <tr>
            <th><h3>ISBNumber</h3></th>
            <th><h3>Title</h3></th>
            <th><h3>Category</h3></th>
            <th><h3>BookId</h3></th>
            <th><h3>Author(s)</h3></th>
            <th><h3>Return Date</h3></th>
            <th><h3>Renew Loan</h3></th>
        <hr>
        </tr>
        <%  try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT bookid, DueDate FROM loans WHERE IDNo = ? AND returned = ?");
                ps.setString(1, memberid);
                ps.setBoolean(2, returned);
                rs = ps.executeQuery();
                while (rs.next()) {
                    bookid = rs.getInt("bookid");
                    date = rs.getDate("DueDate").toLocalDate();
                    try {
                        con2 = dbm.getConnection();
                        ps2 = con2.prepareStatement("SELECT ISBNo FROM bookitem WHERE bookid = ?");
                        ps2.setInt(1, rs.getInt("bookid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            isbno = rs2.getString("ISBNo");
                            try {
                                con3 = dbm.getConnection();
                                ps3 = con3.prepareStatement("SELECT Title, cid FROM books WHERE ISBNo = ?");
                                ps3.setString(1, isbno);
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {
                                    title = rs3.getString("Title");
                                    try {
                                        con4 = dbm.getConnection();
                                        ps4 = con4.prepareStatement("SELECT description FROM category WHERE cid = ?");
                                        ps4.setInt(1, rs3.getInt("cid"));
                                        rs4 = ps4.executeQuery();
                                        if (rs4.next()) {
                                            category = rs4.getString("description");
                                        }
                                    } catch (SQLException ex) {
                                        out.print("System Error : " + ex.getMessage());
                                    } finally {
                                        if (con4 != null) {
                                            try {
                                                con4.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                        if (ps4 != null) {
                                            try {
                                                ps4.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                        if (rs4 != null) {
                                            try {
                                                rs4.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                    }
                                    try {
                                        con5 = dbm.getConnection();
                                        ps5 = con5.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                        ps5.setString(1, isbno);
                                        rs5 = ps5.executeQuery();
                                        while (rs5.next()) {
                                            aid = rs5.getInt("Aid");
                                            try {
                                                con6 = dbm.getConnection();
                                                ps6 = con6.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                                                ps6.setInt(1, aid);
                                                rs6 = ps6.executeQuery();
                                                if (rs6.next()) {
                                                    author = rs6.getString("Author") + " , ";
                                                }
                                            } catch (SQLException ex) {
                                                out.print("<p> System Error, Please try again later.</p>");
                                            } finally {
                                                if (con6 != null) {
                                                    try {
                                                        con6.close();
                                                    } catch (SQLException ex) {
                                                        out.print("System Error : " + ex.getMessage());
                                                    }
                                                }
                                                if (ps6 != null) {
                                                    try {
                                                        ps6.close();
                                                    } catch (SQLException ex) {
                                                        out.print("System Error : " + ex.getMessage());
                                                    }
                                                }
                                                if (rs6 != null) {
                                                    try {
                                                        rs6.close();
                                                    } catch (SQLException ex) {
                                                        out.print("System Error : " + ex.getMessage());
                                                    }
                                                }
                                            }
                                        }
                                        out.print("<tr>");
                                        out.print("<td>" + isbno + "</td>");
                                        out.print("<td>" + title + "</td>");
                                        out.print("<td>" + category + "</td>");
                                        out.print("<td>" + bookid + "</td>");
                                        out.print("<td>" + author + "</td>");
                                        out.print("<td>" + date + "</td>");
                                        out.print("<form METHOD = \"POST\" ACTION = \"lib\">");
                                        out.print("<td><input type=\"hidden\" id=\"memberid\" name=\"memberid\" value = \""+memberid+"\">"
                                                + "<input type=\"hidden\" id=\"title\" name=\"title\" value = \""+title+"\">"
                                                + "<input type=\"hidden\" id=\"process\" name=\"process\" value=\"Loans\">"
                                                + "<input type=\"submit\" id=\"action\" name=\"action\" value=\"renewloan\">"
                                                + "</form></td>");
                                        out.print("</tr>");
                                    } catch (SQLException ex) {
                                        out.print("System Error : " + ex.getMessage());
                                    } finally {
                                        if (con5 != null) {
                                            try {
                                                con5.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                        if (ps5 != null) {
                                            try {
                                                ps5.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                        if (rs5 != null) {
                                            try {
                                                rs5.close();
                                            } catch (SQLException ex) {
                                                out.print("System Error : " + ex.getMessage());
                                            }
                                        }
                                    }
                                }

                            } catch (SQLException ex) {
                                out.print("System Error : " + ex.getMessage());
                            } finally {
                                if (con3 != null) {
                                    try {
                                        con3.close();
                                    } catch (SQLException ex) {
                                        out.print("System Error : " + ex.getMessage());
                                    }
                                }
                                if (ps3 != null) {
                                    try {
                                        ps3.close();
                                    } catch (SQLException ex) {
                                        out.print("System Error : " + ex.getMessage());
                                    }
                                }
                                if (rs3 != null) {
                                    try {
                                        rs3.close();
                                    } catch (SQLException ex) {
                                        out.print("System Error : " + ex.getMessage());
                                    }
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        out.print("System Error : " + ex.getMessage());
                    } finally {
                        if (con2 != null) {
                            try {
                                con2.close();
                            } catch (SQLException ex) {
                                out.print("System Error : " + ex.getMessage());
                            }
                        }
                        if (ps2 != null) {
                            try {
                                ps2.close();
                            } catch (SQLException ex) {
                                out.print("System Error : " + ex.getMessage());
                            }
                        }
                        if (rs2 != null) {
                            try {
                                rs2.close();
                            } catch (SQLException ex) {
                                out.print("System Error : " + ex.getMessage());
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                out.print("System Error : " + ex.getMessage());
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        out.print("System Error : " + ex.getMessage());
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        out.print("System Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        out.print("System Error : " + ex.getMessage());
                    }
                }
            }

        %>

    </table>
</div>


<center>
    <h1 style="position: relative; left: 25px; color: white ">Notice!!!</h1>
    <p style="position:relative; left: 18px;color: white;"> Please note that books must be returned within 10 DAYS to avoid fines. Book loans can be extended by 10 days.<%= new java.util.Date()%></p>
</center>
</body>
</html>
