package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SHA1PasswordVerifier;
import model.Utente;
import model.UtenteDAO;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void init() {
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String mail = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String address = new String();
        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);
        boolean connesso = false;

        if(input != null && input.getEmail().compareTo(mail) == 0 && SHA1PasswordVerifier.verifyPassword(password,input.getPasswordhash())) {

            connesso = true;
            session.setAttribute("connessione",connesso);
            session.setAttribute("UtenteConnesso",input);
            address = "/WEB-INF/results/UtenteConnessoIndex.jsp";

        }else {

            session.setAttribute("connessione",connesso);
            session.setAttribute("UtenteConnesso",input);
            address = "/WEB-INF/results/UtenteNonConnessoLogin.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    public void destroy() {
    }
}