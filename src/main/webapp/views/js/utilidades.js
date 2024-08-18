/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

function indicadorRojo(elemento) {
    elemento.css('border', '2px solid red');

    setTimeout(() => {
        elemento.css('border', '');
    }, 2000);
}

function validaciones(campos) {
    for (let item of campos) {
        if (!item.campo.val()) {
            alert(item.mensaje);
            indicadorRojo(item.campo);
            return false; // Detiene la validación en cuanto un campo falla
        }
    }
    return true; // Todo bien si pasa todas las validaciones
}