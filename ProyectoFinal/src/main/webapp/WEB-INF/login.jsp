<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/styles.css">
    <title>MonEvo</title>
</head>
<body>
    <div class="container">
        <header class="header">
            <img src="img/MonEvo.png" alt="MonEvo">
        </header>

        <main class="main">
            <section class="content">
                <h1>MonEvo</h1>
                <p>Es importante comenzar evaluando tus ingresos y gastos actuales para identificar áreas de mejora.</p>
            </section>

            <section class="content1">
                <!-- Formulario de Login -->
                <form class="formLogin" id="idForm" action="/procesaLogin" method="post" style="display: ${mostrarRegistro != null && !mostrarRegistro ? 'block' : 'none'};">
                    <div id="heading">
                        <span id="headingText">Inicia Sesión</span>
                    </div>

                    <c:if test="${not empty errorLogin}">
					    <div class="alert alert-danger">${errorLogin}</div>
					</c:if>

                    <div class="field">
                        <input placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario != null ? usuario.correo : ''}">
                    </div>
                    <div class="field">
                        <input placeholder="Contraseña" class="input-field" type="password" name="password">
                    </div>
                    <div class="btn">
                        <button type="submit" class="styled-button">Iniciar Sesión</button>
                        <button type="button" class="styled-button" id="toggleToRegister">Regístrate</button>
                    </div>
                </form>

                <!-- Formulario de Registro -->
                <form class="formRegister" id="idForm1" action="/procesaRegistro" method="post" style="display: ${mostrarRegistro ? 'block' : 'none'};">
				    <div id="heading">
				        <span id="headingText">Registro</span>
				    </div>
				
				    <!-- Mensaje de Error -->
				    <c:if test="${not empty errorRegistro}">
				        <div class="alert alert-danger">
				            ${errorRegistro}
				        </div>
				    </c:if>
				
				    <div class="field">
				        <input placeholder="Nombre Completo" class="input-field" type="text" name="nombre" value="${usuario.nombre}">
				    </div>
				    <div class="field">
				        <input placeholder="Nombre de Usuario" class="input-field" type="text" name="nombreUsuario" value="${usuario.nombreUsuario}">
				    </div>
				    <div class="field">
				        <input placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario.correo}">
				    </div>
				    <div class="field">
				        <input placeholder="Contraseña" class="input-field" type="password" name="password">
				    </div>
				    <div class="btn">
				        <button type="submit" class="styled-button">Regístrate</button>
				        <button type="button" class="styled-button" id="toggleToLogin">Volver al Login</button>
				    </div>
				</form>                
				                
            </section>
        </main>
    </div>

    <script>
        // Alternar entre formularios
        document.getElementById('toggleToRegister').addEventListener('click', function () {
            document.getElementById('idForm').style.display = 'none';
            document.getElementById('idForm1').style.display = 'block';
        });

        document.getElementById('toggleToLogin').addEventListener('click', function () {
            document.getElementById('idForm1').style.display = 'none';
            document.getElementById('idForm').style.display = 'block';
        });
    </script>
</body>
</html>

