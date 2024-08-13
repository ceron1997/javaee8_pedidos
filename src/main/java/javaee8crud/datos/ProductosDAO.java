package javaee8crud.datos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javaee8crud.domain.Producto;

public class ProductosDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Producto> obtenerProductos() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    public void insertarproducto(Producto prod) {
        em.persist(prod);
    }

    public Producto findProductoById(int id) {
        return em.find(Producto.class, id);
    }

    // Método para actualizar un producto
    public void updateProducto(Producto producto) {
        // El método merge() sincroniza el estado del producto con la base de datos.
        em.merge(producto);
    }
    
      public void deletePersona(Producto producto) {
        em.remove(em.merge(producto)); //Primero se tiene que actualizar el estado del objeto antes de borrarlo
    }
}
