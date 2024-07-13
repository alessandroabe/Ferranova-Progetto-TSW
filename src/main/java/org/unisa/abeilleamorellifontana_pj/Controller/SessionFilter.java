package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;
import org.unisa.abeilleamorellifontana_pj.Model.SessionManager;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;

import java.io.IOException;


public class SessionFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        //FIXME serve per fa entrare subito loggato o comunque creare la sessione ad ogni iterazione
        if (req.getSession(false) == null) {//caso in cui la sessione manca
            HttpSession s = req.getSession();
            s.setAttribute("Carrello", new Carrello());
        } else {//caso in cui la sessione è presente controllo se è admin
            HttpSession s = req.getSession(false);
            Utente u = (Utente) s.getAttribute("UtenteConnesso");//TODO: sarà utente e basta
            if (u != null) {
                if (u.isAdmin()) //TODO: implementare logica admin
                    System.out.println("sei admin. anche cose");

            }


            chain.doFilter(req, res);
        }
    }
}
