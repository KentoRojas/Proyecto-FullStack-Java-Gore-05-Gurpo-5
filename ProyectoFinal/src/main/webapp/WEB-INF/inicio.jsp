<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/paginaPrincipal.css">
    <link rel="stylesheet" href="/css/paginaIngresoGasto.css">
    <link rel="stylesheet" href="/css/paginaHistorial.css">
    <title>MonEvo</title>
</head>
<body>

    <!-- Barra de Navegación Lateral -->
    <nav class="sidebar">
        <div class="head-menu">
            <a href="#pag1"><img src="img/MonEvo.png" alt="">
        </div>
        <div class="main-menu">
            <ul>
            <li><a href="#normal1"><span class="gridicons--stats-alt-2"></span></a></li>
            <li><a href="#pag2"><span class="flowbite--clipboard-list-outline"></span></a></li>
            <li><a href="#"><span class="flowbite--receipt-solid"></span></a></li>
            <li><a href="#"><span class="carbon--group"></span></a></li>
            <li><a href="#"><span class="carbon--user"></span></a></li>
            </ul>
        </div>
        
    </nav>

    <div class="content-container">
        <button class="btn-escondido" id="btn-escondido"></button>
        <section class="pag" id="pag1">
            <div class="content-pag">
                
                
                <div class="saldo">
                    Saldo: <span ><p id="saldo">${sueldo != null ? sueldo.monto : "0"}</p></span>
                    <button id="toggle-saldo" class="iconoir--eye-solid" onclick="toggleSaldo()"></button>
                </div>
                
                <div id="modal" class="modal">
                    <div class="modal-content" action="${pageContext.request.contextPath}/sueldo" method="post">
                        <form action="${pageContext.request.contextPath}/sueldo" method="post">
			                <label for="monto">Monto:</label>
			                <input type="number" id="monto" name="monto" step="0.01" required><br><br>
			
			                <button type="submit" class="btn-saldo" id="btn-confirmar">Registrar</button>
           			 	</form>
                    </div>
                </div>
                
                <span class="ei--plus" onclick="mostrarInput()"></span>
                
                
            </div>

        <div class="drawer" id="drawer1">
            <img src="img/MonEvo.png" alt="" class="logoGrafico">
            <canvas id="myChart" height="300" width="400"></canvas>
        </div>


        <div class="drawer" id="drawer2">
            <p>Cajón 2</p>
        </div>

        </section>

        <section class="normal" id="normal1">
            <main>
                <h3 class="tituloIngresoGasto">Ingreso de Gastos</h3>
                <div class="btnPlus">
                    <button id="abrirRegistroGasto" class="ei--plusIngresoGastos"></button>
                </div>
            </main>
            
        </section>

        <section class="pag2" id="pag2">
        <main class="content-pag2">
            <div class="historial">
                <h2 class="tituloHistorialGasto">Historial de Gastos</h2>
                <ul id="registroHistorial" class="registroHistorial"></ul>
            </div>
            <div class="borde"></div>

            <div class="grafico">
                grafico
            </div>
        </main>

        <div id="modalRegistrarGasto" class="modalRegistrarGasto">

            <div class="modalRegistrarGasto-content">
                <span id="cerrarRegistroGasto" class="cerrarRegistroGasto">&times;</span>
                <form id="formularioRegistroGasto">
                    <p id="heading">Registrar Gasto</p>

                    <label for="expenseName">Nombre del gasto:</label>
                    <div class="field" class="grupoFormularioRegistroGasto">
                        
                        <input id="expenseName" autocomplete="off" class="input-field" type="text">
                    </div>

                    
                    <label for="expenseType">Tipo de Gasto:</label>
                    <div class="field" class="grupoFormularioRegistroGasto">
                        <select id="expenseType" class="input-field" name="expenseType" required>
                            <option value="">Seleccione una opción</option>
                            <option value="Comida">Comida🍔</option>
                            <option value="Transporte">Transporte🚌</option>
                            <option value="Entretenimiento">Entretenimiento🎉</option>
                            <option value="Basicos">Basicos📝</option>
                            <option value="Otros">Otros</option>
                        </select>
                    </div>

                    <label for="expenseDate">Fecha del Gasto:</label>
                    <div class="field" class=formularioRegistroGasto>
                        <input type="date" id="expenseDate" class="input-field" name="expenseDate" required>
                    </div>

                    <label for="expenseImage">Subir Imagen</label>
                    <div  class=formularioRegistroGasto>
                        <input type="file" id="expenseImage" name="expenseImage" accept="image/*">
                    </div>

                    <div class="btnRegistrarGasto">
                        <button type="submit" class="button2">Registrar Gasto</button>
                    </div>
                </form>
            </div>

        </div>

        <div id="imageModal" class="imageModal">
            <div class="imageModal-content">
            <span id="closeImageModal" class="cerrarRegistroGasto">&times;</span>
            <img id="modalImage" src="" alt="Imagen del Gasto" />
            </div>
        </div>


        </section>
        
        
        


    </div>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/js/paginaPrincipal.js"></script>
    <script src="/js/paginaIngresoGasto.js"></script>
    <script src="/js/paginaHistorial.js"></script>
</body>
</html>