package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SHA1PasswordVerifier;
import model.Utente;
import model.UtenteDAO;

@WebServlet(name = "registrazione", value = "/registrazione")
public class Registrazione extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String address = new String();
        String nome = (String) request.getParameter("nome");
        String email = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String telefono = (String) request.getParameter("telefono");
        UtenteDAO utenteDAO = new UtenteDAO();


        Utente utente_da_inserire = new Utente(1,nome,email,password,telefono,false);
        boolean exist_nome = utenteDAO.inserisciUtente(utente_da_inserire);

        if(exist_nome == false) {
            address = "index.jsp"; //ritorno alla homepage una volta registrato se non c'e' gia un account esistente
        }else if(exist_nome == true) {
            address = "/WEB-INF/results/RegistrazioneAccountEsistente.jsp";//ritorno alla jsp con errore
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    public void destroy() {
    }
}