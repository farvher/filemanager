<%-- 
    Document   : menu
    Created on : 6/08/2015, 02:31:38 PM
    Author     : farvher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top  ">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand " href="/">
                <!--<img  class="img-rounded" src="<%=request.getContextPath().toString()%>/resources/img/icono.png" style="width: 80px"/>-->
          ShareFiler
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
<!--             <ul class="nav navbar-nav"> -->
<!--                 <li ><a href="#">Link </a></li> -->
<!--                 <li><a href="#">Link</a></li> -->
<!--             </ul> -->
            <form class="navbar-form navbar-left" role="search" onsubmit="return busquedaBasicaURL(document.getElementById('busqueda').value, document.getElementById('ubicado').value)" method="GET"  >
                <div class="form-group">
                    <input type="text" class="form-control " id="busqueda" name="busqueda" placeholder="Buscar ...">
                </div>
                <input type="submit" class="btn btn-default" value="Buscar">
                 <a class="btn  btn-default" id="falseinput"> <span class="glyphicon glyphicon-upload"></span></a>
            </form>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="btn btn-xm" > <span id="cargando"
						class="glyphicon glyphicon-refresh glyphicon-refresh-animate hidden">
					</span>
				</a></li>
				<li><a href="#" onclick="document.forms['logoutForm'].submit()"><span
						class="glyphicon glyphicon glyphicon-log-out">Salir</span></a></li>

			</ul>

		</div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->

</nav>

<!--Formulario escondido para enviar los archivos al controlador-->
<form method="post" id="cargar" action="<%=request.getContextPath().toString()%>/form" enctype="multipart/form-data">
    <input type="hidden" name="ruta" id="namefile" value="${ubicado}"/>
    <input type="file" id="fileinput" name="file" style="display: none"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<!--Formulario escondido para desloguearse  -->
 <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>

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
    
    .input-folder {
  border: 0;
  outline: 0;
  background: transparent;
  border-bottom: 1px solid black;
}
</style>



<br/>
<br/>
<br/>
  <jsp:include page="alerts.jsp"/>


