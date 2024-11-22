// Array para almacenar los grupos creados
let grupos = [];

// Array para almacenar los integrantes del grupo
let integrantes = [];

// Función para manejar la búsqueda de una persona (aún simplificada)
function buscarPersona() {
    let persona = document.getElementById("search").value;
    if (persona.trim() !== "") {
        alert(`Persona encontrada: ${persona}`);
    }
}

// Función para agregar integrante al grupo
function agregarIntegrante() {
    let inputIntegrante = document.getElementById("buscar-integrante");
    let nombreIntegrante = inputIntegrante.value.trim();
    
    // Verificamos que el nombre no esté vacío y no se repita
    if (nombreIntegrante !== "" && !integrantes.includes(nombreIntegrante)) {
        // Agregamos el integrante a la lista
        integrantes.push(nombreIntegrante);
        
        // Actualizamos la vista de integrantes
        actualizarListaIntegrantes();
        
        // Limpiamos el input
        inputIntegrante.value = "";
    } else if (integrantes.includes(nombreIntegrante)) {
        alert("Este integrante ya ha sido agregado.");
    }
}

// Función para actualizar la lista de integrantes
function actualizarListaIntegrantes() {
    let listaIntegrantesDiv = document.getElementById("lista-integrantes");
    listaIntegrantesDiv.innerHTML = ""; // Limpiamos la lista
    
    // Iteramos sobre los integrantes y los mostramos
    integrantes.forEach((integrante, index) => {
        let div = document.createElement('div');
        div.classList.add('integrante-item');
        div.innerHTML = `
            ${integrante}
            <span class="eliminar" onclick="eliminarIntegrante(${index})">✖</span>
        `;
        
        listaIntegrantesDiv.appendChild(div);
    });
}

// Función para eliminar un integrante
function eliminarIntegrante(index) {
    integrantes.splice(index, 1);
    actualizarListaIntegrantes();
}

// Función para mostrar el formulario de creación de grupo
function mostrarFormulario() {
    // Reseteamos la lista de integrantes
    integrantes = [];
    actualizarListaIntegrantes();
    
    document.getElementById("formulario").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

// Función para crear el grupo
function crearGrupo() {
    let nombreGrupo = document.getElementById("nombre-grupo").value;
    let saldo = document.getElementById("saldo").value;
    let dueno = document.getElementById("nombre-dueno").value;

    // Verificamos que los campos estén completos
    if (nombreGrupo.trim() === "" || saldo.trim() === "") {
        alert("Por favor, completa todos los campos.");
        return;
    }

    // Creamos el objeto del grupo
    let grupo = {
        nombre: nombreGrupo,
        saldo: saldo,
        dueno: dueno,
        integrantes: [...integrantes] // Copiamos la lista de integrantes
    };

    // Agregamos el grupo a la lista de grupos
    grupos.push(grupo);

    // Llamamos a la función para actualizar la vista de los grupos creados
    actualizarGrupos();

    // Reiniciamos y ocultamos el formulario
    resetFormulario();
}

// Función para actualizar la lista de grupos creados en la página
function actualizarGrupos() {
    let gruposDiv = document.getElementById("grupos-creados");
    gruposDiv.innerHTML = ""; // Limpiamos la sección de grupos

    // Iteramos sobre todos los grupos y los mostramos
    grupos.forEach(grupo => {
        let div = document.createElement('div');
        div.classList.add('grupo-card');
        div.innerHTML = `
            <strong>${grupo.nombre}</strong><br>
            Saldo: ${grupo.saldo}<br>
            Integrantes: ${grupo.integrantes.length}
        `;

        // Al hacer clic en el recuadro del grupo, lo redirigimos a una página de detalles
        div.onclick = function() {
            verDetallesGrupo(grupo);
        };

        gruposDiv.appendChild(div);
    });
}

// Función para resetear el formulario después de crear el grupo
function resetFormulario() {
    // Limpiamos los campos del formulario
    document.getElementById("nombre-grupo").value = "";
    document.getElementById("saldo").value = "";
    
    // Reseteamos la lista de integrantes
    integrantes = [];
    actualizarListaIntegrantes();
    
    // Ocultamos el formulario y el overlay
    document.getElementById("formulario").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}

// Función para ver los detalles del grupo (en una página separada)
function verDetallesGrupo(grupo) {
    let detalles = `
Grupo: ${grupo.nombre}
Dueño: ${grupo.dueno}
Saldo: ${grupo.saldo}
Integrantes: ${grupo.integrantes.join(', ')}
    `;
    alert(detalles);
}