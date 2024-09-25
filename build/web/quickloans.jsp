<%-- 
    Document   : quickloans
    Created on : 15-Jun-2022, 09:58:42
    Author     : TRAIN 76
--%>

<%@page import="za.co.material.Member"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="za.co.material.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        </style>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
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
//                                                + "<input type=\"submit\"  name=\"LOAN BOOK\">"
                                                + "<button style=\"\" type=\"submit\">LOAN BOOK</button>"
                                                + "</form></td>");

                                        out.print("<td> "
                                                + "<form METHOD = \"POST\" ACTION = \"lib\">"
                                                + "<input type = \"hidden\" id=\"title\" name=\"title\" value =\"" + title + "\" >"
                                                + "<input type = \"hidden\" id=\"action\" name=\"action\" value = \"reserve\">"
                                                + "<input type=\"hidden\" value=\"reserves\" id=\"process\" name=\"process\">"
                                                + "<input type=\"hidden\" value=\"" + isbno + "\" id=\"isbno\" name=\"isbno\">"
                                                + "<input type=\"hidden\" value=\"" + memberid + "\" id=\"memberid\" name=\"memberid\">"
                                                //+ "<input type=\"submit\"  name=\"RESERVE BOOK\">"
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
                <center><h1><%= m.getName()%> , HERE'S A LIST OF ALL BOOKS</h1></center>
                <center><p>You can loan or reserve a book/(s) depending on the availability</p></center>
            </table>
            <br>
            <br>
            <center><p>Click below to go to the home page</p></center>
            <center><a href="dashboard.jsp"><button style="font-size: 25px" type="submit">DASHBOARD</button></a></center>
    </body>
</html>
