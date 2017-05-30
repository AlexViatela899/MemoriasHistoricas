function myFunction() {
    document.getElementsByClassName("topnav")[0].classList.toggle("responsive");
}
function mostrarPanel(idPanel) {
    var panel = document.getElementById(idPanel);
    panel.style.display = 'block';
}
