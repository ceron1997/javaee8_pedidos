/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaee8crud.datos;

import java.util.List;
import javaee8crud.domain.TipoCliente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ncero
 */
public class TipoClienteDAO {
     @PersistenceContext
    private EntityManager em;
     public List<TipoCliente> obtenerTipoCliente() {
        TypedQuery<TipoCliente> query = em.createQuery("SELECT t FROM TipoCliente t", TipoCliente.class);
        return query.getResultList();
    }
     
       public TipoCliente findProductoById(int id) {
        return em.find(TipoCliente.class, id);
    }
}
