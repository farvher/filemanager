<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="${contextPath}/resources/img/favicon.ico"/>
        <title>ShareFiler</title>

        <!-- Bootstrap -->
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/custom.css" rel="stylesheet">
        <link href="//code.jquery.com/ui/1.11.4/themes/ui-darkness/jquery-ui.css" rel="stylesheet">
	
		<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
	

    </head>
    <body>
        <div class="container">
            <header>
                <jsp:include page="components/menu.jsp"/>
            </header>
            <div id="manejador">
                <jsp:include page="content/filemanager.jsp"/>
            </div>
            <footer class="">
                <jsp:include page="components/footer.jsp"/>
            </footer>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${contextPath}/resources/js/listeners.js" ></script>
        <script src="${contextPath}/resources/js/ajax.js"></script>
        <script src="${contextPath}/resources/js/jqueryui/jquery-ui.js" ></script>

    </body>
</html>