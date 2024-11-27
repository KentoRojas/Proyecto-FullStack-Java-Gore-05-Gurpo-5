<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle del Grupo</title>
    <link rel="stylesheet" href="/css/detalleGrupo.css">
</head>
<body class="presupuestos-detalle-body">
    <header class="presupuestos-detalle-header">
        <h1 class="presupuestos-detalle-titulo-principal">Detalle del Grupo: ${grupo.nombreGrupo}</h1>
    </header>
    <main class="presupuestos-detalle-contenido-principal">
        <!-- Detalles del Grupo -->
        <section id="presupuestos-detalle-informacion-grupo" class="presupuestos-detalle-seccion">
            <h2 class="presupuestos-detalle-subtitulo">Información del Grupo</h2>
            <p class="presupuestos-detalle-item"><strong>Nombre del Grupo:</strong> ${grupo.nombreGrupo}</p>
            <p class="presupuestos-detalle-item"><strong>Presupuesto Total:</strong> ${grupo.presupuestoTotal}</p>
            <p class="presupuestos-detalle-item"><strong>Fecha de Creación:</strong> ${grupo.fechaCreacion}</p>
        </section>

        <!-- Lista de Miembros -->
        <section id="presupuestos-detalle-miembros-grupo" class="presupuestos-detalle-seccion">
            <h2 class="presupuestos-detalle-subtitulo">Miembros del Grupo</h2>
            <table class="presupuestos-detalle-tabla">
                <thead>
                    <tr>
                        <th>Nombre del Usuario</th>
                        <th>Rol</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="miembro" items="${grupo.miembros}">
                        <tr>
                            <td>${miembro.usuario.nombre}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${miembro.rol == 'admin'}">Administrador</c:when>
                                    <c:otherwise>Miembro</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Acciones -->
        <section id="presupuestos-detalle-acciones-grupo" class="presupuestos-detalle-seccion">
            <h2 class="presupuestos-detalle-subtitulo">Acciones</h2>
            <!-- Añadir presupuesto -->
            <div class="presupuestos-detalle-accion">
                <h3 class="presupuestos-detalle-titulo-accion">Añadir Presupuesto</h3>
                <form action="/grupos/${grupo.idGrupo}/añadirPresupuesto" method="post" class="presupuestos-detalle-formulario">
                    <label for="presupuestos-detalle-monto" class="presupuestos-detalle-label">Monto:</label>
                    <input type="number" id="presupuestos-detalle-monto" name="montoPresupuesto" class="presupuestos-detalle-input" required>
                    <button type="submit" class="presupuestos-detalle-boton">Añadir</button>
                </form>
                <!-- Mostrar mensajes de error o éxito -->
                <c:if test="${not empty error}">
                    <p class="presupuestos-detalle-mensaje-error">${error}</p>
                </c:if>
                <c:if test="${not empty exito}">
                    <p class="presupuestos-detalle-mensaje-exito">${exito}</p>
                </c:if>
            </div>
            
            <!-- Registrar gasto -->
            <div class="presupuestos-detalle-accion">
                <h3 class="presupuestos-detalle-titulo-accion">Registrar Gasto</h3>
                <form action="/grupos/${grupo.idGrupo}/registrarGasto" method="post" class="presupuestos-detalle-formulario">
                    <label for="presupuestos-detalle-monto-gasto" class="presupuestos-detalle-label">Monto:</label>
                    <input type="number" id="presupuestos-detalle-monto-gasto" name="montoGasto" class="presupuestos-detalle-input" required>
                    
                    <label for="presupuestos-detalle-descripcion-gasto" class="presupuestos-detalle-label">Descripción:</label>
                    <input type="text" id="presupuestos-detalle-descripcion-gasto" name="descripcionGasto" class="presupuestos-detalle-input" required>
                    
                    <label for="presupuestos-detalle-categoria-gasto" class="presupuestos-detalle-label">Categoría:</label>
                    <select id="presupuestos-detalle-categoria-gasto" name="categoriaGasto" class="presupuestos-detalle-input" required>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.idCategorias}">${categoria.nombreCategoria}</option>
                        </c:forEach>
                    </select>
                    
                    <button type="submit" class="presupuestos-detalle-boton">Registrar</button>
                </form>
                <!-- Mostrar mensajes de error o éxito -->
                <c:if test="${not empty errorGasto}">
                    <p class="presupuestos-detalle-mensaje-error">${errorGasto}</p>
                </c:if>
                <c:if test="${not empty exitoGasto}">
                    <p class="presupuestos-detalle-mensaje-exito">${exitoGasto}</p>
                </c:if>
            </div>
        </section>

        <!-- Historial de Actividades -->
        <section id="presupuestos-detalle-historial-grupo" class="presupuestos-detalle-seccion">
            <h2 class="presupuestos-detalle-subtitulo">Historial de Actividades</h2>
            <table class="presupuestos-detalle-tabla">
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Monto</th>
                        <th>Descripción</th> 
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="actividad" items="${historial}">
                        <tr>
                            <td>${actividad.usuario.nombre}</td>
                            <td>${actividad.monto}</td>
                            <td>${actividad.descripcion}</td> 
                            <td>${actividad.fecha}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>

    <footer class="presupuestos-detalle-pie">
        <a href="/grupos" class="presupuestos-detalle-enlace-volver">Volver a Mis Grupos</a>
    </footer>
</body>
</html>

