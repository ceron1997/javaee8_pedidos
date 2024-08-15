/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

function enviarFormulario() {
    let form = $('#productoForm');

    // si un campo del formulario required del formulario esta vacio detener la ejecucion
    let id = $('#id').val().trim();
    let nombre = $('#nombre').val().trim();
    let precio = $('#precio').val().trim();

    // Validación: Verifica si los campos obligatorios están llenos

    if (!nombre || !precio) {
        alert('Por favor, complete todos los campos obligatorios.');
        if (!nombre) {
            indicadorRojo($('#nombre'));
        }
        if (!precio) {
            indicadorRojo($('#precio'));
        }


        return; // Detiene la ejecución si algún campo está vacío
    }


    $.ajax({
        type: 'POST',
        url: form.attr('action'),
        data: form.serialize(), // Serializa los datos del formulario
        success: function (response) {


            // Maneja la respuesta del servidor
            $('#resultado').html('<div class="alert alert-success">' + response.message + '</div>');
//            alert(id);
            if (id === null) {
//                alert('hey');
                limpiarProducto();
            }

            setTimeout(() => {
                $('#resultado').html('');
            }, 2000);
        },
        error: function (xhr, status, error) {
            // Maneja los errores
            $('#resultado').html('<div class="alert alert-danger">Hubo un error en la transaccion.</div>');
            setTimeout(() => {
                $('#resultado').html('');
            }, 2000);
        }
    });
}

function limpiarProducto() {
    $('#nombre').val('');
    $('#precio').val('');
}



function eliminarProducto(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
        $.ajax({
            type: 'POST',
            url: contextPath + '/productos',
            data: { id: id, 
            action: "delete"},
            success: function(response) {
                // Manejar la respuesta del servidor
                if (response.status === 'success') {
                    alert('Producto eliminado exitosamente.');
                    // Recargar la lista de productos o eliminar la fila correspondiente
                    location.reload(); // o una función para eliminar la fila específica
                } else {
                    alert('Error al eliminar el producto.');
                }
            },
            error: function() {
                alert('Error en la solicitud de eliminación.');
            }
        });
    }
}



