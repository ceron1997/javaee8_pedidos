package javaee8crud.web;

import java.util.List;
import javaee8crud.domain.TipoCliente;
import javaee8crud.servicio.TipoClienteService;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ncero
 */
@Named
@RequestScoped
public class TipoClienteBean {
    
    @Inject
    private TipoClienteService tipoClienteService; 
    
    private List<TipoCliente> tiposCliente;
    
    @PostConstruct
    public void init() {
//        System.out.println("Iniciando TipoClienteBean...");
        tiposCliente = tipoClienteService.obtenerTiposCliente();
    }

    // Getters y setters
    public List<TipoCliente> getTiposCliente() {
        return tiposCliente;
    }
    
      

    

}
