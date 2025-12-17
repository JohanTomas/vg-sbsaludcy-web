package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.*;
import pe.edu.vallegrande.projectsoftware.service.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contquotes", "/Contnewpm", "/Conteditq", "/Contnewq", "/Contdeleteq", "/Contupdateq"})
public class QuotesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contquotes":
                getAll(req, resp);
                break;
            case "/Contdeleteq":
                delete(req, resp);
                break;
            case "/Conteditq":
                contEdit(req, resp);
                break;
            case "/Contnewpm":
                getAllMP(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<QuotesDto> list;
        QuotesService service = new QuotesService();
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

        RequestDispatcher rd = req.getRequestDispatcher("admin/Quotes/quotes.jsp");
        rd.forward(req,resp);
    }

    private void getAllMP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicDto> mediclist;
        MedicService medicService = new MedicService();
        mediclist = medicService.getAll();

        List<PacientDto> pacientlist;
        PacientService pacientService = new PacientService();
        pacientlist = pacientService.getAll();

        List<StatusDto> statuslist;
        StatusService statusService = new StatusService();
        statuslist = statusService.getAll();

        List<PaymentDto> paymentList;
        PaymentService paymentService = new PaymentService();
        paymentList = paymentService.getAll();

        req.setAttribute("medicList", mediclist);
        req.setAttribute("pacientList", pacientlist);
        req.setAttribute("statusList", statuslist);
        req.setAttribute("paymentList", paymentList);

        RequestDispatcher rd = req.getRequestDispatcher("admin/Quotes/newreservation.jsp");
        rd.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        QuotesService service = new QuotesService();
        service.delete(Id);

        String mensaje = "Cita eliminado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contquotes?mensajedelete=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contquotes");
        rd.forward(req, resp);*/
    }

    private void contEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicDto> mediclist;
        MedicService medicService = new MedicService();
        mediclist = medicService.getAll();

        List<PacientDto> pacientlist;
        PacientService pacientService = new PacientService();
        pacientlist = pacientService.getAll();

        List<StatusDto> statuslist;
        StatusService statusService = new StatusService();
        statuslist = statusService.getAll();

        List<PaymentDto> paymentList;
        PaymentService paymentService = new PaymentService();
        paymentList = paymentService.getAll();

        req.setAttribute("medicList", mediclist);
        req.setAttribute("pacientList", pacientlist);
        req.setAttribute("statusList", statuslist);
        req.setAttribute("paymentList", paymentList);

        int quotesId = Integer.parseInt(req.getParameter("id"));
        QuotesService service = new QuotesService();
        QuotesDto quotes = service.getQuotesId(quotesId);
        req.setAttribute("quotes", quotes);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Quotes/editreservation.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewq":
                create(req, resp);
                break;
            case "/Contupdateq":
                update(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String pacient_id = req.getParameter("pacient_id");
        String medic_id = req.getParameter("medic_id");
        String date_at = req.getParameter("date_at");
        String time_at = req.getParameter("time_at");
        String symtoms = req.getParameter("symtoms");
        String status_id = req.getParameter("status_id");
        String payment_id = req.getParameter("payment_id");
        String message = req.getParameter("message");

        System.out.println("Titulo: " + title);
        System.out.println("Paciente ID: " + pacient_id);
        System.out.println("Medico ID: " + medic_id);
        System.out.println("Fecha: " + date_at);
        System.out.println("Hora/Tiempo: " + time_at);
        System.out.println("Sintomas: " + symtoms);
        System.out.println("Estado ID: " + status_id);
        System.out.println("Pago ID: " + payment_id);
        System.out.println("Mensaje: " + message);

        if (title != null && !title.isEmpty() &&
                pacient_id != null && !pacient_id.isEmpty() &&
                medic_id != null && !medic_id.isEmpty() &&
                date_at != null && !date_at.isEmpty() &&
                time_at != null && !time_at.isEmpty() &&
                symtoms != null && !symtoms.isEmpty() &&
                status_id != null && !status_id.isEmpty() &&
                payment_id != null && !payment_id.isEmpty()) {

            QuotesDto nuevoQuotes = new QuotesDto();
            nuevoQuotes.setTitle(title);
            nuevoQuotes.setPacient_id(pacient_id);
            nuevoQuotes.setMedic_id(medic_id);
            nuevoQuotes.setDate_at(date_at);
            nuevoQuotes.setTime_at(time_at);
            nuevoQuotes.setSymtoms(symtoms);
            nuevoQuotes.setStatus_id(status_id);
            nuevoQuotes.setPayment_id(payment_id);
            nuevoQuotes.setMessage(message);
            nuevoQuotes.setIs_active("A");

            QuotesService service = new QuotesService();
            int nuevoQuotesId = service.create(nuevoQuotes);

            if (nuevoQuotesId != 0) {
                String mensaje = "Cita creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contquotes");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Cita no registrado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contquotes");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "¡ TODOS LOS CAMPOS SON OBLIGATORIO !";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contquotes");
            rd.forward(req, resp);*/
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String pacient_id = req.getParameter("pacient_id");
        String medic_id = req.getParameter("medic_id");
        String date_at = req.getParameter("date_at");
        String time_at = req.getParameter("time_at");
        String symtoms = req.getParameter("symtoms");
        String status_id = req.getParameter("status_id");
        String payment_id = req.getParameter("payment_id");
        String message = req.getParameter("message");
        Boolean isActive = req.getParameter("is_active") != null;
        int quotesId = Integer.parseInt(req.getParameter("quotes_id"));

        if (title != null && !title.isEmpty() &&
                pacient_id != null && !pacient_id.isEmpty() &&
                medic_id != null && !medic_id.isEmpty() &&
                date_at != null && !date_at.isEmpty() &&
                time_at != null && !time_at.isEmpty() &&
                symtoms != null && !symtoms.isEmpty() &&
                status_id != null && !status_id.isEmpty() &&
                payment_id != null && !payment_id.isEmpty()) {

            QuotesDto updateQuotes = new QuotesDto();
            updateQuotes.setId(quotesId);
            updateQuotes.setTitle(title);
            updateQuotes.setPacient_id(pacient_id);
            updateQuotes.setMedic_id(medic_id);
            updateQuotes.setDate_at(date_at);
            updateQuotes.setTime_at(time_at);
            updateQuotes.setSymtoms(symtoms);
            updateQuotes.setStatus_id(status_id);
            updateQuotes.setPayment_id(payment_id);
            updateQuotes.setMessage(message);
            updateQuotes.setIs_active(isActive ? "A" : "I");

            QuotesService service = new QuotesService();
            boolean isUpdated = service.updateQuotes(updateQuotes);

            if (isUpdated) {
                String mensaje = "Cita actualizada con éxito";
                resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            } else {
                String mensaje = "Cita no actualizada";
                resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }
        } else {
            String mensaje = "¡Todos los campos son obligatorios!";
            resp.sendRedirect(req.getContextPath() + "/Contquotes?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
        }
    }
}
