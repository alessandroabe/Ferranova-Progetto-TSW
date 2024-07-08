package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;
import java.sql.SQLException;

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
        HttpSession session = request.getSession();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        String address;

        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);

        if (input != null && input.getEmail().equals(mail) && SHA1PasswordVerifier.verifyPassword(password, input.getPasswordhash())) {
            // Imposta l'utente connesso nella sessione
            session.setAttribute("UtenteConnesso", input);
            address = "index.jsp";
        } else {
            address = getServletContext().getContextPath() + "/login.jsp?error=1";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}