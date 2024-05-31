package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;

@WebServlet(name = "registrazione", value = "/registrazione")
public class Registrazione extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String address;
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        UtenteDAO utenteDAO = new UtenteDAO();


        Utente utente_da_inserire = new Utente(1, nome, email, password, telefono, false);
        boolean exist_nome = utenteDAO.doInsert(utente_da_inserire);

        if (!exist_nome) {
            address = "index.jsp"; //ritorno alla homepage una volta registrato se non c'e' gia un account esistente
        } else {
            exist_nome = true;
            session.setAttribute("alreadysub",exist_nome);
            address = "registrazione.jsp";//ritorno alla jsp con errore
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