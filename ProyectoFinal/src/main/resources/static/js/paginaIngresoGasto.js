//Ingresar Gasto

const openModalButton = document.getElementById('abrirRegistroGasto');
const historyContainer = document.getElementById('registroHistorial');
const modal = document.getElementById('modalRegistrarGasto');
const form = document.getElementById('formularioRegistroGasto');
const closeModalButton = document.getElementById('cerrarRegistroGasto');

openModalButton.addEventListener('click', () => {
    modal.style.display = 'flex';
});


closeModalButton.addEventListener('click', () => {
    modal.style.display = 'none';
});

window.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});


const imageModal = document.getElementById('imageModal'); // Modal para la imagen
const closeImageModal = document.getElementById('closeImageModal'); // Botón para cerrar el modal de la imagen
const modalImage = document.getElementById('modalImage'); // Imagen dentro del modal




// Guarda en el historial
form.addEventListener('submit', function (event) {
    event.preventDefault();
  
    const name = document.getElementById('expenseName').value;
    const type = document.getElementById('expenseType').value;
    const date = document.getElementById('expenseDate').value;
    const imageFile = document.getElementById('expenseImage').files[0];
  
    if (!name || !type || !date) {
      alert("Por favor, complete todos los campos.");
      return;
    }
  
    // Crear el elemento del historial
    const listItem = document.createElement('li');
    listItem.classList.add('cuadroHistorial')
    listItem.innerHTML = `
      <strong>Nombre:</strong> ${name} 
      <strong>Tipo:</strong> ${type} 
      <strong>Fecha:</strong> ${date} 
    `;
  
    // Crear el botón para la imagen
    if (imageFile) {
      const imageContainer = document.createElement('div');
      const imageButton = document.createElement('button');
      
      
    imageButton.classList.add('mingcute--paper-2-fill')
    imageButton.addEventListener('click', () => {
        // Al hacer clic, abrir el modal con la imagen
        const reader = new FileReader();
        reader.onload = function (e) {
            modalImage.src = e.target.result;  // Asignar la imagen al modal
            imageModal.style.display = 'flex'; // Mostrar el modal
        };
        reader.readAsDataURL(imageFile); // Leer la imagen
        
    });

    imageContainer.appendChild(imageButton);
    listItem.appendChild(imageContainer);
    }

    // Agregar al historial
    historyContainer.appendChild(listItem);

    // Resetear el formulario y cerrar el modal
    form.reset();
    modal.style.display = 'none';
    alert("Gasto registrado exitosamente.");
    
    
});

// Cerrando el modal de imagen al hacer clic en el botón de cierre
closeImageModal.addEventListener('click', () => {
    imageModal.style.display = 'none';
});

// Cerrando el modal de imagen al hacer clic fuera de la imagen
window.addEventListener('click', (event) => {
    if (event.target === imageModal) {
        imageModal.style.display = 'none';
    }
});