<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Sueldo</title>
</head>
<body>
    <h2>Registrar Sueldo</h2>

    <form action="${pageContext.request.contextPath}/sueldo" method="post">
        <label for="monto">Monto:</label>
        <input type="number" id="monto" name="monto" step="0.01" required><br><br>

        <label for="fechaIngreso">Fecha de Ingreso:</label>
        <input type="date" id="fechaIngreso" name="fechaIngreso" required><br><br>

        <button type="submit">Registrar</button>
    </form>
</body>
</html>
    