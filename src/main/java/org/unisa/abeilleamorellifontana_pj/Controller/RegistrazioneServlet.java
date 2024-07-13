package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;

@WebServlet(name = "registrazione", value = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        String address;
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String admin = request.getParameter("is_admin");

        UtenteDAO utenteDAO = new UtenteDAO();


        Utente utente_da_inserire = new Utente(1, nome, email, SHA1PasswordVerifier.sha1Hash(password), telefono, !(admin == null));
        boolean exist_nome = utenteDAO.doInsert(utente_da_inserire);


        if (!exist_nome) {
            session.setAttribute("UtenteConnesso", utente_da_inserire);

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