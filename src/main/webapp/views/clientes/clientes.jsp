<%-- 
    Document   : clientes
    Created on : 08-17-2024, 06:03:19 AM
    Author     : ncero
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>clientes</title>
    </head>
    <body>
        <%@ include file="/views/plantilla_bootstrap/layout.jsp" %>
        <div class="container">
            <br>
            <h3>Administrador de Clientes</h3>

            <div class="d-flex justify-content-end">
                <a href="nuevo_cliente.jsp" class="btn btn-success">Agregar Cliente</a>
            </div>
            <br>
            <table class="table table-striped table-bordered table-hover shadow-sm">
                <thead class="table-light">
                    <tr>
                        <th class="">ID</th>
                        <th class="">NOMBRE</th>
                        <th class="">DIRECCION</th>  
                        <th class="">TELEFONO</th>
                        <th class="">EMAIL</th>
                        <th class="">FECHA REGISTRO</th>
                        <th class="">TIPO CLIENTE</th>
                        <th style="width: 20%"  class="text-center">Acción</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${clientesBean.clientes}" var="cliente">
                    <tr>
                        <td>${cliente.id}</td>
                        <td>${cliente.nombre}</td>                      
                        <td>${cliente.direccion}</td>
                        <td>${cliente.telefono}</td>
                        <td>${cliente.email}</td>
                        <td>${cliente.fechaRegistro}</td>
                        <td>${cliente.tipoCliente.descripcion}</td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/clientes?id=${cliente.id}" class="btn btn-primary btn-sm">Editar</a>
                            <button class="btn btn-danger btn-sm" onclick="eliminarCliente(${cliente.id})">Eliminar</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>

        </div>
        <%@ include file="/views/plantilla_bootstrap/layout_footer.jsp" %>
    </body>
</html>

<script src="${pageContext.request.contextPath}/views/js/cliente.js"></script>

<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>