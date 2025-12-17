package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.service.PacientService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/Contp_history",})
public class p_historyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contp_history":
                getAll(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pacientId = Integer.parseInt(req.getParameter("id"));
        PacientService service = new PacientService();
        PacientDto pacient = service.getPacientId(pacientId);
        req.setAttribute("pacient", pacient);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Pacient/pacienthistory.jsp");
        rd.forward(req, resp);
    }
}
