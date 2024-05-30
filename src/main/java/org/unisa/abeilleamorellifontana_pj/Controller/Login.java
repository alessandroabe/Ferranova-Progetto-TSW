package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void init() {
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        String address;
        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);

        if(input != null && input.getEmail().compareTo(mail) == 0 && SHA1PasswordVerifier.verifyPassword(password,input.getPasswordhash())) {

            session.setAttribute("connessione", true);
            session.setAttribute("UtenteConnesso",input);
            address = "index.jsp";

        }else {

            session.setAttribute("connessione", false);
            session.setAttribute("UtenteConnesso",input);
            address = "index.jsp";
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