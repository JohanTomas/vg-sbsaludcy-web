package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import pe.edu.vallegrande.projectsoftware.dto.UserDto;
import pe.edu.vallegrande.projectsoftware.service.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet({"/Contuser", "/Contnewu" , "/Conteditu", "/Contdeleteu", "/Contactu"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contuser":
                getAll(req, resp);
                break;
            case "/Contdeleteu":
                deleteu(req, resp);
                break;
            case "/Conteditu":
                contEdit(req, resp);
                break;
        }
    }

    /* MOSTRAR LISTADO DE REGISTRO */
    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> list;
        UserService service = new UserService();
        list = service.getAll();
        req.setAttribute("userList", list);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Users/user.jsp");
        rd.forward(req, resp);
    }

    /* BORRADO LOGICO */
    private void deleteu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.parseInt(req.getParameter("id"));
        UserService service = new UserService();
        service.delete(Id);

        String mensaje = "Usuario eliminado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contuser?mensajedelete=" + URLEncoder.encode(mensaje, "UTF-8"));
    }

    /* DIRECCIONAR AL Contupdateu */
    private void contEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        UserService service = new UserService();
        UserDto user = service.getUserId(userId);
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("admin/Users/editusuer.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/Contnewu":
                crear(req, resp);
                break;
            case "/Contactu":
                update(req, resp);
                break;
        }
    }

    /* CREAR UN NUEVO USUARIO */
    private void crear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String is_admin = req.getParameter("is_admin");

        System.out.println("Nombre :" + name);
        System.out.println("Apellido :" + lastname);
        System.out.println("Nombre Usuario :" + username);
        System.out.println("Email :" + email);
        System.out.println("Password :" + password);
        System.out.println("Rol :" + is_admin);

        if (name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                username != null && !username.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                is_admin != null && !is_admin.isEmpty()) {

            UserDto nuevoUser = new UserDto();
            nuevoUser.setName(name);
            nuevoUser.setLastname(lastname);
            nuevoUser.setUsername(username);
            nuevoUser.setEmail(email);
            nuevoUser.setPassword(password);
            nuevoUser.setIs_admin(is_admin);
            nuevoUser.setIs_active("A");

            UserService service = new UserService();
            int nuevoUserId = service.create(nuevoUser);

            if (nuevoUserId != 0) {
                String mensaje = "Usuario creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contuser?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            } else {
                String mensaje = "Usuario creado con exito";
                req.setAttribute("mensaje", mensaje);
                resp.sendRedirect(req.getContextPath() + "/Contuser?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
            }
        } else {
            String mensaje = "Todos los campos son obligatorios";
            req.setAttribute("mensaje", mensaje);
            resp.sendRedirect(req.getContextPath() + "/Contuser?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
        }
    }

    /* ACTUALIZAR REGISTRO - USUARIO */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Boolean is_active = req.getParameter("is_active") != null;
        String is_admin = req.getParameter("is_admin");
        int userId = Integer.parseInt(req.getParameter("user_id"));

        UserDto updatedUser = new UserDto();
        updatedUser.setId(userId);
        updatedUser.setName(name);
        updatedUser.setLastname(lastname);
        updatedUser.setUsername(username);
        updatedUser.setEmail(email);
        updatedUser.setPassword(password);
        updatedUser.setIs_active(is_active ? "A" : "I");
        updatedUser.setIs_admin(is_admin);

        UserService service = new UserService();
        service.updateUser(updatedUser);

        String mensaje = "Usuario actualizado correctamente";
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect(req.getContextPath() + "/Contuser?mensaje=" + URLEncoder.encode(mensaje, "UTF-8"));
    }
}