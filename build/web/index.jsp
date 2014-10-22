<%-- 
    Document   : index
    Created on : 22/10/2014, 10:09:11
    Author     : Po
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String collapseKey = "Titulo del mensaje";
        String message = "Escriba aquí su mensaje";

        Object collapseKeyObj = request.getAttribute("CollapseKey");

        if (collapseKeyObj != null) {
            collapseKey = collapseKeyObj.toString();
        }

        Object msgObj = request.getAttribute("Message");

        if (msgObj != null) {
            message = msgObj.toString();
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alertas Radar Mendoza</title>
    </head>
    <body>
        <h1>Alertas Meteorológicas Radar Mendoza</h1>
        <form action="GCMBroadcast" method="post">
            <label>Nueva Alerta</label>
            <br/><input type="text" name="CollapseKey" value="<%=collapseKey%>" />
            <br/><textarea name="Message" rows="3" cols="60" ><%=message%> </textarea> 
            <br/><input type="submit" name="submit" value="Enviar" />
        </form>	
    </body>
</html>