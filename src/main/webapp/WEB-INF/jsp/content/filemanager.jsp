
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<ol class="breadcrumb">
    <li><a href="<%=request.getContextPath().toString()%>"><span class="glyphicon glyphicon-hdd"></span></a></li>
            <c:forEach var="nav" items="${navegador}" >
        <li ><a href="#"  onclick="navegacionBreadCrum(${ubicado})"  title=" ${ubicado} - ${nav}"  >${nav}</a></li>
        </c:forEach>

</ol>
<!--<nav>
    <ul class="pager">
        <li><a href="#">Previous</a></li>
    </ul>
</nav>-->
<input type="hidden" id="ubicado" value="${ubicado}">


<table class="table table-responsive  table-hover table-striped" >
    <thead>
        <tr>
            <th>Dir/File</th>
            <th>Nombre</th>
            <!--<th>Ruta</th>-->
            <!--<th>Parent</th>-->
            <th>Descargar</th>
            <th>Borrar</th>
        </tr>

    </thead>
    <tbody>
        <c:forEach  var="r" items="${root}"  >
            <c:if test="${!r.isHidden()}">
                <tr  style="cursor: pointer"  >
                    <c:choose>
                        <c:when test="${r.isDirectory()}">
                            <td>
                                <a type="button" class="btn btn-default btn-lg btn-info items"  title="${r.path}">
                                    <span class="glyphicon glyphicon-folder-open "   aria-hidden="true" ></span>
                                    <span class="badge ">${fn:length(r.list())}</span> 
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td class=""> 
                                <a type="button" class="btn btn-default btn-lg btn-warning items" class="items" title="${r.path}">
                                    <span class="glyphicon glyphicon-file  " aria-hidden="true"></span>
                                </a>
                            </td>
                        </c:otherwise>
                    </c:choose>

                    <td class="text-primary items" title="${r.path}">
                        <h3>
                            <span class="label label-info ">${r.name}</span>   

                        </h3>

                    </td>
                    <!--<td>${r.path}</td>-->
                    <!--<td>${r.parent}</td>-->

                    <td>
                        <c:if test="${!r.isDirectory()}">
                            <a type="button" class="btn btn-default btn-lg btn-warning" target="_blank" href="/filemanager/download?file_name=${r.path}" >
                                <span class="glyphicon glyphicon-download" aria-hidden="true"></span> 
                            </a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${!r.isDirectory()}">
                            <button type="button" class="btn btn-default btn-lg btn-danger">
                                <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> 
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:if>
        </c:forEach> 
    </tbody>
    <c:if test="${empty root}"><h3 class="alert alert-warning">No se encontraron archivos</h3></c:if>

    <c:if test="${!empty img}">
        <div id="imgprev" class="well" style="display: none" >
                ${img}
            <!--<br/>-->
            <!--<span class="label label-info">${tipo} </span>-->
            <br/>
            <br/>
            <button class="btn btn-danger" onclick="salirAnimationImg()"><span class="glyphicon glyphicon-remove" title="cerrar" aria-hidden="true">  </span></button>
        </div>
    </c:if>
</table>

