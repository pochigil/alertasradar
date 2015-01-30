<%-- 
    Document   : cerrar
    Created on : 23/10/2014, 02:33:34
    Author     : Po
--%>

<%
    session.setAttribute("usuario", null);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>