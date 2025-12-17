package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.service.MedicService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/Contm_history",})
public class m_historyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contm_history":
                getAll(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int medicId = Integer.parseInt(req.getParameter("id"));
        MedicService service = new MedicService();
        MedicDto medic = service.getMedicId(medicId);
        req.setAttribute("medic", medic);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/medichistory.jsp");
        rd.forward(req, resp);
    }
}
