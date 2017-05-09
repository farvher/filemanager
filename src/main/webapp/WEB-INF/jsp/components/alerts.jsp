<%-- 
    Document   : header
    Created on : 6/08/2015, 02:33:13 PM
    Author     : farvher
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${!empty success}">
		<div class="alert alert-success" role="alert">${success}</div>
	</c:when>
	<c:when test="${!empty info}">
		<div class="alert alert-info" role="alert">${info}</div>
	</c:when>
	<c:when test="${!empty warning}">
		<div class="alert alert-warning" role="alert">${warning}</div>
	</c:when>
	<c:when test="${!empty danger}">
		<div class="alert alert-danger" role="alert">${danger}</div>
	</c:when>
</c:choose>





