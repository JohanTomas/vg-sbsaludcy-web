package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.service.p_restoreService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contp_restore", "/Contrestorep"})
public class p_restoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contp_restore":
                getAll(req,resp);
                break;
            case "/Contrestorep":
                restorep(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PacientDto> list;
        p_restoreService service = new p_restoreService();
        list = service.getAll();
        req.setAttribute("pacientList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Pacient/p.restore.jsp");
        rd.forward(req, resp);
    }

    private void restorep(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        p_restoreService service = new p_restoreService();
        service.restore(Id);

        String mensaje = "Paciente restaurado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contp_restore?mensajerestore=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contp_restore");
        rd.forward(req, resp);*/
    }
}