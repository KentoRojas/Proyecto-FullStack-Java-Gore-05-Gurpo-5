<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <!-- Barra de navegación -->
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/inicio">Inicio</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a></li>
        </ul>
    </nav>

    <div class="container">
        <!-- Bienvenida al usuario -->
        <h2>Bienvenido, ${usuarioLogueado.nombre}!</h2>

        <!-- Mostrar el último sueldo ingresado -->
        <div>
            <h2>Último Sueldo:</h2>
            <p>${sueldo != null ? sueldo.monto : "Aún no has registrado un sueldo."}</p>
        </div>

        <!-- Formulario para registrar un nuevo sueldo -->
        <div>
            <h2>Registrar Sueldo</h2>

            <!-- Mostrar mensajes de error si existen -->
            <c:if test="${not empty error}">
                <div style="color: red;">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/sueldo" method="post">
                <label for="monto">Monto:</label>
                <input type="number" id="monto" name="monto" step="0.01" required><br><br>

                <button type="submit">Registrar</button>
            </form>
        </div>
    </div>
</body>
</html>

    