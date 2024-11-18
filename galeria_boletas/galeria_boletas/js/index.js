let imagenesSubidas = []; // Arreglo para almacenar las imágenes y descripciones
let imagenIndex = 0; // Índice para la imagen actualmente visualizada en grande
let paginaActual = 0; // Página actual de la galería
const IMAGENES_POR_PAGINA = 20; // Máximo de imágenes por página

// Función para subir una imagen
function subirImagen() {
    const archivo = document.getElementById('selectorImagen').files[0];
    const descripcion = document.getElementById('descripcionImagen').value;
    
    if (!archivo || !descripcion) {
        alert("Por favor, sube una imagen y agrega una descripción.");
        return;
    }

    const reader = new FileReader();
    reader.onload = function(e) {
        const imagenData = {
            src: e.target.result,
            descripcion: descripcion,
            fecha: new Date(),
        };
        imagenesSubidas.push(imagenData);
        mostrarGaleria();
        document.getElementById('descripcionImagen').value = ''; // Limpiar descripción
        document.getElementById('selectorImagen').value = ''; // Limpiar selector de archivo
    };
    reader.readAsDataURL(archivo);
}

// Mostrar las imágenes en la galería
function mostrarGaleria() {
    const galeria = document.getElementById('galeria');
    galeria.innerHTML = '';

    const imagenesOrdenadas = [...imagenesSubidas].sort((a, b) => b.fecha - a.fecha); // Ordenar por fecha (más reciente primero)

    const inicio = paginaActual * IMAGENES_POR_PAGINA;
    const fin = inicio + IMAGENES_POR_PAGINA;

    imagenesOrdenadas.slice(inicio, fin).forEach(imagen => {
        const divImagen = document.createElement('div');
        divImagen.classList.add('imagen-container');

        const img = document.createElement('img');
        img.src = imagen.src;
        img.alt = imagen.descripcion;
        img.onclick = () => verImagenEnGrande(imagen, divImagen);

        const descripcion = document.createElement('div');
        descripcion.classList.add('descripcion-imagen');
        descripcion.textContent = imagen.descripcion;

        divImagen.appendChild(img);
        divImagen.appendChild(descripcion);
        galeria.appendChild(divImagen);
    });

    mostrarPaginacion(imagenesOrdenadas.length);
}

// Mostrar los botones de paginación
function mostrarPaginacion(totalImagenes) {
    const paginacion = document.getElementById('paginacion');
    paginacion.innerHTML = '';

    const totalPaginas = Math.ceil(totalImagenes / IMAGENES_POR_PAGINA);
    
    if (paginaActual > 0) {
        const botonAnterior = document.createElement('button');
        botonAnterior.textContent = 'Anterior';
        botonAnterior.onclick = () => cambiarPagina(paginaActual - 1);
        paginacion.appendChild(botonAnterior);
    }

    if (paginaActual < totalPaginas - 1) {
        const botonSiguiente = document.createElement('button');
        botonSiguiente.textContent = 'Página siguiente';
        botonSiguiente.onclick = () => cambiarPagina(paginaActual + 1);
        paginacion.appendChild(botonSiguiente);
    }
}

// Cambiar de página
function cambiarPagina(nuevaPagina) {
    paginaActual = nuevaPagina;
    mostrarGaleria();
}

// Función para ver la imagen en tamaño grande dentro de la vista
function verImagenEnGrande(imagen, contenedor) {
    imagenIndex = imagenesSubidas.findIndex(img => img.src === imagen.src);
    const imagenGrande = document.getElementById('imagenGrande');
    const imagenGrandeImg = document.getElementById('imagenGrandeImg');
    imagenGrandeImg.src = imagen.src;
    imagenGrande.style.display = 'flex';

    // Mostrar los botones de eliminar y descargar al seleccionar la imagen
    const botonesImagenGrande = document.getElementById('botonesImagenGrande');
    botonesImagenGrande.style.display = 'block';
}

// Función para cerrar la vista de la imagen grande
function cerrarVistaGrande() {
    const imagenGrande = document.getElementById('imagenGrande');
    imagenGrande.style.display = 'none';
    const botonesImagenGrande = document.getElementById('botonesImagenGrande');
    botonesImagenGrande.style.display = 'none';
}

// Función para borrar una imagen
function borrarImagen() {
    imagenesSubidas = imagenesSubidas.filter(img => img.src !== imagenesSubidas[imagenIndex].src);
    mostrarGaleria();
}

// Función para descargar una imagen
function descargarImagen() {
    const imagen = imagenesSubidas[imagenIndex];
    const link = document.createElement('a');
    link.href = imagen.src;
    link.download = 'imagen_boleta.jpg';
    link.click();
}

// Función para buscar por descripción
function buscar() {
    const descripcionBuscada = document.getElementById('busquedaDescripcion').value.toLowerCase();
    const imagenesFiltradas = imagenesSubidas.filter(imagen => imagen.descripcion.toLowerCase().includes(descripcionBuscada));
    mostrarFiltradas(imagenesFiltradas);
}

// Función para buscar por fecha
function buscarPorFecha() {
    const fechaSeleccionada = document.getElementById('filtroFecha').value;
    if (!fechaSeleccionada) return;

    const fecha = new Date(fechaSeleccionada);
    const imagenesFiltradas = imagenesSubidas.filter(imagen => {
        const fechaImagen = new Date(imagen.fecha);
        return fechaImagen.toDateString() === fecha.toDateString();
    });
    mostrarFiltradas(imagenesFiltradas);
}

// Mostrar las imágenes filtradas
function mostrarFiltradas(imagenes) {
    const galeria = document.getElementById('galeria');
    galeria.innerHTML = '';

    imagenes.forEach(imagen => {
        const divImagen = document.createElement('div');
        divImagen.classList.add('imagen-container');

        const img = document.createElement('img');
        img.src = imagen.src;
        img.alt = imagen.descripcion;

        const descripcion = document.createElement('div');
        descripcion.classList.add('descripcion-imagen');
        descripcion.textContent = imagen.descripcion;

        divImagen.appendChild(img);
        divImagen.appendChild(descripcion);
        galeria.appendChild(divImagen);
    });
}

// Navegar entre imágenes grandes (izquierda/derecha)
function navegarImagen(direccion) {
    imagenIndex += direccion;
    if (imagenIndex < 0) imagenIndex = imagenesSubidas.length - 1;
    if (imagenIndex >= imagenesSubidas.length) imagenIndex = 0;

    const imagenGrandeImg = document.getElementById('imagenGrandeImg');
    imagenGrandeImg.src = imagenesSubidas[imagenIndex].src;
}
