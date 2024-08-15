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
