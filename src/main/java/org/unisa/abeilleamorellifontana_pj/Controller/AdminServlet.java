package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "admin", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    //TODO: convalida tramite filter per gestire le servlet admin


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente u= null;
        if(req.getSession().getAttribute("UtenteConnesso") != null) {
            u = (Utente) req.getSession().getAttribute("UtenteConnesso");
            if(!u.isAdmin()) {
                resp.sendRedirect("index.jsp");
                return;
            }

            ArrayList<Ordine> ordini = OrdineDAO.doRetrieveAll();
            req.setAttribute("Ordini", ordini);

           ArrayList<Prodotto> prodottoArrayList = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveAll();
            HashMap <Integer, Prodotto> prodottoHashMap = new HashMap<>();
            for(Prodotto prodotto : prodottoArrayList) {
                prodottoHashMap.put(prodotto.getId(), prodotto);
            }
            req.setAttribute("prodottoHashMap", prodottoHashMap);
            req.getRequestDispatcher("Admin.jsp").forward(req, resp);


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
