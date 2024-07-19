package org.unisa.abeilleamorellifontana_pj.Controller;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CambioDatiUtenteServlet", value = "/CambioDatiUtenteServlet")

public class CambioDatiUtenteServlet extends HttpServlet {

    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String indirizzo = request.getParameter("indirizzo");

        // Recupera l'utente connesso dalla sessione
        Utente utenteConnesso = (Utente) request.getSession().getAttribute("UtenteConnesso");

        if (utenteConnesso != null) {
            utenteConnesso.setNome(nome);
            utenteConnesso.setCognome(cognome);
            utenteConnesso.setEmail(email);
            utenteConnesso.setTelefono(telefono);
            utenteConnesso.setIndirizzo(indirizzo);

            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doUpdate(utenteConnesso);

            request.getSession().setAttribute("UtenteConnesso", utenteConnesso);
            response.sendRedirect(request.getContextPath() + "/profilo");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    public void destroy() {
    }
}