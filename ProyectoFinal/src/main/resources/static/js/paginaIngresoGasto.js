const openModalButton = document.getElementById('abrirRegistroGasto');
const historyContainer = document.getElementById('registroHistorial');
const modal = document.getElementById('modalRegistrarGasto');
const form = document.getElementById('formularioRegistroGasto');
const closeModalButton = document.getElementById('cerrarRegistroGasto');
const drawer = document.getElementById('drawer2'); // Cajón donde se mostrarán los últimos 3 gastos

// Modal de imagen
const imageModal = document.getElementById('imageModal');
const closeImageModal = document.getElementById('closeImageModal');
const modalImage = document.getElementById('modalImage');

// Mostrar el modal para registrar un gasto
openModalButton.addEventListener('click', () => {
    modal.style.display = 'flex';
});

// Cerrar el modal al hacer clic en el botón de cierre
closeModalButton.addEventListener('click', () => {
    modal.style.display = 'none';
});

// Cerrar el modal al hacer clic fuera de él
window.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});

// Cerrar el modal de imagen al hacer clic en el botón de cierre
closeImageModal.addEventListener('click', () => {
    imageModal.style.display = 'none';
});

// Cerrar el modal de imagen al hacer clic fuera de él
window.addEventListener('click', (event) => {
    if (event.target === imageModal) {
        imageModal.style.display = 'none';
    }
});

// Cargar todos los gastos y los últimos 3 desde el almacenamiento local al cargar la página
document.addEventListener('DOMContentLoaded', function () {
    const gastosGuardados = JSON.parse(localStorage.getItem('historialGastos')) || [];

    // Mostrar todos los gastos en el historial
    gastosGuardados.forEach(item => {
        agregarGastoAlHistorial(item);
    });

    // Mostrar solo los últimos 3 gastos en el cajón
    actualizarDrawer(gastosGuardados);
});

// Función para agregar un gasto al historial completo
function agregarGastoAlHistorial(gasto) {
    const listItem = document.createElement('li');
    listItem.classList.add('cuadroHistorial');
    listItem.innerHTML = `
        <strong>Nombre:</strong> ${gasto.name}
		<strong>Gasto:</strong> ${gasto.gasto}  
        <strong>Tipo:</strong> ${gasto.type} 
        <strong>Fecha:</strong> ${gasto.date} 
    `;

    // Crear el botón para la imagen
    if (gasto.imageSrc) {
        const imageContainer = document.createElement('div');
        const imageButton = document.createElement('button');
        imageButton.classList.add('mingcute--paper-2-fill');
        imageButton.addEventListener('click', () => {
            modalImage.src = gasto.imageSrc;
            imageModal.style.display = 'flex';
        });
        imageContainer.appendChild(imageButton);
        listItem.appendChild(imageContainer);
    }

    historyContainer.appendChild(listItem); // Agregar al historial completo
}

// Función para agregar un gasto al cajón (drawer)
function agregarGastoAlDrawer(gasto) {
    const listItem = document.createElement('li');
    listItem.classList.add('resumenHistorial');
    listItem.innerHTML = `
        <strong>Nombre:</strong> ${gasto.name} 
        <strong>Tipo:</strong> ${gasto.type} 
        <strong>Gasto:</strong> ${gasto.gasto} 
    `;
    drawer.appendChild(listItem); // Agregar al cajón
}

// Actualizar el cajón para que solo muestre los últimos 3 gastos
function actualizarDrawer(gastos) {
    // Limpiar el cajón
    drawer.innerHTML = '';

    // Obtener los últimos 3 gastos
    const ultimosGastos = gastos.slice(-3);

    // Agregar los últimos 3 gastos al cajón
    ultimosGastos.forEach(item => {
        agregarGastoAlDrawer(item);
    });
}

// Guarda el gasto en el almacenamiento local
form.addEventListener('submit', function (event) {
    event.preventDefault();

    const name = document.getElementById('expenseName').value;
	const gasto = document.getElementById('Gasto').value;
    const type = document.getElementById('expenseType').value;
    const date = document.getElementById('expenseDate').value;
    const imageFile = document.getElementById('expenseImage').files[0];

    if (!name || !type || !date) {
        alert("Por favor, complete todos los campos.");
        return;
    }

    const newGasto = {
        name,
		gasto,
        type,
        date,
        imageSrc: null
    };

    // Si se sube una imagen, se guarda en el objeto
    if (imageFile) {
        const reader = new FileReader();
        reader.onload = function (e) {
            newGasto.imageSrc = e.target.result;
            guardarGastoEnHistorial(newGasto);
        };
        reader.readAsDataURL(imageFile);
    } else {
        guardarGastoEnHistorial(newGasto);
    }

    // Resetear el formulario y cerrar el modal
    form.reset();
    modal.style.display = 'none';
    alert("Gasto registrado exitosamente.");
});

// Función para guardar el gasto en el historial y en el almacenamiento local
function guardarGastoEnHistorial(gasto) {
    // Obtener el historial guardado desde el LocalStorage
    const historialGuardado = JSON.parse(localStorage.getItem('historialGastos')) || [];
    
    // Agregar el nuevo gasto al final del arreglo
    historialGuardado.push(gasto);

    // Guardar el historial actualizado en el almacenamiento local
    localStorage.setItem('historialGastos', JSON.stringify(historialGuardado));

    // Agregar el gasto al historial completo
    agregarGastoAlHistorial(gasto);

    // Actualizar el cajón con los últimos 3 gastos
    actualizarDrawer(historialGuardado);
}
