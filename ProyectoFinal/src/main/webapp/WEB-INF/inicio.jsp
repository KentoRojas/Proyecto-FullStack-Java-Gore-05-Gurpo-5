<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
</head>
<body>
    <!-- Barra de navegación -->
    <nav>
        <ul>
            <li><a href="/inicio">Inicio</a></li>
            <li><a href="/grupos">Gestión de Grupos</a></li> <!-- Link para ir a la vista de grupos -->
            <li><a href="/logout">Cerrar Sesión</a></li>
        </ul>
    </nav>

    <div>
        <!-- Mostrar el sueldo acumulado -->
        <h2>Sueldo Acumulado:</h2>
        <p>${sueldoAcumulado}</p>

        <!-- Formulario para registrar un nuevo monto -->
        <h2>Registrar Monto Adicional</h2>
        <form action="${pageContext.request.contextPath}/sueldo" method="post">
            <label for="monto">Monto:</label>
            <input type="number" id="monto" name="monto" step="0.01" required><br><br>

            <button type="submit">Registrar</button>
        </form>
    </div>
</body>
</html>
