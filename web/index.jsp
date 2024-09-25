<%-- 
    Document   : index
    Created on : 15-Jun-2022, 08:38:34
    Author     : TRAIN 76
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body{
                 background-image: url('https://media.istockphoto.com/vectors/abstract-hexagon-wallpaper-white-background-3d-vector-illustration-vector-id1212342896?k=20&m=1212342896&s=612x612&w=0&h=fp3B4g_hE54snO4Nf1ggElVnI0gD9s7tPd5JU0eFl9Q=');
                 
                 background-size: auto;
            }
            form {
                border: 13px solid #f1f1f1;
                background-color: white;
                margin: 300px;
                margin-top: 20px;
                margin-bottom: 20px;
            }
            input[type=text], input[type=password]{
                width: 40%;
                padding: 12px 20px;
                margin: 8px 0;
                display: block;
                border: 1px solid #ccc;
                box-sizing: border-box;
                
            }
            button {
                background-color: grey;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }
            button:hover{
                opacity: 0.8;
            }
            .cancelbtn{
                width: auto;
                padding: 10px;
                background-color: #f44336;
            }
            .imgcont{
                text-align: center;
                margin: 24px 0 12px 0;
            }
            img.avatar{
                width: 20%;
                border-radius: 50%;
                height: 20%;
            }
            .cont{
                padding: 16px;
            }
            span.password{
                float: right;
                padding-top: 16px;
            }
            @media screen and (max-width: 300px){
                span.password{
                    display: block;
                    float:none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body style="background-image: url('library.jpg');">
        <form name="login" METHOD = “POST” ACTION = "lib">
            <div class="imgcont">
                <img src="https://i0.wp.com/afrohr.com/wp-content/uploads/2018/09/login-avatar-6-1.png?fit=330%2C330&ssl=1&w=640" alt="Avatar" class="avatar">
            </div>
            <center><div class="cont">
                <label for="idno"><b>Username</b></label>
                <input type="text" placeholder="000000-0000-000(ONLY NUMBERS!!)" name="idno" required>
                <input type="hidden" id="action" name="action" value="login">
                
                <label for="p"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="p" required>
                <span style="color:red;">${errMsg}</span>
                
                <button type="submit" name="process" value="member">Login</button>
               
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
            </div>
            <div class="cont" style="background-color: #f1f1f1">
                <a href="register.jsp"><button type="button" class="cancelbtn"> Register </button></a>
                <span class="password"><a href="changepwd.jsp">Forgot password?</a> </span> 
            </div></center>
        </form>
    </body>
</html>