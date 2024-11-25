document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("query");

    if (searchInput) {
        console.log("Input encontrado:", searchInput);

        // Agregar un evento al input
        searchInput.addEventListener("input", (event) => {
            console.log("Texto ingresado:", event.target.value);
        });
    } else {
        console.error("No se encontró el input con ID 'query'.");
    }
});
