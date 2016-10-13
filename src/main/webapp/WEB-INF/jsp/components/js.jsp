<%-- 
    Document   : js
    Created on : 30/09/2016, 08:35:48 AM
    Author     : geotor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="/filemanager/resources/js/graunt.js" ></script>
        <script>

 var reports = [
    "metrocuadrado-clicklogoconstructoraproyecto",
            "metrocuadrado-clicklogoinmobiliariadetalle",
            "metrocuadrado-contactarporemailinmobiliariainmuebledetalle",
            "metrocuadrado-contactarporemailinmobiliariainmuebleorigendetalle",
            "metrocuadrado-contactarportelefonoinmobiliariainmuebledetalle",
            "metrocuadrado-contactociudadproyecto",
            "metrocuadrado-contactoconstructor",
            "metrocuadrado-contactoinmuebletipociudadproyecto",
            "metrocuadrado-detallevertelefonociudadproyecto",
            "metrocuadrado-detallevertelefonoinmuebletipociudadproyecto",
            "metrocuadrado-detallevertelefonoinmuebletipoproyecto",
            "metrocuadrado-detallevertelefonoproyecto",
            "metrocuadrado-proyectocontacto",
            "metrocuadrado-proyectodestacadohomeclick",
            "metrocuadrado-proyectospublicadosciudad",
            "metrocuadrado-proyectospublicadosciudadtipoinmueble",
            "metrocuadrado-proyectospublicadosciudadzona",
            "metrocuadrado-proyectovisita",
            "metrocuadrado-proyectovisitadetalle",
            "metrocuadrado-usadospublicadosinmobiliariaciudad",
            "metrocuadrado-usadospublicadosinmobiliariaciudadtiponegocio",
            "metrocuadrado-usadospublicadosinmobiliariaciudadzona",
            "metrocuadrado-usadospublicadosinmobiliariatiponegocio",
            "metrocuadrado-vertelefonoconstructor",
            "metrocuadrado-vertelefonoinmobiliariaciudaddetalle",
            "metrocuadrado-clicklogobusquedaporinmobiliariaintegral",
            "metrocuadrado-clicklogobusquedaporinmobiliariamodnegocio",
            "metrocuadrado-vertelefonoinmobiliariaciudadzonadetalle",
            "metrocuadrado-vertelefonoinmobiliariadetalle",
            "metrocuadrado-vertelefonoinmobiliariaestratodetalle",
            "metrocuadrado-vertelefonoinmobiliariainmuebledetalle",
            "metrocuadrado-vertelefonoinmobiliariapreciodetalle",
            "metrocuadrado-visitaconstructorproyecto",
            "metrocuadrado-visitaresultadosocasional",
            "metrocuadrado-visitaresultadosproyecto",
            "metrocuadrado-visitasporinmobiliariaciudaddetalle",
            "metrocuadrado-visitasporinmobiliariaciudadtiponegociodetalle",
            "metrocuadrado-visitasporinmobiliariadetalle",
            "metrocuadrado-visitasporinmobiliariainmueble",
            "metrocuadrado-visitasporinmobiliariatiponegociodetalle",
            "metrocuadrado-visitasporinmobiliariausuariounicodetalle",
            "metrocuadrado-visitasporinmobiliariausuariounicoinmueble"
]

 

var ambiente = "metrocuadrado-";
//var ambiente = "metrocuadrado_prueba-";
var urlreport ="http://206.128.154.242/report/"



function getTimeStamp(date) {
    return date.getTime();
}

function getTimeStamp() {
    return new Date().getTime();
}

recorrer();
function recorrer(){

$.each(reports,function(i,o){
var timeframe = {
timeframe: 'hourly'
}

ajaxGraunt(urlreport+o,timeframe,null);
})

}




function ajaxGraunt(uri, data, func) {
$("#url").html($("#url").html()+"<br>"+uri.trim())
    $.ajax({
        url: uri,
       
        success: function (data) {
        var html = $("#data").html();
        html.html(html+'<br>'+data)
        },
        error: function (a, b, c) {
           $("#error").html($("#error").html()+"<br>"+a+b+c)

        }

    })

}


        </script>
    </head>
    
    <body>
        <h1>Hello World!</h1>
        <div id="data"></div>

        urls::
        <div id="url"></div>

        error::
        <div id="error"></div>
    </body>
</html>
