package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.dto.QuotesDto;
import pe.edu.vallegrande.projectsoftware.service.MedicService;
import pe.edu.vallegrande.projectsoftware.service.PacientService;
import pe.edu.vallegrande.projectsoftware.service.QuotesService;
import pe.edu.vallegrande.projectsoftware.service.homeService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/platform-initial"})
public class homeController extends HttpServlet {

    private homeService service = new homeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/platform-initial":
                getAll(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalReservaciones = service.getTotalReservaciones();
        int totalPacientes = service.getTotalPacientes();
        int totalMedicos = service.getTotalMedicos();
        int totalAdmins = service.getTotalAdmins();

        // Agregar los totales como atributos de solicitud
        req.setAttribute("totalReservaciones", totalReservaciones);
        req.setAttribute("totalPacientes", totalPacientes);
        req.setAttribute("totalMedicos", totalMedicos);
        req.setAttribute("totalAdmins", totalAdmins);

        List<MedicDto> mediclist;
        MedicService medicService = new MedicService();
        mediclist = medicService.getTOP();

        List<PacientDto> pacientlist;
        PacientService pacientService = new PacientService();
        pacientlist = pacientService.getTOP();

        List<QuotesDto> quoteslist;
        QuotesService quotesService = new QuotesService();
        quoteslist = quotesService.getTOP();

        req.setAttribute("medicList", mediclist);
        req.setAttribute("pacientList", pacientlist);
        req.setAttribute("quotesList", quoteslist);

        // Despachar la solicitud a la vista JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/layout.jsp");
        dispatcher.forward(req, resp);
    }
}
