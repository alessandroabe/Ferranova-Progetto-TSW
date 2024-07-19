package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;
import org.unisa.abeilleamorellifontana_pj.Model.UtenteDAO;
import org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "passwordServlet", urlPatterns = "/passwordServlet")
public class CambioPasswordUtenteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        Utente utenteConnesso = (Utente) request.getSession().getAttribute("UtenteConnesso");

        if (utenteConnesso != null) {
            //UtenteDAO utenteDAO = new UtenteDAO();
            //Utente storedUtente = utenteDAO.doRetrieveByEmail(utenteConnesso.getEmail());

            if (SHA1PasswordVerifier.verifyPassword(currentPassword, utenteConnesso.getPasswordhash())) {
                utenteConnesso.setPasswordhash(SHA1PasswordVerifier.sha1Hash(newPassword));
                UtenteDAO utenteDAO = new UtenteDAO();
                utenteDAO.doUpdate(utenteConnesso);

                //todo fare caso error 2 per vedere se la password nuova è idonea, sempre con regex

                // Set a success message in the session
                request.getSession().setAttribute("message", "Password cambiata con successo!");

                response.sendRedirect(request.getContextPath() + "/profilo");
            } else {
                response.sendRedirect(request.getContextPath() + "/password?error=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");//TODO: evetuale errore
        }


    }
}
