<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle del Grupo</title>
    <link rel="stylesheet" href="/css/detalleGrupo.css">
</head>
<body>
    <header>
        <h1>Detalle del Grupo: ${grupo.nombreGrupo}</h1>
    </header>
    <main>
        <!-- Detalles del Grupo -->
        <section id="infoGrupo">
            <h2>Información del Grupo</h2>
            <p><strong>Nombre del Grupo:</strong> ${grupo.nombreGrupo}</p>
            <p><strong>Presupuesto Total:</strong> ${grupo.presupuestoTotal}</p>
            <p><strong>Fecha de Creación:</strong> ${grupo.fechaCreacion}</p>
        </section>

        <!-- Lista de Miembros -->
        <section id="miembrosGrupo">
            <h2>Miembros del Grupo</h2>
            <table>
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
        <section id="accionesGrupo">
            <h2>Acciones</h2>
            <!-- Añadir presupuesto -->
            <div>
                <h3>Añadir Presupuesto</h3>
                <form action="/grupos/${grupo.idGrupo}/añadirPresupuesto" method="post">
                    <label for="montoPresupuesto">Monto:</label>
                    <input type="number" id="montoPresupuesto" name="montoPresupuesto" required>
                    <button type="submit">Añadir</button>
                </form>
                <!-- Mostrar mensajes de error o éxito -->
                <c:if test="${not empty error}">
                    <p class="mensaje-error">${error}</p>
                </c:if>
                <c:if test="${not empty exito}">
                    <p class="mensaje-exito">${exito}</p>
                </c:if>
            </div>
            
            <!-- Registrar gasto -->
            <div>
                <h3>Registrar Gasto</h3>
                <form action="/grupos/${grupo.idGrupo}/registrarGasto" method="post">
                    <label for="montoGasto">Monto:</label>
                    <input type="number" id="montoGasto" name="montoGasto" required>
                    
                    <label for="descripcionGasto">Descripción:</label>
                    <input type="text" id="descripcionGasto" name="descripcionGasto" required>
                    
                    <label for="categoriaGasto">Categoría:</label>
                    <select id="categoriaGasto" name="categoriaGasto" required>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.idCategorias}">${categoria.nombreCategoria}</option>
                        </c:forEach>
                    </select>
                    
                    <button type="submit">Registrar</button>
                </form>
                <!-- Mostrar mensajes de error o éxito -->
                <c:if test="${not empty errorGasto}">
                    <p class="mensaje-error">${errorGasto}</p>
                </c:if>
                <c:if test="${not empty exitoGasto}">
                    <p class="mensaje-exito">${exitoGasto}</p>
                </c:if>
            </div>
        </section>

        <!-- Historial de Actividades -->
        <section id="historialGrupo">
            <h2>Historial de Actividades</h2>
            <table>
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

    <footer>
        <a href="/grupos">Volver a Mis Grupos</a>
    </footer>
</body>
</html>


