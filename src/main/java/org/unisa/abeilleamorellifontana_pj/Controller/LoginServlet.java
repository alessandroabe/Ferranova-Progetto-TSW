package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        String address;
        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);

        if (input != null && input.getEmail().equals(mail) && SHA1PasswordVerifier.verifyPassword(password, input.getPasswordhash())) {
            // Imposta l'utente connesso nella sessione

            session.setAttribute("UtenteConnesso", input);
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?error=1");
        dispatcher.forward(request, response);

    }

    public void destroy() {

    }
}