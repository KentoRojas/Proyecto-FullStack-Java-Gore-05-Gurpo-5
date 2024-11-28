const bellIcon = document.getElementById('bell-icon');
const notificationCount = document.getElementById('notification-count');
const notificationList = document.getElementById('notification-list');
const notificationMessages = document.getElementById('notification-messages');
const addNotificationBtn = document.getElementById('add-notification');

let notifications = [];

// Toggle notification list visibility
bellIcon.addEventListener('click', () => {
  notificationList.style.display = notificationList.style.display === 'block' ? 'none' : 'block';
});

// Add a new group invitation
addNotificationBtn.addEventListener('click', () => {
  const groupName = `Vacaciones fin de BootCamp`;
  const newInvitation = { group: groupName, id: notifications.length + 1 };
  notifications.push(newInvitation);
  updateNotifications();
});

// Update the notification UI
function updateNotifications() {
  // Update notification count
  notificationCount.textContent = notifications.length;
  notificationCount.style.display = notifications.length > 0 ? 'inline' : 'none';

  // Update notification messages
  notificationMessages.innerHTML = '';
  notifications.forEach((invitation) => {
    const listItem = document.createElement('li');
    listItem.innerHTML = `
      <span>${invitation.group} - Alejandro"Osses"</span>
      <div>
        <button class="accept-btn" onclick="acceptInvitation(${invitation.id})"><svg xmlns="http://www.w3.org/2000/svg" width="30px" height="30px" viewBox="0 0 15 15"><path fill="currentColor" fill-rule="evenodd" d="M0 7.5a7.5 7.5 0 1 1 15 0a7.5 7.5 0 0 1-15 0m7.072 3.21l4.318-5.398l-.78-.624l-3.682 4.601L4.32 7.116l-.64.768z" clip-rule="evenodd"/></svg></button>
        <button class="reject-btn" onclick="rejectInvitation(${invitation.id})"><svg xmlns="http://www.w3.org/2000/svg" width="30px" height="30px" viewBox="0 0 20 20"><path fill="currentColor" d="M2.93 17.07A10 10 0 1 1 17.07 2.93A10 10 0 0 1 2.93 17.07M11.4 10l2.83-2.83l-1.41-1.41L10 8.59L7.17 5.76L5.76 7.17L8.59 10l-2.83 2.83l1.41 1.41L10 11.41l2.83 2.83l1.41-1.41L11.41 10z"/></svg></button>
      </div>
    `;
    notificationMessages.appendChild(listItem);
    });
}

// Accept invitation
function acceptInvitation(id) {
    alert(`You have accepted the invitation to Group ${id}`);
    removeNotification(id);
}

// Reject invitation
function rejectInvitation(id) {
    alert(`Has rechazado la Invitacion al Grupo ${id}`);
    removeNotification(id);
}

// Remove notification from the list
function removeNotification(id) {
    notifications = notifications.filter((invitation) => invitation.id !== id);
    updateNotifications();
}


window.addEventListener('scroll', function() {
    const parallaxSections = document.querySelectorAll('.pag');
    
    parallaxSections.forEach(section => {
        const speed = 0.5;  //
        const offset = window.pageYOffset * speed;  
        section.style.backgroundPositionY = offset + 'px';
    });

    
    const links = document.querySelectorAll('.sidebar ul li a');
    const sections = document.querySelectorAll('section');
    
    let currentSection = null;
    
    sections.forEach(section => {
        const rect = section.getBoundingClientRect();
        if (rect.top <= 0 && rect.bottom > 0) {
            currentSection = section;
        }
    });
    
    links.forEach(link => {
        link.classList.remove('active');
        if (currentSection && link.getAttribute('href').substring(1) === currentSection.id) {
            link.classList.add('active');
        }
    });
});


let saldo = 0;
let saldoVisible = true; // Esta variable controla si el saldo es visible o está oculto


// Suma el monto al saldo y actualiza la visualización
function sumarSaldo() {
    const montoInput = document.getElementById('monto');
    const monto = parseFloat(montoInput.value);

    if (!isNaN(monto) && monto > 0) {
        saldo += monto;
        document.getElementById('saldo').innerText = '$' + saldo;
        montoInput.value = '';
        document.getElementById('input-container').style.display = 'none';
        document.getElementById('confirm-button').style.display = 'none';
    } else {
        alert("Por favor, ingrese un monto válido.");
    }
}



function mostrarInput() {
    document.getElementById('modal').style.display = 'flex';
}

function cerrarModal() {
    document.getElementById('modal').style.display = 'none';
}

function sumarSaldo() {
    const montoInput = document.getElementById('monto');
    const monto = parseFloat(montoInput.value);

    if (!isNaN(monto) && monto > 0) {
        saldo += monto;
        document.getElementById('saldo').innerText = '$' + saldo;
        montoInput.value = '';
        cerrarModal();
    } else {
        alert("Por favor, ingrese un monto válido.");
    }
}


// Grafico
const ctx = document.getElementById('myChart')
const meses = ['Junio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre']
const gasto = [25000, 20000, 32500, 14010, 7890]

const myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: meses,
        datasets: [{
            label: 'Gasto Mensuales',
            data: gasto,
            borderWidth: 1.5,
			backgroundColor: [
			                '#80a5b5', // Color para Junio
			                '#3e5976', // Color para Agosto
			                '#2d4664', // Color para Septiembre
			                '#4BC0C0', // Color para Octubre
			                '#9966FF'  // Color para Noviembre
			            ]
        }] 
    }
    
})
Chart.defaults.color = '#ffffff';


const grafico = document.getElementById('graficoPrincipal')
const mesess = ['Junio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre']
const gastos = [25000, 20000, 32500, 14010, 10890]

const myChar = new Chart(grafico, {
    type: 'bar',
    data: {
        labels: mesess,
        datasets: [{
            label: 'Gasto Mensuales',
            data: gasto,
            borderWidth: 1.5,
			backgroundColor: [
						                '#80a5b5', // Color para Junio
						                '#3e5976', // Color para Agosto
						                '#2d4664', // Color para Septiembre
						                '#4BC0C0', // Color para Octubre
						                '#9966FF'  // Color para Noviembre
						            ]
        }] 
    }
    
})
Chart.defaults.color = '#ffffff';