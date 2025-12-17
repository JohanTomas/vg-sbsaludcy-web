package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.CategoryDto;
import pe.edu.vallegrande.projectsoftware.service.c_restoreService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contc_restore", "/Contrestorec"})
public class c_restoreController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contc_restore":
                getAll(req,resp);
                break;
            case "/Contrestorec":
                restorec(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> list;
        c_restoreService service = new c_restoreService();
        list = service.getAll();
        req.setAttribute("categoryList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Category/c.restore.jsp");
        rd.forward(req, resp);
    }

    private void restorec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        c_restoreService service = new c_restoreService();
        service.delete(Id);

        String mensaje = "Categoria restaurado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contc_restore?mensajerestore=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contc_restore");
        rd.forward(req, resp);*/
    }
}