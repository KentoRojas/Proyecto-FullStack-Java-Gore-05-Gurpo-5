<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Registro de Usuario</title>
	    <link rel="stylesheet" href="/resources/css/style.css">
	</head>
	<body>
	    <div class="container">
	        <h2>Registro de Usuario</h2>
	
	        <!-- Mostrar mensaje de error si existe -->
	        <c:if test="${not empty error}">
	            <div class="alert alert-danger">${error}</div>
	        </c:if>
	
	        <form action="/procesaRegistro" method="post">
			    <!-- Campo para el nombre de usuario -->
			    <label for="nombreUsuario">Nombre de Usuario:</label>
			    <input type="text" id="nombreUsuario" name="nombreUsuario" required>
			
			    <!-- Campo para el nombre completo -->
			    <label for="nombre">Nombre:</label>
			    <input type="text" id="nombre" name="nombre" required>
			
			    <!-- Campo para el correo electrónico -->
			    <label for="correo">Correo:</label>
			    <input type="email" id="correo" name="correo" required>
			
			    <!-- Campo para la contraseña -->
			    <label for="password">Contraseña:</label>
			    <input type="password" id="password" name="password" required>
			
			    <!-- Botón para enviar el formulario -->
			    <button type="submit">Registrar</button>
			</form>
	        
	
	        <p>¿Ya tienes una cuenta? <a href="/usuarios/login">Inicia sesión aquí</a></p>
	    </div>
	</body>
</html>
    