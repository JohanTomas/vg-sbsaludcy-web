package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.service.m_restoreService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contm_restore", "/Contrestorem"})
public class m_restoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contm_restore":
                getAll(req, resp);
                break;
            case "/Contrestorem":
                delete(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicDto> list;
        m_restoreService service = new m_restoreService();
        list = service.getAll();
        req.setAttribute("medicList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/m.restore.jsp");
        rd.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        m_restoreService service = new m_restoreService();
        service.delete(Id);

        String mensaje = "Medico restaurado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contm_restore?mensajerestore=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contm_restore");
        rd.forward(req, resp);*/
    }
}
