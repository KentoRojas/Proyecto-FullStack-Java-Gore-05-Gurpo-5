<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Miembro</title>
</head>
<body>
    <h1>Agregar Miembro al Grupo</h1>
    <form action="/miembro-grupo" method="post">
        <label for="idUsuario">ID Usuario:</label>
        <input type="number" id="idUsuario" name="idUsuario" required><br>

        <label for="idGrupo">ID Grupo:</label>
        <input type="number" id="idGrupo" name="idGrupo" required><br>

        <label for="rol">Rol:</label>
        <select id="rol" name="rol">
            <option value="Admin">Admin</option>
            <option value="Miembro">Miembro</option>
        </select><br>

        <button type="submit">Agregar</button>
    </form>
</body>
</html>
    