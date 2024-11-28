<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/iconos.css">
    <link rel="stylesheet" href="/css/paginaPrincipal.css">
    <link rel="stylesheet" href="/css/paginaIngresoGasto.css">
    <link rel="stylesheet" href="/css/paginaHistorial.css">
    <link rel="stylesheet" href="/css/paginaGaleriaBoleta.css">
    <link rel="stylesheet" href="/css/paginaPresupuestoConpartido.css">
    <link rel="stylesheet" href="css/paginaPerfil.css">
    <title>MonEvo</title>
</head>
<body>

    <button class="add-notification-btn" id="add-notification"></button>

    <!-- Barra de Navegación Lateral -->
    <nav class="sidebar">
        <div class="head-menu">
            <a href="#pag1"><img src="img/MonEvo.png" alt="">
        </div>
        <div class="main-menu">
            <ul>
            <li>

                <div class="notification-container">
                    <span class="bell-icon" id="bell-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="1.3em" height="1.5em" viewBox="0 0 32 32"><path fill="currentColor" d="M28.707 19.293L26 16.586V13a10.014 10.014 0 0 0-9-9.95V1h-2v2.05A10.014 10.014 0 0 0 6 13v3.586l-2.707 2.707A1 1 0 0 0 3 20v3a1 1 0 0 0 1 1h7v.777a5.15 5.15 0 0 0 4.5 5.199A5.006 5.006 0 0 0 21 25v-1h7a1 1 0 0 0 1-1v-3a1 1 0 0 0-.293-.707M19 25a3 3 0 0 1-6 0v-1h6Zm8-3H5v-1.586l2.707-2.707A1 1 0 0 0 8 17v-4a8 8 0 0 1 16 0v4a1 1 0 0 0 .293.707L27 20.414Z"/></svg>
                        <span class="notification-count" id="notification-count">0</span>
                    </span>
                    <div class="notification-list" id="notification-list">
                        <ul id="notification-messages"></ul>
                    </div>
                </div>
                
                
            </li>
            <li><a href="#normal1">
                <div class="icon-container">
                    <div class="tooltip">Historial y Grafico</div>
                    <span class="gridicons--stats-alt-2" class="tooltip"></span>
                </div>
            </a></li>
            <li><a href="#pag2">
                <div class="icon-container">
                    <div class="tooltip">Historial y Grafico</div>
                    <span class="flowbite--clipboard-list-outline"></span>
                </div>
            </a></li>
            <li><a href="#pagGaleriaBoleta">
                <div class="icon-container">
                    <div class="tooltip">Galeria de Boletas</div>
                    <span class="flowbite--receipt-solid"></span>
                </div>
            </a></li>
            <li><a href="#paginaPresupuestoConpartido">
                <div class="icon-container">
                    <div class="tooltip1">Presupuestos Compartidos</div>
                    <span class="carbon--group"></span>
                </div>
            </a></li>
            <li><a href="#">
                <div class="icon-container">
                    <div class="tooltip2">Perfil</div>
                    <span class="carbon--user"></span>
                </div>
            </a></li>
            <li><a href="/logout">
                <div class="icon-container">
                    <div class="tooltip3">Cerrar Sesión</div>
                    <span class="lucide--panel-left-close"></span>
                </div>
            </a></li>
            </ul>
        </div>
        
    </nav>

    <div class="content-container">
        
        <section class="pag" id="pag1">
            <div class="content-pag">
                
                <div class="bienvenidaUsuario">
                    <p class="nombrePerfil">Bienvenido <span>Matias</span></p>
                    <img src="img/perfil.png" alt="">
                </div>
                
                <div class="saldo">

                    <div class="tituloSaldo">
                        <img src="/img/Saldo.png" alt="" class="tituloSaldo">
                    </div>
                    
                    <span class="numSaldo">
                        <p id="saldo" >${sueldo != null ? sueldo.monto : "0"}</p>
                        <button id="toggle-saldo" class="iconoir--eye-solid" onclick="toggleSaldo()"></button>
                        
                    </span>
                    <span class="ei--plus" onclick="mostrarInput()"></span>
                </div>
                    
                <div class="presupuesto">
                    <img src="/img/Presupuesto.png" alt="" class="tituloPresupuesto">
                    
                    <div class="numPresupuesto">
                        <p id="presupuesto">$100000</p>
                        <a class="editPresupuesto"></a>
                    </div>
                    
                </div>
                
                <div id="modal" class="modal">
                    <div class="modal-content" action="${pageContext.request.contextPath}/sueldo" method="post">
                        <form action="${pageContext.request.contextPath}/sueldo" method="post">
			                <label for="monto">Monto:</label>
			                <input type="number" id="monto" name="monto" step="0.01" required>
			
			                <button type="submit" class="btn-saldo" id="btn-confirmar">Registrar</button>
           			 	</form>
                    </div>
                </div>
            </div>

        <div class="drawer" id="drawer1">
            <img src="img/MonEvo.png" alt="" class="logoGrafico">
            <canvas id="myChart" height="300" width="400"></canvas>
        </div>


        <div class="drawer" id="drawer2">
            <div class="resumenHistorial">
                
            </div>
        </div>

        </section>

        <section class="normal" id="normal1">
            <main>
                <a class="tituloIngresoGasto"><img src="img/IngresoDeGasto.png" alt=""></a>
                <div class="btnPlus">
                    <button id="abrirRegistroGasto" class="solar--archive-up-bold"></button>
                </div>
            </main>
            
        </section>

        <section class="pag2" id="pag2">
        <main class="content-historial">
            <div class="historial">
                <span class="tituloHistorialGasto"><img src="img/HistorialGastos.png" alt=""></span>
                <div class="registroHistorial">
                    <ul id="registroHistorial" class="registroHistorial"></ul>
                </div>
                
            </div>
        </main>
        <main class="content-historial2"> 
            <div class="grafico">
                <span class="tituloGrafico"><img src="img/Grafico.png" alt=""></span>
                <canvas id="graficoPrincipal" height="300" width="400"></canvas>
            </div>
        </main>
        <div class="borde"></div>
        <div class="borde1"></div>
        

        <div id="modalRegistrarGasto" class="modalRegistrarGasto">

            <div class="modalRegistrarGasto-content">
                <span id="cerrarRegistroGasto" class="cerrarRegistroGasto">&times;</span>
                <form id="formularioRegistroGasto">
                    <p id="heading">Registrar Gasto</p>

                    <label for="expenseName">Nombre del gasto:</label>
                    <div class="field" class="grupoFormularioRegistroGasto">
                        
                        <input id="expenseName" autocomplete="off" class="input-field" type="text">
                    </div>
                    <label for="Gasto">Gasto:</label>
                    <div class="field" class="grupoFormularioRegistroGasto">
                        <input id="Gasto" autocomplete="off" class="input-field" type="number">
                    </div>

                    
                    <label for="expenseType">Tipo de Gasto:</label>
                    <div class="field" class="grupoFormularioRegistroGasto">
                        <select id="expenseType" class="input-field" name="expenseType" required>
                            <option value="">Seleccione una opción</option>
                            <option value="Comida">Alimentación🍔</option>
                            <option value="Transporte">Transporte🚌</option>
                            <option value="Entretenimiento">Entretenimiento🎉</option>
                            <option value="Compras personales">Compras personales🧦</option>
                            <option value="Viajes y vacaciones">Viajes y vacaciones✈️</option>
                            <option value="Viajes y vacaciones">Donaciones o regalos💸</option>
                            <option value="Reparaciones">Reparaciones🔧</option>
                            <option value="Medicamentos o emergencias médicas">Medicamentos o emergencias médicas💊</option>
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
        
        <section class="pagGaleriaBoleta" id="pagGaleriaBoleta">  
            <main class="contentGaleriaBoleta">

                <span class="tituloGaleriaBoleta">
                    <img src="/img/GaleriaBoleta.png" alt="">
                </span>
                <div class="contenedor-principal">

        <div class="contenedor-filtros">
            <input type="text" id="busqueda-descripcion" placeholder="Buscar por descripción...">
            <button class="boton-buscar-descripcion" onclick="buscar()">Buscar</button>

            <input type="date" id="filtro-fecha">
            <button class="boton-buscar-fecha" onclick="buscarPorFecha()">Buscar por Fecha</button>
        </div>

        <div class="seccion-subir-boleta">
            <input type="file" id="selector-boleta" accept="image/*">
            <textarea id="descripcion-boleta" placeholder="Escribe una descripción..." required></textarea>
            <button class="boton-subir-boleta" onclick="subirBoleta()">Subir Boleta</button>
        </div>

        <div class="galeria-boletas" id="galeria-boletas">
            <!-- Aquí se mostrarán las boletas subidas -->
        </div>

        <!-- Contenedor de paginación -->
        <div id="paginacion-boletas" class="navegacion-paginacion"></div>

        <div id="vista-boleta-grande" class="modal-boleta-grande">
            <span class="boton-cerrar-vista" onclick="cerrarVistaGrande()">×</span>
            <img id="imagen-boleta-ampliada" src="" alt="Boleta grande">
            <button class="flecha-navegacion izquierda" onclick="navegarBoleta(-1)">&#8592;</button>
            <button class="flecha-navegacion derecha" onclick="navegarBoleta(1)">&#8594;</button>

            <!-- Botones Borrar y Descargar -->
            <div id="controles-boleta-grande" class="controles-boleta" style="display: none;">
                <button class="boton-eliminar-boleta" onclick="borrarBoleta()">Borrar</button>
                <button class="boton-descargar-boleta" onclick="descargarBoleta()">Descargar</button>
            </div>
        </div>
    </div>

            </main>

        </section>




        <section class="paginaPresupuestoConpartido" id="paginaPresupuestoConpartido">
			            <header>
			                <div class="tituloPresupuestoCompartido">
			                    <img src="img/PresupuestoCompartido.png" alt="">
			                </div>
			            </header>
			            
			  <!-- Formulario para crear un nuevo grupo -->
	        <section id="crearGrupo">
	            <h3>Crear Nuevo Grupo</h3>
	            <form action="/grupos/crear" method="post" class="grupoInputCrear">
	                <div>
	                    <label for="nombreGrupo">Nombre del Grupo:</label>
	                    <input type="text" id="nombreGrupo" name="nombreGrupo" required>
	                </div>
	                <div>
	                    <label for="presupuestoTotal">Presupuesto Inicial:</label>
	                    <input type="number" id="presupuestoTotal" name="presupuestoTotal" required>
	                </div>
	                <button type="submit" class="btnCrearGrupo">Crear Grupo</button>
	            </form>
	            <!-- Mostrar mensajes de error o éxito -->
	            <c:if test="${not empty error}">
	                <p class="mensaje-error">${error}</p>
	            </c:if>
	            <c:if test="${not empty exito}">
	                <p class="mensaje-exito">${exito}</p>
	            </c:if>
	        </section>
			<!-- Lista de grupos -->
	        <section id="listaGrupos">
	            <h3>Tus Grupos</h3>
	            <div class="grupo-enlaces">
	                <c:forEach var="grupo" items="${grupo}">
	                    <a href="/grupos/detalle/${grupo.idGrupo}" class="grupo-enlace">
	                        ${grupo.nombreGrupo}
	                    </a>
	                </c:forEach>
	            </div>
	        </section>
        </section>


    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/js/paginaPrincipal.js"></script>
    <script src="/js/paginaIngresoGasto.js"></script>
    <script src="/js/paginaHistorial.js"></script>
    <script src="/js/paginaGaleriaBoleta.js"></script>
    <script src="/js/paginaPresupuestoConpartido.js"></script>
    <script src="/js/paginaPerfil.js"></script>
</body>
</html>