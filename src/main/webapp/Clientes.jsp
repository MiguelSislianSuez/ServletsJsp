<!--como vamos aiterear aen la lista de clientes para pintarlos, usaremos JSTL-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_ES"/>


<%-- 
    Document   : Clientes
    Created on : 25 feb. 2021, 9:20:10
    Author     : Kentucky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <title>Control de clientes</title>
    </head>
    <body>
        
        <!--botstrap-->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" 
        integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" 
        integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>


        <!-- iteramos elementos de la lista. llamamos con en include-->

        <jsp:include page ="WEB-INF/paginas/comunes/Encabezado.jsp"/>
        <jsp:include page ="WEB-INF/paginas/comunes/BotonesDeNavegacion.jsp"/>

        <jsp:include page ="WEB-INF/paginas/Cliente/ListadoDeClientes.jsp"/>
        <jsp:include page ="WEB-INF/paginas/comunes/Footer.jsp"/>
        
        

    </body>
</html>
