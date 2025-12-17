package pe.edu.vallegrande.projectsoftware.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.vallegrande.projectsoftware.service.LoginService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("Usuario: " + username);
        System.out.println("Password: " + password);

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            LoginService loginService = new LoginService();
            boolean isAuthenticated = loginService.authenticate(username, password);

            if (isAuthenticated) {
                req.getSession().setAttribute("authenticatedUser", username);
                resp.sendRedirect("/platform-initial");
            } else {
                String mensaje = "Contraseña o Usuario incorrecto";
                req.setAttribute("mensaje", mensaje);
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }
        } else {
            String mensaje = "Ingrese un usuario y una contraseña";
            req.setAttribute("mensaje", mensaje);
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        }
    }
}
