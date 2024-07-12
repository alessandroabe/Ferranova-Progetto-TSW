package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.SessionManager;

import java.io.IOException;


public class SessionFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        //FIXME serve per fa entrare subito loggato o comunque creare la sessione ad ogni iterazione
        if (req.getSession(false) == null) {
            req.getSession().setAttribute("SessionManager", new SessionManager());
            SessionManager s = new SessionManager();
            if (s.isLoggato()) {
                System.out.println("non da falso");
            }
            if (s.isAdmin()) {
                System.out.println("ancora più falso");
            }
        }

        chain.doFilter(req, res);
    }
}
