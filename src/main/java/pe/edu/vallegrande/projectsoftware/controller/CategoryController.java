package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.CategoryDto;
import pe.edu.vallegrande.projectsoftware.service.CategoryService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contcategory", "/Contnewct", "/Conteditct", "/Contdeletect", "/Contactucat"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contcategory":
                getAll(req, resp);
                break;
            case "/Contdeletect":
                deletec(req, resp);
                break;
            case "/Conteditct":
                contEdit(req, resp);
                break;
        }
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> list;
        CategoryService service = new CategoryService();
        list = service.getAll();
        req.setAttribute("categoryList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Category/category.jsp");
        rd.forward(req, resp);
    }

    private void deletec (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        CategoryService service = new CategoryService();
        service.delete(Id);

        String mensaje = "Categoria eliminado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contcategory?mensajedelete=" + URLEncoder.encode(mensaje, "UTF-8"));
        /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
        rd.forward(req, resp);*/
    }

    private void contEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        CategoryService service = new CategoryService();
        CategoryDto category = service.getCategoryId(categoryId);
        req.setAttribute("category", category);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Category/editcategory.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewct":
                crear(req, resp);
                break;
            case "/Contactucat":
                update(req, resp);
                break;
        }
    }

    private void crear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if (name != null && !name.isEmpty()) {

            CategoryDto nuevoCategory = new CategoryDto();
            nuevoCategory.setName(name);
            nuevoCategory.setIs_active("A");

            CategoryService service = new CategoryService();
            int nuevoCategoryId = service.create(nuevoCategory);

            if (nuevoCategoryId != 0) {
                String mensaje = "Categoria creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Categoria no registrado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
            rd.forward(req, resp);*/
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Boolean isActive = req.getParameter("is_active") != null;
        int categoryId = Integer.parseInt(req.getParameter("category_id"));

        if (name != null && !name.isEmpty()) {

            CategoryDto updateCategory = new CategoryDto();
            updateCategory.setId(categoryId);
            updateCategory.setName(name);
            updateCategory.setIs_active(isActive ? "A" : "I");

            CategoryService service = new CategoryService();
            boolean isUpdated = service.updateCategory(updateCategory);

            if (isUpdated) {
                String mensaje = "Categoria actualizada con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
                rd.forward(req, resp);*/
            } else {
                String mensaje = "Categoria no actualizado";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
                /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
                rd.forward(req, resp);*/
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contcategory?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            /*RequestDispatcher rd = req.getRequestDispatcher("/Contcategory");
            rd.forward(req, resp);*/
        }
    }
}