/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javaee8crud.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaee8crud.domain.Cliente;
import javaee8crud.domain.TipoCliente;
import javaee8crud.servicio.ClienteService;
import javaee8crud.servicio.TipoClienteService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ncero
 */
@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    @Inject
    private ClienteService clienteService;
    @Inject
    private TipoClienteService tipoClienteService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            int productoId = Integer.parseInt(id);
            Cliente producto = clienteService.findProductoById(productoId);
//            System.out.println(producto);
            request.setAttribute("cliente", producto);
        } else {
            request.setAttribute("cliente", new Cliente()); // Para el caso de nuevo producto
        }

        request.getRequestDispatcher("/views/clientes/nuevo_cliente.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            delete(request, response);
        } else {
            createOrUpdate(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lógica para eliminar un producto
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int clienteId = Integer.parseInt(id);
            Cliente cliente = clienteService.findProductoById(clienteId);

            clienteService.eliminarCliente(cliente);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Producto eliminado exitosamente.\"}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"ID de producto no válido.\"}");
        }

    }

    private void createOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Recupera los datos del formulario
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaRegistroStr = request.getParameter("fechaRegistro");
        String tipoClienteId_str = request.getParameter("tipoCliente");

        int tipoClienteId = Integer.parseInt(tipoClienteId_str);

        // Crea un nuevo cliente o lo encuentro
        Cliente cliente;

        if (id == null || id.isEmpty()) {
            // Creación de un nuevo cliente
            cliente = new Cliente();
        } else {
            // Actualización de un cliente existente
            int clienteId = Integer.parseInt(id);
            cliente = clienteService.findProductoById(clienteId);
        }

        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaRegistro = new Date();
        try {
            fechaRegistro = formato.parse(fechaRegistroStr);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.setFechaRegistro(fechaRegistro);
        TipoCliente tipoClienteObj = tipoClienteService.findProductoById(tipoClienteId);
        cliente.setTipoCliente(tipoClienteObj);

        try {
            if ("".equals(id)) {
//                System.out.println("guardando");
                clienteService.registrarCliente(cliente); // Guardar nuevo producto
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\",\"message\":\"Cliente registrado exitosamente.\"}");
            } else {
//                System.out.println("actualizando");
                clienteService.updateProducto(cliente); // Actualizar producto existente
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\",\"message\":\"Cliente actualizado exitosamente.\"}");
            }
        } catch (IOException e) {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Error al registrar o actualizar el Cliente.\"}");
        }

    }

}
