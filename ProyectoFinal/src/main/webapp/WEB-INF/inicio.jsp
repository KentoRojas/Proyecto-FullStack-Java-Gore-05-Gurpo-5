<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
    <!-- Barra de navegación -->
    <nav>
        <ul>
            <li><a href="/inicio">Inicio</a></li>
            <li><a href="/sueldo">Ingresar Sueldo</a></li>
            <li><a href="/logout">Cerrar Sesión</a></li>
        </ul>
    </nav>

    <div class="container">
        <h2>Bienvenido, ${usuario.nombre}!</h2>

        <!-- Mostrar el dinero ingresado -->
        <div class="saldo-container">
            <h3>Dinero Ingresado</h3>
            <p>${sueldo != null ? sueldo.monto : "No has ingresado un sueldo aún"}</p>
        </div>

        <!-- Contenedor para el historial y el gráfico -->
        <div class="content">
            <div class="historial">
                <h3>Historial de Registros</h3>
                <p>Aquí se mostrará el historial de tus ingresos y gastos.</p>
                <!-- Dejaremos espacio para implementar el historial -->
            </div>

            <div class="grafico">
                <h3>Gráfico de Gastos</h3>
                <p>Aquí se mostrará un gráfico de tus gastos (próximamente).</p>
                <!-- Dejaremos espacio para el gráfico -->
            </div>
        </div>
    </div>
</body>
</html>
    