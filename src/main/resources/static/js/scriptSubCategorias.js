//Mostrar tallas
const viewButtons = document.querySelectorAll(".view");
const tallaInfoContainers = document.querySelectorAll(".talla-info");
viewButtons.forEach((button, index) => {
  button.addEventListener("click", () => {
    tallaInfoContainers[index].style.display = "block";
  });
});

for (let i = 1; i <= tallaInfoContainers.length; i++) {
  const closeBtn = document.querySelector(`.close-talla-info-${i}`);
  closeBtn.addEventListener("click", () => {
    tallaInfoContainers[i - 1].style.display = "none";
  });
}

for (let i = 1; i <= tallaInfoContainers.length; i++) {
  const closeBtn = document.querySelector(`.close-talla-info-${i}`);
  closeBtn.addEventListener("click", () => {
    tallaInfoContainers[i - 1].style.display = "none";
  });
}

const searchInput = document.getElementById("searchInput");
const trajeRows = document.querySelectorAll("tbody tr");

searchInput.addEventListener("input", function () {
  const searchTerm = searchInput.value.toLowerCase();

  trajeRows.forEach((row) => {
    const nombreTraje = row
      .querySelector("td:nth-child(2)")
      .textContent.toLowerCase();
    if (nombreTraje.includes(searchTerm)) {
      row.style.display = "";
    } else {
      row.style.display = "none";
    }
  });
});