/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javaee8crud.web;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javaee8crud.domain.Producto;
import javaee8crud.servicio.ProductosService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ncero
 */
@WebServlet("/productosData")
public class productosDataServlet extends HttpServlet {

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
            out.println("<title>Servlet productosDataServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productosDataServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if ("busqueda".equals(action)) {
            listarProductosLike(request, response);
        }

        if ("agregarProducto".equals(action)) {
            agregarProducto(request, response);
        }

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
        if ("guardarPedido".equals(action)) {
            guardarPedido(request, response);
        }
        if ("limpiarDetalle".equals(action)) {
            limpiarDetalle(request, response);
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

    private void listarProductosLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.getWriter().write("Este mensaje será visible en el navegador");

        String query = request.getParameter("query");
        List<Producto> productos = productosService.listarProductosLike(query);

        // Verificar si la lista de productos está vacía
        String json;
        if (productos.isEmpty()) {
            json = "{\"mensaje\": \"sin resultados\"}";
        } else {
            // Convertir la lista de productos a JSON
            json = new Gson().toJson(productos);
        }

        // Configurar la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        String productoStr = request.getParameter("producto");
        int productoid = Integer.parseInt(productoStr);
        String producto_nombre = request.getParameter("producto_nombre");
        String precioStr = request.getParameter("precio");
        Double precio = 0.00;
        if (precioStr != null && !precioStr.isEmpty()) {
            precio = Double.valueOf(precioStr.trim());
        }
        int cantidad = 1;
        double total = cantidad * precio;

        // Crear y agregar el producto al carrito si el ID del producto es válido
        if (productoStr != null && !productoStr.trim().isEmpty()) {
            Producto producto = new Producto(productoid, producto_nombre, precio, cantidad, total);
            carrito.add(producto);
        }

// Guardar el carrito actualizado en la sesión
        session.setAttribute("carrito", carrito);

        session.setAttribute("carrito", carrito);

        // Devolver el carrito actualizado en formato HTML parcial
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(" <table class=\"table\">");
        out.println(" <thead>"
                + "                                    <tr>"
                + "                                        <th>"
                + "                                            Cantidad"
                + "                                        </th>"
                + "                                        <th>"
                + "                                            Producto"
                + "                                        </th>"
                + "                                        <th>"
                + "                                            Precio"
                + "                                        </th>"
                + "                                        <th>"
                + "                                            Total"
                + "                                        </th>"
                + "                                    </tr>"
                + " </thead>");
        out.println("");
        out.println("");

        for (Producto prod : carrito) {
            out.println("<tr>");
            out.println("<td>" + prod.getCantidad() + "</td>");
            out.println("<td>" + prod.getNombre() + "</td>");
            out.println("<td>" + prod.getPrecio() + "</td>");
            out.println("<td>" + prod.getTotal() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.close();

    }

    private void guardarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    private void limpiarDetalle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("carrito");
    }

}
