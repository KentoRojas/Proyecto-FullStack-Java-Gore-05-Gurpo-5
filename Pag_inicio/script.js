
document.getElementById('registerBtn').addEventListener('click', function() {

  document.getElementById('headingText').textContent = "Regístrate";


  document.getElementById('nameField').style.display = 'flex';
  document.getElementById('lastNameField').style.display = 'flex';
  document.getElementById('confirmPasswordField').style.display = 'flex';
  document.querySelector('.haveAccount').style.display = 'block'
  document.querySelector('.styled-button .inner-button').style.background = 'linear-gradient(to bottom, #47ca79, #0d8017)'

  //document.getElementById('registerBtn').innerText = 'Registrarse';

  document.getElementById('login').style.display = 'none';

  document.getElementById('backArrow').style.display = 'block';
});


document.getElementById('backArrow').addEventListener('click', function() {

  document.getElementById('headingText').textContent = "Inicia Sesión";


  document.getElementById('nameField').style.display = 'none';
  document.getElementById('lastNameField').style.display = 'none';
  document.getElementById('confirmPasswordField').style.display = 'none';
  document.querySelector('.haveAccount').style.display = 'none'
  document.querySelector('.styled-button .inner-button').style.background = 'linear-gradient(to bottom, #0b2049, #062709)'

  //document.getElementById('registerBtn').textContent = 'Regístrate';

  document.getElementById('login').style.display = 'flex';
  document.getElementById('backArrow').style.display = 'none';
});

document.getElementById('backSesion').addEventListener('click', function() {
  document.getElementById('headingText').textContent = "Inicia Sesión";

  document.getElementById('nameField').style.display = 'none';
  document.getElementById('lastNameField').style.display = 'none';
  document.getElementById('confirmPasswordField').style.display = 'none';
  document.querySelector('.haveAccount').style.display = 'none'
  document.querySelector('.styled-button .inner-button').style.background = 'linear-gradient(to bottom, #0b2049, #062709)'


  document.getElementById('login').style.display = 'flex';
  document.getElementById('backArrow').style.display = 'none';
});

