let menuVisible = false;

// Función que oculta o muestra el menú
function mostrarOcultarMenu() {
    const nav = document.getElementById("nav");
    if (menuVisible) {
        nav.classList.remove("responsive"); // Quita la clase "responsive" para mostrar todos los elementos
        menuVisible = false;
    } else {
        nav.classList.add("responsive"); // Agrega la clase "responsive" para ocultar elementos innecesarios en modo celular
        menuVisible = true;
    }
}

function seleccionar(){
    //oculto el menu una vez que selecciono una opcion
    document.getElementById("nav").classList = "";
    menuVisible = false;
}
