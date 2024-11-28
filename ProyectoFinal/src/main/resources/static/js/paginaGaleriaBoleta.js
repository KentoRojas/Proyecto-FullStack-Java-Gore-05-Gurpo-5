let boletas = []; // Arreglo para almacenar las boletas y descripciones
let boletaIndiceActual = 0; // Índice para la boleta actualmente visualizada en grande
let paginaActual = 0; // Página actual de la galería
const BOLETAS_POR_PAGINA = 20; // Máximo de boletas por página

// Función para subir una boleta
function subirBoleta() {
    const archivo = document.getElementById('selector-boleta').files[0];
    const descripcion = document.getElementById('descripcion-boleta').value;
    
    if (!archivo || !descripcion) {
        alert("Por favor, sube una boleta y agrega una descripción.");
        return;
    }

    const reader = new FileReader();
    reader.onload = function(e) {
        const boletaData = {
            src: e.target.result,
            descripcion: descripcion,
            fecha: new Date(),
        };
        boletas.push(boletaData);
        mostrarGaleriaBoletas();
        document.getElementById('descripcion-boleta').value = ''; // Limpiar descripción
        document.getElementById('selector-boleta').value = ''; // Limpiar selector de archivo
    };
    reader.readAsDataURL(archivo);
}

// Mostrar las boletas en la galería
function mostrarGaleriaBoletas() {
    const galeriaBoletas = document.getElementById('galeria-boletas');
    galeriaBoletas.innerHTML = '';

    const boletasOrdenadas = [...boletas].sort((a, b) => b.fecha - a.fecha); // Ordenar por fecha (más reciente primero)

    const inicio = paginaActual * BOLETAS_POR_PAGINA;
    const fin = inicio + BOLETAS_POR_PAGINA;

    boletasOrdenadas.slice(inicio, fin).forEach(boleta => {
        const divBoleta = document.createElement('div');
        divBoleta.classList.add('contenedor-boleta');

        const img = document.createElement('img');
        img.src = boleta.src;
        img.alt = boleta.descripcion;
        img.onclick = () => verBoletaEnGrande(boleta, divBoleta);

        const descripcion = document.createElement('div');
        descripcion.classList.add('descripcion-boleta');
        descripcion.textContent = boleta.descripcion;

        divBoleta.appendChild(img);
        divBoleta.appendChild(descripcion);
        galeriaBoletas.appendChild(divBoleta);
    });

    mostrarPaginacionBoletas(boletasOrdenadas.length);
}

// Mostrar los botones de paginación
function mostrarPaginacionBoletas(totalBoletas) {
    const paginacionBoletas = document.getElementById('paginacion-boletas');
    paginacionBoletas.innerHTML = '';

    const totalPaginas = Math.ceil(totalBoletas / BOLETAS_POR_PAGINA);
    
    if (paginaActual > 0) {
        const botonAnterior = document.createElement('button');
        botonAnterior.textContent = 'Anterior';
        botonAnterior.onclick = () => cambiarPagina(paginaActual - 1);
        paginacionBoletas.appendChild(botonAnterior);
    }

    if (paginaActual < totalPaginas - 1) {
        const botonSiguiente = document.createElement('button');
        botonSiguiente.textContent = 'Página siguiente';
        botonSiguiente.onclick = () => cambiarPagina(paginaActual + 1);
        paginacionBoletas.appendChild(botonSiguiente);
    }
}

// Cambiar de página
function cambiarPagina(nuevaPagina) {
    paginaActual = nuevaPagina;
    mostrarGaleriaBoletas();
}

// Función para ver la boleta en tamaño grande dentro de la vista
function verBoletaEnGrande(boleta, contenedor) {
    boletaIndiceActual = boletas.findIndex(img => img.src === boleta.src);
    const vistaBoleta = document.getElementById('vista-boleta-grande');
    const imagenBoletaAmpliada = document.getElementById('imagen-boleta-ampliada');
    imagenBoletaAmpliada.src = boleta.src;
    vistaBoleta.style.display = 'flex';

    // Mostrar los botones de eliminar y descargar al seleccionar la boleta
    const controlesBoletaGrande = document.getElementById('controles-boleta-grande');
    controlesBoletaGrande.style.display = 'block';
}

// Función para cerrar la vista de la boleta grande
function cerrarVistaGrande() {
    const vistaBoleta = document.getElementById('vista-boleta-grande');
    vistaBoleta.style.display = 'none';
    const controlesBoletaGrande = document.getElementById('controles-boleta-grande');
    controlesBoletaGrande.style.display = 'none';
}

// Función para borrar una boleta
function borrarBoleta() {
    boletas = boletas.filter(img => img.src !== boletas[boletaIndiceActual].src);
    mostrarGaleriaBoletas();
    cerrarVistaGrande();
}

// Función para descargar una boleta
function descargarBoleta() {
    const boleta = boletas[boletaIndiceActual];
    const link = document.createElement('a');
    link.href = boleta.src;
    link.download = 'boleta_descargada.jpg';
    link.click();
}

// Función para buscar por descripción
function buscar() {
    const descripcionBuscada = document.getElementById('busqueda-descripcion').value.toLowerCase();
    const boletasFiltradas = boletas.filter(boleta => boleta.descripcion.toLowerCase().includes(descripcionBuscada));
    mostrarBoletasFiltradas(boletasFiltradas);
}

// Función para buscar por fecha
function buscarPorFecha() {
    const fechaSeleccionada = document.getElementById('filtro-fecha').value;
    if (!fechaSeleccionada) return;

    const fecha = new Date(fechaSeleccionada);
    const boletasFiltradas = boletas.filter(boleta => {
        const fechaBoleta = new Date(boleta.fecha);
        return fechaBoleta.toDateString() === fecha.toDateString();
    });
    mostrarBoletasFiltradas(boletasFiltradas);
}

// Mostrar las boletas filtradas
function mostrarBoletasFiltradas(boletas) {
    const galeriaBoletas = document.getElementById('galeria-boletas');
    galeriaBoletas.innerHTML = '';

    boletas.forEach(boleta => {
        const divBoleta = document.createElement('div');
        divBoleta.classList.add('contenedor-boleta');

        const img = document.createElement('img');
        img.src = boleta.src;
        img.alt = boleta.descripcion;
        img.onclick = () => verBoletaEnGrande(boleta, divBoleta);

        const descripcion = document.createElement('div');
        descripcion.classList.add('descripcion-boleta');
        descripcion.textContent = boleta.descripcion;

        divBoleta.appendChild(img);
        divBoleta.appendChild(descripcion);
        galeriaBoletas.appendChild(divBoleta);
    });
}

// Navegar entre boletas grandes (izquierda/derecha)
function navegarBoleta(direccion) {
    boletaIndiceActual += direccion;
    if (boletaIndiceActual < 0) boletaIndiceActual = boletas.length - 1;
    if (boletaIndiceActual >= boletas.length) boletaIndiceActual = 0;

    const imagenBoletaAmpliada = document.getElementById('imagen-boleta-ampliada');
    imagenBoletaAmpliada.src = boletas[boletaIndiceActual].src;
}