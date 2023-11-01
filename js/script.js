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

const categorias = document.querySelectorAll('.categoria');

//Seleccionar Categorias
categorias.forEach((categoria) => {
    categoria.addEventListener('click', () => {
        categorias.forEach((c) => {
            c.style.opacity = 0.6; 
            c.querySelector('.categoria-contenido').style.color = '#ffffff'; 
        });

        categoria.style.opacity = 1; // Elimina la transparencia para la categoría seleccionada
        categoria.querySelector('.categoria-contenido').style.color = '#ffffff'; // Cambia el color del texto a blanco para la categoría seleccionada
    });
});

var slidernovia = document.getElementById('slidernovia');
var isDown = false;
var startX;
var scrollLeft;

slidernovia.addEventListener('mousedown', (e) => {
    isDown = true;
    slidernovia.style.cursor = 'grabbing';
    startX = e.pageX - slidernovia.offsetLeft;
    scrollLeft = slidernovia.scrollLeft;
});
slidernovia.addEventListener('mouseleave', () => {
    isDown = false;
    slidernovia.style.cursor = 'grab';
});
slidernovia.addEventListener('mouseup', () => {
    isDown = false;
    slidernovia.style.cursor = 'grab';
});
slidernovia.addEventListener('mousemove', (e) => {
    if(!isDown) return;
    e.preventDefault();
    const x = e.pageX - slidernovia.offsetLeft;
    const walk = (x - startX) * 1.5; //scroll-fast
    slidernovia.scrollLeft = scrollLeft - walk;
});




