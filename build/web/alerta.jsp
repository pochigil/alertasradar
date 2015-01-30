<%-- 
    Document   : alerta
    Created on : 23/10/2014, 01:11:32
    Author     : Po
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String tipo = "";
        String zona = "";
        String intensidad = "";
        String mensaje = "";

        Object tipoObj = request.getAttribute("tipo");
        if (tipoObj != null) {
            tipo = tipoObj.toString();
        }
        Object zonaObj = request.getAttribute("zona");
        if (zonaObj != null) {
            zona = zonaObj.toString();
        }
        Object intensidadObj = request.getAttribute("intensidad");
        if (intensidadObj != null) {
            intensidad = intensidadObj.toString();
        }
        Object mensajeObj = request.getAttribute("mensaje");
        if (mensajeObj != null) {
            mensaje = mensajeObj.toString();
        }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alertas Radar Mendoza</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="wrapper">
            <div id="main" style="padding:50px 0 0 0;">
                <%                if ((session.getAttribute("usuario") == null) || (session.getAttribute("usuario") == "")) {
                        response.sendRedirect("index.jsp");
                    } else {
                        String usuario = (String) session.getAttribute("usuario");
                %>   
                <!-- Form -->
                <form id="contact-form" action="GCMBroadcast" method="post">
                    <div>
                        <label>
                            <table>
                                <tr>
                                    <td><h4>Bienvenido <%=usuario%></h4></td>
                                    <a href="cerrar.jsp"><h5>Cerrar sesion</h5></a>
                                </tr>
                            </table>
                        </label>
                    </div><%
                        }
                    %>
                    <h3>NUEVA ALERTA</h3>
                    <div>
                        <label>
                            <span>Tipo de Alerta</span>
                            <select name="tipo" value="<%=tipo%>" required placeholder="Elegir tipo de alerta">
                                <option value="Alerta por tormentas">Alerta por tormentas</option>
                                <option value="Alerta por heladas">Alerta por heladas</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>
                            <span>Zona de Alerta</span>
                            <select name="zona" value="<%=zona%>" required placeholder="Elegir zona">
                                <option value="Zona Norte">Zona Norte</option>
                                <option value="Zona Este">Zona Este</option>
                                <option value="Zona Oeste">Zona Oeste</option>
                                <option value="Zona Sur">Zona Sur</option>
                                <option value="Provincia de Mendoza">Provincia de Mendoza</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>
                            <span>Intensidad</span>
                            <select name="intensidad" value="<%=intensidad%>" required placeholder="Elegir intensidad">
                                <option value="Baja">Baja</option>
                                <option value="Media">Media</option>
                                <option value="Alta">Alta</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>
                            <span>Mensaje</span>
                            <textarea id="mensaje-text" name="mensaje" value="<%=mensaje%>" placeholder="Mensaje" rows="4" type="text" tabindex="2" autocomplete="off" required></textarea>
                        </label>
                    </div>
                    <div>
                        <button name="Enviar" type="submit" id="enviar-submit">Enviar</button>
                    </div>
                </form>
                <!-- /Form -->
            </div>
        </div>

        <script src="js/scripts.js"></script>

    </form>
</div>
</body>
</html>