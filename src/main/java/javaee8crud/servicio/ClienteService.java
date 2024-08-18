/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaee8crud.servicio;

import java.util.List;
import javaee8crud.datos.ClienteDAO;
import javaee8crud.domain.Cliente;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ncero
 */
@Stateless
public class ClienteService {

    @Inject
    private ClienteDAO clienteDAO;

    public List<Cliente> listarClientes() {
        return clienteDAO.obtenerClientes();
    }

    public void registrarCliente(Cliente cl) {
        clienteDAO.insertarCliente(cl);
    }

    public Cliente findProductoById(int id) {
        return clienteDAO.findProductoById(id);

    }

    public void updateProducto(Cliente producto) {
        clienteDAO.updateProducto(producto);
    }
    
        // Método para eliminar un producto
    public boolean eliminarCliente(Cliente cliente) {

        clienteDAO.deleteCliente(cliente);
        return true;
    }

}
