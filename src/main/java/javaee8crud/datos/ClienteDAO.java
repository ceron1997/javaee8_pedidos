/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaee8crud.datos;

import java.util.List;
import javaee8crud.domain.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ncero
 */
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> obtenerClientes() {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }

    public void insertarCliente(Cliente cl) {
        em.persist(cl);
    }

    public Cliente findProductoById(int id) {
        return em.find(Cliente.class, id);
    }

    public void updateProducto(Cliente cl) {
        em.merge(cl);
    }

    public void deleteCliente(Cliente cliente) {
        em.remove(em.merge(cliente)); //Primero se tiene que actualizar el estado del objeto antes de borrarlo
    }
}
