    <section id="actions" class="py-4 mb-4 bg-lights">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a href="#" class="btn btn-primary btn-block">
                          
                            <i class="fas fa-plus"></i>
                            Regresar al inicio
                        </a>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-success btn-block">
                            <i class="fas fa-check"></i>
                            Guardar cliente
                        </button>
                    </div>
                    <div class="col-md-3">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}"
                           class="btn btn-danger btn-block">
                            <i class="fas fa-check"></i>
                            Eliminar cliente
                        </a>
                    </div>
                </div>
                
            </div>
        </section>