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

const categorias = document.querySelectorAll('.categoria');

//Seleccionar Categorias
categorias.forEach((categoria) => {
    categoria.addEventListener('click', () => {
        categorias.forEach((c) => {
            c.style.opacity = 0.6;
            c.querySelector('.categoria-contenido').style.color = '#ffffff';
        });

        categoria.style.opacity = 1;
        categoria.querySelector('.categoria-contenido').style.color = '#ffffff';
    });
});

//Mostrar tallas
const viewButtons = document.querySelectorAll('.view');
const tallaInfoContainers = document.querySelectorAll('.talla-info');
viewButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        tallaInfoContainers[index].style.display = 'block';
    });
});

for (let i = 1; i <= tallaInfoContainers.length; i++) {
    const closeBtn = document.querySelector(`.close-talla-info-${i}`);
    closeBtn.addEventListener('click', () => {
        tallaInfoContainers[i - 1].style.display = 'none';
    });
}

for (let i = 1; i <= tallaInfoContainers.length; i++) {
    const closeBtn = document.querySelector(`.close-talla-info-${i}`);
    closeBtn.addEventListener('click', () => {
        tallaInfoContainers[i - 1].style.display = 'none';
    });
}

var slidernovia = document.getElementById('slidernovia');
var isDown = false;
var startX;
var startY;
var scrollLeft;

slidernovia.addEventListener('mousedown', (e) => {
    isDown = true;
    slidernovia.style.cursor = 'grabbing';
    startX = e.pageX - slidernovia.offsetLeft;
    startY = e.pageY - slidernovia.offsetTop;
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
    const walk = (x - startX) * 1.5; //scroll-slower
    slidernovia.scrollLeft = scrollLeft - walk;
});

// Eventos de tacto para dispositivos móviles
slidernovia.addEventListener('touchstart', (e) => {
    isDown = true;
    startX = e.touches[0].pageX - slidernovia.offsetLeft;
    startY = e.touches[0].pageY - slidernovia.offsetTop;
    scrollLeft = slidernovia.scrollLeft;
});
slidernovia.addEventListener('touchend', () => {
    isDown = false;
});
slidernovia.addEventListener('touchmove', (e) => {
    if(!isDown) return;

    const x = e.touches[0].pageX - slidernovia.offsetLeft;
    const y = e.touches[0].pageY - slidernovia.offsetTop;

    // Comprueba si el desplazamiento es principalmente horizontal
    if (Math.abs(x - startX) > Math.abs(y - startY)) {
        // Comprueba si el evento es cancelable antes de llamar a preventDefault()
        if (e.cancelable) {
            e.preventDefault();
        }
        const walk = (x - startX) * 1.2; //scroll-slower
        slidernovia.scrollLeft = scrollLeft - walk;
    }
});

// ========================================

document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();

        const targetSection = document.querySelector(this.getAttribute('href'));
        const headerHeight = document.querySelector('.contenedor-header').offsetHeight;
        const targetOffset = targetSection.offsetTop - headerHeight;

        window.scrollTo({
            top: targetOffset,
            behavior: 'smooth'
        });
    });
});

/// mostrar datos en lo del carrito con el localstores

function mostrarDatos(){ 
    var respuesta = "";  
    var totalPagar = 0;  
    if (localStorage.length>0) {
        for(let i=1; i<= localStorage.length; i++){             
            let txt = localStorage.getItem("carrito" + i);            
            if (txt === undefined ){
                console.log("Error: " + i);
              
            }else {
                const carrito = JSON.parse(txt);  
                console.log(txt);
                if (carrito!=null){
                    respuesta += 
                      "<tr>" + "<td>" + carrito.id_producto+ "</td>" +
                                "<td>" + carrito.talla + "</td>" +
                                "<td>" + carrito.precio + "<td>"+
                                "</tr>";
                                totalPagar += carrito.precio;
                }

                respuesta +=  "<tr>"+
               " <td colspan='2'style='text-align: right;'>Total a pagar:</td>"+
                +"<td>"+"$"+totalPagar+"</td>";
              "</tr>";    
            }
        }

    } else {
        respuesta += "<tr>" + "<td colspan='4'> Carrito vacio </td>" +
                     "</tr>"
    }

    document.getElementById("tableBody").innerHTML = respuesta;

}



