/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var path = document.location.pathname;
function itemLIstener() {
    $(".items").on("click", function () {
        var ruta = this.title//toma la segunda columna de la fila escogida 
        var url = "/buscar/";
        var rendered = $("#manejador");
        AjaxGenericHTML(url, rendered, {ruta: ruta}, this)
        updateBuscador(ruta);
    });

}

function busquedaBasicaURL(busqueda, desde) {
    var url = "/filtro/";
    var rendered = $("#manejador");
    if (busqueda != "") {
        AjaxGenericHTML(url, rendered, {palabra: busqueda, buscardesde: desde})


    }
    return false;
}

function navegacionBreadCrum(ruta) {
    var url = "/buscar/";
    var rendered = $("#manejador");
    updateBuscador(ruta);
    AjaxGenericHTML(url, rendered, {ruta: ruta})
}

function updateBuscador(ruta) {
    try {
        $("#namefile").val(ruta);
        var carpetas = ruta.split("/");
        $("#busqueda").attr("placeholder", "Buscar en " + carpetas[carpetas.length - 1])//actualiza el placeholder de buscar
    } catch (e) {
        $("#busqueda").attr("placeholder", ruta);
    }
}

function deleteFile(ruta){
	var url = "/delete"
	var rendered = $("#manejador");
	AjaxGenericHTML(url, rendered, {file_name: ruta})
	updateBuscador(ruta);
}


$(document).ready(function () {
    $("#busqueda").focus();
    itemLIstener();
    animationImg();


});

$(document).ajaxComplete(function () {

    itemLIstener();
    animationImg();
    $("html, body").animate({scrollTop: 0}, "slow");

})
$(document).on("keyup", function () {
  //  $("#busqueda").focus();
})


function replaceAll(text, busca, reemplaza) {
    while (text.toString().indexOf(busca) != - 1)
        text = text.toString().replace(busca, reemplaza);
    return text;
}

function newFolder(){
	var inputText = "<input type='text' id='nameFolder' class='input-folder' placeholder='name folder' />";
	$("#nameNewFolder").html(inputText);
	$("#nameFolder").focus();
	$("#nameFolder").keypress(function (e) {
			var folder = $("#nameFolder").val();
			var ubicado = $("#ubicado").val();
		  if (e.which == 13 && folder!="") {
			  	var url = "/createFolder?ruta=";
			  	var ruta = ubicado+"/"+folder;
			  	window.location.href=url+ruta;
			  }
			});
	}
	


$('#falseinput').click(function () {
    $("#fileinput").click();
});

$("#fileinput").change(function () {
    var enviar = confirm("Desea cargar el archivo " + this.value)
    if (enviar) {
        $("#cargar").submit();
    }

})



function animationImg() {
    var div = $("#imgprev");

    div.show('highlight', {}, 500)
    div.focus();

}
function salirAnimationImg() {

    var div = $("#imgprev");
    div.hide('slide', {}, 500);

}

