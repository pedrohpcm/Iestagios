<%-- 
    Document   : exibirLog
    Created on : 15/11/2017, 16:47:38
    Author     : joaovictor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach var="log" items="${List}">
            <table style="border: 1px solid black; margin: 10px; border-spacing: 10px;">
                <tr>
                    <th>Data da Modificação</th>
                    <th>Tabela Modificada</th>
                    <th>Campo Modificado</th>
                    <th>Modificador</th>
                    <th>Conteúdo</th>
                    <th>Operação</th>
                </tr>
                <tr>
                    <td><c:out value="${log.modificationdate}"></c:out></td>
                    <td><c:out value="${log.modifiedtable}"></c:out></td>
                    <td><c:out value="${log.modifiedfield}"></c:out></td>
                    <td><c:out value="${log.modifier}"></c:out></td>
                    <td><c:out value="${log.content}"></c:out></td>
                    <td><c:out value="${log.operation}"></c:out></td>
                </tr>
            </table>
        </c:forEach>
    </body>
</html>
