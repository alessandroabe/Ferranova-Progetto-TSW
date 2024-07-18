package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MyInit", urlPatterns = "/MyInit", loadOnStartup = 0)
public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            HashMap<String, ArrayList<String>> categorie = new HashMap<>();

            //TODO: Inizializza le categorie
            categorie.put("Ferramenta", new ArrayList<>(List.of("Utensili", "Prodotti", "Abbigliamento")));
            categorie.put("Edilizia", new ArrayList<>(List.of("Utensili", "Prodotti", "Abbigliamento")));
            categorie.put("Giardinaggio", new ArrayList<>(List.of("Utensili", "Prodotti", "Abbigliamento")));

            ServletContext context = config.getServletContext();
            context.setAttribute("Categorie", categorie);
        } catch (Exception e) {
            throw new ServletException("Errore durante l'inizializzazione", e);
        }
    }


}
