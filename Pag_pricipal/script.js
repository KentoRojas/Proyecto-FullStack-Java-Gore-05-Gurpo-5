
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

// Alterna entre mostrar el saldo real o asteriscos
function toggleSaldo() {
    const saldoSpan = document.getElementById('saldo');
    const toggleButton = document.getElementById('toggle-saldo');

    if (saldoVisible) {
        // Ocultar el saldo real y mostrar asteriscos
        saldoSpan.innerText = '$' + '*'.repeat(saldo.toString().length);
        toggleButton.className = 'fluent--eye-off-16-filled'; // Cambia al ícono de "ocultar"
    } else {
        // Mostrar el saldo real
        saldoSpan.innerText = '$' + saldo;
        toggleButton.className = 'iconoir--eye-solid'; // Cambia al ícono de "mostrar"
    }

    // Cambiar el estado de visibilidad
    saldoVisible = !saldoVisible;
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
            borderWidth: 1.5
        }] 
    }
    
})
Chart.defaults.color = '#ffffff';
