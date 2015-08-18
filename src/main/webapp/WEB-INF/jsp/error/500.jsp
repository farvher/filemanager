<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FIleManager</title>

        <!-- Bootstrap -->
        <link href="<%=request.getContextPath().toString() %>/resources/css/bootstrap.min.css" rel="stylesheet">
        
      
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <div class="container">
         
            
            <div id="manejador">
                <h1 class="alert-danger">Error 404</h1>
                <h2>Archivo NO encontrado</h2>
                <div class="well ">
                    ${error}
                    
                </div> 
                
                <button class="btn btn-danger" onclick="javascript : window.location.href='/FIleManager/'">Volver al Inicio</button>
                
                
            </div>

           



        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<%=request.getContextPath().toString() %>/resources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath().toString() %>/resources/js/listeners.js" rel="stylesheet"></script>
        <script src="<%=request.getContextPath().toString() %>/resources/js/ajax.js" rel="stylesheet"></script>

    </body>
</html>