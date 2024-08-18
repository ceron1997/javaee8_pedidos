/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaee8crud.web;

import java.util.List;
import javaee8crud.domain.Cliente;
import javaee8crud.servicio.ClienteService;
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
public class ClientesBean {

    @Inject
    private ClienteService clienteService;


    private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        clientes = clienteService.listarClientes();
//        System.out.println(clientes);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
