<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Gestión Grupos</title>
 
</head>
<body>
    <header>
        <h1>Gestión de Grupos</h1>
    </header>
    <main>
        <!-- Formulario para crear un nuevo grupo -->
        <section id="crearGrupo">
            <h2>Crear Nuevo Grupo</h2>
            <form action="/grupos/crear" method="post">
                <div>
                    <label for="nombreGrupo">Nombre del Grupo:</label>
                    <input type="text" id="nombreGrupo" name="nombreGrupo" required>
                </div>
                <div>
                    <label for="presupuestoTotal">Presupuesto Inicial:</label>
                    <!-- Eliminado step para aceptar solo números enteros -->
                    <input type="number" id="presupuestoTotal" name="presupuestoTotal" required>
                </div>
                <button type="submit">Crear Grupo</button>
            </form>
            <!-- Mostrar mensajes de error o éxito -->
            <c:if test="${not empty error}">
                <p class="mensaje-error">${error}</p>
            </c:if>
            <c:if test="${not empty exito}">
                <p class="mensaje-exito">${exito}</p>
            </c:if>
        </section>

        <!-- Lista de grupos como enlaces flotantes -->
        <section id="listaGrupos">
            <h2>Tus Grupos</h2>
            <div class="grupo-enlaces">
                <c:forEach var="grupo" items="${grupo}">
                    <a href="/grupos/detalle/${grupo.idGrupo}" class="grupo-enlace">
                        ${grupo.nombreGrupo}
                    </a>
                </c:forEach>
            </div>
        </section>
    </main>
</body>
</html>