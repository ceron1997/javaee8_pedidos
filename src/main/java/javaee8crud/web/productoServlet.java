/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javaee8crud.web;

import java.io.IOException;
import java.io.PrintWriter;
import javaee8crud.domain.Producto;
import javaee8crud.servicio.ProductosService;
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
@WebServlet("/productos")
public class productoServlet extends HttpServlet {
    
    @Inject
    private ProductosService productosService;

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
            out.println("<title>Servlet productoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productoServlet at " + request.getContextPath() + "</h1>");
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
            Producto producto = productosService.findProductoById(productoId);
//            System.out.println(producto);
            request.setAttribute("producto", producto);
        } else {
            request.setAttribute("producto", new Producto()); // Para el caso de nuevo producto
        }
        
        request.getRequestDispatcher("/views/productos/nuevo_producto.jsp").forward(request, response);
        
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

    // metodos definidos 
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lógica para eliminar un producto
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int productoId = Integer.parseInt(id);
            Producto producto = productosService.findProductoById(productoId);
            
            productosService.eliminarProducto(producto);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Producto eliminado exitosamente.\"}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"ID de producto no válido.\"}");
        }
    }
    
    private void createOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String precioStr = request.getParameter("precio");
        double precio = Double.parseDouble(precioStr);
        
        Producto producto;
        
        if (id == null || id.isEmpty()) {
            // Creación de un nuevo producto
            producto = new Producto();
        } else {
            // Actualización de un producto existente
            int productoId = Integer.parseInt(id);
            producto = productosService.findProductoById(productoId);
        }
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        
        try {
            if ("".equals(id)) {
                productosService.registrarProducto(producto); // Guardar nuevo producto
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\",\"message\":\"Producto registrado exitosamente.\"}");
            } else {
                productosService.updateProducto(producto); // Actualizar producto existente
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\",\"message\":\"Producto actualizado exitosamente.\"}");
            }
        } catch (IOException e) {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Error al registrar o actualizar el producto.\"}");
        }
    }
    
}
