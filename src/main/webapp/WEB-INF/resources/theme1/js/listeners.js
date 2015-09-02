/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var path = document.location.pathname;
function itemLIstener() {
    $(".items").on("click", function () {
        var ruta = this.cells[1].title//toma la segunda columna de la fila escogida 
        var url = path +"/buscar/";
        var rendered = $("#manejador");
        AjaxGenericHTML(url, rendered, {ruta: ruta})
        updateBuscador(ruta);
        if (document.getElementById("imgprev")) {
//           previImg(); 
        }
        
    });

}

function busquedaBasicaURL(busqueda,desde) {
    var url = path+"/filtro/";
    var rendered = $("#manejador");
    if (busqueda != "") {
        AjaxGenericHTML(url, rendered, {palabra: busqueda , buscardesde : desde})
        

    }
    return false;
}

function navegacionBreadCrum(ruta) {
    var url = path+"/buscar/";
    var rendered = $("#manejador");
    AjaxGenericHTML(url, rendered, {ruta: ruta})
}

function updateBuscador(ruta){
    try {
        var carpetas =ruta.split("/");
        $("#busqueda").attr("placeholder","Buscar en "+carpetas[carpetas.length-1])//actualiza el placeholder de buscar
    } catch (e) {
       $("#busqueda").attr("placeholder",ruta); 
    }

    
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


function previImg() {

    $("#imgprev").css("display", "block");
    $("#imgprev").dialog({
        modal: true, closeOnEscape: true, resizable: true, 
         show: { effect: "blind", duration: 800 }
         
    });
}