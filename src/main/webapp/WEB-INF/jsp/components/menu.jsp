<%-- 
    Document   : menu
    Created on : 6/08/2015, 02:31:38 PM
    Author     : farvher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath().toString()%>">FileManager</a>

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link </a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search" onsubmit="return busquedaBasicaURL(document.getElementById('busqueda').value, document.getElementById('ubicado').value)" method="GET"  >
                <div class="form-group">
                    <input type="text" class="form-control col-xs-push-5" id="busqueda" name="busqueda" placeholder="${ubicado}">
                </div>
                <input type="submit" class="btn btn-default" value="Buscar">
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a id="falseinput" href="#"> <span class="glyphicon glyphicon-upload"></span></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>

            <div id="cargando"  style="display: none">
                <img src="<%=request.getContextPath().toString()%>/resources/img/loading.gif" style="width: 20px"/>
            </div> 
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->

</nav>

<!--Formulario escondido para enviar los archivos al controlador-->
<form method="post" id="cargar" action="<%=request.getContextPath().toString()%>/form" enctype="multipart/form-data">
    <input type="hidden" name="ruta" id="namefile" value="${ubicado}"/>
    <input type="file" id="fileinput" name="file" style="display: none"/>
</form>
