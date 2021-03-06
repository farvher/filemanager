
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<ol class="breadcrumb">
	<li><a href="/"><span
			class="glyphicon glyphicon-hdd" title="Directorio principal"></span></a></li>
	<c:forEach var="nav" items="${navegador}">
		<c:set
			value="${fn:substring(ubicado, 0, (fn:indexOf(ubicado, nav)) + fn:length(nav))}"
			var="ubi" />
		<li><a href="#" onclick="navegacionBreadCrum('${ubi}')"
			title="${ubi}">${nav}</a></li>
	</c:forEach>
	<li id="nameNewFolder">
		<button class="btn btn-default btn-sm" id="newFolder" onclick="newFolder()">
			<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
		</button>
	</li>
</ol>

<input type="hidden" id="ubicado" value="${ubicado}">


<c:if test="${!empty img}">
	<div id="imgprev" class="well" style="display: none">
		${img}
		<!--<br/>-->
		<!--<span class="label label-info">${tipo} </span>-->
		<br /> <br />
		<button class="btn btn-danger" onclick="salirAnimationImg()">
			<span class="glyphicon glyphicon-remove" title="cerrar"
				aria-hidden="true"> </span>
		</button>
	</div>
</c:if>


<c:forEach var="r" items="${root}" varStatus="i">
	<c:if test="${!r.isHidden()}">
		<div class="row">

			<!--icono de carpeta o archivo-->
			<div class="panel panel-default ">
				<div class="panel-heading">
					<div class="">
						<c:choose>
							<c:when test="${r.isDirectory()}">
								<a type="button" class="btn btn-default btn-sm  items"
									title="${r.path}"> <span
									class="glyphicon glyphicon-folder-open " aria-hidden="true"></span>
									<span class="badge ">${fn:length(r.list())}</span>
								</a>
							</c:when>
							<c:otherwise>
								<a type="button" class="btn btn-default btn-sm  items"
									class="items" title="${r.path}"> <span
									class="glyphicon glyphicon-file  " aria-hidden="true"></span>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!--fin de iconos-->
				<!--panel detalle de resultado-->
				<div class="panel-body">
					<div class="text-justify items" title="${r.path}"
						style="cursor: pointer">
						<h4>
							${r.name} <br />
							<%-- 							<small> ${r.path}</small> --%>
						</h4>
					</div>
					<c:if test="${!r.isDirectory()}">
						<a type="button" class="btn  btn-xs btn-success " target="_blank"
							href="/download?file_name=${r.path}"> <span
							class="glyphicon glyphicon-download" aria-hidden="true"></span>
						</a>
					</c:if>
					<c:if test="${!r.isDirectory()}">
						<a type="button" class="btn  btn-xs btn-danger "
							href="/delete?ruta=${r.path}"> <span
							class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
						</a>
					</c:if>
				</div>
				<!--fin de panel-->
				<div class="panel-footer">
					<span class="glyphicon glyphicon-th "> </span> ${i.index+1} de
					${cantidad} ${r.isHidden() ? "<br/><span class='label label-warning'>Oculto<span>":""}
				</div>

			</div>
		</div>
	</c:if>

</c:forEach>
<c:if test="${empty root}">
	<h3 class="alert alert-warning">No se encontraron archivos</h3>
</c:if>



