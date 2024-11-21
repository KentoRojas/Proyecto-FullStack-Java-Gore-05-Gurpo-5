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
            <img src="img/MonEvo.png" alt="">
        </header>
        <main class="main">
            <section class="content">
                <h1>MonEvo</h1>
                <p>No seas un mono con metralleta con tu dinero</p>
                <p>Recuerda que cada pequeño paso hacia una mejor gestión de tus finanzas te acercará a un futuro más estable y prometedor.</p>
            </section>

            <section class="content1">
                <form class="formLogin" id="idForm" action="/procesaLogin" method="post" style="display: ${mostrarRegistro != null && !mostrarRegistro ? 'block' : 'none'};">
                    <div id="heading">
                        <span id="headingText">Inicia Sesión</span>
                    </div>

                    <c:if test="${not empty errorLogin}">
					    <div class="alert alert-danger">${errorLogin}</div>
					</c:if>

                    <div class="field">
                        <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M13.106 7.222c0-2.967-2.249-5.032-5.482-5.032-3.35 0-5.646 2.318-5.646 5.702 0 3.493 2.235 5.708 5.762 5.708.862 0 1.689-.123 2.304-.335v-.862c-.43.199-1.354.328-2.29.328-2.926 0-4.813-1.88-4.813-4.798 0-2.844 1.921-4.881 4.594-4.881 2.735 0 4.608 1.688 4.608 4.156 0 1.682-.554 2.769-1.416 2.769-.492 0-.772-.28-.772-.76V5.206H8.923v.834h-.11c-.266-.595-.881-.964-1.6-.964-1.4 0-2.378 1.162-2.378 2.823 0 1.737.957 2.906 2.379 2.906.8 0 1.415-.39 1.709-1.087h.11c.081.67.703 1.148 1.503 1.148 1.572 0 2.57-1.415 2.57-3.643zm-7.177.704c0-1.197.54-1.907 1.456-1.907.93 0 1.524.738 1.524 1.907S8.308 9.84 7.371 9.84c-.895 0-1.442-.725-1.442-1.914z"></path>
                        </svg>
                        <input autocomplete="off" placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario != null ? usuario.correo : ''}">
                    </div>
                    <div class="field">
                        <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"></path>
                        </svg>
                        <input placeholder="Contraseña" class="input-field" type="password" name="password">
                    </div>
                    <div class="btn">
                        <button type="submit" class="styled-button" id="login">Iniciar Sesión</button>
                        <button type="button" class="styled-button" id="toggleToRegister">Regístrate
                            <div class="inner-button">
                                <svg id="Arrow" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg" height="30px" width="30px" class="icon">
                                    <defs>
                                        <linearGradient y2="100%" x2="100%" y1="0%" x1="0%" id="iconGradient">
                                            <stop style="stop-color:#FFFFFF;stop-opacity:1" offset="0%"></stop>
                                            <stop style="stop-color:#AAAAAA;stop-opacity:1" offset="100%"></stop>
                                        </linearGradient>
                                    </defs>
                                    <path fill="url(#iconGradient)" d="M4 15a1 1 0 0 0 1 1h19.586l-4.292 4.292a1 1 0 0 0 1.414 1.414l6-6a.99.99 0 0 0 .292-.702V15c0-.13-.026-.26-.078-.382a.99.99 0 0 0-.216-.324l-6-6a1 1 0 0 0-1.414 1.414L24.586 14H5a1 1 0 0 0-1 1z"></path>
                                </svg>
                            </div>
                        </button>
                    </div>
                </form>

                <form class="formRegister" id="idForm1" action="/procesaRegistro" method="post" style="display: ${mostrarRegistro ? 'block' : 'none'};">
                    <div id="backArrow" class="back-arrow">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" width="24" height="24" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                        </svg>
                    </div>
                    <div id="heading">
                        <span id="headingTex">Registro</span>
                    </div>

                    <!-- Mensaje de Error -->
				    <c:if test="${not empty errorRegistro}">
				        <div class="alert alert-danger">
				            ${errorRegistro}
				        </div>
				    </c:if>

                    <div class="nameUsuario">
                        <div class="field" id="nameField">
                            <svg
                                height="18"
                                viewBox="0 0 16 16"
                                width="18"
                                xmlns="http://www.w3.org/2000/svg"
                                >
                                <path
                                    d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5m.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1z"
                                ></path>
                            </svg>
                            <input placeholder="Nombre" class="input-field" type="text" name="nombre" value="${usuario.nombre}">
                        </div>
                        <div class="field" id="lastNameField" >
                            <svg
                                height="18"
                                viewBox="0 0 16 16"
                                width="18"
                                xmlns="http://www.w3.org/2000/svg"
                                >
                                <path
                                    d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5m.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1z"
                                ></path>
                            </svg>
                            <input placeholder="Nombre de Usuario" class="input-field" type="text" name="nombreUsuario" value="${usuario.nombreUsuario}">
                        </div>
                    </div>
                    <div class="field">
                        <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M13.106 7.222c0-2.967-2.249-5.032-5.482-5.032-3.35 0-5.646 2.318-5.646 5.702 0 3.493 2.235 5.708 5.762 5.708.862 0 1.689-.123 2.304-.335v-.862c-.43.199-1.354.328-2.29.328-2.926 0-4.813-1.88-4.813-4.798 0-2.844 1.921-4.881 4.594-4.881 2.735 0 4.608 1.688 4.608 4.156 0 1.682-.554 2.769-1.416 2.769-.492 0-.772-.28-.772-.76V5.206H8.923v.834h-.11c-.266-.595-.881-.964-1.6-.964-1.4 0-2.378 1.162-2.378 2.823 0 1.737.957 2.906 2.379 2.906.8 0 1.415-.39 1.709-1.087h.11c.081.67.703 1.148 1.503 1.148 1.572 0 2.57-1.415 2.57-3.643zm-7.177.704c0-1.197.54-1.907 1.456-1.907.93 0 1.524.738 1.524 1.907S8.308 9.84 7.371 9.84c-.895 0-1.442-.725-1.442-1.914z"></path>
                        </svg>
                        <input placeholder="Correo Electrónico" class="input-field" type="text" name="correo" value="${usuario.correo}">
                    </div>
                    <div class="field">
                        <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"></path>
                        </svg>
                        <input placeholder="Contraseña" class="input-field" type="password" name="password">
                    </div>
                    <div class="field">
                        <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"></path>
                        </svg>
                        <input placeholder="Confirmar Contraseña" class="input-field" type="password">
                    </div>
                    <div class="haveAccount">
                        <span id="backSesion">¿Ya tienes una cuenta? Inicia sesión</span>
                    </div>
                    <div class="btn">
                        <button type="submit" class="styled-buttonRegister">Registrar
                            <div class="inner-button">
                                <svg id="Arrow" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg" height="30px" width="30px" class="icon">
                                    <defs>
                                        <linearGradient y2="100%" x2="100%" y1="0%" x1="0%" id="iconGradient">
                                            <stop style="stop-color:#FFFFFF;stop-opacity:1" offset="0%"></stop>
                                            <stop style="stop-color:#AAAAAA;stop-opacity:1" offset="100%"></stop>
                                        </linearGradient>
                                    </defs>
                                    <path fill="url(#iconGradient)" d="M4 15a1 1 0 0 0 1 1h19.586l-4.292 4.292a1 1 0 0 0 1.414 1.414l6-6a.99.99 0 0 0 .292-.702V15c0-.13-.026-.26-.078-.382a.99.99 0 0 0-.216-.324l-6-6a1 1 0 0 0-1.414 1.414L24.586 14H5a1 1 0 0 0-1 1z"></path>
                                </svg>
                            </div>
                        </button>
                    </div>
                </form>
            </section>
        </main>
    </div>
    <script src="/js/script.js"></script>
</body>
</html>