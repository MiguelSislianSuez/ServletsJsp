<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_ES"/>
<!--dejaremos 3 columnas a la derecha-->
<section id="clientes">
    <div class="container">
        <div class="row">
            <!--dejaremos 3 columnas a la derecha para añadir el toral de saldo al numero de clientes-->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th> <!--para id-->
                                <th>Nombre</th><!--concatenaremos en esta colmumna nombre y apellido-->
                                <th>Saldo</th>
                                <!--dejamos una col vacia-->
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--en el scope request podremos acceder a "clientes" para iterarlos-->
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <!-- por cada cliente de la lista clientes, mostramos los valores de sus propiedades-->

                                    <td>${status.count}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <td><fmt:formatNumber value="${cliente.saldo}" type="currency" currencySymbol="$" /></td>
                                    <td>
                                        <!--añadimos un enlace que funcionará como un boton para llamar a nuestro servlet mandandole por
                                        parametro que accion hacer(accion-editor)sobre que cliente (idCliente - $(cliente.idCliente)-->
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right">
                                                
                                            </i>Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--Inicio tarjetas para los totales-->
            <div class="col-md-3">

                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${saldoTotal}" type="currency" currencySymbol="$"/>
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>

            </div>
            <!--Fin Tarjetas-->
        </div>
    </div>
</section>

<!--Agregar cliente ventana MODAL-->
<jsp:include page="/WEB-INF/paginas/Cliente/AgregarClientes.jsp"/>