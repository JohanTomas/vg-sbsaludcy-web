package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;
import pe.edu.vallegrande.projectsoftware.service.PacientService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contpacient", "/Contnewp", "/Conteditp", "/Contdeletep", "/Contactup"})
public class PacientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contpacient":
                getAll(req, resp);
                break;
            case "/Contdeletep":
                deletep(req, resp);
                break;
            case "/Conteditp":
                contEdit(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<PacientDto> list;
        PacientService service = new PacientService();
        list = service.getAll();
        req.setAttribute("pacientList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Pacient/pacient.jsp");
        rd.forward(req, resp);
    }

    private void deletep(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int Id = Integer.parseInt(req.getParameter("id"));
        PacientService service = new PacientService();
        service.delete(Id);

        String mensaje = "Paciente eliminado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contpacient?mensajedelete=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
        rd.forward(req, resp);*/
    }

    private void contEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pacientId = Integer.parseInt(req.getParameter("id"));
        PacientService service = new PacientService();
        PacientDto pacient = service.getPacientId(pacientId);
        req.setAttribute("pacient", pacient);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Pacient/editpacient.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewp":
                crear(req, resp);
                break;
            case "/Contactup":
                update(req, resp);
                break;
        }
    }

    private void crear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String gender = req.getParameter("gender");
        String document_type = req.getParameter("document_type");
        String document_dni = req.getParameter("document_dni");
        String day_of_birth = req.getParameter("day_of_birth");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String sickness = req.getParameter("sickness");
        String alergy = req.getParameter("alergy");

        if (name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                gender != null && !gender.isEmpty() &&
                document_type != null && !document_type.isEmpty() &&
                document_dni != null && !document_dni.isEmpty() &&
                day_of_birth != null && !day_of_birth.isEmpty() &&
                address != null && !address.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                sickness != null && !sickness.isEmpty() &&
                alergy != null && !alergy.isEmpty()) {

            PacientDto nuevoPacient = new PacientDto();
            nuevoPacient.setName(name);
            nuevoPacient.setLastname(lastname);
            nuevoPacient.setGender(gender);
            nuevoPacient.setDocument_type(document_type);
            nuevoPacient.setDocument_dni(document_dni);
            nuevoPacient.setDay_of_birth(day_of_birth);
            nuevoPacient.setAddress(address);
            nuevoPacient.setEmail(email);
            nuevoPacient.setPhone(phone);
            nuevoPacient.setSickness(sickness);
            nuevoPacient.setAlergy(alergy);
            nuevoPacient.setIs_active("A");

            PacientService service = new PacientService();
            int nuevoPacientId = service.create(nuevoPacient);

            if (nuevoPacientId != 0) {
                String mensaje = "Paciente creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Paciente no registrado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
            rd.forward(req, resp);*/
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String gender = req.getParameter("gender");
        String document_type = req.getParameter("document_type");
        String document_dni = req.getParameter("document_dni");
        String day_of_birth = req.getParameter("day_of_birth");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String sickness = req.getParameter("sickness");
        String alergy = req.getParameter("alergy");
        Boolean isActive = req.getParameter("is_active") != null;
        int pacientId = Integer.parseInt(req.getParameter("pacient_id"));

        if (name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                gender != null && !gender.isEmpty() && document_type != null && !document_type.isEmpty() &&
                document_dni != null && !document_dni.isEmpty() &&
                day_of_birth != null && !day_of_birth.isEmpty() &&
                address != null && !address.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                sickness != null && !sickness.isEmpty() &&
                alergy != null && !alergy.isEmpty()) {

            PacientDto updatePacient = new PacientDto();
            updatePacient.setId(pacientId);
            updatePacient.setName(name);
            updatePacient.setLastname(lastname);
            updatePacient.setGender(gender);
            updatePacient.setDocument_type(document_type);
            updatePacient.setDocument_dni(document_dni);
            updatePacient.setDay_of_birth(day_of_birth);
            updatePacient.setAddress(address);
            updatePacient.setEmail(email);
            updatePacient.setPhone(phone);
            updatePacient.setSickness(sickness);
            updatePacient.setAlergy(alergy);
            updatePacient.setIs_active(isActive ? "A" : "I");

            PacientService service = new PacientService();
            boolean isUpdated = service.updatePacient(updatePacient);

            if (isUpdated) {
                String mensaje = "Paciente actualizado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Paciente no actualizado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contpacient?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contpacient");
            rd.forward(req, resp);*/
        }
    }
}
