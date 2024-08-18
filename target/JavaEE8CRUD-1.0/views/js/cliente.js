/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */



function enviarFormulario() {
    let form = $('#clienteForm');

    // si un campo del formulario required del formulario esta vacio detener la ejecucion
    let id = $('#id').val().trim();


    // Validación: Verifica si los campos obligatorios están llenos
    // Array de campos a validar
    let camposAValidar = [
        {campo: $('#nombre'), mensaje: 'Por favor, ingrese su nombre.'},
        {campo: $('#direccion'), mensaje: 'Por favor, ingrese su dirección.'},
        {campo: $('#telefono'), mensaje: 'Por favor, ingrese su número de teléfono.'},
        {campo: $('#email'), mensaje: 'Por favor, ingrese su correo electrónico.'},
        {campo: $('#fechaRegistro'), mensaje: 'Por favor, ingrese la fecha de registro.'},
        {campo: $('#tipoCliente'), mensaje: 'Por favor, seleccione el tipo de cliente.'}
    ];


    if (!validaciones(camposAValidar)) {
        return;
    }

//    console.log(form.serialize());
    $.ajax({
        type: 'POST',
        url: form.attr('action'),
        data: form.serialize(), // Serializa los datos del formulario
        success: function (response) {
            console.log(response);
            // Maneja la respuesta del servidor
            $('#resultado').html('<div class="alert alert-success">' + response.message + '</div>');
            if (!id) {
                limpiarCliente();
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

function limpiarCliente() {
    // Limpiar las cajas de texto
    $('#nombre').val('');
    $('#direccion').val('');
    $('#telefono').val('');
    $('#email').val('');

    // Establecer la fecha al día de hoy en formato dd/mm/yyyy
    let hoy = new Date();
    let dia = String(hoy.getDate()).padStart(2, '0'); // Asegura que el día tenga 2 dígitos
    let mes = String(hoy.getMonth() + 1).padStart(2, '0'); // Los meses en JavaScript son de 0 a 11, por eso sumamos 1
    let año = hoy.getFullYear();
    let fechaFormateada = `${dia}/${mes}/${año}`;
    $('#fechaRegistro').val(fechaFormateada);

    // Seleccionar la primera opción en el campo "tipoCliente"
    $('#tipoCliente').val('1');
}

function eliminarCliente(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este registro?')) {
        $.ajax({
            type: 'POST',
            url: contextPath + '/clientes',
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