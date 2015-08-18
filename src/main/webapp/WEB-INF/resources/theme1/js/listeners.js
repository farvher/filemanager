/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function itemLIstener() {
    $(".items").on("click", function () {
        var ruta = this.cells[1].title//toma la segunda columna de la fila escogida 
        var url = "/FIleManager/buscar/";
        var rendered = $("#manejador");
        AjaxGenericHTML(url, rendered, {ruta: ruta})
    });

}

function busquedaBasicaURL(busqueda) {
    var url = "/FIleManager/filtro/";
    var rendered = $("#manejador");
    if (busqueda != "") {
        AjaxGenericHTML(url, rendered, {palabra: busqueda})
    }
    return false;
}

function navegacionBreadCrum(ruta) {
    var url = "/FIleManager/buscar/";
    var rendered = $("#manejador");
    AjaxGenericHTML(url, rendered, {ruta: ruta})
}


$(document).ready(function () {
    itemLIstener();
});

$(document).ajaxComplete(function () {

    itemLIstener();
})


function replaceAll(text, busca, reemplaza) {
    while (text.toString().indexOf(busca) != - 1)
        text = text.toString().replace(busca, reemplaza);
    return text;
}
