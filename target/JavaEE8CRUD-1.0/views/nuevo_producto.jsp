<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>${producto.id == null ? 'Nuevo Producto' : 'Editar Producto'}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/views/plantilla_bootstrap/layout.jsp" %>
        <div class="container">
            <div class="d-flex justify-content-end">
                <a href="${pageContext.request.contextPath}/views/productos.jsp" class="btn btn-secondary">Regresar</a>
            </div>

            <h2>${producto.id == null ? 'Agregar Nuevo Producto' : 'Editar Producto'}</h2>
            <div id="resultado"></div>

            <form id="productoForm" action="${pageContext.request.contextPath}/productos_save" method="post">
                <!-- Campo oculto para el ID del producto -->
                <input type="hidden" id="id" name="id" value="${producto.id}">

                <div class="form-group">
                    <label for="nombre">Nombre del Producto:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${producto.nombre}" required>
                </div>
                <div class="form-group">
                    <label for="precio">Precio:</label>
                    <input type="number" step=".01" class="form-control" id="precio" name="precio" value="${producto.precio}" required>
                </div>

                <button type="button" class="btn btn-primary" onclick="enviarFormulario()">
                    ${producto.id == null ? 'Guardar' : 'Actualizar'}
                </button>
            </form>
        </div>
        <%@ include file="/views/plantilla_bootstrap/layout_footer.jsp" %>
    </body>
</html>


<script src="${pageContext.request.contextPath}/views/js/producto.js"></script>
