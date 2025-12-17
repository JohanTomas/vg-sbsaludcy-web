package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.annotation.WebServlet;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.dto.PrescriptionDto;
import pe.edu.vallegrande.projectsoftware.dto.QuotesDto;
import pe.edu.vallegrande.projectsoftware.service.MedicService;
import pe.edu.vallegrande.projectsoftware.service.PacientService;
import pe.edu.vallegrande.projectsoftware.service.PrescriptionService;
import pe.edu.vallegrande.projectsoftware.service.QuotesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contprescription", "/Contnewpres", "/Contedotpr", "/Contnewpr", "/Contdeletepr", "/Contupdatepr"})
public class PrescriptionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contprescription":
                getAll(req, resp);
                break;
            case "/Contdeletepr":
                delete(req, resp);
                break;
            case "/Conteditpr":
                contEdit(req, resp);
                break;
            case "/Contnewpres":
                getAllMP(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        List<PrescriptionDto>list;
        PrescriptionService service = new PrescriptionService();
        list = service.getAll();

        List<QuotesDto>quotesList;
        QuotesService quotesService = new QuotesService();
        quotesList = quotesService.getAll();

        List<MedicDto> medicList;
        MedicService medicService = new MedicService();
        medicList = medicService.getAll();

        List<PacientDto> pacientList;
        PacientService pacientService = new PacientService();
        pacientList = pacientService.getAll();

        req.setAttribute("pacientList", pacientList);
        req.setAttribute("medicList", medicList);
        req.setAttribute("quotesList", quotesList);
        req.setAttribute("prescriptionList", list);

        RequestDispatcher rd = req.getRequestDispatcher("admin/Prescription/prescription.jsp");
        rd.forward(req, resp);
    }

    private void getAllMP(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        List<QuotesDto>quotesList;
        QuotesService quotesService = new QuotesService();
        quotesList = quotesService.getAll();

        List<MedicDto>medicList;
        MedicService medicService = new MedicService();
        medicList = medicService.getAll();

        List<PacientDto>pacientList;
        PacientService pacientService = new PacientService();
        pacientList = pacientService.getAll();

        req.setAttribute("quotesList", quotesList);
        req.setAttribute("medicList", medicList);
        req.setAttribute("pacientList", pacientList);

        RequestDispatcher rd = req.getRequestDispatcher("admin/Prescription/newprescription.jsp");
        rd.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PrescriptionService service = new PrescriptionService();
        service.delete(id);

        String mensaje = "Receta correctamente eliminado";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contprescription?mensajedete=" + URLEncoder.encode(mensaje, "UTF-8"));
    }

    private void contEdit(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        List<QuotesDto> quotesList;
        QuotesService quotesService = new QuotesService();
        quotesList = quotesService.getAll();

        List<MedicDto> medicList;
        MedicService medicService = new MedicService();
        medicList = medicService.getAll();

        List<PacientDto> pacientList;
        PacientService pacientService = new PacientService();
        pacientList = pacientService.getAll();

        req.setAttribute("quotesList", quotesList);
        req.setAttribute("medicList", medicList);
        req.setAttribute("pacientList", pacientList);

        req.setAttribute("quotesList", quotesList);
        req.setAttribute("medic", medicList);
        req.setAttribute("pacient", pacientList);

        int prescriptionId = Integer.parseInt(req.getParameter("id"));
        PrescriptionService service = new PrescriptionService();
        PrescriptionDto prescription =service.getPrescriptionId(prescriptionId);
        req.setAttribute("prescription", prescription);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Prescription/editprescription.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewpr":
                create(req, resp);
                break;
            case "/Contupdatepr":
                update(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String reservation_id = req.getParameter("reservation_id");
        String sickness = req.getParameter("sickness");
        String medicaments = req.getParameter("medicaments");
        String price = req.getParameter("price");

        System.out.println("Cita ID: " + reservation_id);
        System.out.println("Enfermedad: " + sickness);
        System.out.println("Medicamentos: " + medicaments);
        System.out.println("Precio: " + price);

        if (reservation_id != null && !reservation_id.isEmpty()&&
            sickness != null && !sickness.isEmpty() &&
            medicaments != null && !medicaments.isEmpty() &&
            price != null && !price.isEmpty()) {

            PrescriptionDto nuevoPrescription = new PrescriptionDto();
            nuevoPrescription.setReservation_id(reservation_id);
            nuevoPrescription.setSickness(sickness);
            nuevoPrescription.setMedicaments(medicaments);
            nuevoPrescription.setPrice(price);
            nuevoPrescription.setIs_active("A");

            PrescriptionService service = new PrescriptionService();
            int nuevoPrescriptionId = service.create(nuevoPrescription);

            if (nuevoPrescriptionId != 0) {
                String mensaje = "Receta creada con éxito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contprescription?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }else {
                String mensaje = "Receta no registrado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contprescription?mensajedete=" + URLEncoder.encode(mensaje, "UTF-8"));
            }
        }else {
            String mensaje = "¡ TODOS LOS CAMPOS SON OBLIGATORIOS !";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contprescription?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String reservation_id = req.getParameter("reservation_id");
        String sickness = req.getParameter("sickness");
        String medicaments = req.getParameter("medicaments");
        String price = req.getParameter("price");
        Boolean isActive = req.getParameter("is_active") != null;
        int prescriptionId = Integer.parseInt(req.getParameter("prescription_id"));

        if (reservation_id != null && !reservation_id.isEmpty()&&
            sickness != null && !sickness.isEmpty() &&
            medicaments != null && !medicaments.isEmpty() &&
            price != null && !price.isEmpty()) {

            PrescriptionDto updatePrescription = new PrescriptionDto();
            updatePrescription.setId(prescriptionId);
            updatePrescription.setReservation_id(reservation_id);
            updatePrescription.setSickness(sickness);
            updatePrescription.setMedicaments(medicaments);
            updatePrescription.setPrice(price);
            updatePrescription.setIs_active(isActive ? "A" : "I");

            PrescriptionService service = new PrescriptionService();
            boolean isUpdate = service.updatePrescription(updatePrescription);

            if (isUpdate) {
                String mensaje = "Receta actualizada con éxito";
                resp.sendRedirect(req.getContextPath() + "/Contprescription?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }else {
                String mensaje = "Receta no actualizado";
                resp.sendRedirect(req.getContextPath() + "/Contprescription?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }
        }else {
            String mensaje = "¡ TODOS LOS CAMPOS SOM OBLIGATORIOS !";
            resp.sendRedirect(req.getContextPath() + "/Contprescription?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
        }
    }

}
