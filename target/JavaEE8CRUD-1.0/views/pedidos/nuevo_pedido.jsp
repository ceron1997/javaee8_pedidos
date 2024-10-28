<%-- 
    Document   : nuevo_pedido
    Created on : 08-21-2024, 06:11:47 AM
    Author     : ncero
--%>

<%@page import="javaee8crud.domain.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>

        <title>Crear Pedido</title>

        <style>
            .detalle-pedido {
                overflow: auto;
                background-color: #f8f9fa;
                padding: 15px;
                border-radius: 5px;
                height: 100%;
            }
            .lista-productos {
                background-color: #f1f3f5;
                padding: 15px;
                border-radius: 5px;
                height: 100%;
            }
            #listaProductos {
                height: 400px; /* Establece un alto fijo */
                overflow-y: auto;  /* Permite el scroll vertical */
                border: 1px solid #ccc; /* Opcional, para darle un borde */
                padding: 10px;    /* Espacio interno */
                background-color: #f9f9f9; /* Color de fondo */
            }
            .producto-cuadro {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 10px;
                margin-bottom: 10px;
                text-align: center;
                cursor: pointer;
            }
            .producto-cuadro:hover {
                background-color: #e2e6ea;
            }
        </style>
    </head>
    <body>
        <%@ include file="/views/plantilla_bootstrap/layout.jsp" %>

        <div class="container mt-4">
            <!-- Área Superior -->
            <div class="row mb-4">
                <div class="col-md-4">
                    <label for="codigoCliente">Código del Cliente:</label>
                    <input type="text" id="codigoCliente" disabled  class="form-control" placeholder="Codigo" readonly>
                </div>
                <div class="col-md-4">
                    <label for="nombreCliente">Nombre del Cliente:</label>
                    <input type="text" id="nombreCliente" disabled class="form-control" placeholder="Nombre" readonly>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <button class="btn btn-primary btn-block me-1" onclick="modalClienteShow()"><i class="bi bi-pin-angle-fill"></i> Seleccionar Cliente</button> 
                    <button class="btn btn-danger btn-block" onclick="quitarCliente()"> <i class="bi bi-trash"></i> Quitar Cliente</button>
                    <button class="btn btn-danger btn-block" onclick="limpiarDetalle()"> <i class="bi bi-trash"></i> Limpiar Detalle</button>

                </div>
            </div>

            <div class="row">
                <!-- Área Inferior Izquierda (Detalle del Pedido) -->
                <div class="col-md-8">
                    <div class="detalle-pedido">
                        <h5>Detalle del Pedido</h5>
                        <div id="detalleProductos">
                            <!-- Aquí se mostrará el detalle de los productos seleccionados -->
                            <%
                                // Obtener el carrito de la sesión
                                List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
                            %>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>
                                            Cantidad
                                        </th>
                                        <th>
                                            Producto
                                        </th>
                                        <th>
                                            Precio
                                        </th>
                                        <th>
                                            Total
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%   if (carrito != null && !carrito.isEmpty()) {
                                            for (Producto producto : carrito) {%>
                                    <tr>

                                        <td><%= producto.getCantidad()%></td>                    
                                        <td><%= producto.getNombre()%></td>
                                        <td><%= producto.getPrecio()%></td>    
                                        <td><%= producto.getTotal()%></td>

                                        <% }
                                            }%>
                                    </tr>


                                </tbody>
                            </table>
                        </div>








                    </div>
                </div>

                <!-- Área Inferior Derecha (Listado y Búsqueda de Productos) -->
                <div class="col-md-4">
                    <div class="lista-productos">
                        <h5>Buscar Producto</h5>
                        <input type="text" id="buscarProducto" class="form-control mb-3"
                               placeholder="Buscar producto..." onkeyup="buscarProducto()">
                        <div id="listaProductos">
                            <!-- Aquí se cargarán los productos en forma de cuadritos -->
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--modalCliente--> 
        <div class="modal fade" id="modalCliente" tabindex="-1" aria-labelledby="largeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="largeModalLabel">Seleccionar Cliente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <input type="text" id="buscarCliente" class="form-control" placeholder="Buscar cliente..." aria-label="Buscar cliente">
                            <button class="btn btn-outline-secondary" type="button" onclick="buscarCliente()">
                                <i class="bi bi-search"></i> 
                            </button>
                        </div>
                        <!--Tabla para mostrar los clientes--> 
                        <table id="tablaClientes" class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>Teléfono</th>
                                    <th>Acciones</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Aquí se insertarán las filas de clientes -->
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!--fin modalCliente--> 

        <%@ include file="/views/plantilla_bootstrap/layout_footer.jsp" %>
    </body>
</html>


<script src="${pageContext.request.contextPath}/views/js/pedido.js?v=${System.currentTimeMillis()}"></script>

<script>
                                var contextPath = '${pageContext.request.contextPath}';

                                buscarProducto();


</script>
