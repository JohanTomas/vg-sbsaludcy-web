package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.dto.QuotesDto;
import pe.edu.vallegrande.projectsoftware.service.MedicService;
import pe.edu.vallegrande.projectsoftware.service.PacientService;
import pe.edu.vallegrande.projectsoftware.service.q_restoreService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/q_restore"})
public class q_restoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/q_restore":
                getAll(req,resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<QuotesDto> list;
        q_restoreService service = new q_restoreService();
        list = service.getAll();

        List<MedicDto> mediclist;
        MedicService medicService = new MedicService();
        mediclist = medicService.getAll();

        List<PacientDto> pacientList;
        PacientService pacientService = new PacientService();
        pacientList = pacientService.getAll();

        req.setAttribute("medicList", mediclist);
        req.setAttribute("pacientList", pacientList);
        req.setAttribute("quotesList", list);

        RequestDispatcher rd = req.getRequestDispatcher("admin/Quotes/q.restore.jsp");
        rd.forward(req,resp);
    }
}
