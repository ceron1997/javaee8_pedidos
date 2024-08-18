<%-- 
    Document   : productos
    Created on : 08-11-2024, 06:42:28 AM
    Author     : ncero
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
            <title>productos</title>
    </head>
    <body>
        <%@ include file="/views/plantilla_bootstrap/layout.jsp" %>
        <div class="container">
            <br>
            <h3>Administrador de Productos</h3>

            <div class="d-flex justify-content-end">
                <a href="nuevo_producto.jsp" class="btn btn-success">Agregar Nuevo Producto</a>
            </div>
            <br>
            <table class="table table-striped table-bordered table-hover shadow-sm">
                <thead class="table-light">
                    <tr>
                        <th class="">ID</th>
                        <th class="">Nombre</th>
                        <th class="">Precio</th>
                        <th style="width: 20%"  class="text-center">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productosBean.productos}" var="producto">
                        <tr>
                            <td>${producto.id}</td>
                            <td>${producto.nombre}</td>
                            <td class="">${producto.precio}</td>

                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/productos?id=${producto.id}" class="btn btn-primary btn-sm">Editar</a>
                                <button class="btn btn-danger btn-sm" onclick="eliminarProducto(${producto.id})">Eliminar</button>
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

<script src="${pageContext.request.contextPath}/views/js/producto.js"></script>

<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

















