package javaee8crud.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javaee8crud.datos.ProductosDAO;
import javaee8crud.domain.Producto;

@Stateless
public class ProductosService {

    @Inject
    private ProductosDAO productosDAO;

    public List<Producto> listarProductos() {
        return productosDAO.obtenerProductos();
    }

    public void registrarProducto(Producto prod) {
        productosDAO.insertarproducto(prod);
    }

    public Producto findProductoById(int id) {
        return productosDAO.findProductoById(id);

    }

    public void updateProducto(Producto producto) {
        productosDAO.updateProducto(producto);
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(Producto producto) {

        productosDAO.deletePersona(producto);
        return true;
    }

}
