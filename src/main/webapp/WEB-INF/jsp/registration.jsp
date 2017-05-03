<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href="${contextPath}/resources/img/favicon.ico" />
<title>ShareFiler</title>

<!-- Bootstrap -->
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/custom.css" rel="stylesheet">
<link
	href="//code.jquery.com/ui/1.11.4/themes/ui-darkness/jquery-ui.css"
	rel="stylesheet">
 <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>
<body>
	<div class="container">

		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<h2 class="form-signin-heading">Create your account</h2>
			<spring:bind path="userName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="userName" class="form-control"
						placeholder="Username" autofocus="true"></form:input>
					<form:errors path="userName"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="pass">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="pass" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="pass"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="passwordConfirm"
						class="form-control" placeholder="Confirm your password"></form:input>
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>


	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/listeners.js"></script>
	<script src="${contextPath}/resources/js/ajax.js"></script>
	<script src="${contextPath}/resources/js/jqueryui/jquery-ui.js"></script>

</body>
</html>