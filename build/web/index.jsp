<%-- 
    Document   : index
    Created on : 22/10/2014, 10:09:11
    Author     : Po
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String usuario = "";
        String password = "";

        Object usuarioObj = request.getAttribute("usuario");
        if (usuarioObj != null) {
            usuario = usuarioObj.toString();
        }
        Object passwordObj = request.getAttribute("password");
        if (passwordObj != null) {
            password = passwordObj.toString();
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Radar Mendoza</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <div class="wrapper">
            <div id="main" style="padding:50px 0 0 0;">
                <!-- Form -->
                <form id="contact-form" action="Login" method="post">
                    <h3>LOGIN</h3>
                    <div>
                        <label>
                            <span>Usuario</span>
                            <input id="usuario-text" name="usuario" value="<%=usuario%>" placeholder="Usuario" type="text" tabindex="1" required autofocus>
                        </label>
                    </div>
                    <div>
                        <label>
                            <span>Password</span>
                            <input id="password-text" name="password" placeholder="Password" type="password" tabindex="2" required>
                        </label>
                    </div>
                    <div>
                        <button name="Login" type="submit" id="login-submit">Entrar</button>
                    </div>
                </form>
                <!-- /Form -->

            </div>
        </div>

        <script src="js/scripts.js"></script>
    </body>
</html>