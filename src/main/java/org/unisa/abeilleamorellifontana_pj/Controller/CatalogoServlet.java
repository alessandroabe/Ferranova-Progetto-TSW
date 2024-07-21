package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO.*;

@WebServlet(name = "catalogo", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<Prodotto> catalogo;
//TODO: spostare tutto nell'init forse, così da avere tutto nella servletcontext
//TODO: gestire il caso di errore, in cui si mette una categoria che non esiste
        if (request.getParameter("categoria") != null) {
            String categoria = request.getParameter("categoria");



            if ((request.getParameter("ricerca") != null) || (request.getParameter("sottocategoria") != null) || (request.getParameter("prezzoMin") != null) || (request.getParameter("prezzoMax") != null)) {
                //TODO: controllo valori subcategoria, forse
                String ricerca = !request.getParameter("ricerca").equalsIgnoreCase("") ? request.getParameter("ricerca") : null;
                String sottocategoria = request.getParameter("sottocategoria");
                BigDecimal prezzoMin = new BigDecimal(request.getParameter("prezzoMin")).equals(BigDecimal.ZERO) ? null : new BigDecimal(request.getParameter("prezzoMin"));
                BigDecimal prezzoMax = new BigDecimal(request.getParameter("prezzoMax")).equals(BigDecimal.valueOf(1000)) ? null : new BigDecimal(request.getParameter("prezzoMax"));

                catalogo = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveByCriteria(ricerca, prezzoMin, prezzoMax, categoria, sottocategoria);

            } else
                catalogo = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveAllByMacrocategoria(categoria);
            request.setAttribute("catalogo", catalogo);

            ServletContext context = request.getServletContext();
            if (context.getAttribute("Categorie") != null) {
                HashMap<String, ArrayList<String>> categorie;
                categorie = (HashMap<String, ArrayList<String>>) context.getAttribute("Categorie");
                List<String> subCategorie = categorie.get(categoria);
                request.setAttribute("subCategorie", subCategorie);
            } else {

                catalogo = (ArrayList<Prodotto>) doRetrieveAll();
                request.setAttribute("catalogo", catalogo);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
    }

}