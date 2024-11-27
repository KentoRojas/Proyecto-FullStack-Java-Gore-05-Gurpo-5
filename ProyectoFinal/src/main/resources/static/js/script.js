document.addEventListener("DOMContentLoaded", () => {
  const formLogin = document.getElementById("idForm");
  const formRegister = document.getElementById("idForm1");

  const toggleToRegister = document.getElementById("toggleToRegister");
  const backArrow = document.getElementById("backArrow");
  const backSesion = document.getElementById("backSesion");

  // Muestra el formulario de registro y oculta el de inicio
  toggleToRegister.addEventListener("click", () => {
      formLogin.style.display = "none";
      formRegister.style.display = "flex";
  });

  // Botón para volver al login (backArrow y backSesion)
  const showLogin = () => {
      formRegister.style.display = "none";
      formLogin.style.display = "flex";
  };

  backArrow.addEventListener("click", showLogin);
  backSesion.addEventListener("click", showLogin);
});