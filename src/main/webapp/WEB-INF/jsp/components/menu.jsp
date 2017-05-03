<%-- 
    Document   : menu
    Created on : 6/08/2015, 02:31:38 PM
    Author     : farvher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse ">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand " href="<%=request.getContextPath().toString()%>">
                <!--<img  class="img-rounded" src="<%=request.getContextPath().toString()%>/resources/img/icono.png" style="width: 80px"/>-->
          ShareFiler
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li ><a href="#">Link </a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search" onsubmit="return busquedaBasicaURL(document.getElementById('busqueda').value, document.getElementById('ubicado').value)" method="GET"  >
                <div class="form-group">
                    <input type="text" class="form-control " id="busqueda" name="busqueda" placeholder="Buscar ...">
                </div>
                <input type="submit" class="btn btn-default" value="Buscar">
            </form>

            <form class="navbar-form">
                <a class="btn  btn-default" id="falseinput"> <span class="glyphicon glyphicon-upload"></span></a>
                <a class="btn  btn-success" id="cargando" style="display: none">
                    <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate">
                    </span>
                </a>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->

</nav>

<!--Formulario escondido para enviar los archivos al controlador-->
<form method="post" id="cargar" action="<%=request.getContextPath().toString()%>/form" enctype="multipart/form-data">
    <input type="hidden" name="ruta" id="namefile" value="${ubicado}"/>
    <input type="file" id="fileinput" name="file" style="display: none"/>
</form>

<style>
    .glyphicon-refresh-animate {
        -animation: spin .7s infinite linear;
        -webkit-animation: spin2 .7s infinite linear;
    }

    @-webkit-keyframes spin2 {
        from { -webkit-transform: rotate(0deg);}
        to { -webkit-transform: rotate(360deg);}
    }

    @keyframes spin {
        from { transform: scale(1) rotate(0deg);}
        to { transform: scale(1) rotate(360deg);}
    }
</style>
 <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>
<br/>
<br/>
<br/>
<br/>

