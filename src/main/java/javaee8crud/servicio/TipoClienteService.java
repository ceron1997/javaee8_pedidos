/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaee8crud.servicio;

import java.util.List;
import javaee8crud.datos.TipoClienteDAO;
import javaee8crud.domain.TipoCliente;
import javax.inject.Inject;

/**
 *
 * @author ncero
 */
public class TipoClienteService {
     @Inject
     private TipoClienteDAO tipoClienteDAO; 
     
       public List<TipoCliente> obtenerTiposCliente() {
        return tipoClienteDAO.obtenerTipoCliente();
    }
       
       
          public TipoCliente findProductoById(int id) {
        return tipoClienteDAO.findProductoById(id);

    }

}
