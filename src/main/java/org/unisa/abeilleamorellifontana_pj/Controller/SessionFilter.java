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
        HttpSession s = req.getSession();
        if ((s.getAttribute("Carrello")) == null)
            s.setAttribute("Carrello", new Carrello());

        Utente u = (Utente) s.getAttribute("UtenteConnesso");
        //caso in cui la sessione è presente controllo se è admin
        //TODO: sarà utente e basta
        if (u != null && u.isAdmin()) //TODO: implementare logica admin
            System.out.println("sei admin");


        chain.doFilter(req, res);
    }
}
