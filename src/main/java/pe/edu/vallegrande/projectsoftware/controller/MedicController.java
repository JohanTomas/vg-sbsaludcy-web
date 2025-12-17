package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;
import pe.edu.vallegrande.projectsoftware.service.MedicService;

import pe.edu.vallegrande.projectsoftware.dto.CategoryDto;
import pe.edu.vallegrande.projectsoftware.service.CategoryService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contmedic", "/Contnewmc", "/Contnewm", "/Conteditm", "/Contdeletem", "/Contactum"})
public class MedicController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contmedic":
                getAll(req, resp);
                break;
            case "/Contdeletem":
                delete(req, resp);
                break;
            case "/Conteditm":
                contEdit(req, resp);
                break;
            case "/Contnewmc":
                getAllCategory(req, resp);
                break;
                /*case "/Conteditmc":
                    getAllCategoryedit(req, resp);
                    break;*/
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicDto> list;
        MedicService service = new MedicService();
        list = service.getAll();
        req.setAttribute("medicList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/medic.jsp");
        rd.forward(req, resp);
    }

    public void getAllCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> list;
        CategoryService service = new CategoryService();
        list = service.getAll();
        req.setAttribute("categoryList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/newmedic.jsp");
        rd.forward(req, resp);
    }

        /*public void getAllCategoryedit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<CategoryDto> list;
            CategoryService service = new CategoryService();
            list = service.getAll();
            req.setAttribute("categoryList", list);
            RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/editmedic.jsp");
            rd.forward(req, resp);
        }*/

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        MedicService service = new MedicService();
        service.delete(Id);

        String mensaje = "Medico eliminado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contmedic?mensajedelete=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contmedic");
        rd.forward(req, resp);*/
    }

    private void contEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> list;
        CategoryService categoryService = new CategoryService();
        list = categoryService.getAll();
        req.setAttribute("categoryList", list);

        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int medicId = Integer.parseInt(idParam);
            MedicService medicService = new MedicService();
            MedicDto medic = medicService.getMedicId(medicId);
            req.setAttribute("medic", medic);
        }

        RequestDispatcher rd = req.getRequestDispatcher("admin/Medic/editmedic.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewm":
                create(req, resp);
                break;
            case "/Contactum":
                update(req, resp);
                break;
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String categoryId = req.getParameter("category_id");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String gender = req.getParameter("gender");
        String document_type = req.getParameter("document_type");
        String document_dni = req.getParameter("document_dni");
        String day_of_birth = req.getParameter("day_of_birth");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        System.out.println("Categoria ID: " + categoryId);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + lastname);
        System.out.println("Genero: " + gender);
        System.out.println("Tipo de Documento: " + document_type);
        System.out.println("Documento DNI: " + document_dni);
        System.out.println("Fecha Nacimiento: " + day_of_birth);
        System.out.println("Direcciòn: " + address);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + phone);

        if (name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                gender != null && !gender.isEmpty() &&
                document_type != null && !document_type.isEmpty() &&
                document_dni != null && !document_dni.isEmpty() &&
                day_of_birth != null && !day_of_birth.isEmpty() &&
                address != null && !address.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty()) {

            MedicDto nuevoMedic = new MedicDto();
            nuevoMedic.setCategory_id(categoryId);
            nuevoMedic.setName(name);
            nuevoMedic.setLastname(lastname);
            nuevoMedic.setGender(gender);
            nuevoMedic.setDocument_type(document_type);
            nuevoMedic.setDocument_dni(document_dni);
            nuevoMedic.setDay_of_birth(day_of_birth);
            nuevoMedic.setAddress(address);
            nuevoMedic.setEmail(email);
            nuevoMedic.setPhone(phone);
            nuevoMedic.setIs_active("A");

            MedicService service = new MedicService();
            int nuevoMedicId = service.create(nuevoMedic);

            if (nuevoMedicId != 0) {
                String mensaje = "Medico creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contmedic");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Medico no registrado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contmedic");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "¡ TODO LOS CAMPOS SON OBLIGATORIO !";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contmedic");
            rd.forward(req, resp);*/
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String categoryId = req.getParameter("category_id");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String gender = req.getParameter("gender");
        String document_type = req.getParameter("document_type");
        String document_dni = req.getParameter("document_dni");
        String day_of_birth = req.getParameter("day_of_birth");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Boolean isActive = req.getParameter("is_active") != null;
        int pacientId = Integer.parseInt(req.getParameter("medic_id"));

        System.out.println("Categoria ID: " + categoryId);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + lastname);
        System.out.println("Genero: " + gender);
        System.out.println("Tipo de Documento: " + document_type);
        System.out.println("Documento DNI: " + document_dni);
        System.out.println("Fecha Nacimiento: " + day_of_birth);
        System.out.println("Direcciòn: " + address);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + phone);
        System.out.println("Paciente ID: " + pacientId);

        if (name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                gender != null && !gender.isEmpty() &&
                document_type != null && !document_type.isEmpty() &&
                document_dni != null && !document_dni.isEmpty() &&
                day_of_birth != null && !day_of_birth.isEmpty() &&
                address != null && !address.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty()) {

            MedicDto updateMedic = new MedicDto();
            updateMedic.setId(pacientId);
            updateMedic.setCategory_id(categoryId);
            updateMedic.setName(name);
            updateMedic.setLastname(lastname);
            updateMedic.setGender(gender);
            updateMedic.setDocument_type(document_type);
            updateMedic.setDocument_dni(document_dni);
            updateMedic.setDay_of_birth(day_of_birth);
            updateMedic.setAddress(address);
            updateMedic.setEmail(email);
            updateMedic.setPhone(phone);
            updateMedic.setIs_active(isActive ? "A" : "I");

            MedicService service = new MedicService();
            boolean isUpdated = service.updateMedic(updateMedic);

            if (isUpdated) {
                String mensaje = "Medico actualizado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            } else {
                String mensaje = "Medico no actualizado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contmedic?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
        }
    }
}

