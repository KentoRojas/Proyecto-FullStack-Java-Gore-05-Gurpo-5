<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <img src="img/MonEvo.png" alt="MonEvo Logo">
        </header>
        <main class="main">
            <section class="content">
                <h1>MonEvo</h1>
                <p>No seas un mono con metralleta con tu dinero</p>
                <p>Recuerda que cada pequeño paso hacia una mejor gestión de tus finanzas te acercará a un futuro más estable y prometedor.</p>
            </section>

            <section class="content1">
                <!-- Formulario de Login -->
                <form class="formLogin" id="idForm" action="/procesaLogin" method="post" style="display: ${mostrarRegistro != null && !mostrarRegistro ? 'block' : 'none'};">
                    <div id="heading">
                        <span id="headingText">Inicia Sesión</span>
                    </div>

                    <!-- Mensaje de Error para Login -->
                    <c:if test="${not empty errorLogin}">
                        <div class="alert">${errorLogin}</div>
                    </c:if>

                    <div class="field">
                        <input autocomplete="off" placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario != null ? fn:escapeXml(usuario.correo) : ''}">
                    </div>
                    <div class="field">
                        <input placeholder="Contraseña" class="input-field" type="password" name="password">
                    </div>
                    <div class="btn">
                        <button type="submit" class="styled-button" id="login">Iniciar Sesión</button>
                        <button type="button" class="styled-button" id="toggleToRegister">Regístrate</button>
                    </div>
                </form>

                <!-- Formulario de Registro -->
                <form class="formRegister" id="idForm1" action="/procesaRegistro" method="post" style="display: ${mostrarRegistro ? 'block' : 'none'};">
                    <div id="backArrow" class="back-arrow">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" width="24" height="24" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                        </svg>
                    </div>
                    <div id="heading">
                        <span id="headingTex">Registro</span>
                    </div>

                    <!-- Mensaje de Error para Registro -->
                    <c:if test="${not empty errorRegistro}">
                        <div class="alert">${errorRegistro}</div>
                    </c:if>

                    <div class="field">
                        <input placeholder="Nombre" class="input-field" type="text" name="nombre" value="${usuario != null ? fn:escapeXml(usuario.nombre) : ''}">
                    </div>
                    <div class="field">
                        <input autocomplete="off" placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario != null ? fn:escapeXml(usuario.correo) : ''}">
                    </div>
                    <div class="field">
                        <input placeholder="Contraseña" class="input-field" type="password" name="password">
                    </div>
                    <div class="field">
                        <input placeholder="Confirmar Contraseña" class="input-field" type="password" name="confirmPassword">
                    </div>
                    <div class="btn">
                        <button type="submit" class="styled-buttonRegister">Registrar</button>
                    </div>
                </form>
            </section>
        </main>
    </div>
    <script src="/js/script.js"></script>
</body>
</html>
