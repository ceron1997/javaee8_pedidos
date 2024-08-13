package javaee8crud.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javaee8crud.domain.Producto;
import javaee8crud.servicio.ProductosService;


@Named
@RequestScoped
public class ProductosBean {

    @Inject
    private ProductosService productosService;

    private List<Producto> productos;

    @PostConstruct
    public void init() {
        productos = productosService.listarProductos();
        System.out.println(productos);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    private Producto producto = new Producto();

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void registrarProducto() {
        this.productosService.registrarProducto(producto);
    }
    
}
