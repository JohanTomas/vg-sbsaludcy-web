package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.UserDto;
import pe.edu.vallegrande.projectsoftware.service.u_restoreService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contu_restore", "/Contrestoreu"})
public class u_restoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contu_restore":
                getAll(req,resp);
                break;
            case "/Contrestoreu":
                restoreu(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> list;
        u_restoreService service = new u_restoreService();
        list = service.getAll();
        req.setAttribute("userList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Users/u.restore.jsp");
        rd.forward(req, resp);
    }

    private void restoreu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        u_restoreService service = new u_restoreService();
        service.restore(Id);

        String mensaje = "Usuario restaurado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contu_restore?mensajerestore=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contu_restore");
        rd.forward(req, resp);*/
    }
}
