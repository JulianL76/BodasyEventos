//======================================//
//<     Menu Hamburguesa               >//
//======================================//
let menuVisible = false;

function mostrarOcultarMenu() {
    const nav = document.getElementById("nav");
    if (menuVisible) {
        nav.classList.remove("responsive");
        menuVisible = false;
    } else {
        nav.classList.add("responsive");
        menuVisible = true;
    }
}

function seleccionar() {
    document.getElementById("nav").classList = "";
    menuVisible = false;
}
//======================================//


