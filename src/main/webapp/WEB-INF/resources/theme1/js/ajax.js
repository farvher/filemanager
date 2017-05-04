/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





function AjaxGenericHTML(url, div, param) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    console.log("busqueda ajax : " + url + " params : " + JSON.stringify(param));
    gifStart();
    $.ajax({
        url: url,
        type: 'POST',
        data: param,
        beforeSend: function(xhr) { 
              xhr.setRequestHeader(header, token); 
            
            },
        success: function (data, textStatus, jqXHR) {
            $(div).html(data);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('ha ocurrido un error' + jqXHR + " - " + textStatus + " - " + errorThrown);
        }
        , complete: function (jqXHR, textStatus) {
            gifStop();
        }
    });


}


function gifStart() {

    $("#cargando").removeClass("hidden");
}
function gifStop() {

    $("#cargando").addClass("hidden")
}
