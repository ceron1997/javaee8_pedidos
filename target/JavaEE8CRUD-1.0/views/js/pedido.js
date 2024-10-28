

function modalClienteShow() {
    // Lógica para seleccionar cliente
    $('#modalCliente').modal('show');
    buscarCliente();
}

function agregarProducto(producto, producto_nombre, precio) {
    // Lógica para agregar el producto al detalle del pedido
//    $("#detalleProductos").append('<p>' + producto_nombre + '</p>');

    let param = {
        action: "agregarProducto",
        producto,
        producto_nombre, 
        precio
    };

    $.ajax({
        url: contextPath + '/productosData',
        method: 'GET',
        data: param,
        success: function (response) {
            $('#detalleProductos').html(response);

        }
    });
}


function buscarCliente() {
    let query = $('#buscarCliente').val();

    $.ajax({
        url: contextPath + '/clientes',
        method: 'GET',
        data: {
            search: query,
            action: "busqueda"
        },
        success: function (response) {
            let clientes = response;
            actualizarTabla(clientes);
        }
    });
}


function actualizarTabla(clientes) {
    let tabla = $('#tablaClientes tbody');
    tabla.html('');  // Limpiar la tabla antes de insertar nuevos resultados

    // Verificar si DataTable está inicializado
    if ($.fn.DataTable.isDataTable('#tablaClientes')) {
        $('#tablaClientes').DataTable().clear().destroy();
    }

    if (clientes.mensaje) {  // Si hay un mensaje, muestra una fila con el mensaje
        tabla.append(`<tr><td colspan="5">${clientes.mensaje}</td></tr>`);
        return;
    }

    // Iterar sobre el array de clientes y agregar filas a la tabla
    clientes.forEach(cliente => {
        tabla.append(`
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nombre}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefono}</td>
                <td>
                    <button class="btn btn-primary btn-sm" 
                            onclick="seleccionarCliente('${cliente.id}', '${cliente.nombre}')">
                      <i class="bi bi-check-circle-fill mr-1"></i> Seleccionar
                    </button>
                </td>
            </tr>
        `);
    });

    // Inicializar DataTable después de agregar las filas
    $('#tablaClientes').DataTable({
        searching: false,
        pageLength: 5,
        destroy: true, // Permitir la destrucción para reiniciar DataTable
        language: {
            emptyTable: "No hay datos disponibles en la tabla"
        }
    });
}

function seleccionarCliente(id, nombre) {
    $('#codigoCliente').val(id);
    $('#nombreCliente').val(nombre);
    $('#modalCliente').modal('hide');

}

function quitarCliente() {
    $('#codigoCliente').val('');
    $('#nombreCliente').val('');
}

function buscarProducto() {
    let query = $('#buscarProducto').val(); // Obtener el valor del campo de búsqueda

    $.ajax({
        url: contextPath + '/productosData', // URL del servlet que devuelve los productos
        method: 'GET',
        data: {
            query: query, // Enviar el término de búsqueda al servidor
            action: "busqueda"
        },
        success: function (response) {

//            alert('hola mundo');
//            console.log(response);
            let productos = response; // Recibir la respuesta en formato JSON

            // Limpiar el listado de productos
            $('#listaProductos').empty();

            // Recorrer los productos y agregarlos al listado
            productos.forEach(function (producto) {

                console.log(producto);
                var productoDiv = $('<div></div>')
                        .addClass('producto-cuadro')
                        .text(producto.nombre)
                        .on('click', function () {
                            agregarProducto(producto.id, producto.nombre, producto.precio);
                        });

                $('#listaProductos').append(productoDiv);
            });
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener los productos:', error);
        }
    });
}


function limpiarDetalle() {

    let param = {
        action: "limpiarDetalle"
    };

    $.ajax({
        url: contextPath + '/productosData',
        method: 'POST',
        data: param,
        success: function (response) {
            $('#detalleProductos').html('');

        }
    });
}