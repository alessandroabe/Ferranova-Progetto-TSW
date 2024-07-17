package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.*;

@WebServlet(name = "registrazione", value = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        String address;
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String admin = request.getParameter("is_admin");
        String indirizzo = request.getParameter("indirizzo");

        UtenteDAO utenteDAO = new UtenteDAO();

        //TODO: fare controlli
        Utente utente = new Utente(0, nome, cognome,email, SHA1PasswordVerifier.sha1Hash(password), telefono, false,indirizzo);


        int id;
        if ((id = utenteDAO.doInsert(utente)) > 0) {
            utente.setId(id);
            session.setAttribute("UtenteConnesso", utente);
            Carrello carrello = (Carrello) session.getAttribute("Carrello");
            carrello.setIdUtente(utente.getId());
        } else {

            response.sendRedirect("registrazione.jsp?error=1");//ritorno alla jsp con errore
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");//ritorno alla homepage una volta registrato se non c'e' gia un account esistente
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {

    }
}