
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<ol class="breadcrumb">
    <li><a href="<%=request.getContextPath().toString()%>"><span class="glyphicon glyphicon-hdd"></span></a></li>
            <c:forEach var="nav" items="${navegador}" >
                <c:set value="${fn:substring(ubicado, 0, (fn:indexOf(ubicado, nav)) + fn:length(nav))}" var="ubi"/>
        <li ><a href="#"  onclick="navegacionBreadCrum('${ubi}')"  title="${ubi}"  >${nav}</a></li>
        </c:forEach>

</ol>
<!--<nav>
    <ul class="pager">
        <li><a href="#">Previous</a></li>
    </ul>
</nav>-->
<input type="hidden" id="ubicado" value="${ubicado}">


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


<c:forEach  var="r" items="${root}"  >
    <c:if test="${!r.isHidden()}">
        <div class="well well-lg">
            <c:choose>
                <c:when test="${r.isDirectory()}">

                    <a type="button" class="btn btn-default btn-lg btn-info items"  title="${r.path}">
                        <span class="glyphicon glyphicon-folder-open "   aria-hidden="true" ></span>
                        <span class="badge ">${fn:length(r.list())}</span> 
                    </a>

                </c:when>
                <c:otherwise>
                    <a type="button" class="btn btn-default btn-lg btn-warning items" class="items" title="${r.path}">
                        <span class="glyphicon glyphicon-file  " aria-hidden="true"></span>
                    </a>

                </c:otherwise>
            </c:choose>
            <div class="panel panel-collapse">

                <div class="text-justify items " title="${r.path}" style="cursor: pointer">
                    <h2>
                        <span class="label label-info ">
                            ${r.name}
                        </span>
                    </h2>
                </div>
                <span class="label label-primary ">
                    ${r.path}
                </span>
            </div>
            <c:if test="${!r.isDirectory()}">
                <a type="button" class="btn btn-default btn-lg btn-success" target="_blank" href="/filemanager/download?file_name=${r.path}"  >
                    <span class="glyphicon glyphicon-download" aria-hidden="true"></span> 
                </a>
            </c:if>
            <c:if test="${!r.isDirectory()}">
                <a type="button" class="btn btn-default btn-lg btn-danger " >
                    <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> 
                </a>
            </c:if>

        </div>
    </c:if>

</c:forEach> 
<c:if test="${empty root}"><h3 class="alert alert-warning">No se encontraron archivos</h3></c:if>



