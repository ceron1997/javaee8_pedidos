<%-- 
    Document   : nuevo_cliente
    Created on : 08-17-2024, 07:24:42 AM
    Author     : ncero
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/views/plantilla_bootstrap/layout.jsp" %>
        <div class="container">
            <div class="d-flex justify-content-end">
                <a href="${pageContext.request.contextPath}/views/clientes/clientes.jsp" class="btn btn-secondary">Regresar</a>
            </div>
            <h2>${producto.id == null ? 'Agregar Nuevo Cliente' : 'Editar Cliente'}</h2>
            <div id="resultado"></div>

            <form id="clienteForm" action="${pageContext.request.contextPath}/clientes" method="post">
                <!-- Campo oculto para el ID del producto -->
                <input type="hidden" id="id" name="id" value="${cliente.id}">

                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" required>
                </div>
                <div class="form-group">
                    <label for="direccion">Direccion</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.direccion}" required>
                </div>
                <div class="form-group">
                    <label for="telefono">Telefono:</label>
                    <input type="number" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" required>
                </div>
                <div class="form-group">
                    <label for="telefono">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" value="${cliente.email}" required>
                </div>

                <div class="form-group">
                    <label for="datepicker">Fecha de Registro</label>
                    <div class="input-group date">
                        <input type="text" id="fechaRegistro" class="form-control" name="fechaRegistro" value="${cliente.fechaRegistro}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="tipoCliente">Tipo de Cliente:</label>
                    <select id="tipoCliente" name="tipoCliente" class="form-control">
                        <c:forEach var="tipo" items="${tipoClienteBean.tiposCliente}">
                            <option value="${tipo.idTipo}">${tipo.descripcion}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="button" class="btn btn-primary" onclick="enviarFormulario()">
                    ${cliente.id == null ? 'Guardar' : 'Actualizar'}
                </button>
            </form>
        </div>
        <%@ include file="/views/plantilla_bootstrap/layout_footer.jsp" %>
    </body>
</html>
<script src="${pageContext.request.contextPath}/views/js/cliente.js"></script>
<script>
                                  $(document).ready(function () {
                                      $('#fechaRegistro').datepicker({
                                          format: 'dd/mm/yyyy',
                                          autoclose: true,
                                          todayHighlight: true
                                      }).datepicker('setDate', new Date());
                                  });



</script>