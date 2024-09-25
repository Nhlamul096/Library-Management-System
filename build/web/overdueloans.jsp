<%-- 
    Document   : overdueloans
    Created on : 10-Jun-2022, 14:33:01
    Author     : TRAIN 76
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
    <center><h1>Overdue Loans</h1></center>
    <center> 
        <form name="reports" METHOD = “POST” ACTION = "lib">
            <ul>
                <p>Enter a range of days the report must be generated for.</p>
                <li>
                    <label for="startDate">Start Date.: </label>
                    <input type="date" id="startDate" name="startDate" placeholder=2020-02-21">
                </li>
                <li>
                    <label for="endDate">End Date.: </label>
                    <input type="date" id="endDate" name="endDate" placeholder="2020-02-21">
                </li>
                <li>
                    <label for="process"> Process </label>
                    <select id="process" name="process">
                        <option value="reports">Reports</option>
                    </select>
                </li>
                <li class="button">
                    <input type="submit" id="action" name="action" value="Get Overdue Books">
                </li>

            </ul>
        </form> 
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </center>
</body>
</html>
