<%-- 
    Document   : membersReport
    Created on : 08-Jun-2022, 12:41:10
    Author     : TRAIN 76
--%>

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
                background-color: whitesmoke;
                display: inline-block;
                margin-left: 15px;
                text-align: center;
            }
        </style>
    </head>
    <body style="background-image: url('https://img3.goodfon.com/wallpaper/nbig/b/5f/library-books-wood.jpg');">
        <h1>Hello World!</h1>
        <form name="loanbook" METHOD = “GET” ACTION = "lib">
            <ul>
                <label>
                    <label for="process"> Process </label>
                    <select id="process" name="process">
                        <option value="reports">Reports</option>
                    </select>
                </label>
                <label class="button">
                    <input type="submit" id="action" name="action" value="getmemberlist">
                </label>

            </ul>
        </form>
        <a href="dashboard.jsp"><button type="submit">Home</button></a>
    </body>
</html>
