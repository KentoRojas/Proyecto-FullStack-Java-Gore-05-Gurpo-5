document.getElementById('formRegisterBtn').addEventListener('click', function() {
  document.getElementById('idForm').style.display = 'none';
  document.getElementById('idForm1').style.display = 'flex';
  document.querySelector('.haveAccount').style.display = 'block'

  document.getElementById('backArrow').style.display = 'block';
});


document.getElementById('backArrow').addEventListener('click', function() {
  document.getElementById('idForm').style.display = 'flex';
  document.getElementById('idForm1').style.display = 'none';
  document.querySelector('.haveAccount').style.display = 'none'

  document.getElementById('backArrow').style.display = 'none';

});

document.getElementById('backSesion').addEventListener('click', function() {
  document.getElementById('idForm').style.display = 'flex';
  document.getElementById('idForm1').style.display = 'none';
  document.querySelector('.haveAccount').style.display = 'none'

  document.getElementById('backArrow').style.display = 'none';

});